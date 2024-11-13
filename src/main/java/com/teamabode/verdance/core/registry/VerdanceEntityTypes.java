package com.teamabode.verdance.core.registry;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.entity.CushionEntity;
import com.teamabode.verdance.common.entity.silkmoth.SilkMothEntity;
import com.teamabode.verdance.common.entity.silkworm.SilkwormEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.Heightmap;

public class VerdanceEntityTypes {
    public static final EntityType<SilkMothEntity> SILK_MOTH = register(
            "silk_moth",
            EntityType.Builder.create(SilkMothEntity::new, SpawnGroup.CREATURE).setDimensions(0.7f, 0.7f)
    );

    public static final EntityType<SilkwormEntity> SILKWORM = register(
            "silkworm",
            EntityType.Builder.create(SilkwormEntity::new, SpawnGroup.CREATURE).setDimensions(0.6f, 0.25f)
    );

    public static final EntityType<CushionEntity> CUSHION = register(
            "cushion",
            EntityType.Builder.create(CushionEntity::new, SpawnGroup.MISC)
                    .disableSummon()
                    .setDimensions(0.6f, 0.25f)
    );

    private static <E extends Entity> EntityType<E> register(String name, EntityType.Builder<E> builder) {
        Identifier id = Verdance.id(name);
        return Registry.register(Registries.ENTITY_TYPE, id, builder.build(id.toString()));
    }

    public static void register() {
        SpawnRestriction.register(SILK_MOTH, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, SilkMothEntity::checkSilkMothSpawnRules);
        FabricDefaultAttributeRegistry.register(SILK_MOTH, SilkMothEntity.createSilkMothAttributes());
        FabricDefaultAttributeRegistry.register(SILKWORM, SilkwormEntity.createSilkwormAttributes());
    }
}
