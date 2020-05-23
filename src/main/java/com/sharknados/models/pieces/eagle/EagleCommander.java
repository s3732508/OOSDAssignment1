package com.sharknados.models.pieces.eagle;

public class EagleCommander extends EaglePiece {
    public EagleCommander(int x, int z){
        setX(x);
        setZ(z);
        setAttack(4);
        setDefence(0);
        setHealth(5);
        setMovement(1);
    }
}
