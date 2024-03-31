package com.teamabode.verdance.core.data.server;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.core.registry.VerdanceItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLootTableProvider;
import net.fabricmc.fabric.impl.datagen.loot.FabricLootTableProviderImpl;
import net.minecraft.data.CachedOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class VerdanceArchaeologyLootTableProvider implements FabricLootTableProvider {
    private final FabricDataOutput output;

    public VerdanceArchaeologyLootTableProvider(FabricDataOutput output) {
        this.output = output;
    }

    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> exporter) {
        LootTable.Builder commonTable = lootTableBuilder(
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
        LootTable.Builder commonFarmTable = lootTableBuilder(
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
        LootTable.Builder rareTable = lootTableBuilder(
                VerdanceItems.DISC_FRAGMENT_RANGE,
                VerdanceItems.DISC_FRAGMENT_RANGE,
                Items.BURN_POTTERY_SHERD,
                Items.DANGER_POTTERY_SHERD,
                Items.SHEAF_POTTERY_SHERD,
                Items.DUNE_ARMOR_TRIM_SMITHING_TEMPLATE,
                Items.RAW_GOLD
        );
        addArchaeologyLoot("ghost_town_common", commonTable, exporter);
        addArchaeologyLoot("ghost_town_farm", commonFarmTable, exporter);
        addArchaeologyLoot("ghost_town_rare", rareTable, exporter);
    }

    private static void addArchaeologyLoot(String name, LootTable.Builder lootTable, BiConsumer<ResourceLocation, LootTable.Builder> exporter) {
        ResourceLocation location = new ResourceLocation(Verdance.MOD_ID, "archaeology/" + name);
        lootTable.setRandomSequence(location);
        exporter.accept(location, lootTable);
    }

    private static LootTable.Builder lootTableBuilder(ItemLike... items) {
        LootTable.Builder tableBuilder = LootTable.lootTable();
        LootPool.Builder poolBuilder = LootPool.lootPool().setRolls(ConstantValue.exactly(1)).setBonusRolls(ConstantValue.exactly(1));

        for (ItemLike item : items) {
            poolBuilder.add(LootItem.lootTableItem(item));
        }
        return tableBuilder.withPool(poolBuilder);
    }

    @SuppressWarnings("all")
    public CompletableFuture<?> run(CachedOutput writer) {
        return FabricLootTableProviderImpl.run(writer, this, LootContextParamSets.ARCHAEOLOGY, output);
    }

    public String getName() {
        return "Archaeology Loot Tables";
    }
}
