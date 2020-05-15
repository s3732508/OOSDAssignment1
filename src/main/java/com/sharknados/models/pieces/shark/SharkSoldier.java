package com.sharknados.models.pieces.shark;

public class SharkSoldier extends SharkPiece {
    public SharkSoldier(int x, int z){
        setX(x);
        setZ(z);
        setAttack(2);
        setDefence(2);
        setHealth(5);
        setMovement(1);
    }
}