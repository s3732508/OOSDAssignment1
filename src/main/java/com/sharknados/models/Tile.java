package com.sharknados.models;


import com.sharknados.models.pieces.Piece;
import com.sharknados.models.AbstractSubject;
import java.util.List;

public class Tile extends AbstractSubject{
    private int x, z;
    private boolean occupied = false;
    private boolean selected = false;
    private boolean highlighted = false;
    private boolean unavailable = false;
    private Piece piece;
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
        notifyAllObservers();
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        notifyAllObservers();
    }

    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
        notifyAllObservers();
    }

    public void setUnavailable(boolean unavailable) {
        this.unavailable = unavailable;
        notifyAllObservers();
    }

    public boolean isOccupied() {
        return occupied;
    }

    public boolean isSelected() {
        return selected;
    }

    public boolean isHighlighted() {
        return highlighted;
    }

    public boolean isUnavailable(){
        return unavailable;
    }

    public void setPiece(Piece piece){
        this.piece = piece;
    }

    public Piece getPiece(){
        return this.piece;
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