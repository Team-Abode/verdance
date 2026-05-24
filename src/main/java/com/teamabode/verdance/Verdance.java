package com.teamabode.verdance;

import com.mojang.logging.LogUtils;
import com.teamabode.verdance.core.misc.VerdanceBoatTypes;
import com.teamabode.verdance.core.misc.VerdanceSurfaceRules;
import com.teamabode.verdance.core.registry.*;
import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.surface.SurfaceGeneration;
import net.minecraft.core.dispenser.BoatDispenseItemBehavior;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;

// TODO: fd integration (unnecessary for initial port)
// TODO: loot modifier data gen

@Mod(Verdance.MOD_ID)
public class Verdance {
    public static final String MOD_ID = "verdance";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Verdance(IEventBus eventBus, ModContainer container) {
        eventBus.addListener(this::commonSetup);

        VerdanceActivities.REGISTRY.register(eventBus);
        VerdanceBlocks.REGISTRY.register(eventBus);
        VerdanceBlockEntityTypes.REGISTRY.register(eventBus);
        VerdanceDecoratedPotPatterns.REGISTRY.register(eventBus);
        VerdanceEntityTypes.REGISTRY.register(eventBus);
        VerdanceItems.REGISTRY.register(eventBus);
        VerdanceMemoryModuleTypes.REGISTRY.register(eventBus);
        VerdanceSensorTypes.REGISTRY.register(eventBus);
        VerdanceSoundEvents.REGISTRY.register(eventBus);
        VerdanceTreeDecoratorTypes.REGISTRY.register(eventBus);
        VerdanceTriggerTypes.REGISTRY.register(eventBus);
        VerdanceTrunkPlacerTypes.REGISTRY.register(eventBus);

        container.registerConfig(ModConfig.Type.COMMON, VerdanceConfig.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        DispenserBlock.registerBehavior(
                VerdanceItems.MULBERRY_BOAT.get(),
                new BoatDispenseItemBehavior(VerdanceBoatTypes.MULBERRY_BOAT_PROXY.getValue(), false)
        );
        DispenserBlock.registerBehavior(
                VerdanceItems.MULBERRY_CHEST_BOAT.get(),
                new BoatDispenseItemBehavior(VerdanceBoatTypes.MULBERRY_BOAT_PROXY.getValue(), true)
        );
        event.enqueueWork(() -> {
            FireBlock flammables = (FireBlock) Blocks.FIRE;
            flammables.setFlammable(VerdanceBlocks.MULBERRY_PLANKS.get(), 5, 20);
            flammables.setFlammable(VerdanceBlocks.MULBERRY_STAIRS.get(), 5, 20);
            flammables.setFlammable(VerdanceBlocks.MULBERRY_SLAB.get(), 5, 20);
            flammables.setFlammable(VerdanceBlocks.MULBERRY_FENCE.get(), 5, 20);
            flammables.setFlammable(VerdanceBlocks.MULBERRY_FENCE_GATE.get(), 5, 20);
            flammables.setFlammable(VerdanceBlocks.MULBERRY_LOG.get(), 5, 5);
            flammables.setFlammable(VerdanceBlocks.STRIPPED_MULBERRY_LOG.get(), 5, 5);
            flammables.setFlammable(VerdanceBlocks.MULBERRY_WOOD.get(), 5, 5);
            flammables.setFlammable(VerdanceBlocks.STRIPPED_MULBERRY_WOOD.get(), 5, 5);
            flammables.setFlammable(VerdanceBlocks.MULBERRY_LEAVES.get(), 30, 60);
            flammables.setFlammable(VerdanceBlocks.FLOWERING_MULBERRY_LEAVES.get(), 30, 60);
            flammables.setFlammable(VerdanceBlocks.VIOLET.get(), 60, 100);
            flammables.setFlammable(VerdanceBlocks.SHRUB.get(), 60, 100);
            flammables.setFlammable(VerdanceBlocks.YELLOW_FLOWERING_SHRUB.get(), 60, 100);
            flammables.setFlammable(VerdanceBlocks.PINK_FLOWERING_SHRUB.get(), 60, 100);

            BiomePlacement.replaceOverworld(Biomes.CHERRY_GROVE, VerdanceBiomes.MULBERRY_FOREST, VerdanceConfig.INSTANCE.mulberryForestProportion.get());
            BiomePlacement.replaceOverworld(Biomes.SPARSE_JUNGLE, VerdanceBiomes.SHRUBLANDS, VerdanceConfig.INSTANCE.shrublandsProportion.get());
            SurfaceGeneration.addOverworldSurfaceRules(
                    ResourceLocation.withDefaultNamespace("rules/overworld"),
                    SurfaceRules.sequence(VerdanceSurfaceRules.shrublands())
            );
        });
    }

    public static ResourceLocation id(String name) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, name);
    }
}
