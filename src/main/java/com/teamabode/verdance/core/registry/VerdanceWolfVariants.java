package com.teamabode.verdance.core.registry;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.core.tag.VerdanceBiomeTags;
import net.minecraft.entity.passive.WolfVariant;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class VerdanceWolfVariants {

    public static final RegistryKey<WolfVariant> GOLDEN = createKey("golden");

    public static void register(Registerable<WolfVariant> context) {
        var biomes = context.getRegistryLookup(RegistryKeys.BIOME);

        context.register(GOLDEN, new WolfVariant(
                Verdance.id("entity/wolf/wolf_golden"),
                Verdance.id("entity/wolf/wolf_golden_tame"),
                Verdance.id("entity/wolf/wolf_golden_angry"),
                biomes.getOrThrow(VerdanceBiomeTags.HAS_GOLDEN_WOLF)
        ));
    }

    private static RegistryKey<WolfVariant> createKey(String name) {
        return RegistryKey.of(RegistryKeys.WOLF_VARIANT, Verdance.id(name));
    }
}
