package com.sharknados.models.pieces.sharks;

import com.sharknados.models.Tile;
import com.sharknados.models.pieces.Piece;

public abstract class Shark extends Piece {

    public Shark(int attack, int defence, Tile tile) {
        super(attack, defence, tile);
    }
}
