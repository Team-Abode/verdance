package com.teamabode.verdance.core.key;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.core.tag.VerdanceBiomeTags;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.animal.WolfVariant;

public class VerdanceWolfVariants {

    public static final ResourceKey<WolfVariant> GOLDEN = createKey("golden");

    public static void register(BootstrapContext<WolfVariant> context) {
        var biomes = context.lookup(Registries.BIOME);

        context.register(GOLDEN, new WolfVariant(
                Verdance.id("entity/wolf/wolf/wolf_golden"),
                Verdance.id("entity/wolf/wolf/wolf_golden_tame"),
                Verdance.id("entity/wolf/wolf/wolf_golden_angry"),
                biomes.getOrThrow(VerdanceBiomeTags.HAS_GOLDEN_WOLF)
        ));
    }

    private static ResourceKey<WolfVariant> createKey(String name) {
        return ResourceKey.create(Registries.WOLF_VARIANT, Verdance.id(name));
    }
}
