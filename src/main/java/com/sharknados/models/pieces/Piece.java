package com.sharknados.models.pieces;

import com.sharknados.models.Tile;

import java.util.List;

public abstract class Piece {
    private int defence;
    private int attack;
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

    public void setTile(Tile tile) { this.tile = tile; }

    public abstract boolean inTheSameArmyAs(Piece piece);
    public abstract String getAbilityName();
    public abstract int getAbilityCost();
    public abstract List<Tile> getValidCellsForAbility(Tile currentTile);
    public abstract void doAbility(Tile currentTile, Tile selectedTile);
}
