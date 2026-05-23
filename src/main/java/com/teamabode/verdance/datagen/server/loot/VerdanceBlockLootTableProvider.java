package com.teamabode.verdance.datagen.server.loot;

import com.teamabode.verdance.core.misc.VerdanceBlockFamilies;
import com.teamabode.verdance.core.registry.VerdanceBlocks;
import com.teamabode.verdance.core.registry.VerdanceItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
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
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.HashSet;
import java.util.Set;

public class VerdanceBlockLootTableProvider extends BlockLootSubProvider {
    private static final float[] NORMAL_LEAVES_STICK_CHANCES = {0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F};

    protected VerdanceBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.DEFAULT_FLAGS, registries);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return VerdanceBlocks.REGISTRY
                .getEntries()
                .stream()
                .map(e -> (Block) e.value())
                .toList();
    }

    @Override
    public void generate() {
        mulberry();
        stucco();
        cantaloupe();
        cushions();
        shrubs();
        add(VerdanceBlocks.SILKWORM_EGGS.get(), this::createSilkTouchOnlyTable);
    }

    private void mulberry() {
        dropSelf(VerdanceBlocks.MULBERRY_LOG.get());
        dropSelf(VerdanceBlocks.MULBERRY_WOOD.get());
        dropSelf(VerdanceBlocks.STRIPPED_MULBERRY_LOG.get());
        dropSelf(VerdanceBlocks.STRIPPED_MULBERRY_WOOD.get());
        dropSelf(VerdanceBlocks.MULBERRY_PLANKS.get());
        dropSelf(VerdanceBlocks.MULBERRY_STAIRS.get());
        add(VerdanceBlocks.MULBERRY_SLAB.get(), this::createSlabItemTable);
        dropSelf(VerdanceBlocks.MULBERRY_FENCE.get());
        dropSelf(VerdanceBlocks.MULBERRY_FENCE_GATE.get());
        add(VerdanceBlocks.MULBERRY_DOOR.get(), this::createDoorTable);
        dropSelf(VerdanceBlocks.MULBERRY_TRAPDOOR.get());
        dropSelf(VerdanceBlocks.MULBERRY_PRESSURE_PLATE.get());
        dropSelf(VerdanceBlocks.MULBERRY_BUTTON.get());
        add(VerdanceBlocks.MULBERRY_LEAVES.get(), this::createMulberryLeaves);
        add(VerdanceBlocks.FLOWERING_MULBERRY_LEAVES.get(), this::createFloweringMulberryLeaves);
        dropSelf(VerdanceBlocks.MULBERRY_SAPLING.get());
        dropPottedContents(VerdanceBlocks.POTTED_MULBERRY_SAPLING.get());
        dropSelf(VerdanceBlocks.MULBERRY_SIGN.get());
        dropSelf(VerdanceBlocks.MULBERRY_HANGING_SIGN.get());
        add(VerdanceBlocks.SILK_COCOON.get(), this.createSingleItemTable(Items.STRING, UniformGenerator.between(5.0f, 6.0f)));
        dropSelf(VerdanceBlocks.VIOLET.get());
        dropPottedContents(VerdanceBlocks.POTTED_VIOLET.get());
    }

    private void cushions() {
        dropSelf(VerdanceBlocks.WHITE_CUSHION.get());
        dropSelf(VerdanceBlocks.LIGHT_GRAY_CUSHION.get());
        dropSelf(VerdanceBlocks.GRAY_CUSHION.get());
        dropSelf(VerdanceBlocks.BLACK_CUSHION.get());
        dropSelf(VerdanceBlocks.BROWN_CUSHION.get());
        dropSelf(VerdanceBlocks.RED_CUSHION.get());
        dropSelf(VerdanceBlocks.ORANGE_CUSHION.get());
        dropSelf(VerdanceBlocks.YELLOW_CUSHION.get());
        dropSelf(VerdanceBlocks.LIME_CUSHION.get());
        dropSelf(VerdanceBlocks.GREEN_CUSHION.get());
        dropSelf(VerdanceBlocks.CYAN_CUSHION.get());
        dropSelf(VerdanceBlocks.LIGHT_BLUE_CUSHION.get());
        dropSelf(VerdanceBlocks.BLUE_CUSHION.get());
        dropSelf(VerdanceBlocks.PURPLE_CUSHION.get());
        dropSelf(VerdanceBlocks.MAGENTA_CUSHION.get());
        dropSelf(VerdanceBlocks.PINK_CUSHION.get());
    }

    private void shrubs() {
        this.add(VerdanceBlocks.SHRUB.get(), block -> createShearsDispatchTable(
                block, this.applyExplosionDecay(block, LootItem.lootTableItem(Items.STICK).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0f, 2.0f))))
        ));
        this.add(VerdanceBlocks.YELLOW_FLOWERING_SHRUB.get(), block -> createShearsDispatchTable(
                block, this.applyExplosionDecay(block, LootItem.lootTableItem(Items.STICK).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0f, 2.0f))))
        ));
        this.add(VerdanceBlocks.PINK_FLOWERING_SHRUB.get(), block -> createShearsDispatchTable(
                block, this.applyExplosionDecay(block, LootItem.lootTableItem(Items.STICK).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0f, 2.0f))))
        ));
        this.dropPottedContents(VerdanceBlocks.POTTED_SHRUB.get());
        this.dropPottedContents(VerdanceBlocks.POTTED_YELLOW_FLOWERING_SHRUB.get());
        this.dropPottedContents(VerdanceBlocks.POTTED_PINK_FLOWERING_SHRUB.get());
    }

    private LootTable.Builder createMulberryLeaves(Block leafBlock) {
        var enchantments = registries.lookupOrThrow(Registries.ENCHANTMENT);
        var lootItem = LootItem.lootTableItem(Items.STICK);

        return createSilkTouchOrShearsDispatchTable(
                leafBlock,
                this.applyExplosionCondition(leafBlock, lootItem).when(BonusLevelTableCondition.bonusLevelFlatChance(enchantments.getOrThrow(Enchantments.FORTUNE), NORMAL_LEAVES_STICK_CHANCES))
        );
    }

    private LootTable.Builder createFloweringMulberryLeaves(Block leafBlock) {
        return createMulberryLeaves(leafBlock).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0f))
                .when(this.doesNotHaveShearsOrSilkTouch())
                .add(this.applyExplosionCondition(leafBlock, LootItem.lootTableItem(VerdanceItems.MULBERRY.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f))))));
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
        add(VerdanceBlocks.CANTALOUPE.get(), block -> {
            var enchantments = registries.lookupOrThrow(Registries.ENCHANTMENT);
            var lootItem = LootItem.lootTableItem(VerdanceItems.CANTALOUPE_SLICE.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0f, 4.0f))).apply(ApplyBonusCount.addUniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE))).apply(LimitCount.limitCount(IntRange.upperBound(4)));
            return createSilkTouchDispatchTable(block, this.applyExplosionDecay(block, lootItem));
        });
        add(VerdanceBlocks.CANTALOUPE_STEM.get(), block -> this.createStemDrops(block, VerdanceItems.CANTALOUPE_SEEDS.get()));
        add(VerdanceBlocks.ATTACHED_CANTALOUPE_STEM.get(), block -> this.createAttachedStemDrops(block, VerdanceItems.CANTALOUPE_SEEDS.get()));
    }
}
