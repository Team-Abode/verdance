package com.teamabode.verdance.client.renderer;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.block.SilkCocoonBlock;
import com.teamabode.verdance.common.block.entity.SilkCocoonBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import org.joml.Quaternionf;

public class SilkCocoonBlockEntityRenderer implements BlockEntityRenderer<SilkCocoonBlockEntity> {
    public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(Verdance.id("silk_cocoon"), "main");
    public static final SpriteIdentifier TEXTURE_LOCATION = new SpriteIdentifier(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE, Verdance.id("entity/silk_cocoon"));

    private final ModelPart cocoon;

    public SilkCocoonBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
        ModelPart root = context.getLayerModelPart(LAYER_LOCATION);
        this.cocoon = root.getChild("cocoon");
    }

    public static TexturedModelData createBodyLayer() {
        ModelData mesh = new ModelData();
        ModelPartData root = mesh.getRoot();

        root.addChild(
                "cocoon",
                ModelPartBuilder.create().uv(0, 0).cuboid(3,0, 6, 10.0f, 12.0f, 10.0f),
                ModelTransform.NONE
        );
        return TexturedModelData.of(mesh, 64, 32);
    }

    @Override
    public void render(SilkCocoonBlockEntity cocoon, float partialTick, MatrixStack poseStack, VertexConsumerProvider bufferSource, int i, int j) {
        poseStack.push();
        VertexConsumer vertex = TEXTURE_LOCATION.getVertexConsumer(bufferSource, RenderLayer::getEntitySolid);
        BlockState state = cocoon.getCachedState();
        Direction dir = state.get(SilkCocoonBlock.FACING);

        float deltaTicks = (cocoon.wobbleTicks + partialTick);
        float wobble = MathHelper.sin(deltaTicks * 3.0f / MathHelper.PI) * (5.0f * MathHelper.RADIANS_PER_DEGREE);

        Quaternionf rotation;

        if (dir == Direction.NORTH || dir == Direction.SOUTH) {
            rotation = RotationAxis.POSITIVE_Z.rotation(wobble);
        }
        else {
            rotation = RotationAxis.POSITIVE_X.rotation(wobble);
        }
        if (cocoon.wobbling) {
            poseStack.multiply(rotation, 0.5f, 0.5f, 0.5f);
        }
        // Rotates the cocoon based off the direction it's facing.
        // Translating 0.5f and back will shift the pivot point for this rotation.
        poseStack.translate(0.5f, 0.5f, 0.5f);
        poseStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-dir.asRotation()));
        poseStack.translate(-0.5f, -0.5f, -0.5f);

        this.cocoon.render(poseStack, vertex, i, j);
        poseStack.pop();
    }
}
