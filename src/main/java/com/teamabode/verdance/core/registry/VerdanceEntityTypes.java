package com.teamabode.verdance.core.registry;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.entity.CushionEntity;
import com.teamabode.verdance.common.entity.silkmoth.SilkMothEntity;
import com.teamabode.verdance.common.entity.silkworm.SilkwormEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;

public class VerdanceEntityTypes {
    public static final EntityType<SilkMothEntity> SILK_MOTH = register(
            "silk_moth",
            EntityType.Builder.of(SilkMothEntity::new, MobCategory.CREATURE).sized(0.7f, 0.7f).eyeHeight(0.35f)
    );

    public static final EntityType<SilkwormEntity> SILKWORM = register(
            "silkworm",
            EntityType.Builder.of(SilkwormEntity::new, MobCategory.CREATURE).sized(0.6f, 0.25f)
    );

    public static final EntityType<CushionEntity> CUSHION = register(
            "cushion",
            EntityType.Builder.of(CushionEntity::new, MobCategory.MISC)
                    .noSummon()
                    .sized(0.6f, 0.25f)
    );

    private static <E extends Entity> EntityType<E> register(String name, EntityType.Builder<E> builder) {
        ResourceLocation id = Verdance.id(name);
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, id, builder.build(id.toString()));
    }

    public static void register() {
        SpawnPlacements.register(SILK_MOTH, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, SilkMothEntity::checkSilkMothSpawnRules);
        FabricDefaultAttributeRegistry.register(SILK_MOTH, SilkMothEntity.createSilkMothAttributes());
        FabricDefaultAttributeRegistry.register(SILKWORM, SilkwormEntity.createSilkwormAttributes());
    }
}
