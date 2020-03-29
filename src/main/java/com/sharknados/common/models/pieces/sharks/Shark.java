package com.sharknados.common.models.pieces.sharks;

import com.sharknados.common.models.pieces.Piece;

public abstract class Shark extends Piece {
    public Shark(int health, int energy) {
        super(health, 1, energy);
    }
}
