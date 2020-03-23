package com.sharknados.common;

public class Board {
    private Tile centerTile;

    public Board(Tile centerTile) {
        this.centerTile = centerTile;
    }

    public Tile getCenterTile() {
        return centerTile;
    }
}
