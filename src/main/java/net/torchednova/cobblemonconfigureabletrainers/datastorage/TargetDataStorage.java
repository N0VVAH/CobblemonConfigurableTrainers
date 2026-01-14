package net.torchednova.cobblemonconfigureabletrainers.datastorage;

import com.google.common.reflect.TypeToken;
import net.minecraft.server.MinecraftServer;
import net.torchednova.cobblemonconfigureabletrainers.internalbattlehandler.BattleLadderController;
import net.torchednova.cobblemonconfigureabletrainers.internalbattlehandler.BattleLadders;
import net.torchednova.cobblemonconfigureabletrainers.internalbattlehandler.EastNPCTrainer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class TargetDataStorage {

    private static final Type LIST_TYPE = new TypeToken<List<BattleLadders>>() {}.getType();
    private static final Type LIST_TYPE_STRING = new TypeToken<List<String>>() {}.getType();

    public static void save(MinecraftServer server)
    {
        try{
            Path file = ModDataPath.getLadderDataFile(server);

            Path parent = file.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }

            String json = ModJson.GSON.toJson(BattleLadderController.bl);
            Files.writeString(file, json);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static ArrayList<BattleLadders> load(MinecraftServer server)
    {
        try{
            Path file = ModDataPath.getLadderDataFile(server);

            if (Files.exists(file) == false)
            {
                return new ArrayList<BattleLadders>();
            }

            String json = Files.readString(file);

            ArrayList<BattleLadders> data = ModJson.GSON.fromJson(json, LIST_TYPE);

            return data != null ? data : new ArrayList<>();

        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void saveTrainers(MinecraftServer server)
    {
        try{
            Path file = ModDataPath.getTrainersDataFile(server);

            Path parent = file.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }

                //LOGGER.info(String.valueOf(EastNPCTrainer.linkers.size()));

            String json = ModJson.GSON.toJson(EastNPCTrainer.linkers);
            Files.writeString(file, json);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public static ArrayList<String> loadTrainer(MinecraftServer server)
    {
        try{
            Path file = ModDataPath.getTrainersDataFile(server);

            if (Files.exists(file) == false)
            {
                return new ArrayList<String>();
            }

            String json = Files.readString(file);

            ArrayList<String> data = ModJson.GSON.fromJson(json, LIST_TYPE_STRING);

            return data != null ? data : new ArrayList<>();

        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
