package com.teamabode.verdance.core.registry;

import com.teamabode.verdance.Verdance;
import net.minecraft.item.equipment.trim.ArmorTrimPattern;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;

public class VerdanceTrimPatterns {

    public static final RegistryKey<ArmorTrimPattern> HERITAGE = createKey("heritage");

    public static void register(Registerable<ArmorTrimPattern> context) {
        context.register(HERITAGE, new ArmorTrimPattern(
                Verdance.id("heritage"),
                Text.translatable("trim_pattern.verdance.heritage"),
                false
        ));
    }

    private static RegistryKey<ArmorTrimPattern> createKey(String name) {
        return RegistryKey.of(RegistryKeys.TRIM_PATTERN, Verdance.id(name));
    }
}
