package com.teamabode.verdance.common.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.PushReaction;
import org.jetbrains.annotations.NotNull;

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

    public @NotNull PushReaction getPistonPushReaction() {
        return PushReaction.IGNORE;
    }

    protected boolean canAddPassenger(Entity entity) {
        return true;
    }

    protected boolean couldAcceptPassenger() {
        return true;
    }


    //To prevent removePassenger sometimes not removing the entity when spamming riding and not riding the cushion block
    @Override
    public void tick() {
        super.tick();
        if (this.getPassengers().isEmpty()) {
            this.discard();
        }
    }

    @Override
    protected void removePassenger(Entity entity) {
        super.removePassenger(entity);
        if (!this.isRemoved() && !entity.level().isClientSide) {
            entity.absMoveTo(entity.getX(), entity.getY() + 0.6D, entity.getZ());
            this.discard();
        }
    }
}
