package net.torchednova.cobblemonconfigureabletrainers.datastorage;

import net.minecraft.server.MinecraftServer;

import java.nio.file.Path;

public class ModDataPath {
    public static Path getLadderDataFile(MinecraftServer server) {
        return server.getWorldPath(net.minecraft.world.level.storage.LevelResource.ROOT).resolve("data").resolve("cobconfigtrainer").resolve("ladder.json");
    }

    public static Path getTrainersDataFile(MinecraftServer server) {
        return server.getWorldPath(net.minecraft.world.level.storage.LevelResource.ROOT).resolve("data").resolve("cobconfigtrainer").resolve("trainers.json");
    }
}
