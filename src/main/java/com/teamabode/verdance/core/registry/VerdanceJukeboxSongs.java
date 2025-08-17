package com.teamabode.verdance.core.registry;

import com.teamabode.verdance.Verdance;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.JukeboxSong;

public class VerdanceJukeboxSongs {
    public static final ResourceKey<JukeboxSong> RANGE = createKey("range");

    public static void register(BootstrapContext<JukeboxSong> context) {
        context.register(RANGE, new JukeboxSong(VerdanceSoundEvents.MUSIC_DISC_RANGE, Component.translatable("jukebox_song.verdance.range"), 87.0f, 1));
    }

    private static ResourceKey<JukeboxSong> createKey(String name) {
        return ResourceKey.create(Registries.JUKEBOX_SONG, Verdance.id(name));
    }
}
