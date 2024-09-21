package com.teamabode.verdance.core.registry;

import com.teamabode.verdance.Verdance;
import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;

public class VerdanceJukeboxSongs {
    public static final RegistryKey<JukeboxSong> RANGE = createKey("range");

    public static void register(Registerable<JukeboxSong> context) {
        context.register(RANGE, new JukeboxSong(VerdanceSoundEvents.MUSIC_DISC_RANGE, Text.translatable("jukebox_song.verdance.range"), 87.0f, 1));
    }

    private static RegistryKey<JukeboxSong> createKey(String name) {
        return RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Verdance.id(name));
    }
}
