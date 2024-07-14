package com.teamabode.verdance.datagen.server;

import com.teamabode.verdance.core.misc.VerdanceBlockFamilies;
import com.teamabode.verdance.core.registry.VerdanceBlocks;
import com.teamabode.verdance.core.registry.VerdanceItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.IntRange;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.LimitCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.concurrent.CompletableFuture;

public class VerdanceBlockLootTableProvider extends FabricBlockLootTableProvider {
    private static final float[] NORMAL_LEAVES_STICK_CHANCES = {0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F};

    public VerdanceBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    public void generate() {
        mulberry();
        stucco();
        cantaloupe();
        add(VerdanceBlocks.SILKWORM_EGGS, this::createSilkTouchOnlyTable);
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
        add(VerdanceBlocks.MULBERRY_LEAVES, this::createMulberryLeaves);
        add(VerdanceBlocks.FLOWERING_MULBERRY_LEAVES, this::createFloweringMulberryLeaves);
        dropSelf(VerdanceBlocks.MULBERRY_SAPLING);
        dropPottedContents(VerdanceBlocks.POTTED_MULBERRY_SAPLING);
        dropSelf(VerdanceBlocks.MULBERRY_SIGN);
        dropSelf(VerdanceBlocks.MULBERRY_HANGING_SIGN);
        add(VerdanceBlocks.SILK_COCOON, this.createSingleItemTable(Items.STRING, UniformGenerator.between(5.0f, 6.0f)));
    }

    private LootTable.Builder createMulberryLeaves(Block leafBlock) {
        HolderLookup.RegistryLookup<Enchantment> registryLookup = registries.lookupOrThrow(Registries.ENCHANTMENT);
        var lootItem = LootItem.lootTableItem(Items.STICK);

        return createSilkTouchOrShearsDispatchTable(
                leafBlock,
                this.applyExplosionCondition(leafBlock, lootItem).when(BonusLevelTableCondition.bonusLevelFlatChance(registryLookup.getOrThrow(Enchantments.FORTUNE), NORMAL_LEAVES_STICK_CHANCES))
        );
    }

    private LootTable.Builder createFloweringMulberryLeaves(Block leafBlock) {
        return createMulberryLeaves(leafBlock).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0f))
                .when(this.doesNotHaveShearsOrSilkTouch())
                .add(this.applyExplosionCondition(leafBlock, LootItem.lootTableItem(VerdanceItems.MULBERRY).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f))))));
    }

    private void stucco() {
        addStucco(VerdanceBlockFamilies.WHITE_STUCCO);
        addStucco(VerdanceBlockFamilies.LIGHT_GRAY_STUCCO);
        addStucco(VerdanceBlockFamilies.GRAY_STUCCO);
        addStucco(VerdanceBlockFamilies.BLACK_STUCCO);
        addStucco(VerdanceBlockFamilies.BROWN_STUCCO);
        addStucco(VerdanceBlockFamilies.RED_STUCCO);
        addStucco(VerdanceBlockFamilies.ORANGE_STUCCO);
        addStucco(VerdanceBlockFamilies.YELLOW_STUCCO);
        addStucco(VerdanceBlockFamilies.LIME_STUCCO);
        addStucco(VerdanceBlockFamilies.GREEN_STUCCO);
        addStucco(VerdanceBlockFamilies.CYAN_STUCCO);
        addStucco(VerdanceBlockFamilies.LIGHT_BLUE_STUCCO);
        addStucco(VerdanceBlockFamilies.BLUE_STUCCO);
        addStucco(VerdanceBlockFamilies.PURPLE_STUCCO);
        addStucco(VerdanceBlockFamilies.MAGENTA_STUCCO);
        addStucco(VerdanceBlockFamilies.PINK_STUCCO);
    }

    private void addStucco(BlockFamily stuccoFamily) {
        dropSelf(stuccoFamily.getBaseBlock());
        dropSelf(stuccoFamily.get(BlockFamily.Variant.STAIRS));
        add(stuccoFamily.get(BlockFamily.Variant.SLAB), this::createSlabItemTable);
        dropSelf(stuccoFamily.get(BlockFamily.Variant.WALL));
    }

    private void cantaloupe() {
        add(VerdanceBlocks.CANTALOUPE, block -> {
            HolderLookup.RegistryLookup<Enchantment> registryLookup = registries.lookupOrThrow(Registries.ENCHANTMENT);
            var lootItem = LootItem.lootTableItem(VerdanceItems.CANTALOUPE_SLICE).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0f, 4.0f))).apply(ApplyBonusCount.addUniformBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE))).apply(LimitCount.limitCount(IntRange.upperBound(4)));
            return createSilkTouchDispatchTable(block, this.applyExplosionDecay(block, lootItem));
        });
        add(VerdanceBlocks.CANTALOUPE_STEM, block -> this.createStemDrops(block, VerdanceItems.CANTALOUPE_SEEDS));
        add(VerdanceBlocks.ATTACHED_CANTALOUPE_STEM, block -> this.createAttachedStemDrops(block, VerdanceItems.CANTALOUPE_SEEDS));
    }
}
