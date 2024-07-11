package com.teamabode.verdance.core.tag;

import com.teamabode.verdance.Verdance;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class VerdanceItemTags {
    public static final TagKey<Item> MULBERRY_LOGS = create("mulberry_logs");
    // TODO: SILK_MOTH_FOOD tag convention
    public static final TagKey<Item> SILKWORM_FOOD = create("silkworm_food");

    private static TagKey<Item> create(String name) {
        return TagKey.create(Registries.ITEM, Verdance.id(name));
    }
}
