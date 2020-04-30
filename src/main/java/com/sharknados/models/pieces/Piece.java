package com.sharknados.models.pieces;

import com.sharknados.models.AbstractSubject;
import com.sharknados.models.Team;
import com.sharknados.models.Tile;

import java.util.List;

public abstract class Piece extends AbstractSubject {


    public enum Type {
        AKHEILOS,
        GREAT_WHITE,
        AETOS_DIOS,
        EAGLE_OWL
    }

    private int defence;
    private int attack;
    private int movement;
    private int x;
    private int z;
    private Tile tile;
    private boolean isCommander;
    private Team team;
    private Type type;

    public Piece(int attack, int defence, Tile tile) {
        this.attack = attack;
        this.defence = defence;
        this.movement = 1;
        this.tile = tile;
        this.x = tile.getX();
        this.z = tile.getZ();
        this.isCommander = false;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefence() {
        return defence;
    }

    public Tile getTile() { return tile; }

    public boolean isCommander(){
        return this.isCommander;
    }

    public void setTeam(Team team){
        this.team = team;
    }

    public Team getTeam(){
        return this.team;
    }

    public void setType(Type type){
        this.type = type;
    }

    public Type getType(){
        return this.type;
    }

    public int getX(){
        return x;
    }

    public int getZ(){
        return z;
    }

    public void setX(int x){
        this.x = x;
        notifyAllObservers();
    }

    public void setZ(int z){
        this.z = z;
        notifyAllObservers();
    }

    public int getMovement(){
        return movement;
    }

    public void setTile(Tile tile) { this.tile = tile; }

    public abstract boolean inTheSameArmyAs(Piece piece);
    public abstract String getAbilityName();
    public abstract int getAbilityCost();
    public abstract List<Tile> getValidCellsForAbility(Tile currentTile);
    public abstract void doAbility(Tile currentTile, Tile selectedTile);
}
