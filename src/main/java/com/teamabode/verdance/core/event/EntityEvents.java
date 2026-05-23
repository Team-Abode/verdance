package com.teamabode.verdance.core.event;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.entity.silkmoth.SilkMoth;
import com.teamabode.verdance.common.entity.silkworm.Silkworm;
import com.teamabode.verdance.core.registry.VerdanceEntityTypes;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

@EventBusSubscriber(modid = Verdance.MOD_ID)
public class EntityEvents {

    @SubscribeEvent
    public static void onEntityAttributeCreationEvent(EntityAttributeCreationEvent event) {
        event.put(VerdanceEntityTypes.SILK_MOTH.get(), SilkMoth.createSilkMothAttributes().build());
        event.put(VerdanceEntityTypes.SILKWORM.get(), Silkworm.createSilkwormAttributes().build());
    }

    @SubscribeEvent
    public static void onRegisterSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(
                VerdanceEntityTypes.SILK_MOTH.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING,
                SilkMoth::checkSilkMothSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.AND
        );
    }
}
