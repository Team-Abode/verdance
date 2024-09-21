package com.teamabode.verdance.core.registry;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.entity.silkmoth.SilkMothBrain;
import com.teamabode.verdance.common.entity.silkmoth.sensor.SilkMothSpecificSensor;
import com.teamabode.verdance.common.entity.silkworm.SilkwormBrain;
import com.teamabode.verdance.common.entity.silkworm.sensor.SilkwormSpecificSensor;
import java.util.function.Supplier;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.entity.ai.brain.sensor.TemptationsSensor;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class VerdanceSensorTypes {

    public static final SensorType<SilkMothSpecificSensor> SILK_MOTH_SPECIFIC_SENSOR = register("silk_moth_specific_sensor", SilkMothSpecificSensor::new);
    public static final SensorType<SilkwormSpecificSensor> SILKWORM_SPECIFIC_SENSOR = register("silkworm_specific_sensor", SilkwormSpecificSensor::new);
    public static final SensorType<TemptationsSensor> SILK_MOTH_TEMPTATIONS = register("silk_moth_temptations", () -> new TemptationsSensor(SilkMothBrain.getTemptations()));
    public static final SensorType<TemptationsSensor> SILKWORM_TEMPTATIONS = register("silkworm_temptations", () -> new TemptationsSensor(SilkwormBrain.getTemptations()));

    public static <U extends Sensor<?>> SensorType<U> register(String name, Supplier<U> sensorType) {
        return Registry.register(Registries.SENSOR_TYPE, Verdance.id(name), new SensorType<>(sensorType));
    }

    public static void register() {

    }
}
