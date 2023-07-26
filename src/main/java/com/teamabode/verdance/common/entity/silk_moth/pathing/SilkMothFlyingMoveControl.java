package com.teamabode.verdance.common.entity.silk_moth.pathing;

import com.teamabode.verdance.common.entity.silk_moth.SilkMoth;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;

public class SilkMothFlyingMoveControl extends MoveControl {
    private final SilkMoth silkMoth;

    public SilkMothFlyingMoveControl(SilkMoth silkMoth) {
        super(silkMoth);
        this.silkMoth = silkMoth;
    }

    public void tick() {
        if (this.operation == Operation.MOVE_TO) {
            this.operation = Operation.WAIT;
            silkMoth.setNoGravity(true);

            double dirX = this.wantedX - silkMoth.getX();
            double dirY = this.wantedY - silkMoth.getY();
            double dirZ = this.wantedZ - silkMoth.getZ();

            double distance = dirX * dirX + dirY * dirY + dirZ * dirZ;
            if (distance < 0.00000025d) {
                silkMoth.setYya(0.0f);
                silkMoth.setZza(0.0f);
                return;
            }
            float yaw = (float) (Mth.atan2(dirZ, dirX) * Mth.RAD_TO_DEG) - 90.0f;
            silkMoth.setYRot(this.rotlerp(silkMoth.getYRot(), yaw, 90.0f));

            float speed = (float) (!silkMoth.onGround() ? silkMoth.getAttributeValue(Attributes.FLYING_SPEED) : silkMoth.getAttributeValue(Attributes.MOVEMENT_SPEED));
            silkMoth.setSpeed(speed);

            double horizontalDistance = Mth.sqrt((float) (dirX * dirX + dirZ * dirZ));

            if (Mth.abs((float) dirY) > 0.0d || Mth.abs((float) horizontalDistance) > 0.0d) {
                float pitch = (float)(-(Mth.atan2(dirY, horizontalDistance) * Mth.RAD_TO_DEG));
                silkMoth.setXRot(this.rotlerp(silkMoth.getXRot(), pitch, 20.0f));
                silkMoth.setYya(dirY > 0.0 ? speed : -speed);
            }
            return;
        }
        silkMoth.setYya(0.0F);
        silkMoth.setZza(0.0F);
    }
}
