package com.teamabode.verdance.core.integration.terrablender;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.core.misc.worldgen.VerdanceSurfaceRules;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.SurfaceRuleManager.RuleCategory;
import terrablender.api.TerraBlenderApi;

public class VerdanceTerraBlender implements TerraBlenderApi {

    public void onTerraBlenderInitialized() {
        Regions.register(Verdance.id("mulberry_forest"), new MulberryForestRegion());
        Regions.register(Verdance.id("shrublands"), new ShrublandsRegion());


        SurfaceRuleManager.addSurfaceRules(RuleCategory.OVERWORLD, Verdance.MOD_ID, VerdanceSurfaceRules.OVERWORLD);
    }
}
