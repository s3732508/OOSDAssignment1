package com.sharknados.models;


import java.util.ArrayList;
import java.util.List;

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

    public final static int NORTH = 0;
    public final static int NORTHEAST = 1;
    public final static int SOUTHEAST = 2;
    public final static int SOUTH = 3;
    public final static int SOUTHWEST = 4;
    public final static int NORTHWEST = 5;

    private int size;

    public final static int deltaX[] = { 0, 1, 1, 0, -1, -1 };
    public final static int deltaZ[] = { -1, -1, 0, 1, 1, 0 };

    private Tile[][] tilePositions;

    public Board(int size) {
        this.size = size;
        this.tilePositions = new Tile[2*size+1][2*size+1];

        // Generate a board. Add tiles to the 2d array
        for (int x = 0; x <= 2*size; x++) {
            int zStart = max(0, size - x);
            int zStop = min(2*size, 3*size - x);
            for (int z = zStart; z <= zStop; z++) {
                tilePositions[x][z] = new Tile(x,z);
            }
        }

        // For each tile in the board set each of it's neighbors
        for (int x = 0; x <= 2*size; x++) {
            int zStart = max(0, size - x);
            int zStop = min(2*size, 3*size - x);
            for (int z = zStart; z <= zStop; z++) {
                //Loop through each direction setting a neighbor if it exists
                for (int direction = 0; direction < 6; direction ++){
                    trySetNeighbor(tilePositions[x][z], direction);
                }
            }
        }
    }


    private boolean trySetNeighbor(Tile tile, int direction){
        boolean success = false;
        Tile neighbor = null;
        try {
            neighbor = tilePositions[tile.getX() + deltaX[direction]][tile.getZ() + deltaZ[direction]];
        } catch (ArrayIndexOutOfBoundsException e) {
            //
        }
        if (neighbor != null){
            tile.setNeighbor(neighbor, direction);
            success = true;
        }
        return success;
    }

    public int getSize() {
        return size;
    }
    public List<Tile> getAllTiles(){
        List<Tile> list = new ArrayList<Tile>();

        for (int x = 0; x <= 2*size; x++) {
            int zStart = max(0, size - x);
            int zStop = min(2*size, 3*size - x);
            for (int z = zStart; z <= zStop; z++) {
                list.add(tilePositions[x][z]);
            }
        }

        return list;
    }
}
