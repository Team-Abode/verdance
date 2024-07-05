package com.teamabode.verdance.common.util;

import net.minecraft.world.item.DyeColor;

import java.util.ArrayList;

public class CreativeCategoryUtils {
    public static final ArrayList<DyeColor> COLORS = createColors();

    public static ArrayList<DyeColor> createColors() {
        ArrayList<DyeColor> colors = new ArrayList<>();
        colors.add(DyeColor.PINK);
        colors.add(DyeColor.MAGENTA);
        colors.add(DyeColor.PURPLE);
        colors.add(DyeColor.BLUE);
        colors.add(DyeColor.LIGHT_BLUE);
        colors.add(DyeColor.CYAN);
        colors.add(DyeColor.GREEN);
        colors.add(DyeColor.LIME);
        colors.add(DyeColor.YELLOW);
        colors.add(DyeColor.ORANGE);
        colors.add(DyeColor.RED);
        colors.add(DyeColor.BROWN);
        colors.add(DyeColor.BLACK);
        colors.add(DyeColor.GRAY);
        colors.add(DyeColor.LIGHT_GRAY);
        colors.add(DyeColor.WHITE);
        return colors;
    }

    /**
     * This method allows you to add a custom dye to the colour order in the creative category (after adding a custom dye to this list the blocks should appear in the creative category)
     *
     * @param  color a vanilla or modded dye
     * @param  index where it places the colour in the list
     */
    public static void addDyeToColourOrder(DyeColor color, int index) {
        COLORS.add(index, color);
    }
}
