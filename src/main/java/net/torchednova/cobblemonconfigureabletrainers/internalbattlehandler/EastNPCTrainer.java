package net.torchednova.cobblemonconfigureabletrainers.internalbattlehandler;

import com.mojang.brigadier.ParseResults;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.TickTask;
import net.minecraft.world.entity.schedule.Schedule;
import net.torchednova.cobblemonconfigureabletrainers.datastorage.TargetDataStorage;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.Async;

import java.util.ArrayList;
import java.util.Objects;

import static com.mojang.text2speech.Narrator.LOGGER;

public class EastNPCTrainer {
    public static ArrayList<String> linkers;

    public static void init(MinecraftServer server)
    {
        linkers = new ArrayList<>();

        linkers = TargetDataStorage.loadTrainer(server);

        server.tell(new TickTask(1, () -> {

        CommandSourceStack source = server.createCommandSourceStack();
        var disp = source.getServer().getCommands().getDispatcher();
        //load from file
        for (int i = 0; i < linkers.size(); i += 2)
        {
            LOGGER.info("tbcs attach " + linkers.get(i) + " " + linkers.get(i+1));
            ParseResults<CommandSourceStack> parse = disp.parse("tbcs attach " + linkers.get(i + 1) + " " + linkers.get(i), source);

            source.getServer().getCommands().performCommand(parse, "");
        }
        }));
    }

    public static void newPair(String uuid, String name)
    {
        linkers.add(uuid);
        linkers.add(name);
    }

    public static void removePair(String uuid)
    {
        for (int i = 0; i < linkers.size(); i+=2)
        {
            if (Objects.equals(linkers.get(i), uuid))
            {
                linkers.remove(i+1);
                linkers.remove(i);
                return;
            }
        }
    }


}
