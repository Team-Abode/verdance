package com.teamabode.verdance.core.registry;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.entity.silk_moth.SilkMoth;
import com.teamabode.verdance.common.entity.silkworm.Silkworm;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.levelgen.Heightmap;

public class VerdanceEntities {

    public static final EntityType<SilkMoth> SILK_MOTH = register(
            "silk_moth",
            FabricEntityTypeBuilder.createMob()
                    .spawnGroup(MobCategory.CREATURE)
                    .entityFactory(SilkMoth::new)
                    .dimensions(EntityDimensions.scalable(0.9f, 0.9f))
                    .defaultAttributes(SilkMoth::createSilkMothAttributes)
                    .spawnRestriction(SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, SilkMoth::checkSilkMothSpawnRules)
                    .build()
    );

    public static final EntityType<Silkworm> SILKWORM = register(
            "silkworm",
            FabricEntityTypeBuilder.createMob()
                    .spawnGroup(MobCategory.CREATURE)
                    .entityFactory(Silkworm::new)
                    .defaultAttributes(Silkworm::createSilkwormAttributes)
                    .dimensions(EntityDimensions.scalable(0.6f, 0.25f))
                    .build()
    );

    private static <E extends Entity, T extends EntityType<E>> EntityType<E> register(String name, T entity) {
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, new ResourceLocation(Verdance.MOD_ID, name), entity);
    }

    public static void register() {

    }
}
