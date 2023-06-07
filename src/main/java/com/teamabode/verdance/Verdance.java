package com.teamabode.verdance;

import com.teamabode.scribe.common.entity.boat.ScribeBoatDispenseItemBehavior;
import com.teamabode.scribe.core.api.config.Config;
import com.teamabode.scribe.core.api.config.ConfigBuilder;
import com.teamabode.verdance.common.entity.silkmoth.SilkMoth;
import com.teamabode.verdance.core.registry.*;
import com.teamabode.verdance.core.misc.*;
import com.teamabode.verdance.core.misc.worldgen.VerdanceTrunkPlacerType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.DefaultAttributes;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.level.block.DispenserBlock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Verdance implements ModInitializer {
    public static final String MOD_ID = "verdance";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final Config CONFIG = new ConfigBuilder(MOD_ID)
            .addBooleanProperty("bonemealable_sugar_cane", true)
            .build();

    public void onInitialize() {
        VerdanceEntities.register();
        VerdanceBlocks.register();
        VerdanceItems.register();
        VerdanceFeatures.register();
        VerdanceSounds.register();
        VerdanceGroupEvents.register();
        VerdanceBoatType.register();
        VerdanceTrunkPlacerType.register();

        registerDispenserBehaviors();
        registerTrades();
    }

    public static void registerDispenserBehaviors() {
        DispenserBlock.registerBehavior(VerdanceItems.MULBERRY_BOAT, new ScribeBoatDispenseItemBehavior(VerdanceBoatType.MULBERRY));
        DispenserBlock.registerBehavior(VerdanceItems.MULBERRY_CHEST_BOAT, new ScribeBoatDispenseItemBehavior(VerdanceBoatType.MULBERRY, true));
    }

    public static void registerTrades() {
        TradeOfferHelper.registerWanderingTraderOffers(1, itemListings -> {
            itemListings.add(new VillagerTrades.ItemsForEmeralds(VerdanceItems.CANTALOUPE_SEEDS, 1, 1, 12, 1));
            itemListings.add(new VillagerTrades.ItemsForEmeralds(VerdanceBlocks.SHRUB, 3, 1, 8, 1));
            itemListings.add(new VillagerTrades.ItemsForEmeralds(VerdanceItems.MULBERRY, 5, 1, 8, 1));
        });
    }


}
