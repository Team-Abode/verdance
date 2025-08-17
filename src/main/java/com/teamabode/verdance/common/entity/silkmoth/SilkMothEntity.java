package com.teamabode.verdance.common.entity.silkmoth;

import com.mojang.serialization.Dynamic;
import com.teamabode.verdance.core.tag.VerdanceBlockTags;
import com.teamabode.verdance.core.registry.VerdanceMemoryModuleTypes;
import com.teamabode.verdance.core.registry.VerdanceSoundEvents;
import com.teamabode.verdance.core.tag.VerdanceItemTags;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.Unit;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("unchecked")
public class SilkMothEntity extends Animal implements FlyingAnimal {
    public static final EntityDataAccessor<Boolean> FLYING = SynchedEntityData.defineId(SilkMothEntity.class, EntityDataSerializers.BOOLEAN);

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState flyAnimationState = new AnimationState();

    private int idleCooldown = 100;

    public float lastBodyPitch;
    public float bodyPitch;
    public float lastAnimationProgress;

    public int lastSoarTicks;
    public int soarTicks;

    public SilkMothEntity(EntityType<? extends Animal> entityType, Level world) {
        super(entityType, world);

        this.moveControl = new MoveControl(this);
        this.setPathfindingMalus(PathType.DANGER_FIRE, -1.0F);
        this.setPathfindingMalus(PathType.WATER, -1.0F);
        this.setPathfindingMalus(PathType.WATER_BORDER, 16.0F);
        this.setPathfindingMalus(PathType.COCOA, -1.0F);
        this.setPathfindingMalus(PathType.FENCE, -1.0F);
    }

    @Override
    protected Brain<?> makeBrain(Dynamic<?> dynamic) {
        return SilkMothBrain.createBrain(this.brainProvider().makeBrain(dynamic));
    }

    @Override
    protected Brain.Provider<SilkMothEntity> brainProvider() {
        return Brain.provider(SilkMothBrain.MEMORY_MODULES, SilkMothBrain.SENSORS);
    }

    @Override
    public Brain<SilkMothEntity> getBrain() {
        return (Brain<SilkMothEntity>) super.getBrain();
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
        Vec3 velocity = this.getDeltaMovement();

        if (this.isFlying()) {
            this.bodyPitch = (float) (-velocity.y * 10.0f);
        }
        else this.bodyPitch = 0.0f;

        this.lastSoarTicks = this.soarTicks;
        if (velocity.horizontalDistance() > 0.05d) {
            this.soarTicks = Mth.clamp(this.soarTicks + 1, 0, 5);
        }
        else this.soarTicks = Mth.clamp(this.soarTicks - 1, 0, 5);

        this.flyAnimationState.animateWhen(this.isFlying(), this.tickCount);
    }

    public float getSoarProgress(float deltaTicks) {
        return Mth.lerpInt(deltaTicks, this.lastSoarTicks, this.soarTicks) / 5.0f;
    }

    @Override
    protected void customServerAiStep() {
        this.getBrain().tick((ServerLevel) this.level(), this);
        SilkMothBrain.updateActivity(this);
        super.customServerAiStep();
    }

    @Override
    protected int calculateFallDamage(float fallDistance, float damageMultiplier) {
        return this.isFlying() ? 0 : super.calculateFallDamage(fallDistance, 0.5f);
    }

    @Override
    protected PathNavigation createNavigation(Level world) {
        GroundPathNavigation navigation = new GroundPathNavigation(this, world);
        navigation.setCanFloat(true);
        navigation.setCanOpenDoors(false);
        navigation.setCanPassDoors(false);
        return navigation;
    }

    private PathNavigation createFlightNavigation(Level world) {
        FlyingPathNavigation navigation = new FlyingPathNavigation(this, world);
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

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand interactionHand) {
        ItemStack stack = player.getItemInHand(interactionHand);
        Level world = this.level();
        boolean isFood = this.isFood(stack);
        InteractionResult interactionResult = super.mobInteract(player, interactionHand);
        if (interactionResult.consumesAction() && isFood) {
            world.playSound(null, this, this.getEatingSound(stack), SoundSource.NEUTRAL, 1.0F, Mth.randomBetween(world.random, 0.8F, 1.2F));
        }
        return interactionResult;
    }

    public void takeOff() {
        this.setFlying(true);
        this.getBrain().setMemory(VerdanceMemoryModuleTypes.IS_FLYING, Unit.INSTANCE);

        long landingTime = this.level().getGameTime() + TimeUtil.rangeOfSeconds(30, 60).sample(random);
        this.getBrain().setMemory(VerdanceMemoryModuleTypes.LANDING_TIME, landingTime);
    }

    public void land() {
        this.setFlying(false);
        this.getBrain().eraseMemory(VerdanceMemoryModuleTypes.IS_FLYING);
        this.getBrain().eraseMemory(VerdanceMemoryModuleTypes.WANTS_TO_LAND);
        this.getBrain().eraseMemory(VerdanceMemoryModuleTypes.LANDING_TIME);
    }

    public void setFlying(boolean flying) {
        this.entityData.set(FLYING, flying);
    }

    @Override
    public boolean isFlying() {
        return this.entityData.get(FLYING);
    }

    @Override
    public float getWalkTargetValue(BlockPos pos, LevelReader world) {
        return -world.getPathfindingCostFromLightLevels(pos);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(VerdanceItemTags.SILK_MOTH_FOOD);
    }

    @Override
    public void spawnChildFromBreeding(ServerLevel world, Animal mate) {
        this.finalizeSpawnChildFromBreeding(world, mate, null);
        this.getBrain().setMemory(MemoryModuleType.IS_PREGNANT, Unit.INSTANCE);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return VerdanceSoundEvents.ENTITY_SILK_MOTH_IDLE;
    }

    @Override
    public SoundEvent getEatingSound(ItemStack itemStack) {
        return VerdanceSoundEvents.ENTITY_SILK_MOTH_EAT;
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
    public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob otherParent) {
        return null;
    }

    public static boolean checkSilkMothSpawnRules(EntityType<? extends Animal> type, ServerLevelAccessor world, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return world.getBlockState(pos.below()).is(VerdanceBlockTags.SILK_MOTHS_SPAWNABLE_ON) && Monster.isDarkEnoughToSpawn(world, pos, random);
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