package com.teamabode.verdance.core.integration.farmersdelight.block.entity;

import com.teamabode.verdance.core.integration.farmersdelight.block.CompatCabinetBlock;
import com.teamabode.verdance.core.integration.farmersdelight.registry.FDCompatBlockEntityTypes;
import com.teamabode.verdance.core.integration.farmersdelight.registry.FDCompatSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.UnknownNullability;

public class CompatCabinetBlockEntity extends RandomizableContainerBlockEntity {
    private NonNullList<ItemStack> contents;
    private final ContainerOpenersCounter openersCounter;

    public CompatCabinetBlockEntity(BlockPos pos, BlockState state) {
        super(FDCompatBlockEntityTypes.CABINET, pos, state);
        this.contents = NonNullList.withSize(27, ItemStack.EMPTY);
        this.openersCounter = new ContainerOpenersCounter() {
            protected void onOpen(Level level, BlockPos pos, BlockState state) {
                CompatCabinetBlockEntity.this.playSound(state, FDCompatSoundEvents.BLOCK_CABINET_OPEN);
                CompatCabinetBlockEntity.this.updateBlockState(state, true);
            }

            protected void onClose(Level level, BlockPos pos, BlockState state) {
                CompatCabinetBlockEntity.this.playSound(state, FDCompatSoundEvents.BLOCK_CABINET_CLOSE);
                CompatCabinetBlockEntity.this.updateBlockState(state, false);
            }

            protected void openerCountChanged(Level level, BlockPos pos, BlockState sta, int arg1, int arg2) {
            }

            protected boolean isOwnContainer(Player p_155060_) {
                if (p_155060_.containerMenu instanceof ChestMenu) {
                    Container container = ((ChestMenu)p_155060_.containerMenu).getContainer();
                    return container == CompatCabinetBlockEntity.this;
                } else {
                    return false;
                }
            }
        };
    }

    public void saveAdditional(CompoundTag compound, HolderLookup.Provider registries) {
        super.saveAdditional(compound, registries);
        if (!this.trySaveLootTable(compound)) {
            ContainerHelper.saveAllItems(compound, this.contents, registries);
        }

    }

    public void loadAdditional(CompoundTag compound, HolderLookup.Provider registries) {
        super.loadAdditional(compound, registries);
        this.contents = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(compound)) {
            ContainerHelper.loadAllItems(compound, this.contents, registries);
        }

    }

    public int getContainerSize() {
        return 27;
    }

    protected NonNullList<ItemStack> getItems() {
        return this.contents;
    }

    protected void setItems(NonNullList<ItemStack> itemsIn) {
        this.contents = itemsIn;
    }

    protected Component getDefaultName() {
        return Component.translatable("farmersdelight.container.cabinet");
    }

    protected AbstractContainerMenu createMenu(int id, Inventory player) {
        return ChestMenu.threeRows(id, player, this);
    }

    public void startOpen(Player pPlayer) {
        if (this.level != null && !this.remove && !pPlayer.isSpectator()) {
            this.openersCounter.incrementOpeners(pPlayer, this.level, this.getBlockPos(), this.getBlockState());
        }

    }

    public void stopOpen(Player pPlayer) {
        if (this.level != null && !this.remove && !pPlayer.isSpectator()) {
            this.openersCounter.decrementOpeners(pPlayer, this.level, this.getBlockPos(), this.getBlockState());
        }

    }

    public void recheckOpen() {
        if (this.level != null && !this.remove) {
            this.openersCounter.recheckOpeners(this.level, this.getBlockPos(), this.getBlockState());
        }

    }

    void updateBlockState(BlockState state, boolean open) {
        if (this.level != null) {
            this.level.setBlock(this.getBlockPos(), state.setValue(CompatCabinetBlock.OPEN, open), 3);
        }
    }

    private void playSound(BlockState state, SoundEvent sound) {
        if (this.level != null) {
            Vec3i cabinetFacingVector = state.getValue(CompatCabinetBlock.FACING).getNormal();
            double x = (double)this.worldPosition.getX() + 0.5 + (double)cabinetFacingVector.getX() / 2.0;
            double y = (double)this.worldPosition.getY() + 0.5 + (double)cabinetFacingVector.getY() / 2.0;
            double z = (double)this.worldPosition.getZ() + 0.5 + (double)cabinetFacingVector.getZ() / 2.0;
            this.level.playSound(null, x, y, z, sound, SoundSource.BLOCKS, 0.5F, this.level.random.nextFloat() * 0.1F + 0.9F);
        }
    }

    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        return new CompoundTag();
    }

    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag compoundTag) {
    }
}
