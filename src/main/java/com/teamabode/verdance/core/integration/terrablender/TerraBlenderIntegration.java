package com.teamabode.verdance.core.integration.terrablender;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.core.misc.VerdanceSurfaceRules;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

public class TerraBlenderIntegration implements TerraBlenderApi {

    @Override
    public void onTerraBlenderInitialized() {
        Regions.register(new VerdanceRegion());

        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, Verdance.MOD_ID, VerdanceSurfaceRules.shrublands());
    }
}
