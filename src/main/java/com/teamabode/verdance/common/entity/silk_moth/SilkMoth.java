package com.teamabode.verdance.common.entity.silk_moth;

import com.mojang.serialization.Dynamic;
import com.teamabode.verdance.core.misc.tag.VerdanceBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Unit;
import net.minecraft.world.DifficultyInstance;
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
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("unchecked")
public class SilkMoth extends Animal implements FlyingAnimal {
    public static final EntityDataAccessor<Boolean> FLYING = SynchedEntityData.defineId(SilkMoth.class, EntityDataSerializers.BOOLEAN);
    //public static final EntityDataAccessor<Boolean> SLEEPING = SynchedEntityData.defineId(SilkMoth.class, EntityDataSerializers.BOOLEAN);

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState flyAnimationState = new AnimationState();

    private int idleCooldown = 100;

    public SilkMoth(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);

        this.moveControl = new MoveControl(this);

        this.setMaxUpStep(1.25f);
        this.setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, 10.0f);
        this.setPathfindingMalus(BlockPathTypes.WATER, 5.0f);
        this.setPathfindingMalus(BlockPathTypes.DANGER_POWDER_SNOW, 10.0f);
    }

    protected Brain<?> makeBrain(Dynamic<?> dynamic) {
        return SilkMothAi.createBrain(this.brainProvider().makeBrain(dynamic));
    }

    protected Brain.Provider<SilkMoth> brainProvider() {
        return Brain.provider(SilkMothAi.MEMORY_MODULES, SilkMothAi.SENSORS);
    }

    public Brain<SilkMoth> getBrain() {
        return (Brain<SilkMoth>) super.getBrain();
    }

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
        this.flyAnimationState.animateWhen(this.isFlying(), this.tickCount);
    }

    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag dataTag) {
        return super.finalizeSpawn(level, difficulty, reason, spawnData, dataTag);
    }

    protected void customServerAiStep() {
        this.getBrain().tick((ServerLevel) this.level(), this);
        SilkMothAi.updateActivity(this);
        super.customServerAiStep();
    }

    protected int calculateFallDamage(float fallDistance, float damageMultiplier) {
        return this.isFlying() ? 0 : super.calculateFallDamage(fallDistance, 0.5f);
    }

    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.SILVERFISH_STEP, 0.3F, 1.0F);
    }

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

    // Data
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(FLYING, false);
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("Flying", this.isFlying());
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setFlying(compound.getBoolean("Flying"));
    }

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

    public void setFlying(boolean flying) {
        this.entityData.set(FLYING, flying);
    }

    public boolean isFlying() {
        return this.entityData.get(FLYING);
    }

    public static boolean checkSilkMothSpawnRules(EntityType<? extends Animal> type, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return level.getBlockState(pos.below()).is(VerdanceBlockTags.SILK_MOTHS_SPAWNABLE_ON) && Monster.isDarkEnoughToSpawn(level, pos, random);
    }

    public float getWalkTargetValue(BlockPos pos, LevelReader level) {
        return -level.getPathfindingCostFromLightLevels(pos);
    }

    public boolean isFood(ItemStack stack) {
        return stack.is(ItemTags.FLOWERS);
    }

    public void spawnChildFromBreeding(ServerLevel level, Animal mate) {
        this.finalizeSpawnChildFromBreeding(level, mate, null);
        this.getBrain().setMemory(MemoryModuleType.IS_PREGNANT, Unit.INSTANCE);
    }

    @Nullable
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return null;
    }

    public MobType getMobType() {
        return MobType.ARTHROPOD;
    }

    public static AttributeSupplier.Builder createSilkMothAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 15.0f)
                .add(Attributes.FLYING_SPEED, 0.5d)
                .add(Attributes.MOVEMENT_SPEED, 0.2d)
                .add(Attributes.FOLLOW_RANGE, 48.0);
    }
}