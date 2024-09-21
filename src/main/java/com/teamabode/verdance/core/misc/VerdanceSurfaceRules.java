package com.teamabode.verdance.core.misc;

import com.teamabode.verdance.core.registry.VerdanceBiomes;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.VerticalSurfaceType;
import net.minecraft.world.gen.noise.NoiseParametersKeys;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import net.minecraft.world.gen.surfacebuilder.MaterialRules.MaterialRule;

public class VerdanceSurfaceRules {

    public static MaterialRule shrublands() {
        MaterialRule coarseDirt = MaterialRules.condition(MaterialRules.stoneDepth(4, false, 0, VerticalSurfaceType.FLOOR), state(Blocks.COARSE_DIRT));

        MaterialRule aboveSurface = MaterialRules.condition(MaterialRules.surface(), MaterialRules.sequence(
                MaterialRules.condition(MaterialRules.noiseThreshold(NoiseParametersKeys.SURFACE, -0.9f, -0.5f), coarseDirt),
                MaterialRules.condition(MaterialRules.noiseThreshold(NoiseParametersKeys.SURFACE, -0.2f, 0.2f), coarseDirt),
                MaterialRules.condition(MaterialRules.noiseThreshold(NoiseParametersKeys.SURFACE, 0.5f, 0.9f), coarseDirt),
                applyDesertRules()
        ));

        return MaterialRules.condition(MaterialRules.biome(VerdanceBiomes.SHRUBLANDS), aboveSurface);
    }

    private static MaterialRule applyDesertRules() {
        MaterialRule sand = state(Blocks.SAND);
        MaterialRule floorSandstone = sandstone(VerticalSurfaceType.CEILING, false, 0);
        MaterialRule deepSandstone = sandstone(VerticalSurfaceType.FLOOR, true, 30);
        MaterialRule desertSurface = MaterialRules.sequence(floorSandstone, sand);

        MaterialRule floorDepthCheck = MaterialRules.condition(MaterialRules.stoneDepth(0, true, VerticalSurfaceType.FLOOR), desertSurface);
        MaterialRule floorSand = MaterialRules.condition(MaterialRules.waterWithStoneDepth(-6, -1), MaterialRules.sequence(floorDepthCheck, deepSandstone));

        MaterialRule ceilingWaterCheck = MaterialRules.condition(MaterialRules.water(-1,0), desertSurface);
        MaterialRule ceilingSand = MaterialRules.condition(MaterialRules.stoneDepth(0, false, VerticalSurfaceType.FLOOR), ceilingWaterCheck);

        return MaterialRules.sequence(ceilingSand, floorSand);
    }


    private static MaterialRule state(Block block) {
        return MaterialRules.block(block.getDefaultState());
    }

    private static MaterialRule sandstone(VerticalSurfaceType surfaceType, boolean addSurfaceDepth, int secondaryDepthRange) {
        return MaterialRules.condition(MaterialRules.stoneDepth(0, addSurfaceDepth, secondaryDepthRange, surfaceType), state(Blocks.SANDSTONE));
    }
}
