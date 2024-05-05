package com.teamabode.verdance.core.integration.terrablender;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.VerdanceConfig;
import com.teamabode.verdance.common.worldgen.biome.VerdanceBiomeBuilder;
import com.teamabode.verdance.core.misc.worldgen.VerdanceSurfaceRules;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.SurfaceRuleManager.RuleCategory;
import terrablender.api.TerraBlenderApi;

public class VerdanceTerraBlender implements TerraBlenderApi {

    public void onTerraBlenderInitialized() {
        Regions.register(new VerdanceRegion(new VerdanceBiomeBuilder(), VerdanceConfig.INSTANCE.mulberryForestWeight.get()));

        SurfaceRuleManager.addSurfaceRules(RuleCategory.OVERWORLD, Verdance.MOD_ID, VerdanceSurfaceRules.OVERWORLD);
    }
}
