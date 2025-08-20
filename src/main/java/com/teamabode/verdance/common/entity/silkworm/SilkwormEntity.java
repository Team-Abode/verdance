package com.teamabode.verdance.common.entity.silkworm;

import com.mojang.serialization.Dynamic;
import com.teamabode.verdance.core.tag.VerdanceItemTags;
import com.teamabode.verdance.core.registry.VerdanceSoundEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.SpiderNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("unchecked")
public class SilkwormEntity extends PathAwareEntity {
    private static final TrackedData<Boolean> CLIMBING_WALL = DataTracker.registerData(SilkwormEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private int age = 0;

    public SilkwormEntity(EntityType<? extends PathAwareEntity> entityType, World level) {
        super(entityType, level);
    }

    @Override
    protected EntityNavigation createNavigation(World level) {
        return new SpiderNavigation(this, level);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(CLIMBING_WALL, false);
    }

    @Override
    public void tick() {
        if (!this.getWorld().isClient()) {
            this.setClimbing(horizontalCollision);
        }
        super.tick();
    }

    @Override
    protected Brain<?> deserializeBrain(Dynamic<?> dynamic) {
        return SilkwormBrain.createBrain(this.createBrainProfile().deserialize(dynamic));
    }

    @Override
    protected Brain.Profile<SilkwormEntity> createBrainProfile() {
        return Brain.createProfile(SilkwormBrain.MEMORY_MODULES, SilkwormBrain.SENSORS);
    }

    @Override
    public Brain<SilkwormEntity> getBrain() {
        return (Brain<SilkwormEntity>) super.getBrain();
    }

    @Override
    protected ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        if (this.isFood(stack)) {
            if (!player.getAbilities().creativeMode) {
                stack.decrement(1);
            }
            this.ageUp(PassiveEntity.toGrowUpAge(this.getTimeUntilAdult()));
            this.getWorld().addParticleClient(
                    ParticleTypes.HAPPY_VILLAGER,
                    this.getParticleX(1.0),
                    this.getRandomBodyY() + 0.5,
                    this.getParticleZ(1.0),
                    0.0,
                    0.0,
                    0.0
            );
            return ActionResult.SUCCESS;
        }
        return super.interactMob(player, hand);
    }

    @Override
    public void tickMovement() {
        this.setAge(this.age + 1);
        super.tickMovement();
    }

    @Override
    protected void mobTick(ServerWorld world) {
        this.getBrain().tick(world, this);
        SilkwormBrain.updateActivity(this);
        super.mobTick(world);
    }

    @Override
    protected void writeCustomData(WriteView view) {
        super.writeCustomData(view);

        view.putBoolean("climbing", this.isClimbing());
        view.putInt("age", this.getAge());
    }

    @Override
    protected void readCustomData(ReadView view) {
        super.readCustomData(view);

        boolean climbing = view.getBoolean("climbing", false);
        int age = view.getInt("age", -24000);

        this.setClimbing(climbing);
        this.setAge(age);
    }

    public boolean isClimbingWall() {
        return dataTracker.get(CLIMBING_WALL);
    }

    public void setClimbing(boolean value) {
        this.getDataTracker().set(CLIMBING_WALL, value);
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int value) {
        this.age = value;
    }

    public void ageUp(int offset) {
        this.setAge(this.age + offset * 20);
    }

    private int getTimeUntilAdult() {
        return Math.max(0, 24000 - this.age);
    }

    public boolean isFood(ItemStack stack) {
        return stack.isIn(VerdanceItemTags.SILKWORM_FOOD);
    }

    @Override
    protected int computeFallDamage(double fallDistance, float damageMultiplier) {
        return super.computeFallDamage(fallDistance, damageMultiplier) - 10;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return VerdanceSoundEvents.ENTITY_SILKWORM_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return VerdanceSoundEvents.ENTITY_SILKWORM_DEATH;
    }

    @Override
    public boolean isClimbing() {
        return this.isClimbingWall();
    }

    @Override
    public boolean shouldDropExperience() {
        return false;
    }


    public static DefaultAttributeContainer.Builder createSilkwormAttributes() {
        return AnimalEntity.createAnimalAttributes()
                .add(EntityAttributes.MAX_HEALTH, 5.0f)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.1d)
                .add(EntityAttributes.FOLLOW_RANGE, 48.0);
    }
}
