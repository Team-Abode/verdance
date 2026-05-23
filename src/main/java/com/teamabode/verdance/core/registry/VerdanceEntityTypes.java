package com.teamabode.verdance.core.registry;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.entity.CushionEntity;
import com.teamabode.verdance.common.entity.silkmoth.SilkMoth;
import com.teamabode.verdance.common.entity.silkworm.Silkworm;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class VerdanceEntityTypes {
    public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(Registries.ENTITY_TYPE, Verdance.MOD_ID);

    public static final Supplier<EntityType<SilkMoth>> SILK_MOTH = register(
            "silk_moth",
            EntityType.Builder.of(SilkMoth::new, MobCategory.CREATURE).sized(0.7f, 0.7f).eyeHeight(0.35f)
    );

    public static final Supplier<EntityType<Silkworm>> SILKWORM = register(
            "silkworm",
            EntityType.Builder.of(Silkworm::new, MobCategory.CREATURE).sized(0.6f, 0.25f)
    );

    public static final Supplier<EntityType<CushionEntity>> CUSHION = register(
            "cushion",
            EntityType.Builder.of(CushionEntity::new, MobCategory.MISC)
                    .noSummon()
                    .sized(0.6f, 0.25f)
    );

    private static <E extends Entity> Supplier<EntityType<E>> register(String name, EntityType.Builder<E> builder) {
        return REGISTRY.register(name, () -> builder.build(Verdance.MOD_ID + "_" + name));
    }
}
