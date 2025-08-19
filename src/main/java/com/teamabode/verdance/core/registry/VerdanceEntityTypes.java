package com.teamabode.verdance.core.registry;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.entity.CushionEntity;
import com.teamabode.verdance.common.entity.silkmoth.SilkMothEntity;
import com.teamabode.verdance.common.entity.silkworm.SilkwormEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.entity.vehicle.ChestBoatEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.Heightmap;

public class VerdanceEntityTypes {
    public static final EntityType<SilkMothEntity> SILK_MOTH = register(
            "silk_moth",
            EntityType.Builder.create(SilkMothEntity::new, SpawnGroup.CREATURE).dimensions(0.7f, 0.7f).eyeHeight(0.35f)
    );

    public static final EntityType<SilkwormEntity> SILKWORM = register(
            "silkworm",
            EntityType.Builder.create(SilkwormEntity::new, SpawnGroup.CREATURE).dimensions(0.6f, 0.25f)
    );

    public static final EntityType<CushionEntity> CUSHION = register(
            "cushion",
            EntityType.Builder.create(CushionEntity::new, SpawnGroup.MISC)
                    .disableSummon()
                    .dimensions(0.6f, 0.25f)
    );

    public static final EntityType<BoatEntity> MULBERRY_BOAT = register(
            "mulberry_boat",
            EntityType.Builder.<BoatEntity>create((type, world) -> new BoatEntity(type, world, () -> VerdanceItems.MULBERRY_BOAT), SpawnGroup.MISC)
                    .dropsNothing()
                    .dimensions(1.375f, 0.5625f)
                    .eyeHeight(0.5625f)
                    .maxTrackingRange(10)
    );

    public static final EntityType<ChestBoatEntity> MULBERRY_CHEST_BOAT = register(
            "mulberry_chest_boat",
            EntityType.Builder.<ChestBoatEntity>create((type, world) -> new ChestBoatEntity(type, world, () -> VerdanceItems.MULBERRY_CHEST_BOAT), SpawnGroup.MISC)
                    .dropsNothing()
                    .dimensions(1.375f, 0.5625f)
                    .eyeHeight(0.5625f)
                    .maxTrackingRange(10)
    );

    private static <E extends Entity> EntityType<E> register(String name, EntityType.Builder<E> builder) {
        var key = RegistryKey.of(RegistryKeys.ENTITY_TYPE, Verdance.id(name));

        return Registry.register(Registries.ENTITY_TYPE, key, builder.build(key));
    }

    public static void register() {
        SpawnRestriction.register(SILK_MOTH, SpawnLocationTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, SilkMothEntity::checkSilkMothSpawnRules);
        FabricDefaultAttributeRegistry.register(SILK_MOTH, SilkMothEntity.createSilkMothAttributes());
        FabricDefaultAttributeRegistry.register(SILKWORM, SilkwormEntity.createSilkwormAttributes());
    }
}
