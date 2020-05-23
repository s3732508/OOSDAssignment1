package com.sharknados.models.pieces.shark;

public class SharkTank extends SharkPiece {
    public SharkTank(int x, int z){
        setX(x);
        setZ(z);
        setAttack(1);
        setDefence(4);
        setHealth(4);
        setMovement(1);
    }
}
