package com.teamabode.verdance.core.registry;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.entity.CushionEntity;
import com.teamabode.verdance.common.entity.silkmoth.SilkMoth;
import com.teamabode.verdance.common.entity.silkworm.Silkworm;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.Heightmap;
import net.minecraft.world.entity.*;

public class VerdanceEntityTypes {
    public static final EntityType<SilkMoth> SILK_MOTH = register(
            "silk_moth",
            EntityType.Builder.create(SilkMoth::new, SpawnGroup.CREATURE).dimensions(0.7f, 0.7f).eyeHeight(0.35f)
    );

    public static final EntityType<Silkworm> SILKWORM = register(
            "silkworm",
            EntityType.Builder.create(Silkworm::new, SpawnGroup.CREATURE).dimensions(0.6f, 0.25f)
    );

    public static final EntityType<CushionEntity> CUSHION = register(
            "cushion",
            EntityType.Builder.create(CushionEntity::new, SpawnGroup.MISC).disableSummon().dimensions(0.6f, 0.25f)
    );

    private static <E extends Entity> EntityType<E> register(String name, EntityType.Builder<E> builder) {
        Identifier id = Verdance.id(name);
        return Registry.register(Registries.ENTITY_TYPE, id, builder.build(id.toString()));
    }

    public static void register() {
        SpawnRestriction.register(SILK_MOTH, SpawnLocationTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, SilkMoth::checkSilkMothSpawnRules);
        FabricDefaultAttributeRegistry.register(SILK_MOTH, SilkMoth.createSilkMothAttributes());
        FabricDefaultAttributeRegistry.register(SILKWORM, Silkworm.createSilkwormAttributes());
    }
}
