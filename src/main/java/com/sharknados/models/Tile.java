package com.sharknados.models;

import com.sharknados.models.pieces.Piece;
import com.sharknados.views.TileView;

import java.util.List;

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
    public boolean checkneighbor(int direction) {
        if(neighbor[direction] !=null)
            return true;
        return false;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public Piece getOccupyingPiece(List<Piece> pieces) {
        for (Piece piece : pieces) {
            if (piece.getTile() == this) {
                return piece;
            }
        }
        return null;
    }

    @Override
    public String toString(){
        return String.format("Axial coords (" + x + ", " + z + ")");
    }
}