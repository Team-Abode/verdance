package com.teamabode.verdance.datagen.server.advancement;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.trigger.SilkwormEggsDestroyedCriterion;
import com.teamabode.verdance.core.registry.VerdanceBlocks;
import com.teamabode.verdance.core.registry.VerdanceItems;
import com.teamabode.verdance.core.registry.VerdanceTriggerTypes;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantments;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class VerdanceAdvancementGenerator implements AdvancementProvider.AdvancementGenerator {
    private static final ResourceLocation HUSBANDRY_SILK_TOUCHED = Verdance.id("husbandry/silk_touched");
    private static final ResourceLocation HUSBANDRY_FEELING_FRESH = Verdance.id("husbandry/feeling_fresh");

    @Override
    public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> saver, ExistingFileHelper existingFileHelper) {
        this.silkTouched(registries, saver);
        this.feelingFresh(saver);
    }

    private void silkTouched(HolderLookup.Provider registryLookup, Consumer<AdvancementHolder> exporter) {
        Advancement.Builder advancement = Advancement.Builder.advancement();
        advancement.display(
                VerdanceBlocks.SILKWORM_EGGS.get(),
                Component.translatable("advancements.verdance.husbandry.silk_touched.title"),
                Component.translatable("advancements.verdance.husbandry.silk_touched.description"),
                null,
                AdvancementType.TASK,
                true, true, false
        );
        ItemPredicate.Builder item = ItemPredicate.Builder.item();
        var enchantments = registryLookup.lookupOrThrow(Registries.ENCHANTMENT);

        item.withSubPredicate(ItemSubPredicates.ENCHANTMENTS, ItemEnchantmentsPredicate.enchantments(List.of(
                new EnchantmentPredicate(enchantments.getOrThrow(Enchantments.SILK_TOUCH), MinMaxBounds.Ints.atLeast(1))
        )));
        advancement.parent(new AdvancementHolder(ResourceLocation.withDefaultNamespace("husbandry/root"), null));
        advancement.addCriterion("silk_touch_silkworm_eggs", SilkwormEggsDestroyedCriterion.TriggerInstance.createCriterion(item));
        advancement.requirements(AdvancementRequirements.Strategy.AND);
        advancement.save(exporter, HUSBANDRY_SILK_TOUCHED.toString());
    }

    private void feelingFresh(Consumer<AdvancementHolder> exporter) {
        Advancement.Builder advancement = Advancement.Builder.advancement();
        advancement.display(
                VerdanceItems.CANTALOUPE_JUICE.get(),
                Component.translatable("advancements.verdance.husbandry.feeling_fresh.title"),
                Component.translatable("advancements.verdance.husbandry.feeling_fresh.description"),
                null,
                AdvancementType.TASK,
                true, true, false
        );
        advancement.parent(new AdvancementHolder(ResourceLocation.withDefaultNamespace("husbandry/root"), null));
        advancement.addCriterion("extinguished_with_cantaloupe_juice", VerdanceTriggerTypes.EXTINGUISHED_WITH_CANTALOUPE_JUICE.get().createCriterion(new PlayerTrigger.TriggerInstance(Optional.empty())));
        advancement.requirements(AdvancementRequirements.Strategy.AND);
        advancement.save(exporter, HUSBANDRY_FEELING_FRESH.toString());
    }
}
