package com.teamabode.verdance.common.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.state.property.Properties;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class CushionEntity extends Entity {

    public CushionEntity(EntityType<?> entityType, World level) {
        super(entityType, level);
        this.noClip = true;
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {

    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound compoundTag) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound compoundTag) {

    }

    public @NotNull PistonBehavior getPistonBehavior() {
        return PistonBehavior.IGNORE;
    }

    protected boolean canAddPassenger(Entity entity) {
        return true;
    }

    protected boolean couldAcceptPassenger() {
        return true;
    }

    @Override
    protected void removePassenger(Entity entity) {
        super.removePassenger(entity);
        if (!this.isRemoved() && !entity.getWorld().isClient) {
            BlockState blockState = entity.getBlockStateAtPos();
            entity.updatePosition(entity.getX(), entity.getY() + 0.6D, entity.getZ());
            this.getWorld().setBlockState(entity.getBlockPos(), blockState.with(Properties.OCCUPIED, false));
            this.discard();
        }
    }
}
