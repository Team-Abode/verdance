package com.teamabode.verdance.common.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class FloweringDesertBushBlock extends DesertBushBlock {
    public static final MapCodec<FloweringDesertBushBlock> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            RegistryKey.createCodec(RegistryKeys.CONFIGURED_FEATURE).fieldOf("feature").forGetter(block -> block.feature),
            createSettingsCodec()
    ).apply(instance, FloweringDesertBushBlock::new));

    private final RegistryKey<ConfiguredFeature<?, ?>> feature;

    public FloweringDesertBushBlock(RegistryKey<ConfiguredFeature<?, ?>> feature, Settings properties) {
        super(properties);
        this.feature = feature;
    }

    @Override
    public boolean canGrow(World level, Random random, BlockPos pos, BlockState state) {
        return random.nextFloat() < 0.333f;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        DynamicRegistryManager registryAccess = world.getRegistryManager();
        var configuredFeatures = registryAccess.getOptional(RegistryKeys.CONFIGURED_FEATURE);
        var featureToPlace = configuredFeatures.flatMap(registry -> registry.getOptional(this.feature));

        featureToPlace.ifPresent(reference -> reference.value().generate(world, world.getChunkManager().getChunkGenerator(), random, pos));
    }

    @Override
    protected MapCodec<FloweringDesertBushBlock> getCodec() {
        return CODEC;
    }
}
