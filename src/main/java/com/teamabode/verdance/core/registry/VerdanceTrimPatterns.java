package com.teamabode.verdance.core.registry;

import com.teamabode.verdance.Verdance;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.armortrim.TrimPattern;

public class VerdanceTrimPatterns {

    public static final ResourceKey<TrimPattern> COMMUNITY = createKey("community");

    public static void register(BootstrapContext<TrimPattern> context) {
        context.register(COMMUNITY, new TrimPattern(
                Verdance.id("community"),
                BuiltInRegistries.ITEM.wrapAsHolder(VerdanceItems.COMMUNITY_ARMOR_TRIM_SMITHING_TEMPLATE),
                Component.translatable("trim_pattern.verdance.community"),
                false
        ));
    }

    private static ResourceKey<TrimPattern> createKey(String name) {
        return ResourceKey.create(Registries.TRIM_PATTERN, Verdance.id(name));
    }
}
