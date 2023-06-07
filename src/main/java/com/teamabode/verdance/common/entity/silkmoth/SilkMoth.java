package com.teamabode.verdance.common.entity.silkmoth;

import com.google.common.collect.ImmutableList;
import com.teamabode.verdance.common.entity.silkmoth.ai.SilkMothNavigation;
import com.teamabode.verdance.common.entity.silkmoth.ai.SilkMothWanderGoal;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import org.jetbrains.annotations.Nullable;

public class SilkMoth extends Animal implements FlyingAnimal {
    protected static final ImmutableList<SensorType<? extends Sensor<? super SilkMoth>>> SENSOR_TYPES = ImmutableList.of();
    protected static final ImmutableList<MemoryModuleType<?>> MEMORY_TYPES = ImmutableList.of();

    public SilkMoth(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new FlyingMoveControl(this, 20, false);

        this.setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, -1.0f);
        this.setPathfindingMalus(BlockPathTypes.WATER, -1.0f);
    }

    protected PathNavigation createNavigation(Level level) {
        FlyingPathNavigation flyingPathNavigation = new SilkMothNavigation(this, level);
        flyingPathNavigation.setCanOpenDoors(false);
        flyingPathNavigation.setCanFloat(false);
        flyingPathNavigation.setCanPassDoors(true);
        return flyingPathNavigation;
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(4, new SilkMothWanderGoal(this));
    }

    protected void checkFallDamage(double y, boolean onGround, BlockState state, BlockPos pos) {

    }

    public static AttributeSupplier.Builder createSilkMothAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 15.0f).add(Attributes.FLYING_SPEED, 0.6000000238418579).add(Attributes.MOVEMENT_SPEED, 0.30000001192092896).add(Attributes.FOLLOW_RANGE, 48.0);
    }

    public MobType getMobType() {
        return MobType.ARTHROPOD;
    }


    @Nullable
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return null;
    }

    public boolean isFlying() {
        return !this.onGround();
    }
}
