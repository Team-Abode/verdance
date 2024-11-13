package com.teamabode.verdance.common.entity;

import com.teamabode.verdance.common.block.CushionBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CushionEntity extends Entity {

    public CushionEntity(EntityType<?> entityType, World level) {
        super(entityType, level);
        this.noClip = true;
    }

    @Override
    protected void initDataTracker() {

    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound compoundTag) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound compoundTag) {

    }

    @Override
    public void tick() {
        super.tick();

        List<Entity> passengers = this.getPassengerList();
        World world = this.getWorld();

        if (passengers.isEmpty() && !world.isClient()) {
            BlockPos pos = this.getBlockPos();
            BlockState state = world.getBlockState(pos);

            if (state.contains(CushionBlock.OCCUPIED)) {
                world.setBlockState(pos, state.with(Properties.OCCUPIED, false));
            }
            this.removeFromDimension();
        }
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
}
