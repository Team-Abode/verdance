package com.teamabode.verdance.common.block.entity;

import com.teamabode.verdance.common.entity.silkmoth.SilkMoth;
import com.teamabode.verdance.core.registry.VerdanceBlockEntityTypes;
import com.teamabode.verdance.core.registry.VerdanceEntityTypes;
import com.teamabode.verdance.core.registry.VerdanceLootTables;
import com.teamabode.verdance.core.registry.VerdanceSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;

public class SilkCocoonBlockEntity extends BlockEntity {
    private int ticks = 0;
    public int wobbleTicks = 0;
    public boolean wobbling = false;

    public SilkCocoonBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(VerdanceBlockEntityTypes.SILK_COCOON.get(), blockPos, blockState);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, SilkCocoonBlockEntity cocoon) {
        int ticks = cocoon.getTicks();

        // Handles wobbling
        if (cocoon.wobbling) {
            cocoon.wobbleTicks++;
        }
        if (cocoon.wobbleTicks >= 10) {
            cocoon.wobbling = false;
            cocoon.wobbleTicks = 0;
        }

        if (ticks >= 4800) {
            SilkMoth silkMoth = new SilkMoth(VerdanceEntityTypes.SILK_MOTH.get(), level);
            silkMoth.setPos(pos.getCenter());
            silkMoth.setYHeadRot(state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot());
            silkMoth.setYRot(state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot());
            silkMoth.takeOff();
            silkMoth.setPersistenceRequired();
            level.addFreshEntity(silkMoth);

            level.playSound(null, pos, VerdanceSoundEvents.ENTITY_SILK_MOTH_EMERGE.get(), SoundSource.NEUTRAL);
            level.destroyBlock(pos, true);
        }
        else if (ticks >= 4400 && ticks % 100 == 0 || ticks >= 3600 && ticks % 200 == 0) {
            cocoon.wobble(level);
        }
        cocoon.setTicks(ticks + 1);
    }

    @Override
    protected void saveAdditional(CompoundTag compound, HolderLookup.Provider provider) {
        compound.putInt("ticks", this.getTicks());
    }

    @Override
    protected void loadAdditional(CompoundTag compound, HolderLookup.Provider provider) {
        this.setTicks(compound.getInt("ticks"));
    }

    public void wobble(Level level) {
        if (this.wobbling) {
            this.wobbleTicks = 0;
        }
        else {
            this.wobbling = true;
        }
        BlockPos pos = this.getBlockPos();

        if (!level.isClientSide) {
            this.dropLoot((ServerLevel) level, pos);
        }
        level.playSound(null, pos, VerdanceSoundEvents.BLOCK_SILK_COCOON_WOBBLE.get(), SoundSource.BLOCKS);
    }

    public void dropLoot(ServerLevel level, BlockPos origin) {
        LootTable loot = level.getServer().reloadableRegistries().getLootTable(VerdanceLootTables.GAMEPLAY_SILK_COCOON_WOBBLE);
        LootParams params = new LootParams.Builder(level).create(LootContextParamSets.EMPTY);
        List<ItemStack> stacks = loot.getRandomItems(params);

        for (ItemStack stack : stacks) {
            ItemEntity itemEntity = new ItemEntity(
                    level,
                    origin.getX() + 0.5d,
                    origin.getY() - 0.5d,
                    origin.getZ() + 0.5d,
                    stack
            );
            itemEntity.setDefaultPickUpDelay();

            level.addFreshEntity(itemEntity);
        }
    }

    public void setTicks(int ticks) {
        this.ticks = ticks;
        this.setChanged();
    }

    public int getTicks() {
        return this.ticks;
    }
}
