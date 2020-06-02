package com.sharknados.models.pieces.eagle;

public class EagleCommander extends EaglePiece {
    public EagleCommander(){
        super(4, 0, 5, 1);
    }

    @Override
    public boolean isCommander(){
        return true;
    }
}
