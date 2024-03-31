package com.teamabode.verdance;

import com.teamabode.sketch.core.api.config.Config;
import com.teamabode.sketch.core.api.config.ConfigBuilder;
import com.teamabode.sketch.core.api.misc.BlockEntityAdditions;
import com.teamabode.verdance.core.integration.farmersdelight.VerdanceFD;
import com.teamabode.verdance.core.misc.VerdanceGroupEvents;
import com.teamabode.verdance.core.misc.tag.VerdanceBiomeTags;
import com.teamabode.verdance.core.misc.worldgen.VerdancePlacedFeatures;
import com.teamabode.verdance.core.registry.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.levelgen.GenerationStep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Verdance implements ModInitializer {
    public static final String MOD_ID = "verdance";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final Config CONFIG = new ConfigBuilder(MOD_ID)
            .addGroup("general", builder -> {
                builder.addBooleanProperty("bonemealable_sugar_cane", true);
                return builder;
            })
            .addGroup("biomes", builder -> {
                builder.addIntProperty("mulberry_forest_weight", 4);
                builder.addIntProperty("shrublands_weight", 15);
                return builder;
            })
            .build();

    public void onInitialize() {
        VerdanceEntities.register();
        VerdanceBlocks.register();
        VerdanceItems.register();
        VerdanceFeatures.register();
        VerdanceSounds.register();
        VerdanceGroupEvents.register();
        VerdanceBoatType.register();
        VerdanceTrunkPlacers.register();
        VerdanceActivities.register();
        VerdanceSensors.register();
        VerdanceMemories.register();

        if (FabricLoader.getInstance().isModLoaded("farmersdelight")) {
            VerdanceFD.register();
        }
        registerBlockEntityAdditions();
        registerBiomeModifications();
        registerTrades();
    }

    public static void registerBlockEntityAdditions() {
        BlockEntityAdditions.appendBlocks(BlockEntityType.SIGN, VerdanceBlocks.MULBERRY_SIGN, VerdanceBlocks.MULBERRY_WALL_SIGN);
        BlockEntityAdditions.appendBlocks(BlockEntityType.HANGING_SIGN, VerdanceBlocks.MULBERRY_HANGING_SIGN, VerdanceBlocks.MULBERRY_WALL_HANGING_SIGN);
    }

    public static void registerBiomeModifications() {
        BiomeModifications.addFeature(
                BiomeSelectors.tag(VerdanceBiomeTags.HAS_CANTALOUPE),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                VerdancePlacedFeatures.PATCH_CANTALOUPE
        );
    }

    public static void registerTrades() {
        TradeOfferHelper.registerWanderingTraderOffers(1, itemListings -> {
            itemListings.add(new VillagerTrades.ItemsForEmeralds(VerdanceItems.CANTALOUPE_SEEDS, 1, 1, 12, 1));
            itemListings.add(new VillagerTrades.ItemsForEmeralds(VerdanceBlocks.SHRUB, 3, 1, 8, 1));
            itemListings.add(new VillagerTrades.ItemsForEmeralds(VerdanceItems.MULBERRY, 5, 1, 8, 1));
        });
    }

    public static ResourceLocation id(String name) {
        return new ResourceLocation(MOD_ID, name);
    }
}
