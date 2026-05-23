package com.teamabode.verdance.common.entity.silkworm;

import com.mojang.serialization.Dynamic;
import com.teamabode.verdance.core.tag.VerdanceItemTags;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WallClimberNavigation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import com.teamabode.verdance.core.registry.VerdanceSoundEvents;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("unchecked")
public class Silkworm extends PathfinderMob {
    private static final EntityDataAccessor<Boolean> CLIMBING_WALL = SynchedEntityData.defineId(Silkworm.class, EntityDataSerializers.BOOLEAN);
    private int tickCount = 0;

    public Silkworm(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        return new WallClimberNavigation(this, level);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(CLIMBING_WALL, false);
    }

    @Override
    public void tick() {
        if (!this.level().isClientSide()) {
            this.setClimbing(horizontalCollision);
        }
        super.tick();
    }

    @Override
    protected Brain<?> makeBrain(Dynamic<?> dynamic) {
        return SilkwormBrain.createBrain(this.brainProvider().makeBrain(dynamic));
    }

    @Override
    protected Brain.Provider<Silkworm> brainProvider() {
        return Brain.provider(SilkwormBrain.MEMORY_MODULES, SilkwormBrain.SENSORS);
    }

    @Override
    public Brain<Silkworm> getBrain() {
        return (Brain<Silkworm>) super.getBrain();
    }

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (this.isFood(stack)) {
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
            this.ageUp(AgeableMob.getSpeedUpSecondsWhenFeeding(this.getTimeUntilAdult()));
            this.level().addParticle(
                    ParticleTypes.HAPPY_VILLAGER,
                    this.getRandomX(1.0),
                    this.getRandomY() + 0.5,
                    this.getRandomZ(1.0),
                    0.0,
                    0.0,
                    0.0
            );
            return InteractionResult.sidedSuccess(this.level().isClientSide());
        }
        return super.mobInteract(player, hand);
    }

    @Override
    public void aiStep() {
        this.setAge(this.tickCount + 1);
        super.aiStep();
    }

    @Override
    protected void customServerAiStep() {
        this.getBrain().tick((ServerLevel) this.level(), this);
        SilkwormBrain.updateActivity(this);
        super.customServerAiStep();
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setClimbing(compound.getBoolean("Climbing"));
        this.setAge(compound.getInt("Age"));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("Climbing", this.onClimbable());
        compound.putInt("Age", this.getAge());
    }


    public boolean isClimbingWall() {
        return entityData.get(CLIMBING_WALL);
    }

    public void setClimbing(boolean value) {
        this.getEntityData().set(CLIMBING_WALL, value);
    }

    public int getAge() {
        return this.tickCount;
    }

    public void setAge(int value) {
        this.tickCount = value;
    }

    public void ageUp(int offset) {
        this.setAge(this.tickCount + offset * 20);
    }

    private int getTimeUntilAdult() {
        return Math.max(0, 24000 - this.tickCount);
    }

    @Override
    protected int calculateFallDamage(float fallDistance, float damageMultiplier) {
        return super.calculateFallDamage(fallDistance, damageMultiplier) - 10;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return VerdanceSoundEvents.ENTITY_SILKWORM_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return VerdanceSoundEvents.ENTITY_SILKWORM_DEATH.get();
    }

    @Override
    public boolean onClimbable() {
        return this.isClimbingWall();
    }

    public boolean isFood(ItemStack stack) {
        return stack.is(VerdanceItemTags.SILKWORM_FOOD);
    }

    @Override
    public boolean shouldDropExperience() {
        return false;
    }

    public static AttributeSupplier.Builder createSilkwormAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 5.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.1d)
                .add(Attributes.FOLLOW_RANGE, 48.0);
    }
}
