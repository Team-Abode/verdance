package com.teamabode.verdance.core.misc.key;

import com.teamabode.verdance.Verdance;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.JukeboxSong;

public class VerdanceJukeboxSongs {

    public static final ResourceKey<JukeboxSong> RANGE = createKey("range");

    private static ResourceKey<JukeboxSong> createKey(String name) {
        return ResourceKey.create(Registries.JUKEBOX_SONG, Verdance.id(name));
    }
}
