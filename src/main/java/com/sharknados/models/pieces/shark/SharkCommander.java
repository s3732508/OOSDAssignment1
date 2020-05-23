package com.sharknados.models.pieces.shark;

public class SharkCommander extends SharkPiece {
    public SharkCommander(int x, int z){
        setX(x);
        setZ(z);
        setAttack(4);
        setDefence(0);
        setHealth(5);
        setMovement(1);
    }
}
