package com.sharknados.models;

import com.sharknados.models.pieces.Piece;
import java.util.ArrayList;
import java.util.List;
import static java.lang.Math.*;

public class Board implements java.io.Serializable{

    //Vectors for direction
    public final static int NORTH = 0;
    public final static int NORTHEAST = 1;
    public final static int SOUTHEAST = 2;
    public final static int SOUTH = 3;
    public final static int SOUTHWEST = 4;
    public final static int NORTHWEST = 5;
    public final static int deltaX[] = { 0, 1, 1, 0, -1, -1 };
    public final static int deltaZ[] = { -1, -1, 0, 1, 1, 0 };

    private int size;

    private Tile[][] tilePositions;
    private List<Piece> pieces;

    public Board(int size) {
        this.size = size;
        this.tilePositions = new HexagonTile[2*size+1][2*size+1];
       // this.tilePositions = new TrapTileDecorator[2*size+1][2*size+1];

        // Generate a board. Add tiles to the 2d array
        for (int x = 0; x <= 2*size; x++) {
            int zStart = max(0, size - x);
            int zStop = min(2*size, 3*size - x);
            for (int z = zStart; z <= zStop; z++) {
            	Tile tile=new TrapTileDecorator(new HexagonTile(x,z));
                tilePositions[x][z] = new HexagonTile(x,z);
            }
        }
        Tile tile=new TrapTileDecorator(new HexagonTile(10,10));
        System.out.println(tile.getX());

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

    public Tile[][] getAllTiles(){
        return tilePositions;
    }

    public List<Piece> getPieces() {return pieces;}

    public Tile getTileAtPosition(int x, int z){
        return tilePositions[x][z];
    }

    public Tile getCentreTile(){
        return tilePositions[size][size];
    }

    public List<Tile> getTileList(){
        List<Tile> tileList = new ArrayList<>();
        for (int x = 0; x <= 2*size; x++) {
            int zStart = max(0, size - x);
            int zStop = min(2*size, 3*size - x);
            for (int z = zStart; z <= zStop; z++) {
                tileList.add(tilePositions[x][z]);
            }
        }
        return tileList;
    }

    public int getDistanceBetweenTiles(Tile a, Tile b){
        //convert to cube coords
        int aX = a.getX() - size;
        int aZ = a.getZ() - size;
        int aY = -aX - aZ;
        int bX = b.getX() - size;
        int bZ = b.getZ() - size;
        int bY = -bX - bZ;

        int dX = abs(aX-bX);
        int dY = abs(aY-bY);
        int dZ = abs(aZ-bZ);

        return max(max(dX,dY),dZ);
    }

}
