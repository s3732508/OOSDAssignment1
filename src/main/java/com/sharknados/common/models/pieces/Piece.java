package com.sharknados.common.models.pieces;

import com.sharknados.common.models.Tile;

import java.util.List;

public abstract class Piece {
    private int health;
    private int attack;
    private int energy;

    public Piece(int health, int attack, int energy) {
        this.health = health;
        this.attack = attack;
        this.energy = energy;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public int getEnergy() {
        return energy;
    }

    public abstract String getAbilityName();
    public abstract int getAbilityCost();
    public abstract List<Tile> getValidCellsForAbility(Tile currentTile);
    public abstract void doAbility(Tile currentTile, Tile selectedTile);
}
