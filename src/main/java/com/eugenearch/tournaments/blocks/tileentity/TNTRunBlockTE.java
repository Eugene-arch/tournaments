package com.eugenearch.tournaments.blocks.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TNTRunBlockTE extends TileEntity implements ITickable {
    private boolean triggered;
    private int ticks = 3;

    @Override
    public void update() {
        if (this.triggered) {
            if (--this.ticks == 0) {
                this.world.setBlockToAir(this.pos);
            }
        }
    }

    public void trigger() {
        this.triggered = true;
    }
}
