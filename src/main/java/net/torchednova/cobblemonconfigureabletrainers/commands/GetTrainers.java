package net.torchednova.cobblemonconfigureabletrainers.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.torchednova.cobblemonconfigureabletrainers.trainer.Trainer;
import net.torchednova.cobblemonconfigureabletrainers.trainer.TrainerHandler;

import java.util.ArrayList;

import static com.mojang.text2speech.Narrator.LOGGER;

public class GetTrainers {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher)
    {
        dispatcher.register(Commands.literal("getTrainers").requires(source -> source.hasPermission(2)).then( Commands.argument("id", IntegerArgumentType.integer(0))
                .executes(context -> {
                    CommandSourceStack source = context.getSource();


                    int count = IntegerArgumentType.getInteger(context, "id");

                    ArrayList<Trainer> tempTrainers = TrainerHandler.getTrainer(count);
                    if (tempTrainers.size() < 1) return 1;
                    StringBuilder toSend = new StringBuilder();

                    toSend.append("Trainer: ");
                    toSend.append(tempTrainers.getFirst().getName());
                    toSend.append(" | ID: ");
                    toSend.append(tempTrainers.getFirst().getId());
                    toSend.append(" | Pos: ");
                    toSend.append(tempTrainers.getFirst().getPos());

                    source.sendSuccess(
                            () -> Component.literal(toSend.toString()),
                            false
                    );

                    return 1;
                })
        ));
    }

}
