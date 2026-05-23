package com.teamabode.verdance.datagen.server.tag;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.core.registry.VerdanceEntityTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class VerdanceEntityTypeTagProvider extends EntityTypeTagsProvider {

    public VerdanceEntityTypeTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, provider, Verdance.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        this.tag(EntityTypeTags.ARTHROPOD)
                .add(VerdanceEntityTypes.SILK_MOTH.get())
                .add(VerdanceEntityTypes.SILKWORM.get());
    }
}
