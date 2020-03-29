package com.sharknados.common.models.pieces.eagles;

import com.sharknados.common.models.pieces.Piece;

public abstract class Eagle extends Piece {
    public Eagle(int attack, int energy) {
        super(1, attack, energy);
    }
}
