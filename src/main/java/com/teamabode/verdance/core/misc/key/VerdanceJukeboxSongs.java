package com.teamabode.verdance.core.misc.key;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.core.registry.VerdanceSoundEvents;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.JukeboxSong;

public class VerdanceJukeboxSongs {
    public static final ResourceKey<JukeboxSong> RANGE = createKey("range");

    private static ResourceKey<JukeboxSong> createKey(String name) {
        return ResourceKey.create(Registries.JUKEBOX_SONG, Verdance.id(name));
    }
}
