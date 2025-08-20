package com.teamabode.verdance.core.registry;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.core.tag.VerdanceBiomeTags;
import net.minecraft.entity.passive.WolfVariant;
import net.minecraft.entity.spawn.BiomeSpawnCondition;
import net.minecraft.entity.spawn.SpawnConditionSelectors;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.AssetInfo;

public class VerdanceWolfVariants {

    public static final RegistryKey<WolfVariant> GOLDEN = createKey("golden");

    public static void register(Registerable<WolfVariant> context) {
        var biomes = context.getRegistryLookup(RegistryKeys.BIOME);
        var hasGoldenWolf = biomes.getOrThrow(VerdanceBiomeTags.HAS_GOLDEN_WOLF);

        var wildTexture = Verdance.id("entity/wolf/wolf_golden");
        var tameTexture = Verdance.id("entity/wolf/wolf_golden_tame");
        var angryTexture = Verdance.id("entity/wolf/wolf_golden_angry");

        context.register(GOLDEN, new WolfVariant(
                new WolfVariant.WolfAssetInfo(
                        new AssetInfo(wildTexture),
                        new AssetInfo(tameTexture),
                        new AssetInfo(angryTexture)
                ),
                SpawnConditionSelectors.createSingle(new BiomeSpawnCondition(hasGoldenWolf), 1)
        ));
    }

    private static RegistryKey<WolfVariant> createKey(String name) {
        return RegistryKey.of(RegistryKeys.WOLF_VARIANT, Verdance.id(name));
    }
}
