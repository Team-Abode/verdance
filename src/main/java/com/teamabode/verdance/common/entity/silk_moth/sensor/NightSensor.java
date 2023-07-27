package com.teamabode.verdance.common.entity.silk_moth.sensor;

import com.teamabode.verdance.common.entity.silk_moth.SilkMoth;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.sensing.Sensor;

import java.util.Set;

public class NightSensor extends Sensor<SilkMoth> {

    protected void doTick(ServerLevel level, SilkMoth entity) {
        if (level.isNight()) {

        }
    }

    public Set<MemoryModuleType<?>> requires() {
        return null;
    }
}
