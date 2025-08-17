package com.teamabode.verdance.common.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.List;
import net.minecraft.core.Holder;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public record DecayPatchConfiguration(Patch patch, List<Distance> distances) implements FeatureConfiguration {
    public static final Codec<DecayPatchConfiguration> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Patch.CODEC.fieldOf("patch").forGetter(DecayPatchConfiguration::patch),
            Distance.CODEC.listOf().fieldOf("distances").forGetter(DecayPatchConfiguration::distances)
    ).apply(instance, DecayPatchConfiguration::new));

    public record Patch(int tries, int horizontalSpread, int verticalSpread) {
        public static final Codec<Patch> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                ExtraCodecs.POSITIVE_INT.fieldOf("tries").orElse(128).forGetter(Patch::tries),
                ExtraCodecs.NON_NEGATIVE_INT.fieldOf("horizontal_spread").orElse(7).forGetter(Patch::horizontalSpread),
                ExtraCodecs.NON_NEGATIVE_INT.fieldOf("vertical_spread").orElse(3).forGetter(Patch::verticalSpread)
        ).apply(instance, Patch::new));
    }

    public record Distance(int distance, Holder<PlacedFeature> feature) {
        public static final Codec<Distance> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                ExtraCodecs.NON_NEGATIVE_INT.fieldOf("distance").forGetter(Distance::distance),
                PlacedFeature.CODEC.fieldOf("feature").forGetter(Distance::feature)
        ).apply(instance, Distance::new));
    }
}
