package com.teamabode.verdance.core.registry;

import com.teamabode.sketch.common.item.SketchBoatItem;
import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.item.CantaloupeJuiceItem;
import com.teamabode.verdance.common.item.CantaloupeSliceItem;
import com.teamabode.verdance.core.misc.VerdanceFoods;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;

public class VerdanceItems {
    public static final Item CANTALOUPE_SLICE = register("cantaloupe_slice", new CantaloupeSliceItem((new Item.Properties()).food(VerdanceFoods.CANTALOUPE_SLICE)));
    public static final Item GRILLED_CANTALOUPE_SLICE = register("grilled_cantaloupe_slice", new Item(new Item.Properties().food(VerdanceFoods.GRILLED_CANTALOUPE_SLICE)));
    public static final Item CANTALOUPE_JUICE = register("cantaloupe_juice", new CantaloupeJuiceItem(new Item.Properties().stacksTo(16)));

    public static final Item CANTALOUPE_SEEDS = register("cantaloupe_seeds", new ItemNameBlockItem(VerdanceBlocks.CANTALOUPE_STEM, new Item.Properties()));

    public static final Item MULBERRY = register("mulberry", new ItemNameBlockItem(VerdanceBlocks.MULBERRY_SAPLING, new Item.Properties().food(VerdanceFoods.MULBERRY)));

    public static final Item MUSIC_DISC_RANGE = register("music_disc_range", new RecordItem(1, VerdanceSounds.MUSIC_DISC_RANGE, new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 87));
    public static final Item DISC_FRAGMENT_RANGE = register("disc_fragment_range", new DiscFragmentItem(new Item.Properties()));

    public static final Item MULBERRY_SIGN = register("mulberry_sign", new SignItem(new Item.Properties().stacksTo(16), VerdanceBlocks.MULBERRY_SIGN, VerdanceBlocks.MULBERRY_WALL_SIGN));
    public static final Item MULBERRY_HANGING_SIGN = register("mulberry_hanging_sign", new HangingSignItem(VerdanceBlocks.MULBERRY_HANGING_SIGN, VerdanceBlocks.MULBERRY_WALL_HANGING_SIGN, new Item.Properties().stacksTo(16)));

    public static final Item MULBERRY_BOAT = register("mulberry_boat", new SketchBoatItem(new Item.Properties().stacksTo(1), VerdanceBoatType.MULBERRY, false));
    public static final Item MULBERRY_CHEST_BOAT = register("mulberry_chest_boat", new SketchBoatItem(new Item.Properties().stacksTo(1), VerdanceBoatType.MULBERRY, true));

    public static final Item SILK_MOTH_SPAWN_EGG = register("silk_moth_spawn_egg", new SpawnEggItem(VerdanceEntities.SILK_MOTH, 13542773, 16383172, new Item.Properties()));
    public static final Item SILKWORM_SPAWN_EGG = register("silkworm_spawn_egg", new SpawnEggItem(VerdanceEntities.SILKWORM, 0xEBEDE6, 0x75665D, new Item.Properties()));

    private static <T extends Item> T register(String name, T item) {
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Verdance.MOD_ID, name), item);
    }

    public static void register() {
        CompostingChanceRegistry.INSTANCE.add(VerdanceBlocks.CANTALOUPE, 0.65F);
        CompostingChanceRegistry.INSTANCE.add(CANTALOUPE_SEEDS, 0.30F);
        CompostingChanceRegistry.INSTANCE.add(CANTALOUPE_SLICE, 0.50F);
        CompostingChanceRegistry.INSTANCE.add(VerdanceBlocks.FLOWERING_MULBERRY_LEAVES, 0.50F);
        CompostingChanceRegistry.INSTANCE.add(GRILLED_CANTALOUPE_SLICE, 0.65F);
        CompostingChanceRegistry.INSTANCE.add(MULBERRY, 0.30F);
        CompostingChanceRegistry.INSTANCE.add(VerdanceBlocks.MULBERRY_LEAVES, 0.30F);
        CompostingChanceRegistry.INSTANCE.add(VerdanceBlocks.SHRUB, 0.65F);
    }
}
