package com.sharknados.models.pieces;

public class PieceFactory {
    public Piece getCommander(PieceAbstractFactory factory){
        return factory.createCommander();
    }

    public Piece getSoldier(PieceAbstractFactory factory){
        return factory.createSoldier();
    }

    public Piece getTank(PieceAbstractFactory factory){
        return factory.createTank();
    }
}
