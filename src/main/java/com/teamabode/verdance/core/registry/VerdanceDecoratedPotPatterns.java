package com.teamabode.verdance.core.registry;

import com.teamabode.verdance.Verdance;
import java.util.Map;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.DecoratedPotPattern;

public class VerdanceDecoratedPotPatterns {
    public static final ResourceKey<DecoratedPotPattern> ABODE = createKey("abode");
    public static final ResourceKey<DecoratedPotPattern> FRILLS = createKey("frills");
    public static final ResourceKey<DecoratedPotPattern> PITCH = createKey("pitch");
    public static final ResourceKey<DecoratedPotPattern> PRICKLE = createKey("prickle");
    public static final ResourceKey<DecoratedPotPattern> SPIRIT = createKey("spirit");
    public static final ResourceKey<DecoratedPotPattern> TRAP = createKey("trap");

    public static final Map<Item, ResourceKey<DecoratedPotPattern>> SHERD_TO_PATTERN = Map.of(
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

    private static ResourceKey<DecoratedPotPattern> createKey(String name) {
        return ResourceKey.create(Registries.DECORATED_POT_PATTERN, Verdance.id(name));
    }

    private static DecoratedPotPattern register(ResourceKey<DecoratedPotPattern> key, String assetId) {
        return Registry.register(BuiltInRegistries.DECORATED_POT_PATTERN, key, new DecoratedPotPattern(Verdance.id(assetId)));
    }
}
