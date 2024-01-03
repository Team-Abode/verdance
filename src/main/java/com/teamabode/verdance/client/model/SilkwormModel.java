package com.teamabode.verdance.client.model;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.entity.silkworm.Silkworm;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class SilkwormModel extends HierarchicalModel<Silkworm> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Verdance.id("silkworm"), "main");
	private final ModelPart root;

	public SilkwormModel(ModelPart root) {
		this.root = root.getChild("root");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition meshRoot = mesh.getRoot();

		PartDefinition root = meshRoot.addOrReplaceChild("root", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -3.0F, -5.5F, 4.0F, 3.0F, 11.0F, new CubeDeformation(0.0F))
				.texOffs(-7, 0).addBox(2.0F, 0.0F, -2.5F, 1.0F, 0.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(-7, 0).addBox(-3.0F, 0.0F, -2.5F, 1.0F, 0.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(0, 14).addBox(-3.0F, -4.0F, -3.5F, 6.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(0, 18).addBox(-3.0F, -4.0F, 0.5F, 6.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(0, 18).addBox(-3.0F, -4.0F, 3.5F, 6.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(mesh, 32, 32);
	}

	public void setupAnim(Silkworm entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float additionalScale = Mth.cos(ageInTicks * 0.25f) * 1.4f * limbSwingAmount;

		this.root.zScale = Mth.clamp(1.0f + additionalScale, 1.0f, 2.0f);
	}

	public ModelPart root() {
		return this.root;
	}
}