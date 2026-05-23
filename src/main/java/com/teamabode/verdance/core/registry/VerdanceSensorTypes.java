package com.teamabode.verdance.core.registry;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.entity.silkmoth.SilkMothBrain;
import com.teamabode.verdance.common.entity.silkmoth.sensor.SilkMothSpecificSensor;
import com.teamabode.verdance.common.entity.silkworm.SilkwormBrain;
import com.teamabode.verdance.common.entity.silkworm.sensor.SilkwormSpecificSensor;
import java.util.function.Supplier;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.ai.sensing.TemptingSensor;
import net.neoforged.neoforge.registries.DeferredRegister;

public class VerdanceSensorTypes {
    public static final DeferredRegister<SensorType<?>> REGISTRY = DeferredRegister.create(Registries.SENSOR_TYPE, Verdance.MOD_ID);

    public static final Supplier<SensorType<SilkMothSpecificSensor>> SILK_MOTH_SPECIFIC_SENSOR = register("silk_moth_specific_sensor", SilkMothSpecificSensor::new);
    public static final Supplier<SensorType<SilkwormSpecificSensor>> SILKWORM_SPECIFIC_SENSOR = register("silkworm_specific_sensor", SilkwormSpecificSensor::new);
    public static final Supplier<SensorType<TemptingSensor>> SILK_MOTH_TEMPTATIONS = register("silk_moth_temptations", () -> new TemptingSensor(SilkMothBrain.getTemptations()));
    public static final Supplier<SensorType<TemptingSensor>> SILKWORM_TEMPTATIONS = register("silkworm_temptations", () -> new TemptingSensor(SilkwormBrain.getTemptations()));

    public static <U extends Sensor<?>> Supplier<SensorType<U>> register(String name, Supplier<U> factory) {
        return REGISTRY.register(name, () -> new SensorType<>(factory));
    }

    public static void register() {

    }
}
