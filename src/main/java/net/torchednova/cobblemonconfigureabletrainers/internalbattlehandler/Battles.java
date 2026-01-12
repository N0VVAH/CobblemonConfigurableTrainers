package net.torchednova.cobblemonconfigureabletrainers.internalbattlehandler;

import com.cobblemon.mod.common.api.battles.model.PokemonBattle;
import com.cobblemon.mod.common.battles.actor.PlayerBattleActor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

import static com.mojang.text2speech.Narrator.LOGGER;

public class Battles {
    public static ArrayList<UUID> battles;

    public static void init()
    {
        battles = new ArrayList<UUID>();
    }

    public static void addBattle(UUID uuid)
    {
        battles.add(uuid);
    }

    public static Boolean haveBattle(UUID uuid)
    {
        for (int i = 0; i < battles.size(); i++)
        {
            if (Objects.equals(battles.get(i).toString(), uuid.toString()))
            {
                return true;
            }
        }
        return false;
    }

    public static void delBattle(UUID uuid)
    {
        for (int i = 0; i < battles.size(); i++)
        {
            if (battles.get(i) == uuid)
            {
                battles.set(i, null);
                battles.remove(i);
                return;
            }
        }
    }



}
