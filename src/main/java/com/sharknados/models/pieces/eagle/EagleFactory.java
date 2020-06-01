package com.sharknados.models.pieces.eagle;

import com.sharknados.models.pieces.Piece;
import com.sharknados.models.pieces.PieceAbstractFactory;

public class EagleFactory implements PieceAbstractFactory {

    public EagleFactory(){

    }

    public Piece createCommander(){
        return new EagleCommander();
    }

    public Piece createSoldier(){
        return new EagleSoldier();
    }

    public Piece createTank(){
        return new EagleTank();
    }
}
