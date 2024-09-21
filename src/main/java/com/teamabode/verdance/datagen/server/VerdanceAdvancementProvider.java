package com.teamabode.verdance.datagen.server;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.trigger.SilkwormEggsDestroyedCriterion;
import com.teamabode.verdance.core.registry.VerdanceBlocks;
import com.teamabode.verdance.core.registry.VerdanceItems;
import com.teamabode.verdance.core.registry.VerdanceCriteria;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.AdvancementRequirements.CriterionMerger;
import net.minecraft.advancement.criterion.TickCriterion;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.predicate.NumberRange.IntRange;
import net.minecraft.predicate.item.EnchantmentPredicate;
import net.minecraft.predicate.item.EnchantmentsPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.predicate.item.ItemSubPredicateTypes;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class VerdanceAdvancementProvider extends FabricAdvancementProvider {
    private static final Identifier HUSBANDRY_SILK_TOUCHED = Verdance.id("husbandry/silk_touched");
    private static final Identifier HUSBANDRY_FEELING_FRESH = Verdance.id("husbandry/feeling_fresh");

    public VerdanceAdvancementProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(RegistryWrapper.WrapperLookup registryLookup, Consumer<AdvancementEntry> exporter) {
        this.silkTouched(registryLookup, exporter);
        this.feelingFresh(exporter);
    }

    private void silkTouched(RegistryWrapper.WrapperLookup registryLookup, Consumer<AdvancementEntry> exporter) {
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
        var enchantments = registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);

        item.subPredicate(ItemSubPredicateTypes.ENCHANTMENTS, EnchantmentsPredicate.enchantments(List.of(
                new EnchantmentPredicate(enchantments.getOrThrow(Enchantments.SILK_TOUCH), IntRange.atLeast(1))
        )));
        advancement.parent(new AdvancementEntry(Identifier.ofVanilla("husbandry/root"), null));
        advancement.criterion("silk_touch_silkworm_eggs", SilkwormEggsDestroyedCriterion.TriggerInstance.createCriterion(item));
        advancement.criteriaMerger(CriterionMerger.AND);
        advancement.build(exporter, HUSBANDRY_SILK_TOUCHED.toString());
    }

    private void feelingFresh(Consumer<AdvancementEntry> exporter) {
        Advancement.Builder advancement = Advancement.Builder.create();
        advancement.display(
                VerdanceItems.CANTALOUPE_JUICE,
                Text.translatable("advancements.verdance.husbandry.feeling_fresh.title"),
                Text.translatable("advancements.verdance.husbandry.feeling_fresh.description"),
                null,
                AdvancementFrame.TASK,
                true, true, false
        );
        advancement.parent(new AdvancementEntry(Identifier.ofVanilla("husbandry/root"), null));
        advancement.criterion("extinguished_with_cantaloupe_juice", VerdanceCriteria.EXTINGUISHED_WITH_CANTALOUPE_JUICE.create(new TickCriterion.Conditions(Optional.empty())));
        advancement.criteriaMerger(CriterionMerger.AND);
        advancement.build(exporter, HUSBANDRY_FEELING_FRESH.toString());
    }
}
