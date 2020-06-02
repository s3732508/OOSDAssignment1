package com.sharknados.models.pieces.shark;

public class SharkCommander extends SharkPiece {
    public SharkCommander(){
        super(4, 0, 5, 1);
    }

    @Override
    public boolean isCommander(){
        return true;
    }
}
