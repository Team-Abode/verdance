package com.teamabode.verdance.datagen.server;

import com.teamabode.verdance.core.misc.VerdanceBlockFamilies;
import com.teamabode.verdance.core.registry.VerdanceBlocks;
import com.teamabode.verdance.core.registry.VerdanceItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.TableBonusLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.LimitCountLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.operator.BoundedIntUnaryOperator;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import java.util.concurrent.CompletableFuture;

public class VerdanceBlockLootTableProvider extends FabricBlockLootTableProvider {
    private static final float[] NORMAL_LEAVES_STICK_CHANCES = {0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F};

    public VerdanceBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    public void generate() {
        mulberry();
        stucco();
        cantaloupe();
        cushions();
        shrubs();
        addDrop(VerdanceBlocks.SILKWORM_EGGS, this::dropsWithSilkTouch);
    }

    private void mulberry() {
        addDrop(VerdanceBlocks.MULBERRY_LOG);
        addDrop(VerdanceBlocks.MULBERRY_WOOD);
        addDrop(VerdanceBlocks.STRIPPED_MULBERRY_LOG);
        addDrop(VerdanceBlocks.STRIPPED_MULBERRY_WOOD);
        addDrop(VerdanceBlocks.MULBERRY_PLANKS);
        addDrop(VerdanceBlocks.MULBERRY_STAIRS);
        addDrop(VerdanceBlocks.MULBERRY_SLAB, this::slabDrops);
        addDrop(VerdanceBlocks.MULBERRY_FENCE);
        addDrop(VerdanceBlocks.MULBERRY_FENCE_GATE);
        addDrop(VerdanceBlocks.MULBERRY_DOOR, this::doorDrops);
        addDrop(VerdanceBlocks.MULBERRY_TRAPDOOR);
        addDrop(VerdanceBlocks.MULBERRY_PRESSURE_PLATE);
        addDrop(VerdanceBlocks.MULBERRY_BUTTON);
        addDrop(VerdanceBlocks.MULBERRY_LEAVES, this::createMulberryLeaves);
        addDrop(VerdanceBlocks.FLOWERING_MULBERRY_LEAVES, this::createFloweringMulberryLeaves);
        addDrop(VerdanceBlocks.MULBERRY_SAPLING);
        addPottedPlantDrops(VerdanceBlocks.POTTED_MULBERRY_SAPLING);
        addDrop(VerdanceBlocks.MULBERRY_SIGN);
        addDrop(VerdanceBlocks.MULBERRY_HANGING_SIGN);
        addDrop(VerdanceBlocks.SILK_COCOON, this.drops(Items.STRING, UniformLootNumberProvider.create(5.0f, 6.0f)));
        addDrop(VerdanceBlocks.VIOLET);
        addPottedPlantDrops(VerdanceBlocks.POTTED_VIOLET);
    }

    private void cushions() {
        addDrop(VerdanceBlocks.WHITE_CUSHION);
        addDrop(VerdanceBlocks.LIGHT_GRAY_CUSHION);
        addDrop(VerdanceBlocks.GRAY_CUSHION);
        addDrop(VerdanceBlocks.BLACK_CUSHION);
        addDrop(VerdanceBlocks.BROWN_CUSHION);
        addDrop(VerdanceBlocks.RED_CUSHION);
        addDrop(VerdanceBlocks.ORANGE_CUSHION);
        addDrop(VerdanceBlocks.YELLOW_CUSHION);
        addDrop(VerdanceBlocks.LIME_CUSHION);
        addDrop(VerdanceBlocks.GREEN_CUSHION);
        addDrop(VerdanceBlocks.CYAN_CUSHION);
        addDrop(VerdanceBlocks.LIGHT_BLUE_CUSHION);
        addDrop(VerdanceBlocks.BLUE_CUSHION);
        addDrop(VerdanceBlocks.PURPLE_CUSHION);
        addDrop(VerdanceBlocks.MAGENTA_CUSHION);
        addDrop(VerdanceBlocks.PINK_CUSHION);
    }

    private void shrubs() {
        this.addDrop(VerdanceBlocks.SHRUB, block -> dropsWithShears(
                block, this.applyExplosionDecay(block, ItemEntry.builder(Items.STICK).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0f, 2.0f))))
        ));
        this.addDrop(VerdanceBlocks.YELLOW_FLOWERING_SHRUB, block -> dropsWithShears(
                block, this.applyExplosionDecay(block, ItemEntry.builder(Items.STICK).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0f, 2.0f))))
        ));
        this.addDrop(VerdanceBlocks.PINK_FLOWERING_SHRUB, block -> dropsWithShears(
                block, this.applyExplosionDecay(block, ItemEntry.builder(Items.STICK).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0f, 2.0f))))
        ));
        this.addPottedPlantDrops(VerdanceBlocks.POTTED_SHRUB);
        this.addPottedPlantDrops(VerdanceBlocks.POTTED_YELLOW_FLOWERING_SHRUB);
        this.addPottedPlantDrops(VerdanceBlocks.POTTED_PINK_FLOWERING_SHRUB);
    }

    private LootTable.Builder createMulberryLeaves(Block leafBlock) {
        var enchantments = registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        var lootItem = ItemEntry.builder(Items.STICK);

        return dropsWithSilkTouchOrShears(
                leafBlock,
                this.addSurvivesExplosionCondition(leafBlock, lootItem).conditionally(TableBonusLootCondition.builder(enchantments.getOrThrow(Enchantments.FORTUNE), NORMAL_LEAVES_STICK_CHANCES))
        );
    }

    private LootTable.Builder createFloweringMulberryLeaves(Block leafBlock) {
        return createMulberryLeaves(leafBlock).pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f))
                .conditionally(this.createWithoutShearsOrSilkTouchCondition())
                .with(this.addSurvivesExplosionCondition(leafBlock, ItemEntry.builder(VerdanceItems.MULBERRY).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))))));
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
        addDrop(stuccoFamily.getBaseBlock());
        addDrop(stuccoFamily.getVariant(BlockFamily.Variant.STAIRS));
        addDrop(stuccoFamily.getVariant(BlockFamily.Variant.SLAB), this::slabDrops);
        addDrop(stuccoFamily.getVariant(BlockFamily.Variant.WALL));
    }

    private void cantaloupe() {
        addDrop(VerdanceBlocks.CANTALOUPE, block -> {
            var enchantments = registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
            var lootItem = ItemEntry.builder(VerdanceItems.CANTALOUPE_SLICE).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 4.0f))).apply(ApplyBonusLootFunction.uniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE))).apply(LimitCountLootFunction.builder(BoundedIntUnaryOperator.createMax(4)));
            return dropsWithSilkTouch(block, this.applyExplosionDecay(block, lootItem));
        });
        addDrop(VerdanceBlocks.CANTALOUPE_STEM, block -> this.cropStemDrops(block, VerdanceItems.CANTALOUPE_SEEDS));
        addDrop(VerdanceBlocks.ATTACHED_CANTALOUPE_STEM, block -> this.attachedCropStemDrops(block, VerdanceItems.CANTALOUPE_SEEDS));
    }
}
