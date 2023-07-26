package com.teamabode.verdance.common.entity.silk_moth;

import com.mojang.serialization.Dynamic;
import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.entity.silk_moth.pathing.SilkMothFlyingMoveControl;
import com.teamabode.verdance.core.registry.VerdanceMemoryModuleType;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.DebugPackets;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.TimeUtil;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("unchecked")
public class SilkMoth extends Animal {
    public static final EntityDataAccessor<Boolean> FLYING = SynchedEntityData.defineId(SilkMoth.class, EntityDataSerializers.BOOLEAN);

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState flyAnimationState = new AnimationState();

    private int idleCooldown = 0;

    public SilkMoth(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
        this.setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, 10.0f);
        this.setPathfindingMalus(BlockPathTypes.WATER, 5.0f);
        this.setPathfindingMalus(BlockPathTypes.DANGER_POWDER_SNOW, 10.0f);
    }

    protected Brain.Provider<SilkMoth> brainProvider() {
        return SilkMothAi.brainProvider();
    }

    protected Brain<?> makeBrain(Dynamic<?> dynamic) {
        return SilkMothAi.makeBrain(this.brainProvider().makeBrain(dynamic));
    }

    public Brain<SilkMoth> getBrain() {
        return (Brain<SilkMoth>) super.getBrain();
    }

    protected void sendDebugPackets() {
        super.sendDebugPackets();
        DebugPackets.sendEntityBrain(this);
    }

    private void setupAnimations() {
        if (this.idleCooldown <= 0) {
            this.idleCooldown = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        }
        if (this.idleCooldown > 0) {
            this.idleCooldown--;
        }
        this.flyAnimationState.animateWhen(this.isFlying() && !this.onGround(), this.tickCount);
    }

    public void tick() {
        super.tick();
        if (this.level().isClientSide()) {
            this.setupAnimations();
        }
        if (this.tickCount % 20 == 0) {
            Verdance.LOGGER.info(
                    "Client Side: " + this.level().isClientSide() +
                    "\nFlying: " + this.entityData.get(FLYING) +
                    "\nCooldown Ticks: " + this.getBrain().getMemory(VerdanceMemoryModuleType.GROUND_COOLDOWN_TICKS) +
                    "\nPose: " + this.getPose().name()
            );
        }
    }

    public void aiStep() {
        super.aiStep();

        if (this.isFlying() && this.onGround() && this.getBrain().getMemory(VerdanceMemoryModuleType.GROUND_COOLDOWN_TICKS).isEmpty()) {
            this.stopFlying();
        }
    }

    public void takeOff() {
        this.setFlying(true);
        this.getBrain().setMemory(VerdanceMemoryModuleType.GROUND_COOLDOWN_TICKS, 100);
    }

    public void stopFlying() {
        int randomTime = TimeUtil.rangeOfSeconds(10, 15).sample(this.getRandom());

        this.setFlying(false);
        this.getBrain().setMemory(VerdanceMemoryModuleType.FLIGHT_COOLDOWN_TICKS, randomTime);
    }

    protected void customServerAiStep() {
        this.level().getProfiler().push("silkMothBrain");
        this.getBrain().tick((ServerLevel) this.level(), this);
        this.level().getProfiler().pop();
        this.level().getProfiler().push("silkMothActivityUpdate");
        SilkMothAi.updateActivity(this);
        this.level().getProfiler().pop();
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

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(FLYING, false);
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
        super.onSyncedDataUpdated(key);
        if (FLYING.equals(key)) {
            if (this.isFlying()) {
                this.moveControl = new SilkMothFlyingMoveControl(this);
                this.navigation = this.createFlightNavigation(this.level());
                return;
            }
            this.moveControl = new MoveControl(this);
            this.navigation = this.createNavigation(this.level());
            this.setNoGravity(false);
        }
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("Flying", this.isFlying());
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setFlying(compound.getBoolean("Flying"));
    }

    public void setFlying(boolean flying) {
        this.entityData.set(FLYING, flying);
    }

    public boolean isFlying() {
        return this.entityData.get(FLYING);
    }

    @Nullable
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return null;
    }

    public static AttributeSupplier.Builder createSilkMothAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 15.0f).add(Attributes.FLYING_SPEED, 0.6d).add(Attributes.MOVEMENT_SPEED, 0.2d).add(Attributes.FOLLOW_RANGE, 48.0);
    }

    public MobType getMobType() {
        return MobType.ARTHROPOD;
    }
}
