package com.sharknados.models.pieces.eagles;

import com.sharknados.models.Tile;
import com.sharknados.models.pieces.Piece;

public abstract class Eagle extends Piece {

    public Eagle(int attack, int defence, Tile tile) {
        super(attack, defence, tile);
    }
}
