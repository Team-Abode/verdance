package com.teamabode.verdance.core.misc;

import net.minecraft.component.type.FoodComponent;

public class VerdanceFoodComponents {
    public static final FoodComponent CANTALOUPE_SLICE = new FoodComponent.Builder()
            .nutrition(2)
            .saturationModifier(0.1f)
            .alwaysEdible()
            .build();

    public static final FoodComponent GRILLED_CANTALOUPE_SLICE = new FoodComponent.Builder()
            .nutrition(6)
            .saturationModifier(0.3f)
            .build();

    public static final FoodComponent CANTALOUPE_JUICE = new FoodComponent.Builder()
            .nutrition(4)
            .saturationModifier(0.1f)
            .build();

    public static final FoodComponent MULBERRY = new FoodComponent.Builder()
            .nutrition(2)
            .saturationModifier(0.1f)
            .build();
}
