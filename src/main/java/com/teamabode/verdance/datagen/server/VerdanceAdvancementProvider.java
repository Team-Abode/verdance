package com.teamabode.verdance.datagen.server;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.trigger.SilkwormEggsDestroyedTrigger;
import com.teamabode.verdance.core.registry.VerdanceBlocks;
import com.teamabode.verdance.core.registry.VerdanceItems;
import com.teamabode.verdance.core.registry.VerdanceTriggerTypes;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.*;
import net.minecraft.advancements.AdvancementRequirements.Strategy;
import net.minecraft.advancements.critereon.*;
import net.minecraft.advancements.critereon.MinMaxBounds.Ints;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantments;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class VerdanceAdvancementProvider extends FabricAdvancementProvider {
    private static final ResourceLocation HUSBANDRY_SILK_TOUCHED = Verdance.id("husbandry/silk_touched");
    private static final ResourceLocation HUSBANDRY_FEELING_FRESH = Verdance.id("husbandry/feeling_fresh");

    public VerdanceAdvancementProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(HolderLookup.Provider registryLookup, Consumer<AdvancementHolder> exporter) {
        this.silkTouched(registryLookup, exporter);
        this.feelingFresh(exporter);
    }

    private void silkTouched(HolderLookup.Provider registryLookup, Consumer<AdvancementHolder> exporter) {
        Advancement.Builder advancement = Advancement.Builder.advancement();
        advancement.display(
                VerdanceBlocks.SILKWORM_EGGS,
                Component.translatable("advancements.verdance.husbandry.silk_touched.title"),
                Component.translatable("advancements.verdance.husbandry.silk_touched.description"),
                null,
                AdvancementType.TASK,
                true, true, false
        );
        ItemPredicate.Builder item = ItemPredicate.Builder.item();
        var enchantments = registryLookup.lookupOrThrow(Registries.ENCHANTMENT);

        item.withSubPredicate(ItemSubPredicates.ENCHANTMENTS, ItemEnchantmentsPredicate.enchantments(List.of(
                new EnchantmentPredicate(enchantments.getOrThrow(Enchantments.SILK_TOUCH), Ints.atLeast(1))
        )));
        advancement.parent(new AdvancementHolder(ResourceLocation.withDefaultNamespace("husbandry/root"), null));
        advancement.addCriterion("silk_touch_silkworm_eggs", SilkwormEggsDestroyedTrigger.TriggerInstance.createCriterion(item));
        advancement.requirements(Strategy.AND);
        advancement.save(exporter, HUSBANDRY_SILK_TOUCHED.toString());
    }

    private void feelingFresh(Consumer<AdvancementHolder> exporter) {
        Advancement.Builder advancement = Advancement.Builder.advancement();
        advancement.display(
                VerdanceItems.CANTALOUPE_JUICE,
                Component.translatable("advancements.verdance.husbandry.feeling_fresh.title"),
                Component.translatable("advancements.verdance.husbandry.feeling_fresh.description"),
                null,
                AdvancementType.TASK,
                true, true, false
        );
        advancement.parent(new AdvancementHolder(ResourceLocation.withDefaultNamespace("husbandry/root"), null));
        advancement.addCriterion("extinguished_with_cantaloupe_juice", VerdanceTriggerTypes.EXTINGUISHED_WITH_CANTALOUPE_JUICE.createCriterion(new PlayerTrigger.TriggerInstance(Optional.empty())));
        advancement.requirements(Strategy.AND);
        advancement.save(exporter, HUSBANDRY_FEELING_FRESH.toString());
    }
}
