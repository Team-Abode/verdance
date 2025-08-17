package com.teamabode.verdance.core.registry;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.entity.silkmoth.SilkMothBrain;
import com.teamabode.verdance.common.entity.silkmoth.sensor.SilkMothSpecificSensor;
import com.teamabode.verdance.common.entity.silkworm.SilkwormBrain;
import com.teamabode.verdance.common.entity.silkworm.sensor.SilkwormSpecificSensor;
import java.util.function.Supplier;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.ai.sensing.TemptingSensor;

public class VerdanceSensorTypes {

    public static final SensorType<SilkMothSpecificSensor> SILK_MOTH_SPECIFIC_SENSOR = register("silk_moth_specific_sensor", SilkMothSpecificSensor::new);
    public static final SensorType<SilkwormSpecificSensor> SILKWORM_SPECIFIC_SENSOR = register("silkworm_specific_sensor", SilkwormSpecificSensor::new);
    public static final SensorType<TemptingSensor> SILK_MOTH_TEMPTATIONS = register("silk_moth_temptations", () -> new TemptingSensor(SilkMothBrain.getTemptations()));
    public static final SensorType<TemptingSensor> SILKWORM_TEMPTATIONS = register("silkworm_temptations", () -> new TemptingSensor(SilkwormBrain.getTemptations()));

    public static <U extends Sensor<?>> SensorType<U> register(String name, Supplier<U> sensorType) {
        return Registry.register(BuiltInRegistries.SENSOR_TYPE, Verdance.id(name), new SensorType<>(sensorType));
    }

    public static void register() {

    }
}
