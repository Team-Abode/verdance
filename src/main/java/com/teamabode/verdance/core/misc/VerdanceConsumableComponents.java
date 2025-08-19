package com.teamabode.verdance.core.misc;

import com.teamabode.verdance.common.consume.ReduceFireTimeConsumeEffect;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.item.consume.UseAction;

public class VerdanceConsumableComponents {

    public static final ConsumableComponent CANTALOUPE_SLICE = createFood()
            .consumeSeconds(1.6f)
            .consumeParticles(true)
            .useAction(UseAction.EAT)
            .consumeEffect(new ReduceFireTimeConsumeEffect(3.0f))
            .build();

    public static final ConsumableComponent CANTALOUPE_JUICE = createDrink()
            .consumeSeconds(1.6f)
            .consumeParticles(false)
            .useAction(UseAction.DRINK)
            .build();

    public static ConsumableComponent.Builder createFood() {
        return ConsumableComponent.builder()
                .consumeSeconds(1.6f)
                .consumeParticles(true)
                .useAction(UseAction.EAT);
    }

    public static ConsumableComponent.Builder createDrink() {
        return ConsumableComponent.builder()
                .consumeSeconds(1.6f)
                .consumeParticles(false)
                .useAction(UseAction.DRINK);
    }


}
