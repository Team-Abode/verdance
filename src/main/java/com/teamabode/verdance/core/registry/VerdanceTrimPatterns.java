package com.teamabode.verdance.core.registry;

import com.teamabode.verdance.Verdance;
import net.minecraft.item.trim.ArmorTrimPattern;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;

public class VerdanceTrimPatterns {

    public static final RegistryKey<ArmorTrimPattern> COMMUNITY = createKey("community");

    public static void register(Registerable<ArmorTrimPattern> context) {
        context.register(COMMUNITY, new ArmorTrimPattern(
                Verdance.id("community"),
                Registries.ITEM.getEntry(VerdanceItems.COMMUNITY_ARMOR_TRIM_SMITHING_TEMPLATE),
                Text.translatable("trim_pattern.verdance.community"),
                false
        ));
    }

    private static RegistryKey<ArmorTrimPattern> createKey(String name) {
        return RegistryKey.of(RegistryKeys.TRIM_PATTERN, Verdance.id(name));
    }
}
