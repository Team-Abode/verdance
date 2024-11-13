package com.teamabode.verdance.datagen.server;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.trigger.SilkwormEggsDestroyedCriterion;
import com.teamabode.verdance.core.registry.VerdanceBlocks;
import com.teamabode.verdance.core.registry.VerdanceItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.CriterionMerger;
import net.minecraft.advancement.criterion.TickCriterion;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.predicate.NumberRange.IntRange;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.predicate.item.EnchantmentPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class VerdanceAdvancementProvider extends FabricAdvancementProvider {
    private static final Identifier HUSBANDRY_SILK_TOUCHED = Verdance.id("husbandry/silk_touched");
    private static final Identifier HUSBANDRY_FEELING_FRESH = Verdance.id("husbandry/feeling_fresh");

    public VerdanceAdvancementProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateAdvancement(Consumer<Advancement> exporter) {
        this.silkTouched(exporter);
        this.feelingFresh(exporter);
    }

    private void silkTouched(Consumer<Advancement> exporter) {
        Advancement.Builder advancement = Advancement.Builder.create();
        advancement.display(
                VerdanceBlocks.SILKWORM_EGGS,
                Text.translatable("advancements.verdance.husbandry.silk_touched.title"),
                Text.translatable("advancements.verdance.husbandry.silk_touched.description"),
                null,
                AdvancementFrame.TASK,
                true, true, false
        );
        ItemPredicate.Builder item = ItemPredicate.Builder.create();
        item.enchantment(new EnchantmentPredicate(Enchantments.FORTUNE, IntRange.atLeast(1)));

        advancement.parent(new Identifier("minecraft", "husbandry/root"));
        advancement.criterion("silk_touch_silkworm_eggs", SilkwormEggsDestroyedCriterion.Conditions.create(item.build()));
        advancement.criteriaMerger(CriterionMerger.AND);
        advancement.build(exporter, HUSBANDRY_SILK_TOUCHED.toString());
    }

    private void feelingFresh(Consumer<Advancement> exporter) {
        Advancement.Builder advancement = Advancement.Builder.create();
        advancement.display(
                VerdanceItems.CANTALOUPE_JUICE,
                Text.translatable("advancements.verdance.husbandry.feeling_fresh.title"),
                Text.translatable("advancements.verdance.husbandry.feeling_fresh.description"),
                null,
                AdvancementFrame.TASK,
                true, true, false
        );
        advancement.parent(new Identifier("minecraft", "husbandry/root"));
        advancement.criterion(
                "extinguished_with_cantaloupe_juice",
                new TickCriterion.Conditions(Verdance.id("extinguished_with_cantaloupe_juice"), LootContextPredicate.EMPTY)
        );
        advancement.criteriaMerger(CriterionMerger.AND);
        advancement.build(exporter, HUSBANDRY_FEELING_FRESH.toString());
    }
}
