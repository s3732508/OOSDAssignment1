package com.sharknados.common;

public class Tile {
    private Tile north;
    private Tile northEast;
    private Tile southEast;
    private Tile south;
    private Tile southWest;
    private Tile northWest;

    public Tile getNorth() {
        return north;
    }

    public void setNorth(Tile north) {
        this.north = north;
    }

    public Tile getNorthEast() {
        return northEast;
    }

    public void setNorthEast(Tile northEast) {
        this.northEast = northEast;
    }

    public Tile getSouthEast() {
        return southEast;
    }

    public void setSouthEast(Tile southEast) {
        this.southEast = southEast;
    }

    public Tile getSouth() {
        return south;
    }

    public void setSouth(Tile south) {
        this.south = south;
    }

    public Tile getSouthWest() {
        return southWest;
    }

    public void setSouthWest(Tile southWest) {
        this.southWest = southWest;
    }

    public Tile getNorthWest() {
        return northWest;
    }

    public void setNorthWest(Tile northWest) {
        this.northWest = northWest;
    }
}