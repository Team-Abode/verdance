package com.teamabode.verdance.common.entity.silkmoth.control;

import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.MathHelper;

public class PitchFlyingMoveControl extends MoveControl {
    private final int pitchChange;
    private final boolean noGravity;

    public PitchFlyingMoveControl(MobEntity entity, int pitchChange, boolean noGravity) {
        super(entity);

        this.pitchChange = pitchChange;
        this.noGravity = noGravity;
    }

    @Override
    public void tick() {
        if (this.state == State.MOVE_TO) {
            this.state = State.WAIT;
            this.entity.setNoGravity(true);
            double xDelta = this.targetX - this.entity.getX();
            double yDelta = this.targetY - this.entity.getY();
            double zDelta = this.targetZ - this.entity.getZ();

            double lengthSqr = xDelta * xDelta + yDelta * yDelta + zDelta * zDelta;

            if (lengthSqr < 0.0d) {
                this.entity.setUpwardSpeed(0.0f);
                this.entity.setForwardSpeed(0.0f);
                return;
            }

            float newYaw = (float) (MathHelper.atan2(zDelta, xDelta) * (double)(180F / (float)Math.PI)) - 90.0F;
            this.entity.setYaw(this.wrapDegrees(this.entity.getYaw(), newYaw, 90.0F));

            float movementSpeed = (float) this.speed;

            if (this.entity.isOnGround()) {
                movementSpeed *= (float) this.entity.getAttributeValue(EntityAttributes.MOVEMENT_SPEED);
            } else {
                movementSpeed *= (float) this.entity.getAttributeValue(EntityAttributes.FLYING_SPEED);
            }

            this.entity.setMovementSpeed(movementSpeed);

            double lengthXZ = Math.sqrt(xDelta * xDelta + zDelta * zDelta);

            if (Math.abs(yDelta) > 0.0f || Math.abs(lengthXZ) > 0.0f) {
                float newPitch = (float)(-(MathHelper.atan2(yDelta, lengthXZ) * (double)(180F / (float)Math.PI)));

                newPitch = MathHelper.clamp(MathHelper.wrapDegrees(newPitch), -this.pitchChange, this.pitchChange);

                this.entity.setPitch(this.changeAngle(this.entity.getPitch(), newPitch, 2.0f));
                this.entity.setUpwardSpeed(yDelta > (double)0.0F ? movementSpeed : -movementSpeed);
            }
        }
        else {
            if (!this.noGravity) {
                this.entity.setNoGravity(false);
            }
            this.entity.setUpwardSpeed(0.0F);
            this.entity.setForwardSpeed(0.0F);
        }
    }
}
