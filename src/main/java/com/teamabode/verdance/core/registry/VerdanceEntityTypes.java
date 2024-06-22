package com.teamabode.verdance.core.registry;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.entity.silkmoth.SilkMoth;
import com.teamabode.verdance.common.entity.silkworm.Silkworm;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.levelgen.Heightmap;

public class VerdanceEntityTypes {

    public static final EntityType<SilkMoth> SILK_MOTH = register(
            "silk_moth",
            EntityType.Builder.of(SilkMoth::new, MobCategory.CREATURE).sized(0.9f, 0.9f).build("silk_moth")
    );

    public static final EntityType<Silkworm> SILKWORM = register(
            "silkworm",
            EntityType.Builder.of(Silkworm::new, MobCategory.CREATURE).sized(0.6f, 0.25f).build("silkworm")
    );

    private static <E extends Entity, T extends EntityType<E>> EntityType<E> register(String name, T entity) {
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, new ResourceLocation(Verdance.MOD_ID, name), entity);
    }

    public static void register() {
        SpawnPlacements.register(SILK_MOTH, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, SilkMoth::checkSilkMothSpawnRules);
        FabricDefaultAttributeRegistry.register(SILK_MOTH, SilkMoth.createSilkMothAttributes());
        FabricDefaultAttributeRegistry.register(SILKWORM, Silkworm.createSilkwormAttributes());
    }
}
