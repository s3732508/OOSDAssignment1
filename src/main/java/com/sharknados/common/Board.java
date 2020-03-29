package com.sharknados.common;


import static java.lang.Math.max;
import static java.lang.Math.min;

public class Board {
    /*
    //
    Vectors for direction
    //
    NORTH       = (0, -1)
    NORTHEAST   = (+1, -1)
    SOUTHEAST   = (+1, 0)
    SOUTH       = (0, +1)
    SOUTHWEST   = (-1, +1)
    NORTHWEST   = (-1, 0)
     */

    private Tile[][] tilePositions;

    public void generateBoard(int size){
        this.tilePositions = new Tile[2*size+1][2*size+1];

        for (int x = 0; x <= 2*size; x++) {
            int zStart = max(0, size - x);
            int zStop = min(2*size, 3*size - x);
            for (int z = zStart; z <= zStop; z++) {
                tilePositions[x][z] = new Tile(x,z);
            }
        }

        for (int x = 0; x <= 2*size; x++) {
            int zStart = max(0, size - x);
            int zStop = min(2*size, 3*size - x);
            for (int z = zStart; z <= zStop; z++) {
                setNeighbors(tilePositions[x][z]);
            }
        }

    }

    private void setNeighbors(Tile tile){
        //NORTH       = (0, -1)
        trySetNeighbor(tile, 0, -1);
        //NORTHEAST   = (+1, -1)
        trySetNeighbor(tile, 1, -1);
        //SOUTHEAST   = (+1, 0)
        trySetNeighbor(tile, +1, 0);
        //SOUTH       = (0, +1)
        trySetNeighbor(tile, 0, +1);
        //SOUTHWEST   = (-1, +1)
        trySetNeighbor(tile, -1, +1);
        //NORTHWEST   = (-1, 0)
        trySetNeighbor(tile, -1, 0);
    }

    private boolean trySetNeighbor(Tile tile, int xVector, int zVector){
        boolean success = false;
        Tile neighbor = tilePositions[tile.getX()+xVector][tile.getZ()+zVector];
        if (neighbor != null){
            tile.setNeighborByVector(neighbor, xVector, zVector);
            success = true;
        }
        return success;
    }
}
