package net.torchednova.cobblemonconfigureabletrainers.trainer;

import net.minecraft.world.phys.Vec3;

public class Trainer {
    public Trainer(int id, String name, Vec3 pos) {
        this.id = id;
        this.name = name;
        this.pos = pos;

        if (this.id == -1) {
            this.name = null;
            this.pos = null;

            this.del = true;
        }
    }

    public Boolean getDel() { return del;}
    public int getId() { return id; }
    public String getName() { return name; }
    public Vec3 getPos() { return pos; }

    private String name = null;
    private int id = -1;
    private Vec3 pos = null;

    private Boolean del = false;

}
