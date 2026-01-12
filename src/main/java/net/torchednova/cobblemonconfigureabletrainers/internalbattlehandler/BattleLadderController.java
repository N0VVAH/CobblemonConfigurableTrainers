package net.torchednova.cobblemonconfigureabletrainers.internalbattlehandler;

import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class BattleLadderController {
    public static ArrayList<BattleLadders> bl;

    public static void init()
    {
        bl = new ArrayList<>();
    }

    public static BattleLadders getBattleLadder(int id)
    {
        for (int i = 0; i < bl.size(); i++)
        {
            if (bl.get(i).getId() == id)
            {
                return bl.get(i);
            }
        }

        return null;
    }

    public static BattleLadders getBattleLadder(String name)
    {
        for (int i = 0; i < bl.size(); i++)
        {
            if (Objects.equals(bl.get(i).getName(), name))
            {
                return bl.get(i);
            }
        }

        return null;
    }

    public static void newBattleLadder(String name, ArrayList<Vec3> tingy)
    {
        if (tingy == null)
        {
            tingy = new ArrayList<>();
        }
        bl.add(new BattleLadders(bl.size(), name, tingy));
    }

    public static Vec3 getNextPos(UUID player)
    {
        for (int i = 0; i < bl.size(); i++)
        {
            if (Objects.equals(bl.get(i).getPlayer().toString(), player.toString()))
            {
                return bl.get(i).getNext();
            }
        }
        return null;
    }

    public static Vec3 getEndPos(UUID player)
    {
        for (int i = 0; i < bl.size(); i++)
        {
            if (Objects.equals(bl.get(i).getPlayer().toString(), player.toString()))
            {
                return bl.get(i).getExit();
            }
        }
        return null;
    }



}
