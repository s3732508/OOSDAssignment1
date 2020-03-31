package com.sharknados.models.pieces.sharks;

import com.sharknados.models.pieces.Piece;

public abstract class Shark extends Piece {
    public Shark(int health, int energy) {
        super(health, 1, energy);
    }
}
