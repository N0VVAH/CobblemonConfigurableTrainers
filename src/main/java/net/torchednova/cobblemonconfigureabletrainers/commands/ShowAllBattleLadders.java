package net.torchednova.cobblemonconfigureabletrainers.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;
import net.torchednova.cobblemonconfigureabletrainers.internalbattlehandler.BattleLadderController;
import net.torchednova.cobblemonconfigureabletrainers.internalbattlehandler.BattleLadders;

import java.util.ArrayList;
import java.util.UUID;

import static com.mojang.text2speech.Narrator.LOGGER;

public class ShowAllBattleLadders {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher)
    {
        dispatcher.register(Commands.literal("showbattleladders")
                .executes(context -> {
                    CommandSourceStack source = context.getSource();
                    StringBuilder sb = new StringBuilder();
                    sb.append("BattleLadders:");

                    for (int i = 0; i < BattleLadderController.bl.size(); i++)
                    {
                        sb.append("\nName: ");
                        sb.append(BattleLadderController.bl.get(i).getName());
                        sb.append("\nBattles: ");
                        sb.append(BattleLadderController.bl.get(i).getBatSize());
                        sb.append("\nPlayer in: ");
                        sb.append(BattleLadderController.bl.get(i).getPlayer());
                    }

                    source.sendSuccess(
                            () -> Component.literal(sb.toString()),
                            false
                    );
                    return 1;
                })
        );
    }
}
