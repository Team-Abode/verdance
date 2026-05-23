package com.teamabode.verdance.datagen.server.loot;

import com.teamabode.verdance.core.registry.VerdanceBlocks;
import com.teamabode.verdance.core.registry.VerdanceItems;
import com.teamabode.verdance.core.registry.VerdanceLootTables;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.function.BiConsumer;

public class VerdanceArchaeologyLootTableSubProvider implements LootTableSubProvider {
    private final HolderLookup.Provider registries;

    public VerdanceArchaeologyLootTableSubProvider(HolderLookup.Provider registries) {
        this.registries = registries;
    }

    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> exporter) {
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
                .add(VerdanceBlocks.WHITE_STUCCO_WALL.get())
                .add(VerdanceBlocks.YELLOW_STUCCO_WALL.get())
                .add(VerdanceBlocks.CYAN_STUCCO_WALL.get())
                .add(VerdanceBlocks.GREEN_STUCCO_WALL.get())
                .add(VerdanceBlocks.BROWN_STUCCO_WALL.get())
                .add(Items.OAK_HANGING_SIGN)
                .add(Items.GLASS_BOTTLE)
                .add(Items.WHEAT_SEEDS)
                .add(Items.WHEAT)
                .add(VerdanceItems.CANTALOUPE_SEEDS.get())
                .add(Items.ARMADILLO_SCUTE)
                .add(Items.BUCKET)
                .add(Items.LEAD)
                .add(VerdanceItems.DISC_FRAGMENT_RANGE.get())
                .export(exporter);

        ArchaeologyLootBuilder.create(VerdanceLootTables.ARCHAEOLOGY_TOWN_RUINS_TREASURE)
                .add(Items.EMERALD, 3)
                .add(Items.COAL, 3)
                .add(Items.GOLD_INGOT, 3)
                .add(VerdanceItems.HERITAGE_ARMOR_TRIM_SMITHING_TEMPLATE.get())
                .add(VerdanceItems.ABODE_POTTERY_SHERD.get())
                .add(VerdanceItems.FRILLS_POTTERY_SHERD.get())
                .add(VerdanceItems.PITCH_POTTERY_SHERD.get())
                .add(VerdanceItems.PRICKLE_POTTERY_SHERD.get())
                .add(VerdanceItems.SPIRIT_POTTERY_SHERD.get())
                .add(VerdanceItems.TRAP_POTTERY_SHERD.get())
                .export(exporter);
    }

    public static class ArchaeologyLootBuilder {
        private final ResourceKey<LootTable> key;
        private final LootPool.Builder pool = LootPool.lootPool();

        private ArchaeologyLootBuilder(ResourceKey<LootTable> key) {
            this.key = key;
            pool.setRolls(ConstantValue.exactly(1.0f));
            pool.setBonusRolls(ConstantValue.exactly(0.0f));
        }

        public static ArchaeologyLootBuilder create(ResourceKey<LootTable> lootTable) {
            return new ArchaeologyLootBuilder(lootTable);
        }

        public ArchaeologyLootBuilder add(ItemLike item) {
            pool.add(LootItem.lootTableItem(item));
            return this;
        }

        public ArchaeologyLootBuilder add(ItemLike item, int weight) {
            pool.add(LootItem.lootTableItem(item).setWeight(weight));
            return this;
        }

        public void export(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> exporter) {
            LootTable.Builder lootTable = LootTable.lootTable();
            lootTable.withPool(pool);
            lootTable.setParamSet(LootContextParamSets.ARCHAEOLOGY);
            lootTable.setRandomSequence(key.location());
            exporter.accept(key, lootTable);
        }
    }
}
