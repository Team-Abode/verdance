package com.teamabode.verdance.common.entity.silkworm;

import com.mojang.serialization.Dynamic;
import com.teamabode.verdance.common.entity.silk_moth.SilkMoth;
import com.teamabode.verdance.core.misc.tag.VerdanceItemTags;
import com.teamabode.verdance.core.registry.VerdanceEntities;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WallClimberNavigation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("unchecked")
public class Silkworm extends PathfinderMob {
    private static final EntityDataAccessor<Boolean> CLIMBING = SynchedEntityData.defineId(Silkworm.class, EntityDataSerializers.BOOLEAN);
    private int age = 0;

    public Silkworm(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }

    protected PathNavigation createNavigation(Level level) {
        return new WallClimberNavigation(this, level);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.getEntityData().define(CLIMBING, false);
    }

    public void tick() {
        if (!this.level().isClientSide()) {
            this.setClimbing(horizontalCollision);
        }
        super.tick();
    }

    protected Brain<?> makeBrain(Dynamic<?> dynamic) {
        return SilkwormAi.createBrain(this.brainProvider().makeBrain(dynamic));
    }

    protected Brain.Provider<Silkworm> brainProvider() {
        return Brain.provider(SilkwormAi.MEMORY_MODULES, SilkwormAi.SENSORS);
    }

    public Brain<Silkworm> getBrain() {
        return (Brain<Silkworm>) super.getBrain();
    }

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

    public void aiStep() {
        this.setAge(this.age + 1);
        super.aiStep();
    }

    protected void customServerAiStep() {
        this.getBrain().tick((ServerLevel) this.level(), this);
        SilkwormAi.updateActivity(this);
        super.customServerAiStep();
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag dataTag) {
        return super.finalizeSpawn(level, difficulty, reason, spawnData, dataTag);
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setClimbing(compound.getBoolean("Climbing"));
        this.setAge(compound.getInt("Age"));
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("Climbing", this.isClimbing());
        compound.putInt("Age", this.getAge());
    }

    public boolean isClimbing() {
        return this.getEntityData().get(CLIMBING);
    }

    public void setClimbing(boolean value) {
        this.getEntityData().set(CLIMBING, value);
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int value) {
        this.age = value;

        if (this.age >= 24000) {
            this.convert();
        }
    }

    public void ageUp(int offset) {
        this.setAge(this.age + offset * 20);
    }

    private int getTimeUntilAdult() {
        return Math.max(0, 24000 - this.age);
    }

    protected void convert() {
        if (this.level() instanceof ServerLevel server) {
            SilkMoth silkMoth = VerdanceEntities.SILK_MOTH.create(this.level());
            if (silkMoth != null) {
                silkMoth.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), this.getXRot());
                silkMoth.finalizeSpawn(server, server.getCurrentDifficultyAt(silkMoth.blockPosition()), MobSpawnType.CONVERSION, null, null);
                if (this.hasCustomName()) {
                    silkMoth.setCustomName(this.getCustomName());
                    silkMoth.setCustomNameVisible(this.isCustomNameVisible());
                }
                silkMoth.setPersistenceRequired();
                server.addFreshEntityWithPassengers(silkMoth);
                this.discard();
            }
        }
    }

    public boolean onClimbable() {
        return this.isClimbing();
    }

    public boolean isFood(ItemStack stack) {
        return stack.is(VerdanceItemTags.SILKWORM_FOOD);
    }

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