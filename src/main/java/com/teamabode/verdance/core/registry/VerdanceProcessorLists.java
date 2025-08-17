package com.teamabode.verdance.core.registry;

import com.google.common.collect.Lists;
import com.teamabode.verdance.Verdance;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.templatesystem.AlwaysTrueTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.CappedProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.PosAlwaysTrueTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.ProcessorRule;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraft.world.level.levelgen.structure.templatesystem.rule.blockentity.AppendLoot;

public class VerdanceProcessorLists {
    public static final ResourceKey<StructureProcessorList> TOWN_RUINS_ARCHAEOLOGY = createKey("town_ruins_archaeology");
    public static final ResourceKey<StructureProcessorList> TOWN_RUINS_SMALL_ARCHAEOLOGY = createKey("town_ruins_small_archaeology");
    public static final ResourceKey<StructureProcessorList> TOWN_RUINS_ROAD_ARCHAEOLOGY = createKey("town_ruins_road_archaeology");

    public static void register(BootstrapContext<StructureProcessorList> context) {
        registerArchaeology(context, TOWN_RUINS_ARCHAEOLOGY, 6, 3);
        registerArchaeology(context, TOWN_RUINS_SMALL_ARCHAEOLOGY, 3, 1);
        registerArchaeology(context, TOWN_RUINS_ROAD_ARCHAEOLOGY, 3, 0);
    }

    private static void registerArchaeology(BootstrapContext<StructureProcessorList> context, ResourceKey<StructureProcessorList> key, int commonAmount, int rareAmount) {
        ArrayList<StructureProcessor> processors = Lists.newArrayList();

        // Common
        processors.add(new CappedProcessor(
                new RuleProcessor(List.of(new ProcessorRule(
                        new BlockMatchTest(Blocks.SAND),
                        AlwaysTrueTest.INSTANCE,
                        PosAlwaysTrueTest.INSTANCE,
                        Blocks.SUSPICIOUS_SAND.defaultBlockState(),
                        new AppendLoot(VerdanceLootTables.ARCHAEOLOGY_TOWN_RUINS_COMMON)
                ))),
                ConstantInt.of(commonAmount)
        ));
        // Rare
        if (rareAmount > 0) {
            processors.add(new CappedProcessor(
                    new RuleProcessor(List.of(new ProcessorRule(
                            new BlockMatchTest(Blocks.SAND),
                            AlwaysTrueTest.INSTANCE,
                            PosAlwaysTrueTest.INSTANCE,
                            Blocks.SUSPICIOUS_SAND.defaultBlockState(),
                            new AppendLoot(VerdanceLootTables.ARCHAEOLOGY_TOWN_RUINS_TREASURE)
                    ))),
                    ConstantInt.of(rareAmount)
            ));
        }
        register(context, key, processors);
    }

    private static void register(BootstrapContext<StructureProcessorList> context, ResourceKey<StructureProcessorList> key, List<StructureProcessor> processors) {
        context.register(key, new StructureProcessorList(processors));
    }

    private static ResourceKey<StructureProcessorList> createKey(String name) {
        return ResourceKey.create(Registries.PROCESSOR_LIST, Verdance.id(name));
    }
}
