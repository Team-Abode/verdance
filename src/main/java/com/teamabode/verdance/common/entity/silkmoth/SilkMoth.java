package com.teamabode.verdance.common.entity.silkmoth;

import com.mojang.serialization.Dynamic;
import com.teamabode.verdance.core.tag.VerdanceBlockTags;
import com.teamabode.verdance.core.registry.VerdanceMemoryModuleTypes;
import com.teamabode.verdance.core.registry.VerdanceSoundEvents;
import com.teamabode.verdance.core.tag.VerdanceItemTags;
import net.minecraft.block.BlockState;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Flutterer;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.MobNavigation;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TimeHelper;
import net.minecraft.util.Unit;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("unchecked")
public class SilkMoth extends AnimalEntity implements Flutterer {
    public static final TrackedData<Boolean> FLYING = DataTracker.registerData(SilkMoth.class, TrackedDataHandlerRegistry.BOOLEAN);

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState flyAnimationState = new AnimationState();

    private int idleCooldown = 100;

    public float lastBodyPitch;
    public float bodyPitch;
    public float lastAnimationProgress;

    public int lastSoarTicks;
    public int soarTicks;

    public SilkMoth(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);

        this.moveControl = new MoveControl(this);
        this.setPathfindingPenalty(PathNodeType.DAMAGE_FIRE, 10.0f);
        this.setPathfindingPenalty(PathNodeType.WATER, 5.0f);
        this.setPathfindingPenalty(PathNodeType.DANGER_POWDER_SNOW, 10.0f);
    }

    @Override
    protected Brain<?> deserializeBrain(Dynamic<?> dynamic) {
        return SilkMothBrain.createBrain(this.createBrainProfile().deserialize(dynamic));
    }

    @Override
    protected Brain.Profile<SilkMoth> createBrainProfile() {
        return Brain.createProfile(SilkMothBrain.MEMORY_MODULES, SilkMothBrain.SENSORS);
    }

    @Override
    public Brain<SilkMoth> getBrain() {
        return (Brain<SilkMoth>) super.getBrain();
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getWorld().isClient()) {
            this.setupAnimations();
        }
    }

    private void setupAnimations() {
        if (this.idleCooldown <= 0) {
            this.idleCooldown = this.random.nextInt(100) + 80;
            this.idleAnimationState.start(this.age);
        }
        if (this.idleCooldown > 0) {
            this.idleCooldown--;
        }
        Vec3d deltaMovement = this.getVelocity();

        if (this.isInAir()) {
            this.bodyPitch = (float) (-deltaMovement.y * 10.0f);
        }
        else this.bodyPitch = 0.0f;

        this.lastSoarTicks = this.soarTicks;
        if (deltaMovement.horizontalLength() > 0.05d) {
            this.soarTicks = MathHelper.clamp(this.soarTicks + 1, 0, 5);
        }
        else this.soarTicks = MathHelper.clamp(this.soarTicks - 1, 0, 5);

        this.flyAnimationState.setRunning(this.isInAir(), this.age);
    }

    public float getSoarProgress(float deltaTicks) {
        return MathHelper.lerp(deltaTicks, this.lastSoarTicks, this.soarTicks) / 5.0f;
    }

    @Override
    protected void mobTick() {
        this.getBrain().tick((ServerWorld) this.getWorld(), this);
        SilkMothBrain.updateActivity(this);
        super.mobTick();
    }

    @Override
    protected int computeFallDamage(float fallDistance, float damageMultiplier) {
        return this.isInAir() ? 0 : super.computeFallDamage(fallDistance, 0.5f);
    }

    @Override
    protected EntityNavigation createNavigation(World world) {
        MobNavigation navigation = new MobNavigation(this, world);
        navigation.setCanSwim(true);
        navigation.setCanPathThroughDoors(false);
        navigation.setCanEnterOpenDoors(false);
        return navigation;
    }

    private EntityNavigation createFlightNavigation(World world) {
        BirdNavigation navigation = new BirdNavigation(this, world);
        navigation.setCanPathThroughDoors(false);
        navigation.setCanSwim(true);
        navigation.setCanEnterOpenDoors(false);
        return navigation;
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(FLYING, false);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound compound) {
        super.writeCustomDataToNbt(compound);
        compound.putBoolean("Flying", this.isInAir());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound compound) {
        super.readCustomDataFromNbt(compound);
        this.setFlying(compound.getBoolean("Flying"));
    }

    @Override
    public void onTrackedDataSet(TrackedData<?> key) {
        super.onTrackedDataSet(key);
        if (FLYING.equals(key)) {
            if (this.isInAir()) {
                this.moveControl = new FlightMoveControl(this, 20, true);
                this.navigation = this.createFlightNavigation(this.getWorld());
            }
            else {
                this.moveControl = new MoveControl(this);
                this.navigation = this.createNavigation(this.getWorld());
                this.setNoGravity(false);
                this.setOnGround(true);
            }
        }
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand interactionHand) {
        ItemStack stack = player.getStackInHand(interactionHand);
        World world = this.getWorld();
        boolean isFood = this.isBreedingItem(stack);
        ActionResult interactionResult = super.interactMob(player, interactionHand);
        if (interactionResult.isAccepted() && isFood) {
            world.playSoundFromEntity(null, this, this.getEatSound(stack), SoundCategory.NEUTRAL, 1.0F, MathHelper.nextBetween(world.random, 0.8F, 1.2F));
        }
        return interactionResult;
    }

    public void takeOff() {
        this.setFlying(true);
        this.getBrain().remember(VerdanceMemoryModuleTypes.IS_FLYING, Unit.INSTANCE);

        long landingTime = this.getWorld().getTime() + TimeHelper.betweenSeconds(30, 60).get(random);
        this.getBrain().remember(VerdanceMemoryModuleTypes.LANDING_TIME, landingTime);
    }

    public void land() {
        this.setFlying(false);
        this.getBrain().forget(VerdanceMemoryModuleTypes.IS_FLYING);
        this.getBrain().forget(VerdanceMemoryModuleTypes.WANTS_TO_LAND);
        this.getBrain().forget(VerdanceMemoryModuleTypes.LANDING_TIME);
    }

    public void setFlying(boolean flying) {
        this.dataTracker.set(FLYING, flying);
    }

    @Override
    public boolean isInAir() {
        return this.dataTracker.get(FLYING);
    }

    @Override
    public float getPathfindingFavor(BlockPos pos, WorldView world) {
        return -world.getPhototaxisFavor(pos);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isIn(VerdanceItemTags.SILK_MOTH_FOOD);
    }

    @Override
    public void breed(ServerWorld world, AnimalEntity mate) {
        this.breed(world, mate, null);
        this.getBrain().remember(MemoryModuleType.IS_PREGNANT, Unit.INSTANCE);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return VerdanceSoundEvents.ENTITY_SILK_MOTH_IDLE;
    }

    @Override
    public SoundEvent getEatSound(ItemStack itemStack) {
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
        if (!this.isInAir()) {
            this.playSound(SoundEvents.ENTITY_SILVERFISH_STEP, 0.1F, 1.0F);
        }
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity otherParent) {
        return null;
    }

    public static boolean checkSilkMothSpawnRules(EntityType<? extends AnimalEntity> type, ServerWorldAccess world, SpawnReason spawnType, BlockPos pos, Random random) {
        return world.getBlockState(pos.down()).isIn(VerdanceBlockTags.SILK_MOTHS_SPAWNABLE_ON) && HostileEntity.isSpawnDark(world, pos, random);
    }

    public static DefaultAttributeContainer.Builder createSilkMothAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0f)
                .add(EntityAttributes.GENERIC_FLYING_SPEED, 0.5d)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2d)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 48.0)
                .add(EntityAttributes.GENERIC_STEP_HEIGHT, 1.25f);
    }
}