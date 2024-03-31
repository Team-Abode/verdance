package com.teamabode.verdance.core.registry;

import com.teamabode.verdance.Verdance;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class VerdanceSounds {

    public static final SoundEvent MUSIC_DISC_RANGE = register("music_disc.range");

    public static void register() {

    }

    private static SoundEvent register(String name) {
        return Registry.register(BuiltInRegistries.SOUND_EVENT, new ResourceLocation(Verdance.MOD_ID, name), SoundEvent.createVariableRangeEvent(new ResourceLocation(Verdance.MOD_ID, name)));
    }
}
