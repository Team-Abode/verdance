package com.teamabode.verdance.core.registry.misc;

import com.mojang.serialization.Codec;
import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.feature.MulberryTrunkPlacer;
import com.teamabode.verdance.core.mixin.general.TrunkPlacerTypeMixin;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

public class VerdanceTrunkPlacerType {
    public static final TrunkPlacerType<MulberryTrunkPlacer> MULBERRY_TRUNK_PLACER = register("mulberry_trunk_placer", MulberryTrunkPlacer.CODEC);

    private static <P extends TrunkPlacer> TrunkPlacerType<P> register(String key, Codec<P> codec) {
        return TrunkPlacerTypeMixin.callRegister(new ResourceLocation(Verdance.MOD_ID, key).toString(), codec);
    }

    public static void register() {

    }
}
