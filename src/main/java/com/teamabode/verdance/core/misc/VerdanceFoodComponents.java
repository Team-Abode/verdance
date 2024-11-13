package com.teamabode.verdance.core.misc;

import net.minecraft.item.FoodComponent;

public class VerdanceFoodComponents {
    public static final FoodComponent CANTALOUPE_SLICE = new FoodComponent.Builder()
            .hunger(2)
            .saturationModifier(0.1f)
            .alwaysEdible()
            .build();

    public static final FoodComponent GRILLED_CANTALOUPE_SLICE = new FoodComponent.Builder()
            .hunger(6)
            .saturationModifier(0.3f)
            .build();

    public static final FoodComponent CANTALOUPE_JUICE = new FoodComponent.Builder()
            .hunger(4)
            .saturationModifier(0.1f)
            .build();

    public static final FoodComponent MULBERRY = new FoodComponent.Builder()
            .hunger(2)
            .saturationModifier(0.1f)
            .build();
}
