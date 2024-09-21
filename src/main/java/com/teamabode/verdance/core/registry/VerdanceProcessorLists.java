package com.teamabode.verdance.core.registry;

import com.google.common.collect.Lists;
import com.teamabode.verdance.Verdance;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.structure.processor.CappedStructureProcessor;
import net.minecraft.structure.processor.RuleStructureProcessor;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.structure.processor.StructureProcessorRule;
import net.minecraft.structure.rule.AlwaysTruePosRuleTest;
import net.minecraft.structure.rule.AlwaysTrueRuleTest;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.blockentity.AppendLootRuleBlockEntityModifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import java.util.ArrayList;
import java.util.List;

public class VerdanceProcessorLists {
    public static final RegistryKey<StructureProcessorList> TOWN_RUINS_ARCHAEOLOGY = createKey("town_ruins_archaeology");
    public static final RegistryKey<StructureProcessorList> TOWN_RUINS_SMALL_ARCHAEOLOGY = createKey("town_ruins_small_archaeology");
    public static final RegistryKey<StructureProcessorList> TOWN_RUINS_ROAD_ARCHAEOLOGY = createKey("town_ruins_road_archaeology");

    public static void register(Registerable<StructureProcessorList> context) {
        registerArchaeology(context, TOWN_RUINS_ARCHAEOLOGY, 6, 3);
        registerArchaeology(context, TOWN_RUINS_SMALL_ARCHAEOLOGY, 3, 1);
        registerArchaeology(context, TOWN_RUINS_ROAD_ARCHAEOLOGY, 3, 0);
    }

    private static void registerArchaeology(Registerable<StructureProcessorList> context, RegistryKey<StructureProcessorList> key, int commonAmount, int rareAmount) {
        ArrayList<StructureProcessor> processors = Lists.newArrayList();

        // Common
        processors.add(new CappedStructureProcessor(
                new RuleStructureProcessor(List.of(new StructureProcessorRule(
                        new BlockMatchRuleTest(Blocks.SAND),
                        AlwaysTrueRuleTest.INSTANCE,
                        AlwaysTruePosRuleTest.INSTANCE,
                        Blocks.SUSPICIOUS_SAND.getDefaultState(),
                        new AppendLootRuleBlockEntityModifier(VerdanceLootTables.ARCHAEOLOGY_TOWN_RUINS_COMMON)
                ))),
                ConstantIntProvider.create(commonAmount)
        ));
        // Rare
        if (rareAmount > 0) {
            processors.add(new CappedStructureProcessor(
                    new RuleStructureProcessor(List.of(new StructureProcessorRule(
                            new BlockMatchRuleTest(Blocks.SAND),
                            AlwaysTrueRuleTest.INSTANCE,
                            AlwaysTruePosRuleTest.INSTANCE,
                            Blocks.SUSPICIOUS_SAND.getDefaultState(),
                            new AppendLootRuleBlockEntityModifier(VerdanceLootTables.ARCHAEOLOGY_TOWN_RUINS_TREASURE)
                    ))),
                    ConstantIntProvider.create(rareAmount)
            ));
        }
        register(context, key, processors);
    }

    private static void register(Registerable<StructureProcessorList> context, RegistryKey<StructureProcessorList> key, List<StructureProcessor> processors) {
        context.register(key, new StructureProcessorList(processors));
    }

    private static RegistryKey<StructureProcessorList> createKey(String name) {
        return RegistryKey.of(RegistryKeys.PROCESSOR_LIST, Verdance.id(name));
    }
}
