package com.teamabode.verdance.common.entity.silkmoth;

import com.mojang.serialization.Dynamic;
import com.teamabode.verdance.core.tag.VerdanceBlockTags;
import com.teamabode.verdance.core.registry.VerdanceMemoryModuleTypes;
import com.teamabode.verdance.core.registry.VerdanceSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Unit;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathType;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("unchecked")
public class SilkMoth extends Animal implements FlyingAnimal {
    public static final EntityDataAccessor<Boolean> FLYING = SynchedEntityData.defineId(SilkMoth.class, EntityDataSerializers.BOOLEAN);
    //public static final EntityDataAccessor<Boolean> SLEEPING = SynchedEntityData.defineId(SilkMoth.class, EntityDataSerializers.BOOLEAN);

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState flyAnimationState = new AnimationState();

    private int idleCooldown = 100;
    private double bodyPitch = 0.0d;

    public SilkMoth(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);

        this.moveControl = new MoveControl(this);
        this.setPathfindingMalus(PathType.DAMAGE_FIRE, 10.0f);
        this.setPathfindingMalus(PathType.WATER, 5.0f);
        this.setPathfindingMalus(PathType.DANGER_POWDER_SNOW, 10.0f);
    }

    @Override
    protected Brain<?> makeBrain(Dynamic<?> dynamic) {
        return SilkMothAi.createBrain(this.brainProvider().makeBrain(dynamic));
    }

    @Override
    protected Brain.Provider<SilkMoth> brainProvider() {
        return Brain.provider(SilkMothAi.MEMORY_MODULES, SilkMothAi.SENSORS);
    }

    @Override
    public Brain<SilkMoth> getBrain() {
        return (Brain<SilkMoth>) super.getBrain();
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide()) {
            this.setupAnimations();
        }
    }

    private void setupAnimations() {
        if (this.idleCooldown <= 0) {
            this.idleCooldown = this.random.nextInt(100) + 80;
            this.idleAnimationState.start(this.tickCount);
        }
        if (this.idleCooldown > 0) {
            this.idleCooldown--;
        }
        double yDelta = this.getDeltaMovement().y();

        this.bodyPitch = -yDelta * 10;

        if(!this.isFlying()) this.bodyPitch = 0;

        this.flyAnimationState.animateWhen(this.isFlying(), this.tickCount);
    }

    public double getBodyPitch() {
        return this.bodyPitch;
    }

    @Override
    protected void customServerAiStep() {
        this.getBrain().tick((ServerLevel) this.level(), this);
        SilkMothAi.updateActivity(this);
        super.customServerAiStep();
    }

    @Override
    protected int calculateFallDamage(float fallDistance, float damageMultiplier) {
        return this.isFlying() ? 0 : super.calculateFallDamage(fallDistance, 0.5f);
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        GroundPathNavigation navigation = new GroundPathNavigation(this, level);
        navigation.setCanFloat(true);
        navigation.setCanOpenDoors(false);
        navigation.setCanPassDoors(false);
        return navigation;
    }

    private PathNavigation createFlightNavigation(Level level) {
        FlyingPathNavigation navigation = new FlyingPathNavigation(this, level);
        navigation.setCanOpenDoors(false);
        navigation.setCanFloat(true);
        navigation.setCanPassDoors(false);
        return navigation;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(FLYING, false);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("Flying", this.isFlying());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setFlying(compound.getBoolean("Flying"));
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
        super.onSyncedDataUpdated(key);
        if (FLYING.equals(key)) {
            if (this.isFlying()) {
                this.moveControl = new FlyingMoveControl(this, 20, true);
                this.navigation = this.createFlightNavigation(this.level());
            }
            else {
                this.moveControl = new MoveControl(this);
                this.navigation = this.createNavigation(this.level());
                this.setNoGravity(false);
                this.setOnGround(true);
            }
        }
    }

    public void takeOff() {
        this.setFlying(true);
        this.getBrain().setMemory(VerdanceMemoryModuleTypes.IS_FLYING, Unit.INSTANCE);
    }

    public void land() {
        this.setFlying(false);
        this.getBrain().eraseMemory(VerdanceMemoryModuleTypes.IS_FLYING);
        this.getBrain().eraseMemory(VerdanceMemoryModuleTypes.WANTS_TO_LAND);
    }

    public void setFlying(boolean flying) {
        this.entityData.set(FLYING, flying);
    }

    @Override
    public boolean isFlying() {
        return this.entityData.get(FLYING);
    }

    @Override
    public float getWalkTargetValue(BlockPos pos, LevelReader level) {
        return -level.getPathfindingCostFromLightLevels(pos);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(ItemTags.FLOWERS);
    }

    @Override
    public void spawnChildFromBreeding(ServerLevel level, Animal mate) {
        this.finalizeSpawnChildFromBreeding(level, mate, null);
        this.getBrain().setMemory(MemoryModuleType.IS_PREGNANT, Unit.INSTANCE);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return VerdanceSoundEvents.ENTITY_SILK_MOTH_IDLE;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return VerdanceSoundEvents.ENTITY_SILK_MOTH_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return VerdanceSoundEvents.ENTITY_SILK_MOTH_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        if (!this.isFlying()) {
            this.playSound(SoundEvents.SILVERFISH_STEP, 0.1F, 1.0F);
        }
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return null;
    }

    public static boolean checkSilkMothSpawnRules(EntityType<? extends Animal> type, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return level.getBlockState(pos.below()).is(VerdanceBlockTags.SILK_MOTHS_SPAWNABLE_ON) && Monster.isDarkEnoughToSpawn(level, pos, random);
    }

    public static AttributeSupplier.Builder createSilkMothAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0f)
                .add(Attributes.FLYING_SPEED, 0.5d)
                .add(Attributes.MOVEMENT_SPEED, 0.2d)
                .add(Attributes.FOLLOW_RANGE, 48.0)
                .add(Attributes.STEP_HEIGHT, 1.25f);
    }
}