package net.torchednova.cobblemonconfigureabletrainers.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

import net.torchednova.cobblemonconfigureabletrainers.internalbattlehandler.Battles;

import java.util.UUID;

import static com.mojang.text2speech.Narrator.LOGGER;

public class StartBattle {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("fight")
                .requires(source -> source.hasPermission(2))
                .then(Commands.argument("battletype", StringArgumentType.string())
                        .then(Commands.argument("ainame", StringArgumentType.string())
                                .then(Commands.argument("playername", StringArgumentType.greedyString())
                                    .executes(context -> {

                                        CommandSourceStack source = context.getSource();

                                        String bt = StringArgumentType.getString(context, "battletype");
                                        String ai = StringArgumentType.getString(context, "ainame");
                                        UUID aiuuid = UUID.fromString(ai);
                                        Battles.addBattle(aiuuid);
                                        //LOGGER.info("Battle Start UUID: " + aiuuid.toString());
                                        String pl = StringArgumentType.getString(context, "playername");

                                        source.sendSuccess(
                                            () -> Component.literal("Starting fight..."),
                                    false
                                        );
                                        var disp = source.getServer().getCommands().getDispatcher();
                                        ParseResults<CommandSourceStack> parse = disp.parse("tbcs battle " + bt + " " + pl + " vs " + ai, source);

                                        source.getServer().getCommands().performCommand(parse, "");
                                        disp = null;
                                        aiuuid = null;

                                    return 1;
                                    })
                                )
                        )
                )
        );
    }
}
