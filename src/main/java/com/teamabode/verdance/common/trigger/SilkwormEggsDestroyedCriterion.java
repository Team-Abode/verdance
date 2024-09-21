package com.teamabode.verdance.common.trigger;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.teamabode.verdance.core.registry.VerdanceCriteria;
import java.util.Optional;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.server.network.ServerPlayerEntity;

public class SilkwormEggsDestroyedCriterion extends AbstractCriterion<SilkwormEggsDestroyedCriterion.TriggerInstance> {

    @Override
    public Codec<TriggerInstance> getConditionsCodec() {
        return TriggerInstance.CODEC;
    }

    public void trigger(ServerPlayerEntity player, ItemStack stack) {
        this.trigger(player, triggerInstance -> triggerInstance.matches(stack));
    }

    public record TriggerInstance(Optional<LootContextPredicate> player, Optional<ItemPredicate> item) implements AbstractCriterion.Conditions {
        public static final Codec<TriggerInstance> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                EntityPredicate.LOOT_CONTEXT_PREDICATE_CODEC.optionalFieldOf("player").forGetter(TriggerInstance::player),
                ItemPredicate.CODEC.optionalFieldOf("item").forGetter(TriggerInstance::item)
        ).apply(instance, TriggerInstance::new));

        public boolean matches(ItemStack stack) {
            return this.item.isEmpty() || this.item.get().test(stack);
        }

        public static AdvancementCriterion<TriggerInstance> createCriterion(ItemPredicate.Builder builder) {
            return VerdanceCriteria.SILKWORM_EGGS_DESTROYED.create(new TriggerInstance(Optional.empty(), Optional.of(builder.build())));
        }

        @Override
        public Optional<LootContextPredicate> player() {
            return this.player;
        }
    }
}
