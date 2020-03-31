package com.sharknados.models;

public class Tile {
    private int x, z;
    private Tile north, northEast, southEast, south, southWest, northWest;

    public Tile(int x, int z){
        this.x = x;
        this.z = z;
    }

    public int getX(){
        return x;
    }

    public int getZ(){
        return z;
    }

    public void setNeighborByVector(Tile neighbor, int xVector, int zVector){
        switch(xVector) {
            case -1:
                //NORTHWEST
                if (zVector == 0){
                    setNorthWest(neighbor);
                }

                //SOUTHWEST
                else if (zVector == 1){
                    setSouthWest(neighbor);
                }
                break;

            case 0:
                //NORTH
                if (zVector == -1){
                    setNorth(neighbor);
                }

                //SOUTH
                else if (zVector == 1){
                    setSouth(neighbor);
                }
                break;

            case 1:
                //NORTHEAST
                if (zVector == -1){
                    setNorthEast(neighbor);
                }

                //SOUTHEAST
                else if (zVector == 0){
                    setSouthEast(neighbor);
                }
                break;
            default:
                // code block
        }
    }

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

    @Override
    public String toString(){
        return String.format("(" + x + ", " + z + ")");
    }
}