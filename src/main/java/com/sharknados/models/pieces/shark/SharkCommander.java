package com.sharknados.models.pieces.shark;

public class SharkCommander extends SharkPiece {
    public SharkCommander(){
        setAttack(4);
        setDefence(0);
        setHealth(5);
        setMovement(1);
    }

    @Override
    public boolean isCommander(){
        return true;
    }
}
