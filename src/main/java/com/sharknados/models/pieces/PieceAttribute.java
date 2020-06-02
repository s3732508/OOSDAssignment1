package com.sharknados.models.pieces;

import java.io.Serializable;

public class PieceAttribute implements Serializable {
    private final int value;

    public PieceAttribute(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
