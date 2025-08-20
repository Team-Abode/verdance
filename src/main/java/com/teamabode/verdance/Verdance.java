package com.teamabode.verdance;

import com.teamabode.verdance.core.integration.CompatUtils;
import com.teamabode.verdance.core.integration.farmersdelight.FDIntegration;
import com.teamabode.verdance.core.integration.guarding.GuardingIntegration;
import com.teamabode.verdance.core.misc.VerdanceBiomeModifications;
import com.teamabode.verdance.core.misc.VerdanceLootTableEvents;
import com.teamabode.verdance.core.misc.VerdanceStructurePoolModifiers;
import com.teamabode.verdance.core.misc.VerdanceSurfaceRules;
import com.teamabode.verdance.core.registry.*;
import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.surface.SurfaceGeneration;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper.WanderingTraderOffersBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class Verdance implements ModInitializer {
    public static final String MOD_ID = "verdance";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        VerdanceEntityTypes.register();
        VerdanceBlocks.register();
        VerdanceBlockEntityTypes.register();
        VerdanceItems.register();
        VerdanceConsumeEffectTypes.register();
        VerdanceDecoratedPotPatterns.register();
        VerdanceSoundEvents.register();
        VerdanceTrunkPlacerTypes.register();
        VerdanceTreeDecoratorTypes.register();
        VerdanceActivities.register();
        VerdanceSensorTypes.register();
        VerdanceMemoryModuleTypes.register();
        VerdanceCriteria.init();
        VerdanceBiomeModifications.register();
        VerdanceStructurePoolModifiers.register();
        VerdanceLootTableEvents.register();
        registerBlockEntitySupportedBlock();
        registerTrades();
        registerItemGroupEvents();
        registerBiomePlacements();
        registerSurfaceRules();
        registerIntegrations();
    }

    public static void registerIntegrations() {
        Optional<ModContainer> container = FabricLoader.getInstance().getModContainer(MOD_ID);
        if (container.isEmpty()) return;

        if (CompatUtils.isModLoaded("farmersdelight")) {
            FDIntegration.register(container.get());
        }
        if (CompatUtils.isModLoaded("guarding")) {
            GuardingIntegration.register(container.get());
        }
    }

    public static void registerBlockEntitySupportedBlock() {
        BlockEntityType.SIGN.addSupportedBlock(VerdanceBlocks.MULBERRY_SIGN);
        BlockEntityType.SIGN.addSupportedBlock(VerdanceBlocks.MULBERRY_WALL_SIGN);
        BlockEntityType.HANGING_SIGN.addSupportedBlock(VerdanceBlocks.MULBERRY_HANGING_SIGN);
        BlockEntityType.HANGING_SIGN.addSupportedBlock(VerdanceBlocks.MULBERRY_WALL_HANGING_SIGN);
    }

    public static void registerTrades() {
        TradeOfferHelper.registerWanderingTraderOffers(builder -> {

            builder.addOffersToPool(
                WanderingTraderOffersBuilder.SELL_COMMON_ITEMS_POOL,

                (entity, random) -> new TradeOffers.SellItemFactory(
                        VerdanceItems.CANTALOUPE_SEEDS, 1, 1, 12, 1
                ).create(entity, random),

                (entity, random) -> new TradeOffers.SellItemFactory(
                        VerdanceItems.MULBERRY, 5, 1, 8, 1
                ).create(entity, random),

                (entity, random) -> new TradeOffers.SellItemFactory(
                        VerdanceBlocks.VIOLET, 1, 1, 12, 1
                ).create(entity, random),

                (entity, random) -> new TradeOffers.SellItemFactory(
                        VerdanceBlocks.DESERT_BUSH, 1, 1, 12, 1
                ).create(entity, random),

                (entity, random) -> new TradeOffers.SellItemFactory(
                        VerdanceBlocks.DESERT_BUSH, 1, 1, 12, 1
                ).create(entity, random),

                (entity, random) -> new TradeOffers.SellItemFactory(
                        VerdanceBlocks.YELLOW_FLOWERING_DESERT_BUSH, 1, 1, 12, 1
                ).create(entity, random),

                (entity, random) -> new TradeOffers.SellItemFactory(
                        VerdanceBlocks.YELLOW_FLOWERING_DESERT_BUSH, 1, 1, 12, 1
                ).create(entity, random)
            );

            builder.addOffersToPool(
                WanderingTraderOffersBuilder.SELL_SPECIAL_ITEMS_POOL,
                (entity, random) -> new TradeOffers.SellItemFactory(
                        VerdanceBlocks.MULBERRY_LOG, 1, 8, 4, 1
                ).create(entity, random)
            );
        });

        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 2, itemListings -> {
            itemListings.add(new TradeOffers.BuyItemFactory(VerdanceBlocks.CANTALOUPE, 6, 12, 10));
        });
    }

    public static void registerItemGroupEvents() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.addAfter(
                    Items.CHERRY_BUTTON,
                    VerdanceBlocks.MULBERRY_LOG,
                    VerdanceBlocks.MULBERRY_WOOD,
                    VerdanceBlocks.STRIPPED_MULBERRY_LOG,
                    VerdanceBlocks.STRIPPED_MULBERRY_WOOD,
                    VerdanceBlocks.MULBERRY_PLANKS,
                    VerdanceBlocks.MULBERRY_STAIRS,
                    VerdanceBlocks.MULBERRY_SLAB,
                    VerdanceBlocks.MULBERRY_FENCE,
                    VerdanceBlocks.MULBERRY_FENCE_GATE,
                    VerdanceBlocks.MULBERRY_DOOR,
                    VerdanceBlocks.MULBERRY_TRAPDOOR,
                    VerdanceBlocks.MULBERRY_PRESSURE_PLATE,
                    VerdanceBlocks.MULBERRY_BUTTON
            );
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.addAfter(
                    Items.CHERRY_CHEST_BOAT,
                    VerdanceItems.MULBERRY_BOAT,
                    VerdanceItems.MULBERRY_CHEST_BOAT
            );
            entries.addBefore(Items.MUSIC_DISC_5, VerdanceItems.MUSIC_DISC_RANGE);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> {
            entries.addAfter(
                    Items.CHERRY_HANGING_SIGN,
                    VerdanceItems.MULBERRY_SIGN,
                    VerdanceItems.MULBERRY_HANGING_SIGN
            );
            entries.addAfter(
                    Items.PINK_BED,
                    VerdanceBlocks.WHITE_CUSHION,
                    VerdanceBlocks.LIGHT_GRAY_CUSHION,
                    VerdanceBlocks.GRAY_CUSHION,
                    VerdanceBlocks.BLACK_CUSHION,
                    VerdanceBlocks.BROWN_CUSHION,
                    VerdanceBlocks.RED_CUSHION,
                    VerdanceBlocks.ORANGE_CUSHION,
                    VerdanceBlocks.YELLOW_CUSHION,
                    VerdanceBlocks.LIME_CUSHION,
                    VerdanceBlocks.GREEN_CUSHION,
                    VerdanceBlocks.CYAN_CUSHION,
                    VerdanceBlocks.LIGHT_BLUE_CUSHION,
                    VerdanceBlocks.BLUE_CUSHION,
                    VerdanceBlocks.PURPLE_CUSHION,
                    VerdanceBlocks.MAGENTA_CUSHION,
                    VerdanceBlocks.PINK_CUSHION
            );
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.addAfter(Items.GLOW_BERRIES, VerdanceItems.MULBERRY);
            entries.addAfter(Items.MELON_SLICE, VerdanceItems.CANTALOUPE_SLICE, VerdanceItems.GRILLED_CANTALOUPE_SLICE);
            entries.addAfter(Items.HONEY_BOTTLE, VerdanceItems.CANTALOUPE_JUICE);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
            entries.addAfter(Blocks.CHERRY_LOG, VerdanceBlocks.MULBERRY_LOG);
            entries.addAfter(Items.MELON, VerdanceBlocks.CANTALOUPE);
            entries.addAfter(Items.MELON_SEEDS, VerdanceItems.CANTALOUPE_SEEDS);
            entries.addAfter(Blocks.CHERRY_LEAVES, VerdanceBlocks.MULBERRY_LEAVES, VerdanceBlocks.FLOWERING_MULBERRY_LEAVES);
            entries.addAfter(Blocks.CHERRY_SAPLING, VerdanceItems.MULBERRY);
            entries.addAfter(Items.FROGSPAWN, VerdanceBlocks.SILKWORM_EGGS);
            entries.addAfter(Items.CORNFLOWER, VerdanceBlocks.VIOLET);

            entries.addBefore(Items.DEAD_BUSH, VerdanceBlocks.DESERT_BUSH, VerdanceBlocks.YELLOW_FLOWERING_DESERT_BUSH, VerdanceBlocks.PINK_FLOWERING_DESERT_BUSH);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COLORED_BLOCKS).register(entries -> {
            entries.addAfter(
                    Items.PINK_TERRACOTTA,
                    VerdanceBlocks.WHITE_STUCCO,
                    VerdanceBlocks.WHITE_STUCCO_STAIRS,
                    VerdanceBlocks.WHITE_STUCCO_SLAB,
                    VerdanceBlocks.WHITE_STUCCO_WALL,
                    VerdanceBlocks.LIGHT_GRAY_STUCCO,
                    VerdanceBlocks.LIGHT_GRAY_STUCCO_STAIRS,
                    VerdanceBlocks.LIGHT_GRAY_STUCCO_SLAB,
                    VerdanceBlocks.LIGHT_GRAY_STUCCO_WALL,
                    VerdanceBlocks.GRAY_STUCCO,
                    VerdanceBlocks.GRAY_STUCCO_STAIRS,
                    VerdanceBlocks.GRAY_STUCCO_SLAB,
                    VerdanceBlocks.GRAY_STUCCO_WALL,
                    VerdanceBlocks.BLACK_STUCCO,
                    VerdanceBlocks.BLACK_STUCCO_STAIRS,
                    VerdanceBlocks.BLACK_STUCCO_SLAB,
                    VerdanceBlocks.BLACK_STUCCO_WALL,
                    VerdanceBlocks.BROWN_STUCCO,
                    VerdanceBlocks.BROWN_STUCCO_STAIRS,
                    VerdanceBlocks.BROWN_STUCCO_SLAB,
                    VerdanceBlocks.BROWN_STUCCO_WALL,
                    VerdanceBlocks.RED_STUCCO,
                    VerdanceBlocks.RED_STUCCO_STAIRS,
                    VerdanceBlocks.RED_STUCCO_SLAB,
                    VerdanceBlocks.RED_STUCCO_WALL,
                    VerdanceBlocks.ORANGE_STUCCO,
                    VerdanceBlocks.ORANGE_STUCCO_STAIRS,
                    VerdanceBlocks.ORANGE_STUCCO_SLAB,
                    VerdanceBlocks.ORANGE_STUCCO_WALL,
                    VerdanceBlocks.YELLOW_STUCCO,
                    VerdanceBlocks.YELLOW_STUCCO_STAIRS,
                    VerdanceBlocks.YELLOW_STUCCO_SLAB,
                    VerdanceBlocks.YELLOW_STUCCO_WALL,
                    VerdanceBlocks.LIME_STUCCO,
                    VerdanceBlocks.LIME_STUCCO_STAIRS,
                    VerdanceBlocks.LIME_STUCCO_SLAB,
                    VerdanceBlocks.LIME_STUCCO_WALL,
                    VerdanceBlocks.GREEN_STUCCO,
                    VerdanceBlocks.GREEN_STUCCO_STAIRS,
                    VerdanceBlocks.GREEN_STUCCO_SLAB,
                    VerdanceBlocks.GREEN_STUCCO_WALL,
                    VerdanceBlocks.CYAN_STUCCO,
                    VerdanceBlocks.CYAN_STUCCO_STAIRS,
                    VerdanceBlocks.CYAN_STUCCO_SLAB,
                    VerdanceBlocks.CYAN_STUCCO_WALL,
                    VerdanceBlocks.LIGHT_BLUE_STUCCO,
                    VerdanceBlocks.LIGHT_BLUE_STUCCO_STAIRS,
                    VerdanceBlocks.LIGHT_BLUE_STUCCO_SLAB,
                    VerdanceBlocks.LIGHT_BLUE_STUCCO_WALL,
                    VerdanceBlocks.BLUE_STUCCO,
                    VerdanceBlocks.BLUE_STUCCO_STAIRS,
                    VerdanceBlocks.BLUE_STUCCO_SLAB,
                    VerdanceBlocks.BLUE_STUCCO_WALL,
                    VerdanceBlocks.PURPLE_STUCCO,
                    VerdanceBlocks.PURPLE_STUCCO_STAIRS,
                    VerdanceBlocks.PURPLE_STUCCO_SLAB,
                    VerdanceBlocks.PURPLE_STUCCO_WALL,
                    VerdanceBlocks.MAGENTA_STUCCO,
                    VerdanceBlocks.MAGENTA_STUCCO_STAIRS,
                    VerdanceBlocks.MAGENTA_STUCCO_SLAB,
                    VerdanceBlocks.MAGENTA_STUCCO_WALL,
                    VerdanceBlocks.PINK_STUCCO,
                    VerdanceBlocks.PINK_STUCCO_STAIRS,
                    VerdanceBlocks.PINK_STUCCO_SLAB,
                    VerdanceBlocks.PINK_STUCCO_WALL
            );
            entries.addAfter(
                    Items.PINK_BED,
                    VerdanceBlocks.WHITE_CUSHION,
                    VerdanceBlocks.LIGHT_GRAY_CUSHION,
                    VerdanceBlocks.GRAY_CUSHION,
                    VerdanceBlocks.BLACK_CUSHION,
                    VerdanceBlocks.BROWN_CUSHION,
                    VerdanceBlocks.RED_CUSHION,
                    VerdanceBlocks.ORANGE_CUSHION,
                    VerdanceBlocks.YELLOW_CUSHION,
                    VerdanceBlocks.LIME_CUSHION,
                    VerdanceBlocks.GREEN_CUSHION,
                    VerdanceBlocks.CYAN_CUSHION,
                    VerdanceBlocks.LIGHT_BLUE_CUSHION,
                    VerdanceBlocks.BLUE_CUSHION,
                    VerdanceBlocks.PURPLE_CUSHION,
                    VerdanceBlocks.MAGENTA_CUSHION,
                    VerdanceBlocks.PINK_CUSHION
            );
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(entries -> entries.addAfter(Blocks.CAULDRON, VerdanceBlocks.WHITE_CUSHION));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.addBefore(Items.DISC_FRAGMENT_5, VerdanceItems.DISC_FRAGMENT_RANGE);
            entries.addBefore(Items.ANGLER_POTTERY_SHERD, VerdanceItems.ABODE_POTTERY_SHERD);
            entries.addAfter(Items.FRIEND_POTTERY_SHERD, VerdanceItems.FRILLS_POTTERY_SHERD);
            entries.addBefore(Items.PLENTY_POTTERY_SHERD, VerdanceItems.PITCH_POTTERY_SHERD);
            entries.addBefore(Items.PRIZE_POTTERY_SHERD, VerdanceItems.PRICKLE_POTTERY_SHERD);
            entries.addAfter(Items.SNORT_POTTERY_SHERD, VerdanceItems.SPIRIT_POTTERY_SHERD, VerdanceItems.TRAP_POTTERY_SHERD);
            entries.addAfter(Items.HOST_ARMOR_TRIM_SMITHING_TEMPLATE, VerdanceItems.COMMUNITY_ARMOR_TRIM_SMITHING_TEMPLATE);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(entries -> {
            entries.addBefore(
                    Items.SILVERFISH_SPAWN_EGG,
                    VerdanceItems.SILK_MOTH_SPAWN_EGG,
                    VerdanceItems.SILKWORM_SPAWN_EGG
            );
        });
    }

    public static void registerBiomePlacements() {
        BiomePlacement.replaceOverworld(BiomeKeys.CHERRY_GROVE, VerdanceBiomes.MULBERRY_FOREST, VerdanceConstants.MULBERRY_FOREST_PROPORTION);
        BiomePlacement.replaceOverworld(BiomeKeys.SPARSE_JUNGLE, VerdanceBiomes.SHRUBLANDS, VerdanceConstants.SHRUBLANDS_PROPORTION);
    }

    public static void registerSurfaceRules() {
        SurfaceGeneration.addOverworldSurfaceRules(Identifier.ofVanilla("rules/overworld"), MaterialRules.sequence(
                VerdanceSurfaceRules.shrublands()
        ));
    }

    public static Identifier id(String name) {
        return Identifier.of(MOD_ID, name);
    }
}
