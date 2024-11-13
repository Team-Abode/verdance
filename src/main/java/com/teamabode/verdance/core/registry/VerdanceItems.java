package com.teamabode.verdance.core.registry;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.item.CantaloupeJuiceItem;
import com.teamabode.verdance.core.misc.VerdanceFoodComponents;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.item.*;
import net.minecraft.item.Item.Settings;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Rarity;

public class VerdanceItems {
    public static final Item CANTALOUPE_SLICE = register(
            "cantaloupe_slice",
            new Settings()
                    .food(VerdanceFoodComponents.CANTALOUPE_SLICE)
    );
    public static final Item GRILLED_CANTALOUPE_SLICE = register(
            "grilled_cantaloupe_slice",
            new Settings()
                    .food(VerdanceFoodComponents.GRILLED_CANTALOUPE_SLICE)
    );
    public static final Item CANTALOUPE_JUICE = register(
            "cantaloupe_juice",
            new CantaloupeJuiceItem(new Settings()
                    .food(VerdanceFoodComponents.CANTALOUPE_JUICE)
                    .maxCount(16))
    );
    public static final Item CANTALOUPE_SEEDS = register(
            "cantaloupe_seeds",
            new AliasedBlockItem(VerdanceBlocks.CANTALOUPE_STEM, new Settings())
    );
    public static final Item MULBERRY = register(
            "mulberry",
            new AliasedBlockItem(VerdanceBlocks.MULBERRY_SAPLING, new Settings()
                    .food(VerdanceFoodComponents.MULBERRY))
    );
    public static final Item MULBERRY_SIGN = register(
            "mulberry_sign",
            new SignItem(
                    new Settings().maxCount(16),
                    VerdanceBlocks.MULBERRY_SIGN,
                    VerdanceBlocks.MULBERRY_WALL_SIGN)
    );
    public static final Item MULBERRY_HANGING_SIGN = register(
            "mulberry_hanging_sign",
            new HangingSignItem(VerdanceBlocks.MULBERRY_HANGING_SIGN, VerdanceBlocks.MULBERRY_WALL_HANGING_SIGN, new Settings()
                    .maxCount(16))
    );
    public static final Item MULBERRY_BOAT = register(
            "mulberry_boat", new BoatItem(
                    false,
                    VerdanceBoatTypes.MULBERRY,
                    new Settings().maxCount(1))
    );
    public static final Item MULBERRY_CHEST_BOAT = register(
            "mulberry_chest_boat", new BoatItem(
                    true,
                    VerdanceBoatTypes.MULBERRY,
                    new Settings().maxCount(1))
    );
    public static final Item MUSIC_DISC_RANGE = register(
            "music_disc_range",
            new MusicDiscItem(1, VerdanceSoundEvents.MUSIC_DISC_RANGE, new Settings().rarity(Rarity.RARE).maxCount(1), 87)
    );
    public static final Item DISC_FRAGMENT_RANGE = register(
            "disc_fragment_range",
            new DiscFragmentItem(new Settings())
    );

    public static final Item ABODE_POTTERY_SHERD = register("abode_pottery_sherd");
    public static final Item FRILLS_POTTERY_SHERD = register("frills_pottery_sherd");
    public static final Item PITCH_POTTERY_SHERD = register("pitch_pottery_sherd");
    public static final Item PRICKLE_POTTERY_SHERD = register("prickle_pottery_sherd");
    public static final Item SPIRIT_POTTERY_SHERD = register("spirit_pottery_sherd");
    public static final Item TRAP_POTTERY_SHERD = register("trap_pottery_sherd");

    public static final Item COMMUNITY_ARMOR_TRIM_SMITHING_TEMPLATE = register(
            "community_armor_trim_smithing_template",
            SmithingTemplateItem.of(VerdanceTrimPatterns.COMMUNITY)
    );

    public static final Item SILK_MOTH_SPAWN_EGG = register(
            "silk_moth_spawn_egg",
            new SpawnEggItem(VerdanceEntityTypes.SILK_MOTH, 13542773, 16383172, new Settings())
    );
    public static final Item SILKWORM_SPAWN_EGG = register(
            "silkworm_spawn_egg",
            new SpawnEggItem(VerdanceEntityTypes.SILKWORM, 0xEBEDE6, 0x75665D, new Settings())
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
        CompostingChanceRegistry.INSTANCE.add(VerdanceBlocks.SHRUB, 0.30f);
        CompostingChanceRegistry.INSTANCE.add(VerdanceBlocks.YELLOW_FLOWERING_SHRUB, 0.30f);
        CompostingChanceRegistry.INSTANCE.add(VerdanceBlocks.PINK_FLOWERING_SHRUB, 0.30f);
    }

    // Utils
    private static Item register(String name) {
        return register(name, new Settings());
    }

    private static Item register(String name, Settings properties) {
        return register(name, new Item(properties));
    }

    private static <T extends Item> T register(String name, T item) {
        return Registry.register(Registries.ITEM, Verdance.id(name), item);
    }
}
