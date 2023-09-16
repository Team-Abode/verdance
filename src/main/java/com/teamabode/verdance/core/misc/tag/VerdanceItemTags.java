package com.teamabode.verdance.core.misc.tag;

import com.teamabode.verdance.Verdance;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class VerdanceItemTags {
    public static final TagKey<Item> MULBERRY_LOGS = create("mulberry_logs");
    public static final TagKey<Item> SILKWORM_FOOD = create("silkworm_food");

    private static TagKey<Item> create(String name) {
        return TagKey.create(Registries.ITEM, new ResourceLocation(Verdance.MOD_ID, name));
    }
}
