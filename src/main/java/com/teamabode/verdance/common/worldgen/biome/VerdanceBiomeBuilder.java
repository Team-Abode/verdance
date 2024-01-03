package com.teamabode.verdance.common.worldgen.biome;

import com.mojang.datafixers.util.Pair;
import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.core.misc.worldgen.VerdanceBiomes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;

import java.util.function.Consumer;

@SuppressWarnings({"SameParameterValue", "unused"})
public class VerdanceBiomeBuilder extends BiomeBuilder {
    @SuppressWarnings("all")
    private static final ResourceKey<Biome>[][] OCEAN_BIOMES = new ResourceKey[][]{
            {Biomes.DEEP_FROZEN_OCEAN, Biomes.DEEP_COLD_OCEAN, Biomes.DEEP_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN, Biomes.WARM_OCEAN},
            {Biomes.FROZEN_OCEAN, Biomes.COLD_OCEAN, Biomes.OCEAN, Biomes.LUKEWARM_OCEAN, Biomes.WARM_OCEAN}
    };
    @SuppressWarnings("all")
    private static final ResourceKey<Biome>[][] MIDDLE_BIOMES = new ResourceKey[][]{
            {Biomes.SNOWY_PLAINS, Biomes.SNOWY_PLAINS, Biomes.SNOWY_PLAINS, Biomes.SNOWY_TAIGA, Biomes.TAIGA},
            {Biomes.PLAINS, Biomes.PLAINS, Biomes.FOREST, Biomes.TAIGA, Biomes.OLD_GROWTH_SPRUCE_TAIGA},
            {Biomes.FLOWER_FOREST, Biomes.PLAINS, Biomes.FOREST, Biomes.BIRCH_FOREST, Biomes.DARK_FOREST},
            {VerdanceBiomes.SHRUBLANDS, Biomes.SAVANNA, Biomes.FOREST, Biomes.JUNGLE, Biomes.JUNGLE},
            {VerdanceBiomes.SHRUBLANDS, Biomes.DESERT, Biomes.DESERT, Biomes.DESERT, VerdanceBiomes.SHRUBLANDS}
    };
    @SuppressWarnings("all")
    private static final ResourceKey<Biome>[][] MIDDLE_BIOME_VARIANTS = new ResourceKey[][]{
            {Biomes.ICE_SPIKES, null, Biomes.SNOWY_TAIGA, null, null},
            {null, null, null, null, Biomes.OLD_GROWTH_PINE_TAIGA},
            {Biomes.SUNFLOWER_PLAINS, null, null, Biomes.OLD_GROWTH_BIRCH_FOREST, null},
            {null, null, Biomes.PLAINS, Biomes.SPARSE_JUNGLE, Biomes.BAMBOO_JUNGLE},
            {null, null, null, null, null}
    };
    @SuppressWarnings("all")
    private static final ResourceKey<Biome>[][] PLATEAU_BIOMES = new ResourceKey[][]{
            {Biomes.SNOWY_PLAINS, Biomes.SNOWY_PLAINS, Biomes.SNOWY_PLAINS, Biomes.SNOWY_TAIGA, Biomes.SNOWY_TAIGA},
            {VerdanceBiomes.MULBERRY_FOREST, VerdanceBiomes.MULBERRY_FOREST, VerdanceBiomes.MULBERRY_FOREST, VerdanceBiomes.MULBERRY_FOREST, VerdanceBiomes.MULBERRY_FOREST},
            {VerdanceBiomes.MULBERRY_FOREST, VerdanceBiomes.MULBERRY_FOREST, VerdanceBiomes.MULBERRY_FOREST, VerdanceBiomes.MULBERRY_FOREST, VerdanceBiomes.MULBERRY_FOREST},
            {Biomes.SAVANNA_PLATEAU, Biomes.SAVANNA_PLATEAU, Biomes.FOREST, Biomes.FOREST, Biomes.JUNGLE},
            {VerdanceBiomes.SHRUBLANDS, VerdanceBiomes.SHRUBLANDS, Biomes.BADLANDS, Biomes.WOODED_BADLANDS, Biomes.WOODED_BADLANDS}
    };
    @SuppressWarnings("all")
    private static final ResourceKey<Biome>[][] PLATEAU_BIOME_VARIANTS = new ResourceKey[][]{
            {Biomes.ICE_SPIKES, null, null, null, null},
            {Biomes.CHERRY_GROVE, null, Biomes.MEADOW, Biomes.MEADOW, Biomes.OLD_GROWTH_PINE_TAIGA},
            {VerdanceBiomes.MULBERRY_FOREST, VerdanceBiomes.MULBERRY_FOREST, Biomes.FOREST, Biomes.BIRCH_FOREST, null},
            {null, null, null, null, null},
            {Biomes.ERODED_BADLANDS, Biomes.ERODED_BADLANDS, null, null, null}
    };
    @SuppressWarnings("all")
    private static final ResourceKey<Biome>[][] SHATTERED_BIOMES = new ResourceKey[][]{
            {Biomes.WINDSWEPT_GRAVELLY_HILLS, Biomes.WINDSWEPT_GRAVELLY_HILLS, Biomes.WINDSWEPT_HILLS, Biomes.WINDSWEPT_FOREST, Biomes.WINDSWEPT_FOREST},
            {Biomes.WINDSWEPT_GRAVELLY_HILLS, Biomes.WINDSWEPT_GRAVELLY_HILLS, Biomes.WINDSWEPT_HILLS, Biomes.WINDSWEPT_FOREST, Biomes.WINDSWEPT_FOREST},
            {Biomes.WINDSWEPT_HILLS, Biomes.WINDSWEPT_HILLS, Biomes.WINDSWEPT_HILLS, Biomes.WINDSWEPT_FOREST, Biomes.WINDSWEPT_FOREST},
            {null, null, null, null, null},
            {null, null, null, null, null}
    };
    private static final Climate.Parameter[] TEMPERATURE_POINTS = {
            Climate.Parameter.span(-1.0F, -0.45F),
            Climate.Parameter.span(-0.45F, -0.15F),
            Climate.Parameter.span(-0.15F, 0.2F),
            Climate.Parameter.span(0.2F, 0.55F),
            Climate.Parameter.span(0.55F, 1.0F)
    };
    private static final Climate.Parameter[] HUMIDITY_POINTS = {
            Climate.Parameter.span(-1.0F, -0.35F),
            Climate.Parameter.span(-0.35F, -0.1F),
            Climate.Parameter.span(-0.1F, 0.1F),
            Climate.Parameter.span(0.1F, 0.3F),
            Climate.Parameter.span(0.3F, 1.0F)
    };
    private static final Climate.Parameter[] EROSION_POINTS = {
            Climate.Parameter.span(-1.0F, -0.78F),
            Climate.Parameter.span(-0.78F, -0.375F),
            Climate.Parameter.span(-0.375F, -0.2225F),
            Climate.Parameter.span(-0.2225F, 0.05F),
            Climate.Parameter.span(0.05F, 0.45F),
            Climate.Parameter.span(0.45F, 0.55F),
            Climate.Parameter.span(0.55F, 1.0F)
    };
    private static final Climate.Parameter FROZEN_RANGE = TEMPERATURE_POINTS[0];
    private static final Climate.Parameter UNFROZEN_RANGE = Climate.Parameter.span(TEMPERATURE_POINTS[1], TEMPERATURE_POINTS[4]);
    private static final Climate.Parameter MUSHROOM_FIELDS_CONTINENTALNESS = Climate.Parameter.span(-1.2F, -1.05F);;
    private static final Climate.Parameter DEEP_OCEAN_CONTINENTALNESS = Climate.Parameter.span(-1.05F, -0.455F);
    private static final Climate.Parameter OCEAN_CONTINENTALNESS = Climate.Parameter.span(-0.455F, -0.19F);
    private static final Climate.Parameter COAST_CONTINENTALNESS = Climate.Parameter.span(-0.19F, -0.11F);
    private static final Climate.Parameter INLAND_CONTINENTALNESS = Climate.Parameter.span(-0.11F, 0.55F);
    private static final Climate.Parameter NEAR_INLAND_CONTINENTALNESS = Climate.Parameter.span(-0.11F, 0.03F);
    private static final Climate.Parameter MID_INLAND_CONTINENTALNESS = Climate.Parameter.span(0.03F, 0.3F);
    private static final Climate.Parameter FAR_INLAND_CONTINENTALNESS = Climate.Parameter.span(0.3F, 1.0F);

