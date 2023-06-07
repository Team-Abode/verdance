package com.teamabode.verdance.client.model;


import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.entity.silkmoth.SilkMoth;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class SilkMothModel<T extends SilkMoth> extends HierarchicalModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Verdance.MOD_ID, "silk_moth"), "main");

	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart antennae;
	private final ModelPart rightLegs;
	private final ModelPart leftLegs;
	private final ModelPart rightWing;
	private final ModelPart leftWing;

	public SilkMothModel(ModelPart root) {
		this.root = root.getChild("root");
		this.body = this.root.getChild("body");
		this.head = this.body.getChild("head");
		this.antennae = this.head.getChild("antennae");
		this.rightLegs = this.body.getChild("right_legs");
		this.leftLegs = this.body.getChild("left_legs");
		this.rightWing = this.body.getChild("right_wing");
		this.leftWing = this.body.getChild("left_wing");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition partDefinition = mesh.getRoot();

		PartDefinition root = partDefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 6).addBox(-4.0F, -4.0F, -5.0F, 8.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 18).addBox(-3.0F, -3.0F, -1.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, 1.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -7.0F, -6.0F, 6.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 5.0F, -1.0F));

		PartDefinition antennae = head.addOrReplaceChild("antennae", CubeListBuilder.create().texOffs(0, 30).addBox(-3.0F, -4.0F, -2.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 30).mirror().addBox(2.0F, -4.0F, -2.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -7.0F, -4.0F));

		PartDefinition rightLegs = body.addOrReplaceChild("right_legs", CubeListBuilder.create().texOffs(16, -7).addBox(0.0F, 0.0F, -3.0F, 0.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 3.0F, 0.0F));

		PartDefinition leftLegs = body.addOrReplaceChild("left_legs", CubeListBuilder.create().texOffs(16, -7).addBox(0.0F, 0.0F, -3.0F, 0.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 3.0F, 0.0F));

		PartDefinition leftWing = body.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(-14, 36).addBox(0.0F, 0.0F, -8.0F, 10.0F, 0.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -3.0F, 1.0F));

		PartDefinition rightWing = body.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(-14, 36).mirror().addBox(-10.0F, 0.0F, -8.0F, 10.0F, 0.0F, 14.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-3.0F, -3.0F, 1.0F));

		return LayerDefinition.create(mesh, 64, 64);
	}

	public void setupAnim(T moth, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		this.createFlyAnims(ageInTicks);
	}

	public void createFlyAnims(float lifeTime) {
		this.body.xRot = (float) Math.toRadians(Math.sin(lifeTime * 0.72f) * 5);
		this.body.zRot = (float) Math.toRadians(Math.sin(lifeTime * 0.36f) * 15);

		this.leftWing.zRot = (float) Math.toRadians(Math.sin(lifeTime * 1.44f) * -45.0f);
		this.rightWing.zRot = (float) Math.toRadians(Math.sin(lifeTime * 1.44f) * 45.0f);

		this.antennae.xRot = (float) Math.toRadians(22.5f + Math.cos(lifeTime * 1.44f) * 5.0f);
	}

	public ModelPart root() {
		return this.root;
	}
}