package com.teamabode.verdance.client.model;

import com.teamabode.sketch.client.model.SketchAnimatableModel;
import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.client.VerdanceAnimations;
import com.teamabode.verdance.common.entity.silkworm.Silkworm;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.EntityModelLayer;

public class SilkwormModel extends SketchAnimatableModel<Silkworm> {
	public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(Verdance.id("silkworm"), "main");
	private final ModelPart root;

	public SilkwormModel(ModelPart root) {
		this.root = root.getChild("root");
	}

	public static TexturedModelData createBodyLayer() {
		ModelData mesh = new ModelData();
		ModelPartData meshRoot = mesh.getRoot();

		ModelPartData root = meshRoot.addChild("root", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -3.0F, -5.5F, 4.0F, 3.0F, 11.0F, new Dilation(0.0F))
				.uv(-7, 0).cuboid(2.0F, 0.0F, -2.5F, 1.0F, 0.0F, 7.0F, new Dilation(0.0F))
				.uv(-7, 0).cuboid(-3.0F, 0.0F, -2.5F, 1.0F, 0.0F, 7.0F, new Dilation(0.0F))
				.uv(0, 14).cuboid(-3.0F, -4.0F, -3.5F, 6.0F, 4.0F, 0.0F, new Dilation(0.0F))
				.uv(0, 18).cuboid(-3.0F, -4.0F, 0.5F, 6.0F, 4.0F, 0.0F, new Dilation(0.0F))
				.uv(0, 18).cuboid(-3.0F, -4.0F, 3.5F, 6.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		return TexturedModelData.of(mesh, 32, 32);
	}

	@Override
	public void setAngles(Silkworm entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.animateWalk(VerdanceAnimations.SILKWORM_MOVE, limbAngle, limbDistance, 2.0f, 4.0f);
	}

	public ModelPart getPart() {
		return this.root;
	}
}