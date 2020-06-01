package com.sharknados.models.pieces.shark;

import com.sharknados.models.pieces.Piece;
import com.sharknados.models.pieces.PieceAbstractFactory;

public class SharkFactory implements PieceAbstractFactory {

    public SharkFactory(){

    }

    public Piece createCommander(){
        return new SharkCommander();
    }

    public Piece createSoldier(){
        return new SharkSoldier();
    }

    public Piece createTank(){
        return new SharkTank();
    }
}
