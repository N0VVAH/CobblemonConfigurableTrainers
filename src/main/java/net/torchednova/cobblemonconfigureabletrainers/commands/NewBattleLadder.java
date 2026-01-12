package net.torchednova.cobblemonconfigureabletrainers.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.coordinates.Vec3Argument;
import net.minecraft.network.chat.Component;
import net.minecraft.world.phys.Vec3;
import net.torchednova.cobblemonconfigureabletrainers.internalbattlehandler.BattleLadderController;
import net.torchednova.cobblemonconfigureabletrainers.internalbattlehandler.BattleLadders;

public class NewBattleLadder {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("newbattleladder").requires(source -> source.hasPermission(2))
                        .then(Commands.argument("name", StringArgumentType.greedyString())
                                .executes(context -> {
                                    CommandSourceStack source = context.getSource();

                                    String name = StringArgumentType.getString(context, "name");

                                    BattleLadderController.newBattleLadder(name, null);

                                    source.sendSuccess(
                                            () -> Component.literal("New BattleLadder " + name + " Created."),
                                            false
                                    );
                                    return 1;
                                }))
        );
    }
}
