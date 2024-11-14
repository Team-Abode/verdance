package com.teamabode.verdance.datagen.server;

import com.teamabode.verdance.core.registry.VerdanceBlocks;
import com.teamabode.verdance.core.registry.VerdanceItems;
import com.teamabode.verdance.core.registry.VerdanceLootTables;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class VerdanceArchaeologyLootTableProvider extends SimpleFabricLootTableProvider {
    public VerdanceArchaeologyLootTableProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup, LootContextTypes.ARCHAEOLOGY);
    }

    @Override
    public void accept(BiConsumer<RegistryKey<LootTable>, LootTable.Builder> exporter) {
        ArchaeologyLootBuilder.create(VerdanceLootTables.ARCHAEOLOGY_TOWN_RUINS_COMMON)
                .add(Items.CLAY, 2)
                .add(Items.WHITE_DYE, 2)
                .add(Items.GREEN_DYE, 2)
                .add(Items.CYAN_DYE, 2)
                .add(Items.ORANGE_DYE, 2)
                .add(Items.YELLOW_DYE, 2)
                .add(Items.DEAD_BUSH, 2)
                .add(Items.STICK)
                .add(Items.WOODEN_HOE)
                .add(Items.CANDLE)
                .add(Items.BLACK_CANDLE)
                .add(Items.PURPLE_CANDLE)
                .add(Items.GREEN_CANDLE)
                .add(Items.YELLOW_CANDLE)
                .add(Items.BLUE_CANDLE)
                .add(VerdanceBlocks.WHITE_STUCCO_WALL)
                .add(VerdanceBlocks.YELLOW_STUCCO_WALL)
                .add(VerdanceBlocks.CYAN_STUCCO_WALL)
                .add(VerdanceBlocks.GREEN_STUCCO_WALL)
                .add(VerdanceBlocks.BROWN_STUCCO_WALL)
                .add(Items.OAK_HANGING_SIGN)
                .add(Items.GLASS_BOTTLE)
                .add(Items.WHEAT_SEEDS)
                .add(Items.WHEAT)
                .add(VerdanceItems.CANTALOUPE_SEEDS)
                .add(Items.ARMADILLO_SCUTE)
                .add(Items.BUCKET)
                .add(Items.LEAD)
                .add(VerdanceItems.DISC_FRAGMENT_RANGE)
                .export(exporter);

        ArchaeologyLootBuilder.create(VerdanceLootTables.ARCHAEOLOGY_TOWN_RUINS_TREASURE)
                .add(Items.EMERALD, 3)
                .add(Items.COAL, 3)
                .add(Items.GOLD_INGOT, 3)
                .add(VerdanceItems.COMMUNITY_ARMOR_TRIM_SMITHING_TEMPLATE)
                .add(VerdanceItems.ABODE_POTTERY_SHERD)
                .add(VerdanceItems.FRILLS_POTTERY_SHERD)
                .add(VerdanceItems.PITCH_POTTERY_SHERD)
                .add(VerdanceItems.PRICKLE_POTTERY_SHERD)
                .add(VerdanceItems.SPIRIT_POTTERY_SHERD)
                .add(VerdanceItems.TRAP_POTTERY_SHERD)
                .export(exporter);
    }

    public static class ArchaeologyLootBuilder {
        private final RegistryKey<LootTable> key;
        private final LootPool.Builder pool = LootPool.builder();

        private ArchaeologyLootBuilder(RegistryKey<LootTable> key) {
            this.key = key;
            pool.rolls(ConstantLootNumberProvider.create(1.0f));
            pool.bonusRolls(ConstantLootNumberProvider.create(0.0f));
        }

        public static ArchaeologyLootBuilder create(RegistryKey<LootTable> lootTable) {
            return new ArchaeologyLootBuilder(lootTable);
        }

        public ArchaeologyLootBuilder add(ItemConvertible item) {
            pool.with(ItemEntry.builder(item));
            return this;
        }

        public ArchaeologyLootBuilder add(ItemConvertible item, int weight) {
            pool.with(ItemEntry.builder(item).weight(weight));
            return this;
        }

        public void export(BiConsumer<RegistryKey<LootTable>, LootTable.Builder> exporter) {
            LootTable.Builder lootTable = LootTable.builder();
            lootTable.pool(pool);
            lootTable.type(LootContextTypes.ARCHAEOLOGY);
            lootTable.randomSequenceId(key.getValue());
            exporter.accept(key, lootTable);
        }
    }
}
