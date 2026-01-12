package net.torchednova.cobblemonconfigureabletrainers.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.torchednova.cobblemonconfigureabletrainers.trainer.Trainer;
import net.torchednova.cobblemonconfigureabletrainers.trainer.TrainerHandler;

import java.util.ArrayList;

import static com.mojang.text2speech.Narrator.LOGGER;

public class GetAllTrainers {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher)
    {
        dispatcher.register(Commands.literal("getAllTrainers").requires(source -> source.hasPermission(2))
                .executes(context -> {
                    CommandSourceStack source = context.getSource();



                    ArrayList<Trainer> tempTrainers = TrainerHandler.getTrainer(-1);
                    if (tempTrainers.size() < 1) return 1;
                    StringBuilder toSend = new StringBuilder();


                    toSend.append("All Trainers: ");
                    for (int i = 0; i < tempTrainers.size(); i++)
                    {
                        toSend.append("\n");
                        toSend.append("Trainer: ");
                        toSend.append(tempTrainers.get(i).getName());
                        toSend.append(" | ID: ");
                        toSend.append(tempTrainers.get(i).getId());
                        toSend.append(" | Pos: ");
                        toSend.append(tempTrainers.get(i).getPos());
                    }
                    source.sendSuccess(
                            () -> Component.literal(toSend.toString()),
                            false
                    );

                    tempTrainers = null;

                    return 1;
                })
        );
    }
}
