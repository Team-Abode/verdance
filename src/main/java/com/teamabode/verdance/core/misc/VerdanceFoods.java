package com.teamabode.verdance.core.misc;

import net.minecraft.world.food.FoodConstants;
import net.minecraft.world.food.FoodProperties;

public class VerdanceFoods {
    public static final FoodProperties CANTALOUPE_SLICE = new FoodProperties.Builder()
            .nutrition(1)
            .saturationMod(FoodConstants.FOOD_SATURATION_POOR)
            .fast()
            .alwaysEat()
            .build();

    public static final FoodProperties GRILLED_CANTALOUPE_SLICE = new FoodProperties.Builder()
            .nutrition(6)
            .saturationMod(FoodConstants.FOOD_SATURATION_GOOD)
            .build();

    public static final FoodProperties MULBERRY = new FoodProperties.Builder()
            .nutrition(2)
            .saturationMod(FoodConstants.FOOD_SATURATION_POOR)
            .build();
}
