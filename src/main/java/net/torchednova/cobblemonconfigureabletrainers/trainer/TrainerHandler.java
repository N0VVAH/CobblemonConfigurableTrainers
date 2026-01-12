package net.torchednova.cobblemonconfigureabletrainers.trainer;

import net.minecraft.world.phys.Vec3;
import net.torchednova.cobblemonconfigureabletrainers.trainer.Trainer;

import java.util.ArrayList;

public class TrainerHandler {
    private static ArrayList<Trainer> trainers = new ArrayList<>();

    public static void init()
    {

    }


    public static Trainer newTrainer(String name, Vec3 pos)
    {
        trainers.add(new Trainer(trainers.size(), name, pos));
        return trainers.getLast();
    }


    public static ArrayList<Trainer> getTrainer(int id)
    {
        ArrayList<Trainer> tempTrainerStorage;

        if (id == -1)
        {
            tempTrainerStorage = new ArrayList<>(trainers);
        }
        else
        {
            tempTrainerStorage = new ArrayList<>();
            for (int i = 0; i < trainers.size(); i++)
            {
                if (trainers.get(i).getDel() == true)
                {
                    trainers.set(i, null);
                    trainers.remove(i);
                }
                else if (trainers.get(i).getId() == id)
                {
                    tempTrainerStorage.add(trainers.get(i));
                    break;
                }
            }
        }


        return tempTrainerStorage;
    }


}


