package com.teamabode.verdance.core.misc;

import net.minecraft.world.food.FoodProperties;

public class VerdanceFoodComponents {
    public static final FoodProperties CANTALOUPE_SLICE = new FoodProperties.Builder()
            .nutrition(2)
            .saturationModifier(0.1f)
            .alwaysEdible()
            .build();

    public static final FoodProperties GRILLED_CANTALOUPE_SLICE = new FoodProperties.Builder()
            .nutrition(6)
            .saturationModifier(0.3f)
            .build();

    public static final FoodProperties CANTALOUPE_JUICE = new FoodProperties.Builder()
            .nutrition(4)
            .saturationModifier(0.1f)
            .build();

    public static final FoodProperties MULBERRY = new FoodProperties.Builder()
            .nutrition(2)
            .saturationModifier(0.1f)
            .build();
}
