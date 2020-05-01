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
    private int health;
    private int movement;
    private int x;
    private int z;
    private boolean isCommander;
    private Team team;
    private Type type;

    public Piece(int x, int z, int attack, int defence, int health, int movement) {
        this.attack = attack;
        this.defence = defence;
        this.health = health;
        this.movement = movement;
        this.x = x;
        this.z = z;
        this.isCommander = false;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefence() {
        return defence;
    }

    public int getHealth(){
        return health;
    }

    public void setHealth(int health){
        this.health = health;
        notifyAllObservers();;
    }

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


    public abstract boolean inTheSameArmyAs(Piece piece);
    public abstract String getAbilityName();
    public abstract int getAbilityCost();
    public abstract List<Tile> getValidCellsForAbility(Tile currentTile);
    public abstract void doAbility(Tile currentTile, Tile selectedTile);
}
