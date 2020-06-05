package com.sharknados.models.pieces;

import com.sharknados.models.AbstractSubject;
import com.sharknados.models.Team;
import com.sharknados.models.tiles.Tile;

import java.util.List;

public abstract class Piece extends AbstractSubject implements java.io.Serializable {
    private PieceAttribute defence;
    private PieceAttribute attack;
    private PieceAttribute health;
    private PieceAttribute movement;
    private PieceAttribute attackRange;

    private PieceMode mode;

    private int x;
    private int z;
    private boolean selected;

    protected Piece(int attack, int defence, int health, int movement, int attackRange, PieceMode defaultMode) {
        this.defence = new PieceAttribute(defence);
        this.attack = new PieceAttribute(attack);
        this.health = new PieceAttribute(health);
        this.movement = new PieceAttribute(movement);
        this.attackRange = new PieceAttribute(attackRange);

        this.mode = defaultMode;
    }

    public boolean isCommander(){
        return false;
    }

    public boolean isSelected(){
        return this.selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        notifyAllObservers();
    }

    public void setMode(PieceMode mode) {
        this.mode = mode;
        notifyAllObservers();
    }

    private int getAttributeValue(PieceAttribute attribute) {
        return attribute.getValue() + mode.getBonuses(this).getOrDefault(attribute, 0);
    }

    public PieceAttribute getAttackAttribute() {
        return attack;
    }

    public int getAttack(){
        return getAttributeValue(attack);
    }

    public void setAttack(int atk){
        this.attack = new PieceAttribute(atk);
        notifyAllObservers();
    }

    public PieceAttribute getDefenceAttribute() {
        return defence;
    }

    public int getDefence(){
        return getAttributeValue(defence);
    }

    public void setDefence(int def){
        this.defence = new PieceAttribute(def);
        notifyAllObservers();
    }

    public PieceAttribute getHealthAttribute() {
        return health;
    }

    public int getHealth(){
        return getAttributeValue(health);
    }
    public void setHealth(int health){
        this.health = new PieceAttribute(health);
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

    public PieceAttribute getMovementAttribute() {
        return movement;
    }

    public int getMovement(){
        return getAttributeValue(movement);
    }
    public void setMovement(int movement){
        this.movement = new PieceAttribute(movement);
        notifyAllObservers();
    }

    public PieceAttribute getAttackRangeAttribute() {
        return this.attackRange;
    }

    public int getAttackRange() {
        return getAttributeValue(attackRange);
    }

    public void setAttackRange(int range) {
        this.attackRange = new PieceAttribute(range);
    }

    public PieceMode getMode() {
        return mode;
    }

    public boolean takeDamage(int incomingDamage, Tile myTile){
        boolean isGameOver = false;
        Piece self = myTile.getPiece();
        int damage = incomingDamage - self.getDefence();
        System.out.println(self.getClass().getSimpleName() + " takes: " + damage + " damage!");

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

    public abstract boolean doAbility(Tile myTile, Tile targetTile);
}
