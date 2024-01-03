package com.teamabode.verdance.common.entity.silk_moth.behaviors;

import com.teamabode.verdance.common.entity.silk_moth.SilkMoth;
import com.teamabode.verdance.core.registry.VerdanceMemories;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.behavior.BlockPosTracker;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.ai.memory.WalkTarget;
import net.minecraft.world.entity.ai.util.AirAndWaterRandomPos;
import net.minecraft.world.entity.ai.util.HoverRandomPos;
import net.minecraft.world.phys.Vec3;

import java.util.Map;

public class AerialStroll extends ImprovedOneShot<SilkMoth> {

    public void requires(Map<MemoryModuleType<?>, MemoryStatus> map) {
        map.put(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT);
        map.put(MemoryModuleType.LOOK_TARGET, MemoryStatus.REGISTERED);
        map.put(VerdanceMemories.IS_FLYING, MemoryStatus.VALUE_PRESENT);
    }

    public void run(ServerLevel level, SilkMoth entity, long gameTime) {
        Vec3 pos = this.findPos(entity);

        if (pos != null) {
            entity.getBrain().setMemory(MemoryModuleType.WALK_TARGET, new WalkTarget(new BlockPosTracker(pos), 1.0f, 0));
        }
    }

    private Vec3 findPos(SilkMoth entity) {
        Vec3 view = entity.getViewVector(0.0f);
        return HoverRandomPos.getPos(entity, 10, 7, view.x(), view.z(), 90.0f * Mth.DEG_TO_RAD, 3, 1);
    }
}
