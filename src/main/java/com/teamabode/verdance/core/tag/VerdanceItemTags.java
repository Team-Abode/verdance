package com.teamabode.verdance.core.tag;

import com.teamabode.verdance.Verdance;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class VerdanceItemTags {
    public static final TagKey<Item> MULBERRY_LOGS = create("mulberry_logs");
    public static final TagKey<Item> SILK_MOTH_FOOD = create("silk_moth_food");
    public static final TagKey<Item> SILKWORM_FOOD = create("silkworm_food");

    private static TagKey<Item> create(String name) {
        return TagKey.of(RegistryKeys.ITEM, Verdance.id(name));
    }
}
