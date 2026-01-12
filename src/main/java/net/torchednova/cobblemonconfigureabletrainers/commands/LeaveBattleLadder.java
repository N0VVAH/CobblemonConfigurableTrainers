package net.torchednova.cobblemonconfigureabletrainers.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.ParseResults;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;
import net.torchednova.cobblemonconfigureabletrainers.internalbattlehandler.BattleLadderController;
import net.torchednova.cobblemonconfigureabletrainers.trainer.Trainer;
import net.torchednova.cobblemonconfigureabletrainers.trainer.TrainerHandler;

import java.util.ArrayList;
import java.util.UUID;

public class LeaveBattleLadder {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher)
    {
        dispatcher.register(Commands.literal("leaveBattleLadder")
                .executes(context -> {
                    CommandSourceStack source = context.getSource();

                    UUID playerUUID = source.getEntity().getUUID();

                    Vec3 pos = BattleLadderController.getEndPos(playerUUID);
                    if (pos == null)
                    {
                        source.sendSuccess(
                                () -> Component.literal("You are not in a BattleLadder..."),
                                false
                        );
                        pos = null;
                        return 1;
                    }

                    ServerPlayer sPlayer = (ServerPlayer) source.getPlayer();
                    if (sPlayer == null) { return -1; }
                    sPlayer.connection.teleport(pos.x, pos.y, pos.z, 0, 0);

                    source.sendSuccess(
                            () -> Component.literal("You have been returned to outside"),
                            false
                    );
                    pos = null;
                    return 1;
                })
        );
    }
}
