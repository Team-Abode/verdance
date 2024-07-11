package com.teamabode.verdance.core.misc;

import com.teamabode.verdance.core.key.VerdanceBiomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.SurfaceRules.RuleSource;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.CaveSurface;

public class VerdanceSurfaceRules {

    private static final RuleSource BEDROCK_FLOOR = SurfaceRules.ifTrue(SurfaceRules.verticalGradient("bedrock_floor", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5)), SurfaceRules.state(Blocks.BEDROCK.defaultBlockState()));
    private static final RuleSource DEEPSLATE_LAYER = SurfaceRules.ifTrue(SurfaceRules.verticalGradient("deepslate_layer", VerticalAnchor.bottom(), VerticalAnchor.absolute(8)), SurfaceRules.state(Blocks.DEEPSLATE.defaultBlockState()));

    private static final RuleSource SHRUBLANDS = SurfaceRules.ifTrue(
            SurfaceRules.abovePreliminarySurface(),
            SurfaceRules.sequence(
                    SurfaceRules.ifTrue(
                            SurfaceRules.stoneDepthCheck(0, true, CaveSurface.FLOOR),
                            SurfaceRules.state(Blocks.SAND.defaultBlockState())
                    ),
                    SurfaceRules.ifTrue(
                            SurfaceRules.stoneDepthCheck(3, true, 0, CaveSurface.FLOOR),
                            SurfaceRules.state(Blocks.SANDSTONE.defaultBlockState())
                    )
            )
    );

    public static final RuleSource OVERWORLD = SurfaceRules.ifTrue(
            SurfaceRules.isBiome(VerdanceBiomes.SHRUBLANDS),
            SurfaceRules.sequence(
                    BEDROCK_FLOOR,
                    DEEPSLATE_LAYER,
                    SHRUBLANDS
            )
    );
}