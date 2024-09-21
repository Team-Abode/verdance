package com.teamabode.verdance.core.registry;

import com.teamabode.verdance.Verdance;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;

public class VerdanceSoundEvents {
    public static final SoundEvent BLOCK_STUCCO_BREAK = register("block.stucco.break");
    public static final SoundEvent BLOCK_STUCCO_FALL = register("block.stucco.fall");
    public static final SoundEvent BLOCK_STUCCO_HIT = register("block.stucco.hit");
    public static final SoundEvent BLOCK_STUCCO_STEP = register("block.stucco.step");
    public static final SoundEvent BLOCK_STUCCO_PLACE = register("block.stucco.place");

    public static final SoundEvent ENTITY_SILK_MOTH_IDLE = register("entity.silk_moth.idle");
    public static final SoundEvent ENTITY_SILK_MOTH_HURT = register("entity.silk_moth.hurt");
    public static final SoundEvent ENTITY_SILK_MOTH_EAT = register("entity.silk_moth.eat");
    public static final SoundEvent ENTITY_SILK_MOTH_EMERGE = register("entity.silk_moth.emerge");
    public static final SoundEvent ENTITY_SILK_MOTH_DEATH = register("entity.silk_moth.death");

    public static final SoundEvent ENTITY_SILKWORM_HURT = register("entity.silkworm.hurt");
    public static final SoundEvent ENTITY_SILKWORM_DEATH = register("entity.silkworm.death");

    public static final SoundEvent BLOCK_SILK_COCOON_BREAK = register("block.silk_cocoon.break");
    public static final SoundEvent BLOCK_SILK_COCOON_FALL = register("block.silk_cocoon.fall");
    public static final SoundEvent BLOCK_SILK_COCOON_HIT = register("block.silk_cocoon.hit");
    public static final SoundEvent BLOCK_SILK_COCOON_PLACE = register("block.silk_cocoon.place");
    public static final SoundEvent BLOCK_SILK_COCOON_STEP = register("block.silk_cocoon.step");
    public static final SoundEvent BLOCK_SILK_COCOON_WOBBLE = register("block.silk_cocoon.wobble");

    public static final RegistryEntry<SoundEvent> MUSIC_DISC_RANGE = registerHolder("music_disc.range");

    public static void register() {}

    private static SoundEvent register(String name) {
        return Registry.register(Registries.SOUND_EVENT, Verdance.id(name), SoundEvent.of(Verdance.id(name)));
    }

    private static RegistryEntry.Reference<SoundEvent> registerHolder(String name) {
        return Registry.registerReference(Registries.SOUND_EVENT, Verdance.id(name), SoundEvent.of(Verdance.id(name)));
    }
}
