package com.teamabode.verdance.common.trigger;

import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.core.registry.VerdanceCriteria;
import java.util.Optional;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.advancement.criterion.AbstractCriterionConditions;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.AdvancementEntityPredicateDeserializer;
import net.minecraft.predicate.entity.AdvancementEntityPredicateSerializer;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class SilkwormEggsDestroyedCriterion extends AbstractCriterion<SilkwormEggsDestroyedCriterion.Conditions> {
    public static final Identifier ID = Verdance.id("silkworm_eggs_destroyed");

    public void trigger(ServerPlayerEntity player, ItemStack stack) {
        this.trigger(player, conditions -> conditions.test(stack));
    }

    @Override
    protected Conditions conditionsFromJson(JsonObject obj, LootContextPredicate playerPredicate, AdvancementEntityPredicateDeserializer predicateDeserializer) {
        ItemPredicate predicate = ItemPredicate.fromJson(obj.get("item"));
        return new Conditions(playerPredicate, predicate);
    }

    @Override
    public Identifier getId() {
        return ID;
    }

    public static class Conditions extends AbstractCriterionConditions {
        private final ItemPredicate item;

        public Conditions(LootContextPredicate player, ItemPredicate item) {
            super(SilkwormEggsDestroyedCriterion.ID, player);
            this.item = item;
        }

        public static Conditions create(ItemPredicate item) {
            return new Conditions(LootContextPredicate.EMPTY, item);
        }

        public boolean test(ItemStack stack) {
            return item.test(stack);
        }

        @Override
        public JsonObject toJson(AdvancementEntityPredicateSerializer predicateSerializer) {
            JsonObject obj = super.toJson(predicateSerializer);
            obj.add("item", item.toJson());
            return obj;
        }
    }
}
