package com.sharknados.models;

import com.sharknados.views.TileView;

public class Tile {
    private int x, z;
    private boolean occupied = false;
    private Tile neighbor[] = {null, null, null, null, null, null};

    public Tile(int x, int z){
        this.x = x;
        this.z = z;
    }

    public int getX(){
        return x;
    }

    public int getZ(){
        return z;
    }

    public void setNeighbor(Tile neighbor, int direction){
                this.neighbor[direction] = neighbor;
    }

    public Tile getNeighbor(int direction) {
        return neighbor[direction];
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public boolean isOccupied() {
        return occupied;
    }

    @Override
    public String toString(){
        return String.format("(" + x + ", " + z + ")");
    }
}