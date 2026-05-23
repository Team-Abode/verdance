package com.teamabode.verdance.core.event;

import com.teamabode.verdance.core.registry.VerdanceBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;

@EventBusSubscriber
public class BlockEntityEvents {

    @SubscribeEvent
    private static void onAddBlocks(BlockEntityTypeAddBlocksEvent event) {
        event.modify(BlockEntityType.SIGN, VerdanceBlocks.MULBERRY_SIGN.get(), VerdanceBlocks.MULBERRY_WALL_SIGN.get());
        event.modify(BlockEntityType.HANGING_SIGN, VerdanceBlocks.MULBERRY_HANGING_SIGN.get(), VerdanceBlocks.MULBERRY_WALL_HANGING_SIGN.get());
    }
}
