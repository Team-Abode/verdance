package com.teamabode.verdance.core.registry;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.entity.silk_moth.SilkMothAi;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.ai.sensing.TemptingSensor;

import java.util.function.Supplier;

public class VerdanceSensorType {

    public static final SensorType<TemptingSensor> SILK_MOTH_TEMPTATIONS = register("silk_moth_temptations", () -> new TemptingSensor(SilkMothAi.getTemptations()));

    public static <U extends Sensor<?>> SensorType<U> register(String name, Supplier<U> sensorType) {
        return Registry.register(BuiltInRegistries.SENSOR_TYPE, Verdance.id(name), new SensorType<>(sensorType));
    }

    public static void register() {

    }

}
