package com.sharknados.models.pieces;

public interface PieceAbstractFactory {
    public Piece createCommander();

    public Piece createSoldier();

    public Piece createTank();
}
