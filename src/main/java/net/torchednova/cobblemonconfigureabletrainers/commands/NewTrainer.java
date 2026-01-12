package net.torchednova.cobblemonconfigureabletrainers.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.UuidArgument;
import net.minecraft.commands.arguments.coordinates.Vec3Argument;
import net.minecraft.network.chat.Component;
import net.minecraft.world.phys.Vec3;
import net.torchednova.cobblemonconfigureabletrainers.internalbattlehandler.BattleLadderController;
import net.torchednova.cobblemonconfigureabletrainers.internalbattlehandler.BattleLadders;
import net.torchednova.cobblemonconfigureabletrainers.trainer.Trainer;
import net.torchednova.cobblemonconfigureabletrainers.trainer.TrainerHandler;

public class NewTrainer {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("newtrainer").requires(source -> source.hasPermission(2))
                .then(Commands.argument("pos", Vec3Argument.vec3())
                        .then(Commands.argument("BattleLadder", StringArgumentType.string())
                .executes(context -> {
                    CommandSourceStack source = context.getSource();
                    Vec3 pos = Vec3Argument.getCoordinates(context, "pos")
                            .getPosition(context.getSource());

                    String name = StringArgumentType.getString(context, "BattleLadder");
                    BattleLadders bl = BattleLadderController.getBattleLadder(name);
                    if (bl == null)
                    {
                        source.sendSuccess(
                                () -> Component.literal("That batterladder does not exist type '/newbattleladder {name}' to create a new one "),
                                false
                        );
                        return 1;
                    }

                    bl.addBattle(pos);

                    source.sendSuccess(
                            () -> Component.literal("New Trainer has been added to " + name + " at " + pos),
                            false
                    );
                    return 1;
                })))
        );
    }
}
