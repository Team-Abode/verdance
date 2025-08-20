package com.teamabode.verdance.client.model;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.client.animation.SilkwormEntityAnimations;
import com.teamabode.verdance.client.state.SilkwormEntityRenderState;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;

public class SilkwormEntityModel extends EntityModel<SilkwormEntityRenderState> {
	public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(Verdance.id("silkworm"), "main");

	private final Animation moveAnimation;

	public SilkwormEntityModel(ModelPart root) {
		super(root);

		this.moveAnimation = SilkwormEntityAnimations.MOVE.createAnimation(root);
	}

	public static TexturedModelData createBodyLayer() {
		ModelData mesh = new ModelData();
		ModelPartData meshRoot = mesh.getRoot();

		ModelPartData root = meshRoot.addChild("root", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -3.0F, -5.5F, 4.0F, 3.0F, 11.0F, new Dilation(0.0F))
				.uv(-7, 0).cuboid(2.0F, 0.0F, -2.5F, 1.0F, 0.0F, 7.0F, new Dilation(0.0F))
				.uv(-7, 0).cuboid(-3.0F, 0.0F, -2.5F, 1.0F, 0.0F, 7.0F, new Dilation(0.0F))
				.uv(0, 14).cuboid(-3.0F, -4.0F, -3.5F, 6.0F, 4.0F, 0.0F, new Dilation(0.0F))
				.uv(0, 18).cuboid(-3.0F, -4.0F, 0.5F, 6.0F, 4.0F, 0.0F, new Dilation(0.0F))
				.uv(0, 18).cuboid(-3.0F, -4.0F, 3.5F, 6.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 24.0F, 0.0F));

		return TexturedModelData.of(mesh, 32, 32);
	}

	@Override
	public void setAngles(SilkwormEntityRenderState state) {
		super.setAngles(state);

		this.moveAnimation.applyWalking(state.limbSwingAnimationProgress, state.limbSwingAmplitude, 2.0f, 4.0f);
	}
}