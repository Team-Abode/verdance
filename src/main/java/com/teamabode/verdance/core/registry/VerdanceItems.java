package com.teamabode.verdance.core.registry;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.core.misc.VerdanceConsumableComponents;
import com.teamabode.verdance.core.misc.VerdanceFoodComponents;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.item.*;
import net.minecraft.item.Item.Settings;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Rarity;

import java.util.function.Function;

public class VerdanceItems {
    public static final Item CANTALOUPE_SLICE = register(
            "cantaloupe_slice",
            new Settings()
                    .food(VerdanceFoodComponents.CANTALOUPE_SLICE, VerdanceConsumableComponents.CANTALOUPE_SLICE)
    );
    public static final Item GRILLED_CANTALOUPE_SLICE = register(
            "grilled_cantaloupe_slice",
            new Settings()
                    .food(VerdanceFoodComponents.GRILLED_CANTALOUPE_SLICE)

    );
    public static final Item CANTALOUPE_JUICE = register(
            "cantaloupe_juice",
            new Settings()
                    .food(VerdanceFoodComponents.CANTALOUPE_JUICE, VerdanceConsumableComponents.CANTALOUPE_JUICE)
    );
    public static final Item CANTALOUPE_SEEDS = register(
            "cantaloupe_seeds",
            settings -> new BlockItem(VerdanceBlocks.CANTALOUPE_STEM, settings),
            new Settings()
                    .useItemPrefixedTranslationKey()
    );
    public static final Item MULBERRY = register(
            "mulberry",
            settings -> new BlockItem(VerdanceBlocks.MULBERRY_SAPLING, settings),
            new Settings()
                    .useItemPrefixedTranslationKey()
                    .food(VerdanceFoodComponents.MULBERRY)
    );
    public static final Item MULBERRY_SIGN = register(
            "mulberry_sign",
            settings -> new SignItem(VerdanceBlocks.MULBERRY_SIGN, VerdanceBlocks.MULBERRY_WALL_SIGN, settings),
            new Settings()
                    .maxCount(16)
    );
    public static final Item MULBERRY_HANGING_SIGN = register(
            "mulberry_hanging_sign",
            settings -> new HangingSignItem(VerdanceBlocks.MULBERRY_HANGING_SIGN, VerdanceBlocks.MULBERRY_WALL_HANGING_SIGN, settings),
            new Settings()
                    .maxCount(16)
    );
    public static final Item MULBERRY_BOAT = register(
            "mulberry_boat",
            settings -> new BoatItem(VerdanceEntityTypes.MULBERRY_BOAT, settings),
            new Settings()
                    .maxCount(1)
    );
    public static final Item MULBERRY_CHEST_BOAT = register(
            "mulberry_chest_boat",
            settings -> new BoatItem(VerdanceEntityTypes.MULBERRY_CHEST_BOAT, settings),
            new Settings()
                    .maxCount(1)
    );
    public static final Item MUSIC_DISC_RANGE = register(
            "music_disc_range",
            new Settings()
                    .maxCount(1)
                    .rarity(Rarity.UNCOMMON)
                    .jukeboxPlayable(VerdanceJukeboxSongs.RANGE)
    );
    public static final Item DISC_FRAGMENT_RANGE = register(
            "disc_fragment_range",
            DiscFragmentItem::new,
            new Settings().rarity(Rarity.UNCOMMON)
    );

    public static final Item ABODE_POTTERY_SHERD = register("abode_pottery_sherd", new Settings().rarity(Rarity.UNCOMMON));
    public static final Item FRILLS_POTTERY_SHERD = register("frills_pottery_sherd", new Settings().rarity(Rarity.UNCOMMON));
    public static final Item PITCH_POTTERY_SHERD = register("pitch_pottery_sherd", new Settings().rarity(Rarity.UNCOMMON));
    public static final Item PRICKLE_POTTERY_SHERD = register("prickle_pottery_sherd", new Settings().rarity(Rarity.UNCOMMON));
    public static final Item SPIRIT_POTTERY_SHERD = register("spirit_pottery_sherd", new Settings().rarity(Rarity.UNCOMMON));
    public static final Item TRAP_POTTERY_SHERD = register("trap_pottery_sherd", new Settings().rarity(Rarity.UNCOMMON));

    public static final Item HERITAGE_ARMOR_TRIM_SMITHING_TEMPLATE = register(
            "heritage_armor_trim_smithing_template",
            SmithingTemplateItem::of,
            new Settings().rarity(Rarity.UNCOMMON)
    );

    public static final Item SILK_MOTH_SPAWN_EGG = register(
            "silk_moth_spawn_egg",
            settings -> new SpawnEggItem(VerdanceEntityTypes.SILK_MOTH, settings),
            new Settings()
    );
    public static final Item SILKWORM_SPAWN_EGG = register(
            "silkworm_spawn_egg",
            settings -> new SpawnEggItem(VerdanceEntityTypes.SILKWORM, settings),
            new Settings()
    );

    public static void register() {
        CompostingChanceRegistry.INSTANCE.add(VerdanceBlocks.CANTALOUPE, 0.65F);
        CompostingChanceRegistry.INSTANCE.add(CANTALOUPE_SEEDS, 0.30F);
        CompostingChanceRegistry.INSTANCE.add(CANTALOUPE_SLICE, 0.50F);
        CompostingChanceRegistry.INSTANCE.add(VerdanceBlocks.FLOWERING_MULBERRY_LEAVES, 0.50F);
        CompostingChanceRegistry.INSTANCE.add(GRILLED_CANTALOUPE_SLICE, 0.65F);
        CompostingChanceRegistry.INSTANCE.add(MULBERRY, 0.30F);
        CompostingChanceRegistry.INSTANCE.add(VerdanceBlocks.MULBERRY_LEAVES, 0.30F);
        CompostingChanceRegistry.INSTANCE.add(VerdanceBlocks.VIOLET, 0.65f);
        CompostingChanceRegistry.INSTANCE.add(VerdanceBlocks.DESERT_BUSH, 0.30f);
        CompostingChanceRegistry.INSTANCE.add(VerdanceBlocks.YELLOW_FLOWERING_DESERT_BUSH, 0.30f);
        CompostingChanceRegistry.INSTANCE.add(VerdanceBlocks.PINK_FLOWERING_DESERT_BUSH, 0.30f);
    }

    // Utils
    private static Item register(String name, Function<Settings, Item> item, Settings settings) {
        var key = RegistryKey.of(RegistryKeys.ITEM, Verdance.id(name));

        return Registry.register(Registries.ITEM, key, item.apply(settings.registryKey(key)));
    }

    private static Item register(String name, Settings settings) {
        return register(name, Item::new, settings);
    }

    private static Item register(String name) {
        return register(name, new Settings());
    }
}
