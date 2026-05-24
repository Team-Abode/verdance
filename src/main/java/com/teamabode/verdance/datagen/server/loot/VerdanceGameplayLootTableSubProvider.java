package com.teamabode.verdance.datagen.server.loot;

import com.teamabode.verdance.core.registry.VerdanceLootTables;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.function.BiConsumer;

public class VerdanceGameplayLootTableSubProvider implements LootTableSubProvider {
    private final HolderLookup.Provider registries;

    public VerdanceGameplayLootTableSubProvider(HolderLookup.Provider registries) {
        this.registries = registries;
    }

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> exporter) {
        exporter.accept(VerdanceLootTables.GAMEPLAY_SILK_COCOON_WOBBLE, this.createGameplaySilkCocoonWobble());
    }

    private LootTable.Builder createGameplaySilkCocoonWobble() {
        var builder = LootTable.lootTable();
        var pool = LootPool.lootPool();

        var item = LootItem.lootTableItem(Items.STRING);
        item.apply(
                SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f), false)
        );
        pool.setRolls(ConstantValue.exactly(1.0f));
        pool.setBonusRolls(ConstantValue.exactly(0.0f));
        pool.add(item);
        pool.when(LootItemRandomChanceCondition.randomChance(0.33f));

        return builder.setParamSet(LootContextParamSets.CHEST).withPool(pool);
    }
}
