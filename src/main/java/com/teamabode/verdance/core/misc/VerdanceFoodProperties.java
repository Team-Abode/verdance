package com.teamabode.verdance.core.misc;

import net.minecraft.world.food.FoodConstants;
import net.minecraft.world.food.FoodProperties;

public class VerdanceFoodProperties {
    public static final FoodProperties CANTALOUPE_SLICE = new FoodProperties.Builder()
            .nutrition(2)
            .saturationMod(FoodConstants.FOOD_SATURATION_POOR)
            .alwaysEat()
            .build();

    public static final FoodProperties GRILLED_CANTALOUPE_SLICE = new FoodProperties.Builder()
            .nutrition(5)
            .saturationMod(FoodConstants.FOOD_SATURATION_LOW)
            .build();

    public static final FoodProperties MULBERRY = new FoodProperties.Builder()
            .nutrition(2)
            .saturationMod(FoodConstants.FOOD_SATURATION_POOR)
            .build();
}
