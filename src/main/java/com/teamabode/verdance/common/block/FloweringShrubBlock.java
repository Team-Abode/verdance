package com.teamabode.verdance.common.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class FloweringShrubBlock extends ShrubBlock {
    public static final MapCodec<FloweringShrubBlock> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            ResourceKey.codec(Registries.CONFIGURED_FEATURE).fieldOf("feature").forGetter(block -> block.feature),
            propertiesCodec()
    ).apply(instance, FloweringShrubBlock::new));

    private final ResourceKey<ConfiguredFeature<?, ?>> feature;

    public FloweringShrubBlock(ResourceKey<ConfiguredFeature<?, ?>> feature, Properties properties) {
        super(properties);
        this.feature = feature;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return random.nextFloat() < 0.333f;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        RegistryAccess registryAccess = level.registryAccess();
        var configuredFeatures = registryAccess.registry(Registries.CONFIGURED_FEATURE);
        var featureToPlace = configuredFeatures.flatMap(registry -> registry.getHolder(this.feature));

        featureToPlace.ifPresent(reference -> reference.value().place(level, level.getChunkSource().getGenerator(), random, pos));
    }

    @Override
    protected MapCodec<FloweringShrubBlock> codec() {
        return CODEC;
    }
}
