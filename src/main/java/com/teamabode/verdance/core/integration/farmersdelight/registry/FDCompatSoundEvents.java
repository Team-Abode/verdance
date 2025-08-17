package com.teamabode.verdance.core.integration.farmersdelight.registry;

import com.teamabode.verdance.Verdance;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;

public class FDCompatSoundEvents {
    public static final SoundEvent BLOCK_CABINET_OPEN = register("block.cabinet.open");
    public static final SoundEvent BLOCK_CABINET_CLOSE = register("block.cabinet.close");

    public static void register() {

    }

    private static SoundEvent register(String name) {
        return Registry.register(BuiltInRegistries.SOUND_EVENT, Verdance.id(name), SoundEvent.createVariableRangeEvent(Verdance.id(name)));
    }
}
