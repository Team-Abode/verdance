package com.teamabode.verdance.core.mixin;

import com.teamabode.sketch.core.api.config.ConfigManager;
import com.teamabode.verdance.VerdanceConfig;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class VerdanceMixinPlugin implements IMixinConfigPlugin {
    public void onLoad(String mixinPackage) {
        // Instead of the main class, we need register at this point.
        ConfigManager.INSTANCE.register(VerdanceConfig.INSTANCE);
    }

    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {


        if (mixinClassName.contains("SugarCaneBlock")) {
            return VerdanceConfig.INSTANCE.canBonemealSugarCane.get();
        }
        if (mixinClassName.contains("SporeBlossomBlock")) {
            return VerdanceConfig.INSTANCE.canBonemealSporeBlossom.get();
        }
        return true;
    }

    public String getRefMapperConfig() { return null; }

    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

    }

    public List<String> getMixins() {
        return null;
    }

    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }

    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }
}
