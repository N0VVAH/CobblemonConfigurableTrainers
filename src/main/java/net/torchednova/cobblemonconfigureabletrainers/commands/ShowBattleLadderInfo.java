package net.torchednova.cobblemonconfigureabletrainers.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.torchednova.cobblemonconfigureabletrainers.internalbattlehandler.BattleLadderController;
import net.torchednova.cobblemonconfigureabletrainers.internalbattlehandler.BattleLadders;

public class ShowBattleLadderInfo {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher)
    {
        dispatcher.register(Commands.literal("showbattleladderinfo")
                .then(Commands.argument("name", StringArgumentType.greedyString())
                .executes(context -> {
                    CommandSourceStack source = context.getSource();

                    String name = StringArgumentType.getString(context, "name");
                    BattleLadders bl = BattleLadderController.getBattleLadder(name);
                    if (bl == null)
                    {
                        source.sendSuccess(
                                () -> Component.literal(name + " does not exist"),
                                false
                        );
                        return 1;
                    }

                    StringBuilder sb = new StringBuilder();
                    sb.append(name);
                    sb.append("\n");
                    sb.append("\nPlayer in: ");
                    sb.append(bl.getPlayer());

                    for (int i = 0; i < bl.getBatSize(); i++)
                    {
                        sb.append("\n");
                        sb.append(i);
                        sb.append(": ");
                        sb.append(bl.getBatAtIndex(i));
                    }

                    source.sendSuccess(
                            () -> Component.literal(sb.toString()),
                            false
                    );
                    return 1;
                })
        ));
    }
}
