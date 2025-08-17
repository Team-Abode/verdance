package com.teamabode.verdance.common.entity;

import com.teamabode.verdance.common.block.CushionBlock;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.PushReaction;

public class CushionEntity extends Entity {

    public CushionEntity(EntityType<?> entityType, Level level) {
        super(entityType, level);
        this.noPhysics = true;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {

    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compoundTag) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compoundTag) {

    }

    @Override
    public void tick() {
        super.tick();

        List<Entity> passengers = this.getPassengers();
        Level world = this.level();

        if (passengers.isEmpty() && !world.isClientSide()) {
            BlockPos pos = this.blockPosition();
            BlockState state = world.getBlockState(pos);

            if (state.hasProperty(CushionBlock.OCCUPIED)) {
                world.setBlockAndUpdate(pos, state.setValue(BlockStateProperties.OCCUPIED, false));
            }
            this.removeAfterChangingDimensions();
        }
    }

    public @NotNull PushReaction getPistonPushReaction() {
        return PushReaction.IGNORE;
    }

    protected boolean canAddPassenger(Entity entity) {
        return true;
    }

    protected boolean couldAcceptPassenger() {
        return true;
    }
}
