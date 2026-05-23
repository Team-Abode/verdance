package com.teamabode.verdance.datagen;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.core.registry.*;
import com.teamabode.verdance.datagen.client.VerdanceBlockStateProvider;
import com.teamabode.verdance.datagen.client.VerdanceItemModelProvider;
import com.teamabode.verdance.datagen.server.VerdanceRecipeProvider;
import com.teamabode.verdance.datagen.server.advancement.VerdanceAdvancementProvider;
import com.teamabode.verdance.datagen.server.loot.VerdanceLootTableProvider;
import com.teamabode.verdance.datagen.server.tag.VerdanceBiomeTagProvider;
import com.teamabode.verdance.datagen.server.tag.VerdanceBlockTagProvider;
import com.teamabode.verdance.datagen.server.tag.VerdanceEntityTypeTagProvider;
import com.teamabode.verdance.datagen.server.tag.VerdanceItemTagProvider;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Set;

@EventBusSubscriber(modid = Verdance.MOD_ID)
public class VerdanceDataGenerator {
    public static final RegistrySetBuilder REGISTRY_SET_BUILDER = new RegistrySetBuilder()
            .add(Registries.BIOME, VerdanceBiomes::register)
            .add(Registries.CONFIGURED_FEATURE, VerdanceConfiguredFeatures::register)
            .add(Registries.JUKEBOX_SONG, VerdanceJukeboxSongs::register)
            .add(Registries.PLACED_FEATURE, VerdancePlacedFeatures::register)
            .add(Registries.PROCESSOR_LIST, VerdanceProcessorLists::register)
            .add(Registries.STRUCTURE, VerdanceStructures::register)
            .add(Registries.STRUCTURE_SET, VerdanceStructureSets::register)
            .add(Registries.TEMPLATE_POOL, VerdanceTemplatePools::register)
            .add(Registries.TRIM_PATTERN, VerdanceTrimPatterns::register)
            .add(Registries.WOLF_VARIANT, VerdanceWolfVariants::register);

    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();

        generator.addProvider(
                event.includeServer(),
                new VerdanceAdvancementProvider(
                        generator.getPackOutput(),
                        event.getLookupProvider(),
                        event.getExistingFileHelper()
                )
        );
        generator.addProvider(
                event.includeServer(),
                new VerdanceLootTableProvider(
                        generator.getPackOutput(),
                        event.getLookupProvider()
                )
        );
        generator.addProvider(
                event.includeServer(),
                new VerdanceRecipeProvider(
                        generator.getPackOutput(),
                        event.getLookupProvider()
                )
        );

        var builtInEntriesProvider = generator.addProvider(
                event.includeServer(),
                (DataProvider.Factory<DatapackBuiltinEntriesProvider>) output -> new DatapackBuiltinEntriesProvider(
                        output,
                        event.getLookupProvider(),
                        REGISTRY_SET_BUILDER,
                        Set.of(Verdance.MOD_ID)
                )
        );

        var blockTagProvider = new VerdanceBlockTagProvider(
                generator.getPackOutput(),
                event.getLookupProvider(),
                event.getExistingFileHelper()
        );
        generator.addProvider(
                event.includeServer(),
                blockTagProvider
        );
        generator.addProvider(
                event.includeServer(),
                new VerdanceItemTagProvider(
                        generator.getPackOutput(),
                        event.getLookupProvider(),
                        blockTagProvider.contentsGetter()
                )
        );
        generator.addProvider(
                event.includeServer(),
                new VerdanceEntityTypeTagProvider(
                        generator.getPackOutput(),
                        event.getLookupProvider(),
                        event.getExistingFileHelper()
                )
        );
        generator.addProvider(
                event.includeServer(),
                new VerdanceBiomeTagProvider(
                        generator.getPackOutput(),
                        builtInEntriesProvider.getRegistryProvider(),
                        event.getExistingFileHelper()
                )
        );

        generator.addProvider(
                event.includeClient(),
                new VerdanceItemModelProvider(
                        generator.getPackOutput(),
                        event.getExistingFileHelper()
                )
        );
        generator.addProvider(
                event.includeClient(),
                new VerdanceBlockStateProvider(
                        generator.getPackOutput(),
                        event.getExistingFileHelper()
                )
        );
    }
}
