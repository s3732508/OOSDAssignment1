package com.sharknados.models.pieces;

import com.sharknados.models.AbstractSubject;
import com.sharknados.models.Team;

public abstract class Piece extends AbstractSubject implements java.io.Serializable{
    private int defence;
    private int attack;
    private int health;
    private int movement;
    private int x;
    private int z;



    public int getAttack(){
        return attack;
    }
    public void setAttack(int atk){
        this.attack = atk;
        notifyAllObservers();
    }

    public int getDefence(){
        return defence;
    }
    public void setDefence(int def){
        this.defence = def;
        notifyAllObservers();
    }

    public int getHealth(){
        return health;
    }
    public void setHealth(int health){
        this.health = health;
        notifyAllObservers();
    }

    public int getX(){
        return x;
    }
    public void setX(int x){
        this.x = x;
        notifyAllObservers();
    }

    public int getZ(){
        return z;
    }
    public void setZ(int z){
        this.z = z;
        notifyAllObservers();
    }

    public int getMovement(){
        return movement;
    }
    public void setMovement(int movement){
        this.movement = movement;
        notifyAllObservers();
    }

    public abstract Team getTeam();

    public abstract boolean inTheSameArmyAs(Piece piece);
}
