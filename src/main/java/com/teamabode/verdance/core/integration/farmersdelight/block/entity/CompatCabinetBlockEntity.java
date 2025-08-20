package com.teamabode.verdance.core.integration.farmersdelight.block.entity;

import com.teamabode.verdance.core.integration.farmersdelight.block.CompatCabinetBlock;
import com.teamabode.verdance.core.integration.farmersdelight.registry.FDCompatBlockEntityTypes;
import com.teamabode.verdance.core.integration.farmersdelight.registry.FDCompatSoundEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.block.entity.ViewerCountManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.text.Text;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public class CompatCabinetBlockEntity extends LootableContainerBlockEntity {
    private DefaultedList<ItemStack> contents;
    private final ViewerCountManager openersCounter;

    public CompatCabinetBlockEntity(BlockPos pos, BlockState state) {
        super(FDCompatBlockEntityTypes.CABINET, pos, state);
        this.contents = DefaultedList.ofSize(27, ItemStack.EMPTY);
        this.openersCounter = new ViewerCountManager() {
            protected void onContainerOpen(World level, BlockPos pos, BlockState state) {
                CompatCabinetBlockEntity.this.playSound(state, FDCompatSoundEvents.BLOCK_CABINET_OPEN);
                CompatCabinetBlockEntity.this.updateBlockState(state, true);
            }

            protected void onContainerClose(World level, BlockPos pos, BlockState state) {
                CompatCabinetBlockEntity.this.playSound(state, FDCompatSoundEvents.BLOCK_CABINET_CLOSE);
                CompatCabinetBlockEntity.this.updateBlockState(state, false);
            }

            protected void onViewerCountUpdate(World level, BlockPos pos, BlockState sta, int arg1, int arg2) {
            }

            protected boolean isPlayerViewing(PlayerEntity player) {
                if (player.currentScreenHandler instanceof GenericContainerScreenHandler) {
                    Inventory container = ((GenericContainerScreenHandler)player.currentScreenHandler).getInventory();
                    return container == CompatCabinetBlockEntity.this;
                } else {
                    return false;
                }
            }
        };
    }

    @Override
    protected void writeData(WriteView view) {
        super.writeData(view);
        if (!this.writeLootTable(view)) {
            Inventories.writeData(view, this.contents);
        }
    }

    @Override
    protected void readData(ReadView view) {
        super.readData(view);

        this.contents = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);

        if (!this.readLootTable(view)) {
            Inventories.readData(view, this.contents);
        }
    }

    @Override
    public void onBlockReplaced(BlockPos pos, BlockState state) {
        BlockEntity tileEntity = this.world.getBlockEntity(pos);
        if (tileEntity instanceof Inventory) {
            ItemScatterer.spawn(this.world, pos, (Inventory)tileEntity);
        }

        super.onBlockReplaced(pos, state);
    }

    @Override
    public int size() {
        return 27;
    }

    @Override
    protected DefaultedList<ItemStack> getHeldStacks() {
        return this.contents;
    }

    @Override
    protected void setHeldStacks(DefaultedList<ItemStack> itemsIn) {
        this.contents = itemsIn;
    }

    @Override
    protected Text getContainerName() {
        return Text.translatable("farmersdelight.container.cabinet");
    }

    @Override
    protected ScreenHandler createScreenHandler(int id, PlayerInventory player) {
        return GenericContainerScreenHandler.createGeneric9x3(id, player, this);
    }

    @Override
    public void onOpen(PlayerEntity pPlayer) {
        if (this.world != null && !this.removed && !pPlayer.isSpectator()) {
            this.openersCounter.openContainer(pPlayer, this.world, this.getPos(), this.getCachedState());
        }

    }

    @Override
    public void onClose(PlayerEntity pPlayer) {
        if (this.world != null && !this.removed && !pPlayer.isSpectator()) {
            this.openersCounter.closeContainer(pPlayer, this.world, this.getPos(), this.getCachedState());
        }

    }

    public void recheckOpen() {
        if (this.world != null && !this.removed) {
            this.openersCounter.updateViewerCount(this.world, this.getPos(), this.getCachedState());
        }

    }

    void updateBlockState(BlockState state, boolean open) {
        if (this.world != null) {
            this.world.setBlockState(this.getPos(), state.with(CompatCabinetBlock.OPEN, open), 3);
        }
    }

    private void playSound(BlockState state, SoundEvent sound) {
        if (this.world != null) {
            Vec3i cabinetFacingVector = state.get(CompatCabinetBlock.FACING).getVector();
            double x = (double)this.pos.getX() + 0.5 + (double)cabinetFacingVector.getX() / 2.0;
            double y = (double)this.pos.getY() + 0.5 + (double)cabinetFacingVector.getY() / 2.0;
            double z = (double)this.pos.getZ() + 0.5 + (double)cabinetFacingVector.getZ() / 2.0;
            this.world.playSound(null, x, y, z, sound, SoundCategory.BLOCKS, 0.5F, this.world.random.nextFloat() * 0.1F + 0.9F);
        }
    }
}
