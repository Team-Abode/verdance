package com.teamabode.verdance.core.data.server;

import com.teamabode.verdance.core.registry.VerdanceBlocks;
import com.teamabode.verdance.core.registry.VerdanceItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.IntRange;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.LimitCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.function.BiConsumer;

public class VerdanceBlockLootTableProvider extends FabricBlockLootTableProvider {

    public VerdanceBlockLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    public void generate() {
        mulberry();
        shrub();
        stucco();
        cantaloupe();
    }

    private void shrub() {
        this.add(VerdanceBlocks.SHRUB, block -> {
            var lootItem = LootItem.lootTableItem(Items.STICK).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0f, 2.0f)));

            return createShearsDispatchTable(block, this.applyExplosionDecay(block, lootItem));
        });
    }

    private void mulberry() {
        dropSelf(VerdanceBlocks.MULBERRY_LOG);
        dropSelf(VerdanceBlocks.MULBERRY_WOOD);
        dropSelf(VerdanceBlocks.STRIPPED_MULBERRY_LOG);
        dropSelf(VerdanceBlocks.STRIPPED_MULBERRY_WOOD);
        dropSelf(VerdanceBlocks.MULBERRY_PLANKS);
        dropSelf(VerdanceBlocks.MULBERRY_STAIRS);
        add(VerdanceBlocks.MULBERRY_SLAB, this::createSlabItemTable);
        dropSelf(VerdanceBlocks.MULBERRY_FENCE);
        dropSelf(VerdanceBlocks.MULBERRY_FENCE_GATE);
        add(VerdanceBlocks.MULBERRY_DOOR, this::createDoorTable);
        dropSelf(VerdanceBlocks.MULBERRY_TRAPDOOR);
        dropSelf(VerdanceBlocks.MULBERRY_PRESSURE_PLATE);
        dropSelf(VerdanceBlocks.MULBERRY_BUTTON);
        dropSelf(VerdanceBlocks.MULBERRY_SIGN);
        dropSelf(VerdanceBlocks.MULBERRY_HANGING_SIGN);
    }

    private void stucco() {
        dropSelf(VerdanceBlocks.WHITE_STUCCO);
        dropSelf(VerdanceBlocks.WHITE_STUCCO_STAIRS);
        add(VerdanceBlocks.WHITE_STUCCO_SLAB, this::createSlabItemTable);
        dropSelf(VerdanceBlocks.WHITE_STUCCO_WALL);
    }

    private void cantaloupe() {
        add(VerdanceBlocks.CANTALOUPE, block -> {
            var lootItem = LootItem.lootTableItem(VerdanceItems.CANTALOUPE_SLICE).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0f, 4.0f))).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)).apply(LimitCount.limitCount(IntRange.upperBound(4)));
            return createSilkTouchDispatchTable(block, this.applyExplosionDecay(block, lootItem));
        });
        add(VerdanceBlocks.CANTALOUPE_STEM, block -> this.createStemDrops(block, VerdanceItems.CANTALOUPE_SEEDS));
        add(VerdanceBlocks.ATTACHED_CANTALOUPE_STEM, block -> this.createAttachedStemDrops(block, VerdanceItems.CANTALOUPE_SEEDS));
    }

    public void accept(BiConsumer<ResourceLocation, LootTable.Builder> consumer) {
        super.generate(consumer);
    }
}
