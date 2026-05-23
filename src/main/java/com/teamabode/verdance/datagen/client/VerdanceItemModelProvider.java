package com.teamabode.verdance.datagen.client;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.core.registry.VerdanceBlocks;
import com.teamabode.verdance.core.registry.VerdanceItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class VerdanceItemModelProvider extends ItemModelProvider {

    public VerdanceItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Verdance.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(VerdanceItems.MULBERRY.get());
        basicItemWithBlockTexture(VerdanceBlocks.VIOLET.get());
        basicItemWithBlockTexture(VerdanceBlocks.SHRUB.get());
        basicItemWithBlockTexture(VerdanceBlocks.YELLOW_FLOWERING_SHRUB.get());
        basicItemWithBlockTexture(VerdanceBlocks.PINK_FLOWERING_SHRUB.get());
        basicItem(VerdanceItems.CANTALOUPE_SEEDS.get());
        basicItem(VerdanceItems.CANTALOUPE_SLICE.get());
        basicItem(VerdanceItems.MULBERRY.get());
        basicItem(VerdanceItems.CANTALOUPE_SLICE.get());
        basicItem(VerdanceItems.GRILLED_CANTALOUPE_SLICE.get());
        basicItem(VerdanceItems.CANTALOUPE_JUICE.get());
        basicItem(VerdanceBlocks.MULBERRY_DOOR.get().asItem());
        basicItem(VerdanceItems.MULBERRY_BOAT.get());
        basicItem(VerdanceItems.MULBERRY_CHEST_BOAT.get());
        basicItem(VerdanceItems.ABODE_POTTERY_SHERD.get());
        basicItem(VerdanceItems.FRILLS_POTTERY_SHERD.get());
        basicItem(VerdanceItems.PITCH_POTTERY_SHERD.get());
        basicItem(VerdanceItems.PRICKLE_POTTERY_SHERD.get());
        basicItem(VerdanceItems.SPIRIT_POTTERY_SHERD.get());
        basicItem(VerdanceItems.TRAP_POTTERY_SHERD.get());
        basicItem(VerdanceItems.HERITAGE_ARMOR_TRIM_SMITHING_TEMPLATE.get());
        basicItem(VerdanceItems.MUSIC_DISC_RANGE.get());
        basicItem(VerdanceItems.DISC_FRAGMENT_RANGE.get());
        basicItem(VerdanceBlocks.MULBERRY_DOOR.get().asItem());
        basicItem(VerdanceBlocks.MULBERRY_SIGN.get().asItem());
        basicItem(VerdanceBlocks.MULBERRY_HANGING_SIGN.get().asItem());
    }

    private void basicItemWithBlockTexture(Block block) {
        var itemLocation = BuiltInRegistries.ITEM.getKey(block.asItem());
        var blockLocation = BuiltInRegistries.BLOCK.getKey(block);

        getBuilder(itemLocation.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", blockLocation.withPath(path -> "block/" + path));
    }
}
