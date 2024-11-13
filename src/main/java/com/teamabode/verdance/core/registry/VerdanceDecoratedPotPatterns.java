package com.teamabode.verdance.core.registry;

import com.teamabode.verdance.Verdance;
import java.util.Map;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class VerdanceDecoratedPotPatterns {
    public static final RegistryKey<String> ABODE = createKey("abode");
    public static final RegistryKey<String> FRILLS = createKey("frills");
    public static final RegistryKey<String> PITCH = createKey("pitch");
    public static final RegistryKey<String> PRICKLE = createKey("prickle");
    public static final RegistryKey<String> SPIRIT = createKey("spirit");
    public static final RegistryKey<String> TRAP = createKey("trap");

    public static final Map<Item, RegistryKey<String>> SHERD_TO_PATTERN = Map.of(
            VerdanceItems.ABODE_POTTERY_SHERD, ABODE,
            VerdanceItems.FRILLS_POTTERY_SHERD, FRILLS,
            VerdanceItems.PITCH_POTTERY_SHERD, PITCH,
            VerdanceItems.PRICKLE_POTTERY_SHERD, PRICKLE,
            VerdanceItems.SPIRIT_POTTERY_SHERD, SPIRIT,
            VerdanceItems.TRAP_POTTERY_SHERD, TRAP
    );

    public static void register() {
        register(ABODE, "abode_pottery_pattern");
        register(FRILLS, "frills_pottery_pattern");
        register(PITCH, "pitch_pottery_pattern");
        register(PRICKLE, "prickle_pottery_pattern");
        register(SPIRIT, "spirit_pottery_pattern");
        register(TRAP, "trap_pottery_pattern");
    }

    private static RegistryKey<String> createKey(String name) {
        return RegistryKey.of(RegistryKeys.DECORATED_POT_PATTERN, Verdance.id(name));
    }

    private static String register(RegistryKey<String> key, String assetId) {
        return Registry.register(Registries.DECORATED_POT_PATTERN, key, assetId);
    }
}
