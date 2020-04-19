package com.sharknados.models.pieces;

import com.sharknados.controllers.PieceController;
import com.sharknados.controllers.TileController;
import com.sharknados.models.AbstractModel;
import com.sharknados.models.Tile;

import java.util.List;

public abstract class Piece extends AbstractModel {
    private int defence;
    private int attack;
    private int x;
    private int z;
    private Tile tile;

    public Piece(int attack, int defence, Tile tile) {
        this.attack = attack;
        this.defence = defence;
        this.tile = tile;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefence() {
        return defence;
    }

    public Tile getTile() { return tile; }

    public void setX(int x){
        int oldValue  =  this.x;
        this.x = x;
        firePropertyChange(PieceController.X_PROPERTY, oldValue, x);
    }

    public void setZ(int z){
        int oldValue  =  this.z;
        this.z = z;
        firePropertyChange(PieceController.Z_PROPERTY, oldValue, z);
    }

    public void fireInitialProperties(){
        firePropertyChange(PieceController.X_PROPERTY, null, x);
        firePropertyChange(PieceController.Z_PROPERTY, null, z);
    }

    public void setTile(Tile tile) { this.tile = tile; }

    public abstract boolean inTheSameArmyAs(Piece piece);
    public abstract String getAbilityName();
    public abstract int getAbilityCost();
    public abstract List<Tile> getValidCellsForAbility(Tile currentTile);
    public abstract void doAbility(Tile currentTile, Tile selectedTile);
}
