package com.teamabode.verdance.core.data.client;

import com.teamabode.verdance.core.registry.VerdanceItems;
import com.teamabode.verdance.core.registry.misc.VerdanceFamilies;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;

public class VerdanceModelProvider extends FabricModelProvider {

    public VerdanceModelProvider(FabricDataOutput output) {
        super(output);
    }

    public void generateBlockStateModels(BlockModelGenerators generator) {
        VerdanceFamilies.getAllFamilies().forEach(family -> generator.family(family.getBaseBlock()).generateFor(family));
    }

    public void generateItemModels(ItemModelGenerators generator) {
        generator.generateFlatItem(VerdanceItems.CANTALOUPE_SLICE, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(VerdanceItems.CANTALOUPE_SEEDS, ModelTemplates.FLAT_ITEM);

        generator.generateFlatItem(VerdanceItems.MUSIC_DISC_RANGE, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(VerdanceItems.DISC_FRAGMENT_RANGE, ModelTemplates.FLAT_ITEM);
    }
}
