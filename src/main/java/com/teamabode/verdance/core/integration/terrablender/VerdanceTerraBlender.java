package com.teamabode.verdance.core.integration.terrablender;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.core.misc.worldgen.VerdanceSurfaceRules;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

public class VerdanceTerraBlender implements TerraBlenderApi {

    public void onTerraBlenderInitialized() {
        Regions.register(new ResourceLocation(Verdance.MOD_ID, "default"), new VerdanceRegion());
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, Verdance.MOD_ID, VerdanceSurfaceRules.OVERWORLD);
    }
}
