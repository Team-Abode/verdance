package com.teamabode.verdance.common.block;

import com.teamabode.verdance.core.registry.VerdanceBlocks;
import net.minecraft.block.AttachedStemBlock;
import net.minecraft.block.GourdBlock;
import net.minecraft.block.StemBlock;

public class CantaloupeBlock extends GourdBlock {

    public CantaloupeBlock(Settings settings) {
        super(settings);
    }

    @Override
    public StemBlock getStem() {
        return (StemBlock) VerdanceBlocks.CANTALOUPE_STEM;
    }

    @Override
    public AttachedStemBlock getAttachedStem() {
        return (AttachedStemBlock) VerdanceBlocks.ATTACHED_CANTALOUPE_STEM;
    }
}
