package net.torchednova.cobblemonconfigureabletrainers;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.OutgoingChatMessage;
import net.minecraft.network.chat.PlayerChatMessage;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.torchednova.cobblemonconfigureabletrainers.internalbattlehandler.BattleLadderController;
import net.torchednova.cobblemonconfigureabletrainers.internalbattlehandler.BattleLadders;

@EventBusSubscriber
public class LadderRadiusCheck {

    //time between checks
    private static final int CHECK_INTERVAL_TICKS = 30 * 20;
    private static int tickCount = 0;

    @SubscribeEvent
    public static void onServerTick(ServerTickEvent.Post event)
    {
        tickCount++;
        if (tickCount < CHECK_INTERVAL_TICKS) return;

        tickCount = 0;

        for (ServerPlayer player : event.getServer().getPlayerList().getPlayers()) {

            if (player.level().dimension() != Level.OVERWORLD) return;
            if (BattleLadderController.playerInLadder(player.getUUID()) == false) return;

            BattleLadders bl = BattleLadderController.getBattleLadders(player.getUUID());
            Vec3 pos = bl.getCur();

            double dist = player.blockPosition().distToCenterSqr(pos);
            if (dist > bl.getDist())
            {
                pos = bl.getExit();
                player.connection.teleport(pos.x, pos.y, pos.z, 0, 0);
                bl.setPlayer(null);
                PlayerChatMessage chatMessage = PlayerChatMessage.unsigned(
                        player.getUUID(),
                        "Please use /leavebattleladder to end early"
                );
                CommandSourceStack source = player.createCommandSourceStack();
                source.sendChatMessage(new OutgoingChatMessage.Player(chatMessage),
                        false,
                        ChatType.bind(ChatType.CHAT, player));
                //"Please use /leavebattleladder to end early"
            }


        }

    }

}
