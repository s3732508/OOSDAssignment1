package com.sharknados.models.pieces;

import com.sharknados.models.AbstractSubject;
import com.sharknados.models.Team;
import com.sharknados.models.Tile;

import java.util.List;

public abstract class Piece extends AbstractSubject implements java.io.Serializable{
    private int defence;
    private int attack;
    private int health;
    private int movement;
    private int x;
    private int z;

    public boolean isCommander(){
        return false;
    }

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

    public boolean takeDamage(int incomingDamage, Tile myTile){
        boolean isGameOver = false;
        Piece self = myTile.getPiece();
        int damage = incomingDamage - self.getDefence();
        if (damage < 1) {
            damage = 1;
        }
        System.out.println("HIT! " + self.getClass().getSimpleName() + " takes: " + damage + " damage!");
        if (getHealth() - damage <= 0) {
            System.out.println(self.getClass().getSimpleName() + " is destroyed!");
            isGameOver = destroyOrGameOver(myTile);
        }
        else {
            int newHealth = getHealth() - damage;
            setHealth(newHealth);
        }
        return isGameOver;
    }

    //return true if a Commander has been destroyed
    public boolean destroyOrGameOver(Tile pieceTile){
        setHealth(0);
        setX(-1);
        setZ(-1);
        pieceTile.setPiece(null);
        return isCommander();
    }

    public abstract Team getTeam();

    public abstract boolean inTheSameArmyAs(Piece piece);

    public abstract List<Tile> getAbilityRange(Tile myTile, List<Tile> allTiles);

    public abstract boolean doAbility(Tile targetTile);
}