    public void addBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        this.addOffCoastBiomes(mapper);
        this.addInlandBiomes(mapper);
        this.addUndergroundBiomes(mapper);
    }

    private void addOffCoastBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer) {
        this.addSurfaceBiome(consumer, FULL_RANGE, FULL_RANGE, MUSHROOM_FIELDS_CONTINENTALNESS, FULL_RANGE, FULL_RANGE, 0.0F, Biomes.MUSHROOM_FIELDS);

        for(int i = 0; i < TEMPERATURE_POINTS.length; ++i) {
            Climate.Parameter parameter = TEMPERATURE_POINTS[i];
            this.addSurfaceBiome(consumer, parameter, FULL_RANGE, DEEP_OCEAN_CONTINENTALNESS, FULL_RANGE, FULL_RANGE, 0.0F, OCEAN_BIOMES[0][i]);
            this.addSurfaceBiome(consumer, parameter, FULL_RANGE, OCEAN_CONTINENTALNESS, FULL_RANGE, FULL_RANGE, 0.0F, OCEAN_BIOMES[1][i]);
        }
    }

    private void addInlandBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer) {
        this.addMidSlice(consumer, Climate.Parameter.span(-1.0F, -0.93333334F));
        this.addHighSlice(consumer, Climate.Parameter.span(-0.93333334F, -0.7666667F));
        this.addPeaks(consumer, Climate.Parameter.span(-0.7666667F, -0.56666666F));
        this.addHighSlice(consumer, Climate.Parameter.span(-0.56666666F, -0.4F));
        this.addMidSlice(consumer, Climate.Parameter.span(-0.4F, -0.26666668F));
        this.addLowSlice(consumer, Climate.Parameter.span(-0.26666668F, -0.05F));
        this.addValleys(consumer, Climate.Parameter.span(-0.05F, 0.05F));
        this.addLowSlice(consumer, Climate.Parameter.span(0.05F, 0.26666668F));
        this.addMidSlice(consumer, Climate.Parameter.span(0.26666668F, 0.4F));
        this.addHighSlice(consumer, Climate.Parameter.span(0.4F, 0.56666666F));
        this.addPeaks(consumer, Climate.Parameter.span(0.56666666F, 0.7666667F));
        this.addHighSlice(consumer, Climate.Parameter.span(0.7666667F, 0.93333334F));
        this.addMidSlice(consumer, Climate.Parameter.span(0.93333334F, 1.0F));
    }

    private void addPeaks(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, Climate.Parameter param) {
        for(int i = 0; i < TEMPERATURE_POINTS.length; ++i) {
            Climate.Parameter parameter = TEMPERATURE_POINTS[i];

            for(int j = 0; j < HUMIDITY_POINTS.length; ++j) {
                Climate.Parameter parameter2 = HUMIDITY_POINTS[j];
                ResourceKey<Biome> resourceKey = this.pickMiddleBiome(i, j, param);
                ResourceKey<Biome> resourceKey2 = this.pickMiddleBiomeOrBadlandsIfHot(i, j, param);
                ResourceKey<Biome> resourceKey3 = this.pickMiddleBiomeOrBadlandsIfHotOrSlopeIfCold(i, j, param);
                ResourceKey<Biome> resourceKey4 = this.pickPlateauBiome(i, j, param);
                ResourceKey<Biome> resourceKey5 = this.pickShatteredBiome(i, j, param);
                ResourceKey<Biome> resourceKey6 = this.maybePickWindsweptSavannaBiome(i, j, param, resourceKey5);
                ResourceKey<Biome> resourceKey7 = this.pickPeakBiome(i, j, param);
                this.addSurfaceBiome(consumer, parameter, parameter2, Climate.Parameter.span(COAST_CONTINENTALNESS, FAR_INLAND_CONTINENTALNESS), EROSION_POINTS[0], param, 0.0F, resourceKey7);
                this.addSurfaceBiome(consumer, parameter, parameter2, Climate.Parameter.span(COAST_CONTINENTALNESS, NEAR_INLAND_CONTINENTALNESS), EROSION_POINTS[1], param, 0.0F, resourceKey3);
                this.addSurfaceBiome(consumer, parameter, parameter2, Climate.Parameter.span(MID_INLAND_CONTINENTALNESS, FAR_INLAND_CONTINENTALNESS), EROSION_POINTS[1], param, 0.0F, resourceKey7);
                this.addSurfaceBiome(consumer, parameter, parameter2, Climate.Parameter.span(COAST_CONTINENTALNESS, NEAR_INLAND_CONTINENTALNESS), Climate.Parameter.span(EROSION_POINTS[2], EROSION_POINTS[3]), param, 0.0F, resourceKey);
                this.addSurfaceBiome(consumer, parameter, parameter2, Climate.Parameter.span(MID_INLAND_CONTINENTALNESS, FAR_INLAND_CONTINENTALNESS), EROSION_POINTS[2], param, 0.0F, resourceKey4);
                this.addSurfaceBiome(consumer, parameter, parameter2, MID_INLAND_CONTINENTALNESS, EROSION_POINTS[3], param, 0.0F, resourceKey2);
                this.addSurfaceBiome(consumer, parameter, parameter2, FAR_INLAND_CONTINENTALNESS, EROSION_POINTS[3], param, 0.0F, resourceKey4);
                this.addSurfaceBiome(consumer, parameter, parameter2, Climate.Parameter.span(COAST_CONTINENTALNESS, FAR_INLAND_CONTINENTALNESS), EROSION_POINTS[4], param, 0.0F, resourceKey);
                this.addSurfaceBiome(consumer, parameter, parameter2, Climate.Parameter.span(COAST_CONTINENTALNESS, NEAR_INLAND_CONTINENTALNESS), EROSION_POINTS[5], param, 0.0F, resourceKey6);
                this.addSurfaceBiome(consumer, parameter, parameter2, Climate.Parameter.span(MID_INLAND_CONTINENTALNESS, FAR_INLAND_CONTINENTALNESS), EROSION_POINTS[5], param, 0.0F, resourceKey5);
                this.addSurfaceBiome(consumer, parameter, parameter2, Climate.Parameter.span(COAST_CONTINENTALNESS, FAR_INLAND_CONTINENTALNESS), EROSION_POINTS[6], param, 0.0F, resourceKey);
            }
        }

    }

    private void addHighSlice(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, Climate.Parameter param) {
        for(int i = 0; i < TEMPERATURE_POINTS.length; ++i) {
            Climate.Parameter temperatureSpan = TEMPERATURE_POINTS[i];

            for(int j = 0; j < HUMIDITY_POINTS.length; ++j) {
                Climate.Parameter humiditySpan = HUMIDITY_POINTS[j];
                ResourceKey<Biome> resourceKey = this.pickMiddleBiome(i, j, param);
                ResourceKey<Biome> resourceKey2 = this.pickMiddleBiomeOrBadlandsIfHot(i, j, param);
                ResourceKey<Biome> resourceKey3 = this.pickMiddleBiomeOrBadlandsIfHotOrSlopeIfCold(i, j, param);
                ResourceKey<Biome> resourceKey4 = this.pickPlateauBiome(i, j, param);
                ResourceKey<Biome> resourceKey5 = this.pickShatteredBiome(i, j, param);
                ResourceKey<Biome> resourceKey6 = this.maybePickWindsweptSavannaBiome(i, j, param, resourceKey);
                ResourceKey<Biome> resourceKey7 = this.pickSlopeBiome(i, j, param);
                ResourceKey<Biome> resourceKey8 = this.pickPeakBiome(i, j, param);
                this.addSurfaceBiome(consumer, temperatureSpan, humiditySpan, COAST_CONTINENTALNESS, Climate.Parameter.span(EROSION_POINTS[0], EROSION_POINTS[1]), param, 0.0F, resourceKey);
                this.addSurfaceBiome(consumer, temperatureSpan, humiditySpan, NEAR_INLAND_CONTINENTALNESS, EROSION_POINTS[0], param, 0.0F, resourceKey7);
                this.addSurfaceBiome(consumer, temperatureSpan, humiditySpan, Climate.Parameter.span(MID_INLAND_CONTINENTALNESS, FAR_INLAND_CONTINENTALNESS), EROSION_POINTS[0], param, 0.0F, resourceKey8);
                this.addSurfaceBiome(consumer, temperatureSpan, humiditySpan, NEAR_INLAND_CONTINENTALNESS, EROSION_POINTS[1], param, 0.0F, resourceKey3);
                this.addSurfaceBiome(consumer, temperatureSpan, humiditySpan, Climate.Parameter.span(MID_INLAND_CONTINENTALNESS, FAR_INLAND_CONTINENTALNESS), EROSION_POINTS[1], param, 0.0F, resourceKey7);
                this.addSurfaceBiome(consumer, temperatureSpan, humiditySpan, Climate.Parameter.span(COAST_CONTINENTALNESS, NEAR_INLAND_CONTINENTALNESS), Climate.Parameter.span(EROSION_POINTS[2], EROSION_POINTS[3]), param, 0.0F, resourceKey);
                this.addSurfaceBiome(consumer, temperatureSpan, humiditySpan, Climate.Parameter.span(MID_INLAND_CONTINENTALNESS, FAR_INLAND_CONTINENTALNESS), EROSION_POINTS[2], param, 0.0F, resourceKey4);
                this.addSurfaceBiome(consumer, temperatureSpan, humiditySpan, MID_INLAND_CONTINENTALNESS, EROSION_POINTS[3], param, 0.0F, resourceKey2);
                this.addSurfaceBiome(consumer, temperatureSpan, humiditySpan, FAR_INLAND_CONTINENTALNESS, EROSION_POINTS[3], param, 0.0F, resourceKey4);
                this.addSurfaceBiome(consumer, temperatureSpan, humiditySpan, Climate.Parameter.span(COAST_CONTINENTALNESS, FAR_INLAND_CONTINENTALNESS), EROSION_POINTS[4], param, 0.0F, resourceKey);
                this.addSurfaceBiome(consumer, temperatureSpan, humiditySpan, Climate.Parameter.span(COAST_CONTINENTALNESS, NEAR_INLAND_CONTINENTALNESS), EROSION_POINTS[5], param, 0.0F, resourceKey6);
                this.addSurfaceBiome(consumer, temperatureSpan, humiditySpan, Climate.Parameter.span(MID_INLAND_CONTINENTALNESS, FAR_INLAND_CONTINENTALNESS), EROSION_POINTS[5], param, 0.0F, resourceKey5);
                this.addSurfaceBiome(consumer, temperatureSpan, humiditySpan, Climate.Parameter.span(COAST_CONTINENTALNESS, FAR_INLAND_CONTINENTALNESS), EROSION_POINTS[6], param, 0.0F, resourceKey);
            }
        }

    }

    private void addMidSlice(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, Climate.Parameter param) {
        this.addSurfaceBiome(consumer, FULL_RANGE, FULL_RANGE, COAST_CONTINENTALNESS, Climate.Parameter.span(EROSION_POINTS[0], EROSION_POINTS[2]), param, 0.0F, Biomes.STONY_SHORE);
        this.addSurfaceBiome(consumer, Climate.Parameter.span(TEMPERATURE_POINTS[1], TEMPERATURE_POINTS[2]), FULL_RANGE, Climate.Parameter.span(NEAR_INLAND_CONTINENTALNESS, FAR_INLAND_CONTINENTALNESS), EROSION_POINTS[6], param, 0.0F, Biomes.SWAMP);
        this.addSurfaceBiome(consumer, Climate.Parameter.span(TEMPERATURE_POINTS[3], TEMPERATURE_POINTS[4]), FULL_RANGE, Climate.Parameter.span(NEAR_INLAND_CONTINENTALNESS, FAR_INLAND_CONTINENTALNESS), EROSION_POINTS[6], param, 0.0F, Biomes.MANGROVE_SWAMP);

        for(int i = 0; i < TEMPERATURE_POINTS.length; ++i) {
            Climate.Parameter temperatureSpan = TEMPERATURE_POINTS[i];

            for(int j = 0; j < HUMIDITY_POINTS.length; ++j) {
                Climate.Parameter humiditySpan = HUMIDITY_POINTS[j];
                ResourceKey<Biome> resourceKey = this.pickMiddleBiome(i, j, param);
                ResourceKey<Biome> resourceKey2 = this.pickMiddleBiomeOrBadlandsIfHot(i, j, param);
                ResourceKey<Biome> resourceKey3 = this.pickMiddleBiomeOrBadlandsIfHotOrSlopeIfCold(i, j, param);
                ResourceKey<Biome> resourceKey4 = this.pickShatteredBiome(i, j, param);
                ResourceKey<Biome> resourceKey5 = this.pickPlateauBiome(i, j, param);
                ResourceKey<Biome> resourceKey6 = this.pickBeachBiome(i, j);
                ResourceKey<Biome> resourceKey7 = this.maybePickWindsweptSavannaBiome(i, j, param, resourceKey);
                ResourceKey<Biome> resourceKey8 = this.pickShatteredCoastBiome(i, j, param);
                ResourceKey<Biome> resourceKey9 = this.pickSlopeBiome(i, j, param);
                this.addSurfaceBiome(consumer, temperatureSpan, humiditySpan, Climate.Parameter.span(NEAR_INLAND_CONTINENTALNESS, FAR_INLAND_CONTINENTALNESS), EROSION_POINTS[0], param, 0.0F, resourceKey9);
                this.addSurfaceBiome(consumer, temperatureSpan, humiditySpan, Climate.Parameter.span(NEAR_INLAND_CONTINENTALNESS, MID_INLAND_CONTINENTALNESS), EROSION_POINTS[1], param, 0.0F, resourceKey3);
                this.addSurfaceBiome(consumer, temperatureSpan, humiditySpan, FAR_INLAND_CONTINENTALNESS, EROSION_POINTS[1], param, 0.0F, i == 0 ? resourceKey9 : resourceKey5);
                this.addSurfaceBiome(consumer, temperatureSpan, humiditySpan, NEAR_INLAND_CONTINENTALNESS, EROSION_POINTS[2], param, 0.0F, resourceKey);
                this.addSurfaceBiome(consumer, temperatureSpan, humiditySpan, MID_INLAND_CONTINENTALNESS, EROSION_POINTS[2], param, 0.0F, resourceKey2);
                this.addSurfaceBiome(consumer, temperatureSpan, humiditySpan, FAR_INLAND_CONTINENTALNESS, EROSION_POINTS[2], param, 0.0F, resourceKey5);
                this.addSurfaceBiome(consumer, temperatureSpan, humiditySpan, Climate.Parameter.span(COAST_CONTINENTALNESS, NEAR_INLAND_CONTINENTALNESS), EROSION_POINTS[3], param, 0.0F, resourceKey);
                this.addSurfaceBiome(consumer, temperatureSpan, humiditySpan, Climate.Parameter.span(MID_INLAND_CONTINENTALNESS, FAR_INLAND_CONTINENTALNESS), EROSION_POINTS[3], param, 0.0F, resourceKey2);
                if (param.max() < 0L) {
                    this.addSurfaceBiome(consumer, temperatureSpan, humiditySpan, COAST_CONTINENTALNESS, EROSION_POINTS[4], param, 0.0F, resourceKey6);
                    this.addSurfaceBiome(consumer, temperatureSpan, humiditySpan, Climate.Parameter.span(NEAR_INLAND_CONTINENTALNESS, FAR_INLAND_CONTINENTALNESS), EROSION_POINTS[4], param, 0.0F, resourceKey);
                } else {
                    this.addSurfaceBiome(consumer, temperatureSpan, humiditySpan, Climate.Parameter.span(COAST_CONTINENTALNESS, FAR_INLAND_CONTINENTALNESS), EROSION_POINTS[4], param, 0.0F, resourceKey);
                }

                this.addSurfaceBiome(consumer, temperatureSpan, humiditySpan, COAST_CONTINENTALNESS, EROSION_POINTS[5], param, 0.0F, resourceKey8);
                this.addSurfaceBiome(consumer, temperatureSpan, humiditySpan, NEAR_INLAND_CONTINENTALNESS, EROSION_POINTS[5], param, 0.0F, resourceKey7);
                this.addSurfaceBiome(consumer, temperatureSpan, humiditySpan, Climate.Parameter.span(MID_INLAND_CONTINENTALNESS, FAR_INLAND_CONTINENTALNESS), EROSION_POINTS[5], param, 0.0F, resourceKey4);
                if (param.max() < 0L) {
                    this.addSurfaceBiome(consumer, temperatureSpan, humiditySpan, COAST_CONTINENTALNESS, EROSION_POINTS[6], param, 0.0F, resourceKey6);
                } else {
                    this.addSurfaceBiome(consumer, temperatureSpan, humiditySpan, COAST_CONTINENTALNESS, EROSION_POINTS[6], param, 0.0F, resourceKey);
                }

                if (i == 0) {
                    this.addSurfaceBiome(consumer, temperatureSpan, humiditySpan, Climate.Parameter.span(NEAR_INLAND_CONTINENTALNESS, FAR_INLAND_CONTINENTALNESS), EROSION_POINTS[6], param, 0.0F, resourceKey);
                }
            }
        }

    }

    private void addLowSlice(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, Climate.Parameter param) {
        this.addSurfaceBiome(consumer, FULL_RANGE, FULL_RANGE, COAST_CONTINENTALNESS, Climate.Parameter.span(EROSION_POINTS[0], EROSION_POINTS[2]), param, 0.0F, Biomes.STONY_SHORE);
        this.addSurfaceBiome(consumer, Climate.Parameter.span(TEMPERATURE_POINTS[1], TEMPERATURE_POINTS[2]), FULL_RANGE, Climate.Parameter.span(NEAR_INLAND_CONTINENTALNESS, FAR_INLAND_CONTINENTALNESS), EROSION_POINTS[6], param, 0.0F, Biomes.SWAMP);
        this.addSurfaceBiome(consumer, Climate.Parameter.span(TEMPERATURE_POINTS[3], TEMPERATURE_POINTS[4]), FULL_RANGE, Climate.Parameter.span(NEAR_INLAND_CONTINENTALNESS, FAR_INLAND_CONTINENTALNESS), EROSION_POINTS[6], param, 0.0F, Biomes.MANGROVE_SWAMP);

        for(int i = 0; i < TEMPERATURE_POINTS.length; ++i) {
            Climate.Parameter parameter = TEMPERATURE_POINTS[i];

            for(int j = 0; j < HUMIDITY_POINTS.length; ++j) {
                Climate.Parameter parameter2 = HUMIDITY_POINTS[j];
                ResourceKey<Biome> resourceKey = this.pickMiddleBiome(i, j, param);
                ResourceKey<Biome> resourceKey2 = this.pickMiddleBiomeOrBadlandsIfHot(i, j, param);
                ResourceKey<Biome> resourceKey3 = this.pickMiddleBiomeOrBadlandsIfHotOrSlopeIfCold(i, j, param);
                ResourceKey<Biome> resourceKey4 = this.pickBeachBiome(i, j);
                ResourceKey<Biome> resourceKey5 = this.maybePickWindsweptSavannaBiome(i, j, param, resourceKey);
                ResourceKey<Biome> resourceKey6 = this.pickShatteredCoastBiome(i, j, param);
                this.addSurfaceBiome(consumer, parameter, parameter2, NEAR_INLAND_CONTINENTALNESS, Climate.Parameter.span(EROSION_POINTS[0], EROSION_POINTS[1]), param, 0.0F, resourceKey2);
                this.addSurfaceBiome(consumer, parameter, parameter2, Climate.Parameter.span(MID_INLAND_CONTINENTALNESS, FAR_INLAND_CONTINENTALNESS), Climate.Parameter.span(EROSION_POINTS[0], EROSION_POINTS[1]), param, 0.0F, resourceKey3);
                this.addSurfaceBiome(consumer, parameter, parameter2, NEAR_INLAND_CONTINENTALNESS, Climate.Parameter.span(EROSION_POINTS[2], EROSION_POINTS[3]), param, 0.0F, resourceKey);
                this.addSurfaceBiome(consumer, parameter, parameter2, Climate.Parameter.span(MID_INLAND_CONTINENTALNESS, FAR_INLAND_CONTINENTALNESS), Climate.Parameter.span(EROSION_POINTS[2], EROSION_POINTS[3]), param, 0.0F, resourceKey2);
                this.addSurfaceBiome(consumer, parameter, parameter2, COAST_CONTINENTALNESS, Climate.Parameter.span(EROSION_POINTS[3], EROSION_POINTS[4]), param, 0.0F, resourceKey4);
                this.addSurfaceBiome(consumer, parameter, parameter2, Climate.Parameter.span(NEAR_INLAND_CONTINENTALNESS, FAR_INLAND_CONTINENTALNESS), EROSION_POINTS[4], param, 0.0F, resourceKey);
                this.addSurfaceBiome(consumer, parameter, parameter2, COAST_CONTINENTALNESS, EROSION_POINTS[5], param, 0.0F, resourceKey6);
                this.addSurfaceBiome(consumer, parameter, parameter2, NEAR_INLAND_CONTINENTALNESS, EROSION_POINTS[5], param, 0.0F, resourceKey5);
                this.addSurfaceBiome(consumer, parameter, parameter2, Climate.Parameter.span(MID_INLAND_CONTINENTALNESS, FAR_INLAND_CONTINENTALNESS), EROSION_POINTS[5], param, 0.0F, resourceKey);
                this.addSurfaceBiome(consumer, parameter, parameter2, COAST_CONTINENTALNESS, EROSION_POINTS[6], param, 0.0F, resourceKey4);
                if (i == 0) {
                    this.addSurfaceBiome(consumer, parameter, parameter2, Climate.Parameter.span(NEAR_INLAND_CONTINENTALNESS, FAR_INLAND_CONTINENTALNESS), EROSION_POINTS[6], param, 0.0F, resourceKey);
                }
            }
        }

    }

    private void addValleys(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, Climate.Parameter param) {
        this.addSurfaceBiome(consumer, FROZEN_RANGE, FULL_RANGE, COAST_CONTINENTALNESS, Climate.Parameter.span(EROSION_POINTS[0], EROSION_POINTS[1]), param, 0.0F, param.max() < 0L ? Biomes.STONY_SHORE : Biomes.FROZEN_RIVER);
        this.addSurfaceBiome(consumer, UNFROZEN_RANGE, FULL_RANGE, COAST_CONTINENTALNESS, Climate.Parameter.span(EROSION_POINTS[0], EROSION_POINTS[1]), param, 0.0F, param.max() < 0L ? Biomes.STONY_SHORE : Biomes.RIVER);
        this.addSurfaceBiome(consumer, FROZEN_RANGE, FULL_RANGE, NEAR_INLAND_CONTINENTALNESS, Climate.Parameter.span(EROSION_POINTS[0], EROSION_POINTS[1]), param, 0.0F, Biomes.FROZEN_RIVER);
        this.addSurfaceBiome(consumer, UNFROZEN_RANGE, FULL_RANGE, NEAR_INLAND_CONTINENTALNESS, Climate.Parameter.span(EROSION_POINTS[0], EROSION_POINTS[1]), param, 0.0F, Biomes.RIVER);
        this.addSurfaceBiome(consumer, FROZEN_RANGE, FULL_RANGE, Climate.Parameter.span(COAST_CONTINENTALNESS, FAR_INLAND_CONTINENTALNESS), Climate.Parameter.span(EROSION_POINTS[2], EROSION_POINTS[5]), param, 0.0F, Biomes.FROZEN_RIVER);
        this.addSurfaceBiome(consumer, UNFROZEN_RANGE, FULL_RANGE, Climate.Parameter.span(COAST_CONTINENTALNESS, FAR_INLAND_CONTINENTALNESS), Climate.Parameter.span(EROSION_POINTS[2], EROSION_POINTS[5]), param, 0.0F, Biomes.RIVER);
        this.addSurfaceBiome(consumer, FROZEN_RANGE, FULL_RANGE, COAST_CONTINENTALNESS, EROSION_POINTS[6], param, 0.0F, Biomes.FROZEN_RIVER);
        this.addSurfaceBiome(consumer, UNFROZEN_RANGE, FULL_RANGE, COAST_CONTINENTALNESS, EROSION_POINTS[6], param, 0.0F, Biomes.RIVER);
        this.addSurfaceBiome(consumer, Climate.Parameter.span(TEMPERATURE_POINTS[1], TEMPERATURE_POINTS[2]), FULL_RANGE, Climate.Parameter.span(INLAND_CONTINENTALNESS, FAR_INLAND_CONTINENTALNESS), EROSION_POINTS[6], param, 0.0F, Biomes.SWAMP);
        this.addSurfaceBiome(consumer, Climate.Parameter.span(TEMPERATURE_POINTS[3], TEMPERATURE_POINTS[4]), FULL_RANGE, Climate.Parameter.span(INLAND_CONTINENTALNESS, FAR_INLAND_CONTINENTALNESS), EROSION_POINTS[6], param, 0.0F, Biomes.MANGROVE_SWAMP);
        this.addSurfaceBiome(consumer, FROZEN_RANGE, FULL_RANGE, Climate.Parameter.span(INLAND_CONTINENTALNESS, FAR_INLAND_CONTINENTALNESS), EROSION_POINTS[6], param, 0.0F, Biomes.FROZEN_RIVER);

        for(int i = 0; i < TEMPERATURE_POINTS.length; ++i) {
            Climate.Parameter parameter = TEMPERATURE_POINTS[i];

            for(int j = 0; j < HUMIDITY_POINTS.length; ++j) {
                Climate.Parameter parameter2 = HUMIDITY_POINTS[j];
                ResourceKey<Biome> resourceKey = this.pickMiddleBiomeOrBadlandsIfHot(i, j, param);
                this.addSurfaceBiome(consumer, parameter, parameter2, Climate.Parameter.span(MID_INLAND_CONTINENTALNESS, FAR_INLAND_CONTINENTALNESS), Climate.Parameter.span(EROSION_POINTS[0], EROSION_POINTS[1]), param, 0.0F, resourceKey);
            }
        }

    }

    private void addUndergroundBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consume) {
        this.addUndergroundBiome(consume, FULL_RANGE, FULL_RANGE, Climate.Parameter.span(0.8F, 1.0F), FULL_RANGE, FULL_RANGE, 0.0F, Biomes.DRIPSTONE_CAVES);
        this.addUndergroundBiome(consume, FULL_RANGE, Climate.Parameter.span(0.7F, 1.0F), FULL_RANGE, FULL_RANGE, FULL_RANGE, 0.0F, Biomes.LUSH_CAVES);
        this.addBottomBiome(consume, FULL_RANGE, FULL_RANGE, FULL_RANGE, Climate.Parameter.span(EROSION_POINTS[0], EROSION_POINTS[1]), FULL_RANGE, 0.0F, Biomes.DEEP_DARK);
    }

    private ResourceKey<Biome> pickMiddleBiome(int temperature, int humidity, Climate.Parameter param) {
        if (param.max() < 0L) {
            return MIDDLE_BIOMES[temperature][humidity];
        } else {
            ResourceKey<Biome> resourceKey = MIDDLE_BIOME_VARIANTS[temperature][humidity];
            return resourceKey == null ? MIDDLE_BIOMES[temperature][humidity] : resourceKey;
        }
    }

    private ResourceKey<Biome> pickMiddleBiomeOrBadlandsIfHot(int temperature, int humidity, Climate.Parameter param) {
        return temperature == 4 ? this.pickBadlandsBiome(humidity, param) : this.pickMiddleBiome(temperature, humidity, param);
    }

    private ResourceKey<Biome> pickMiddleBiomeOrBadlandsIfHotOrSlopeIfCold(int temperature, int humidity, Climate.Parameter param) {
        return temperature == 0 ? this.pickSlopeBiome(temperature, humidity, param) : this.pickMiddleBiomeOrBadlandsIfHot(temperature, humidity, param);
    }

    private ResourceKey<Biome> maybePickWindsweptSavannaBiome(int temperature, int humidity, Climate.Parameter param, ResourceKey<Biome> key) {
        return temperature > 1 && humidity < 4 && param.max() >= 0L ? Biomes.WINDSWEPT_SAVANNA : key;
    }

    private ResourceKey<Biome> pickShatteredCoastBiome(int temperature, int humidity, Climate.Parameter param) {
        ResourceKey<Biome> resourceKey = param.max() >= 0L ? this.pickMiddleBiome(temperature, humidity, param) : this.pickBeachBiome(temperature, humidity);
        return this.maybePickWindsweptSavannaBiome(temperature, humidity, param, resourceKey);
    }

    private ResourceKey<Biome> pickBeachBiome(int temperature, int humidity) {
        if (temperature == 0) {
            return Biomes.SNOWY_BEACH;
        } else {
            return temperature == 4 ? Biomes.DESERT : Biomes.BEACH;
        }
    }

    private ResourceKey<Biome> pickBadlandsBiome(int humidity, Climate.Parameter param) {
        if (humidity < 2) {
            return param.max() < 0L ? Biomes.BADLANDS : Biomes.ERODED_BADLANDS;
        } else {
            return humidity < 3 ? Biomes.BADLANDS : Biomes.WOODED_BADLANDS;
        }
    }

    private ResourceKey<Biome> pickPlateauBiome(int temperature, int humidity, Climate.Parameter param) {
        if (param.max() >= 0L) {
            ResourceKey<Biome> resourceKey = PLATEAU_BIOME_VARIANTS[temperature][humidity];
            if (resourceKey != null) {
                return resourceKey;
            }
        }
        return PLATEAU_BIOMES[temperature][humidity];
    }

    private ResourceKey<Biome> pickPeakBiome(int temperature, int humidity, Climate.Parameter param) {
        if (temperature <= 2) {
            return param.max() < 0L ? Biomes.JAGGED_PEAKS : Biomes.FROZEN_PEAKS;
        } else {
            return temperature == 3 ? Biomes.STONY_PEAKS : this.pickBadlandsBiome(humidity, param);
        }
    }

    private ResourceKey<Biome> pickSlopeBiome(int temperature, int humidity, Climate.Parameter param) {
        if (temperature >= 3) {
            return this.pickPlateauBiome(temperature, humidity, param);
        } else {
            return humidity <= 1 ? Biomes.SNOWY_SLOPES : Biomes.GROVE;
        }
    }

    private ResourceKey<Biome> pickShatteredBiome(int temperature, int humidity, Climate.Parameter param) {
        ResourceKey<Biome> resourceKey = SHATTERED_BIOMES[temperature][humidity];
        return resourceKey == null ? this.pickMiddleBiome(temperature, humidity, param) : resourceKey;
    }

    private void addSurfaceBiome(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, Climate.Parameter temperature, Climate.Parameter humidity, Climate.Parameter continentalness, Climate.Parameter erosion, Climate.Parameter depth, float weirdness, ResourceKey<Biome> key) {
        consumer.accept(Pair.of(Climate.parameters(temperature, humidity, continentalness, erosion, Climate.Parameter.point(0.0F), depth, weirdness), key));
        consumer.accept(Pair.of(Climate.parameters(temperature, humidity, continentalness, erosion, Climate.Parameter.point(1.0F), depth, weirdness), key));
    }

    private void addUndergroundBiome(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, Climate.Parameter temperature, Climate.Parameter humidity, Climate.Parameter continentalness, Climate.Parameter erosion, Climate.Parameter depth, float weirdness, ResourceKey<Biome> key) {
        consumer.accept(Pair.of(Climate.parameters(temperature, humidity, continentalness, erosion, Climate.Parameter.span(0.2F, 0.9F), depth, weirdness), key));
    }

    private void addBottomBiome(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, Climate.Parameter temerature, Climate.Parameter humidity, Climate.Parameter continentalness, Climate.Parameter erosion, Climate.Parameter depth, float weirdness, ResourceKey<Biome> key) {
        consumer.accept(Pair.of(Climate.parameters(temerature, humidity, continentalness, erosion, Climate.Parameter.point(1.1F), depth, weirdness), key));
    }

    public ResourceLocation getId() {
        return Verdance.id("mulberry_forest");
    }
}