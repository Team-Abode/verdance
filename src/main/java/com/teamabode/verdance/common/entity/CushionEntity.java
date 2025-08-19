package com.teamabode.verdance.common.entity;

import com.teamabode.verdance.common.block.CushionBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Properties;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
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
    public void tick() {
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

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {

    }

    @Override
    protected void readCustomData(ReadView view) {

    }

    @Override
    protected void writeCustomData(WriteView view) {

    }

    @Override
    public boolean damage(ServerWorld world, DamageSource source, float amount) {
        return false;
    }

    public @NotNull PistonBehavior getPistonBehavior() {
        return PistonBehavior.IGNORE;
    }
}
