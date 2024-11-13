package com.teamabode.verdance.core.mixin;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class VerdanceMixinPlugin implements IMixinConfigPlugin {
    public void onLoad(String mixinPackage) {
        // TODO: config!!
        // Instead of the main class, we need register at this point.
        //ConfigManager.INSTANCE.register(new VerdanceConfig());
    }

    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if (mixinClassName.contains("SugarCaneBlock")) {
            //return VerdanceConfig.CAN_BONEMEAL_SUGAR_CANE.get();
        }
        if (mixinClassName.contains("SporeBlossomBlock")) {
            //return VerdanceConfig.CAN_BONEMEAL_SPORE_BLOSSOM.get();
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
