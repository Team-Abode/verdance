package com.teamabode.verdance.core.event;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.core.registry.VerdanceBlocks;
import com.teamabode.verdance.core.registry.VerdanceItems;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.event.village.WandererTradesEvent;

@EventBusSubscriber(modid = Verdance.MOD_ID)
public class TradeEvents {

    @SubscribeEvent
    private static void onWandererTrades(WandererTradesEvent event) {
        var genericTrades = event.getGenericTrades();

        genericTrades.add(new VillagerTrades.ItemsForEmeralds(
                VerdanceItems.CANTALOUPE_SEEDS.get(),
                1,
                1,
                12,
                1
        ));

        genericTrades.add(new VillagerTrades.ItemsForEmeralds(
                VerdanceItems.MULBERRY.get(),
                5,
                1,
                8,
                1
        ));

        genericTrades.add(new VillagerTrades.ItemsForEmeralds(
                VerdanceBlocks.VIOLET.get(),
                1,
                1,
                12,
                1
        ));

        genericTrades.add(new VillagerTrades.ItemsForEmeralds(
                VerdanceBlocks.SHRUB.get(),
                1,
                1,
                12,
                1
        ));
        genericTrades.add(new VillagerTrades.ItemsForEmeralds(
                VerdanceBlocks.YELLOW_FLOWERING_SHRUB.get(),
                1,
                1,
                12,
                1
        ));
        genericTrades.add(new VillagerTrades.ItemsForEmeralds(
                VerdanceBlocks.PINK_FLOWERING_SHRUB.get(),
                1,
                1,
                12,
                1
        ));
    }

    @SubscribeEvent
    private static void onVillagerTrades(VillagerTradesEvent event) {
        if (event.getType() == VillagerProfession.FARMER) {
            var trades = event.getTrades().get(2);

            trades.add(new VillagerTrades.EmeraldForItems(
                    VerdanceBlocks.CANTALOUPE.get(),
                    6,
                    12,
                    10
            ));
        }
    }
}
