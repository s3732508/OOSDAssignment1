package com.sharknados.models.pieces.eagle;

public class EagleCommander extends EaglePiece {
    public EagleCommander(){
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
