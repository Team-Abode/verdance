package com.teamabode.verdance.core.integration.farmersdelight.registry;

import com.teamabode.verdance.Verdance;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;

public class FDCompatSoundEvents {
    public static final SoundEvent BLOCK_CABINET_OPEN = register("block.cabinet.open");
    public static final SoundEvent BLOCK_CABINET_CLOSE = register("block.cabinet.close");

    public static void register() {

    }

    private static SoundEvent register(String name) {
        return Registry.register(Registries.SOUND_EVENT, Verdance.id(name), SoundEvent.of(Verdance.id(name)));
    }
}
