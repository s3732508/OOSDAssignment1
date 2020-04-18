package com.sharknados.models;

import com.sharknados.controllers.TileController;
import com.sharknados.models.pieces.Piece;


import java.util.List;


public class Tile extends AbstractModel{
    private int x, z;
    private boolean occupied = false;
    private boolean selected = false;
    private boolean highlighted = false;
    private boolean unavailable = false;
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
        boolean oldValue  =  this.occupied;
        this.occupied = occupied;
        firePropertyChange(TileController.OCCUPIED_PROPERTY, oldValue, occupied);
    }

    public void setSelected(boolean selected) {
        boolean oldValue  =  this.selected;
        this.selected = selected;
        firePropertyChange(TileController.SELECTED_PROPERTY, oldValue, selected);
    }

    public void setHighlighted(boolean highlighted) {
        boolean oldValue  =  this.highlighted;
        this.highlighted = highlighted;
        firePropertyChange(TileController.HIGHLIGHTED_PROPERTY, oldValue, highlighted);
    }

    public void setUnavailable(boolean unavailable) {
        boolean oldValue  =  this.unavailable;
        this.unavailable = unavailable;
        firePropertyChange(TileController.UNAVAILABLE_PROPERTY, oldValue, unavailable);
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

    public Piece getOccupyingPiece(List<Piece> pieces) {
        for (Piece piece : pieces) {
            if (piece.getTile() == this) {
                return piece;
            }
        }
        return null;
    }

    public void fireInitialProperties(){
        firePropertyChange(TileController.OCCUPIED_PROPERTY, null, occupied);
        firePropertyChange(TileController.SELECTED_PROPERTY, null, selected);
        firePropertyChange(TileController.HIGHLIGHTED_PROPERTY, null, highlighted);
        firePropertyChange(TileController.UNAVAILABLE_PROPERTY, null, unavailable);
    }

    @Override
    public String toString(){
        return String.format("Axial coords (" + x + ", " + z + ")");
    }
}