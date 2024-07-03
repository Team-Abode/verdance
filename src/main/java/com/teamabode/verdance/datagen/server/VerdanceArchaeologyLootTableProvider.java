package com.teamabode.verdance.datagen.server;

import com.teamabode.verdance.core.misc.key.VerdanceLootTables;
import com.teamabode.verdance.core.registry.VerdanceItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class VerdanceArchaeologyLootTableProvider extends SimpleFabricLootTableProvider {

    public VerdanceArchaeologyLootTableProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup, LootContextParamSets.ARCHAEOLOGY);
    }

    private static void addArchaeologyLoot(ResourceKey<LootTable> reference, LootTable.Builder lootTable, BiConsumer<ResourceKey<LootTable>, LootTable.Builder> exporter) {
        lootTable.setRandomSequence(reference.location());
        exporter.accept(reference, lootTable);
    }

    private static LootTable.Builder build(ItemLike... items) {
        LootTable.Builder tableBuilder = LootTable.lootTable();
        LootPool.Builder poolBuilder = LootPool.lootPool().setRolls(ConstantValue.exactly(1)).setBonusRolls(ConstantValue.exactly(1));

        for (ItemLike item : items) {
            poolBuilder.add(LootItem.lootTableItem(item));
        }
        return tableBuilder.withPool(poolBuilder);
    }

    @Override
    public String getName() {
        return "Verdance Archaeology Loot Tables";
    }

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> exporter) {
        LootTable.Builder commonTable = build(
                Items.CANDLE,
                Items.YELLOW_CANDLE,
                Items.BROWN_CANDLE,
                Items.FEATHER,
                Items.FLINT,
                Items.SUGAR,
                Items.CLAY,
                Items.CLAY_BALL,
                Items.WHITE_DYE,
                Items.BROWN_DYE,
                Items.WOODEN_SHOVEL,
                Items.GLASS_BOTTLE
        );
        LootTable.Builder commonFarmTable = build(
                Items.FEATHER,
                Items.FLINT,
                Items.SUGAR,
                Items.CLAY,
                Items.CLAY_BALL,
                Items.GREEN_DYE,
                Items.WOODEN_HOE,
                Items.SPRUCE_SAPLING,
                VerdanceItems.CANTALOUPE_SEEDS,
                Items.WHEAT,
                Items.COARSE_DIRT,
                Items.GLASS_BOTTLE
        );
        LootTable.Builder rareTable = build(
                VerdanceItems.DISC_FRAGMENT_RANGE,
                VerdanceItems.DISC_FRAGMENT_RANGE,
                Items.BURN_POTTERY_SHERD,
                Items.DANGER_POTTERY_SHERD,
                Items.SHEAF_POTTERY_SHERD,
                Items.DUNE_ARMOR_TRIM_SMITHING_TEMPLATE,
                Items.RAW_GOLD
        );
        addArchaeologyLoot(VerdanceLootTables.GHOST_TOWN_ARCHAEOLOGY_COMMON, commonTable, exporter);
        addArchaeologyLoot(VerdanceLootTables.GHOST_TOWN_ARCHAEOLOGY_FARM, commonFarmTable, exporter);
        addArchaeologyLoot(VerdanceLootTables.GHOST_TOWN_ARCHAEOLOGY_RARE, rareTable, exporter);
    }
}
