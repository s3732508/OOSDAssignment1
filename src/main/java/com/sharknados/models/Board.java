package com.sharknados.models;


import com.sharknados.models.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;
import static java.lang.Math.abs;

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

    private Tile[][] tiles;
    private List<Piece> pieces;

    public Board(int size) {
        this.size = size;
        this.tiles = new Tile[2*size+1][2*size+1];

        // Generate a board. Add tiles to the 2d array
        for (int x = 0; x <= 2*size; x++) {
            int zStart = max(0, size - x);
            int zStop = min(2*size, 3*size - x);
            for (int z = zStart; z <= zStop; z++) {
                tiles[x][z] = new Tile(x,z);
            }
        }

        // For each tile in the board set each of it's neighbors
        for (int x = 0; x <= 2*size; x++) {
            int zStart = max(0, size - x);
            int zStop = min(2*size, 3*size - x);
            for (int z = zStart; z <= zStop; z++) {
                //Loop through each direction setting a neighbor if it exists
                for (int direction = 0; direction < 6; direction ++){
                    trySetNeighbor(tiles[x][z], direction);
                }
            }
        }
    }

    private boolean canMovePieceToTile(Piece piece, Tile destTile) {
        Tile fromTile = piece.getTile();

        if (destTile == piece.getTile())
            return false;
        //limit movement to just one tile away
        int x = destTile.getX() - fromTile.getX();
        int z = destTile.getZ() - fromTile.getZ();
        if (abs(x) > 1 || abs(z) > 1) {
            return false;
        }
        // if the destination Tile is occupied by enemy, move in
        Piece destPiece = destTile.getOccupyingPiece(getPieces());
        if (destPiece != null) {
            if (piece.inTheSameArmyAs(destPiece))
                return false;
        }
        return true;
    }

    public void move(Piece piece, Tile tile) {
        // if tile is already occupied, kill the occupier
        Piece destPiece = tile.getOccupyingPiece(pieces);
        if (destPiece != null) {
            destPiece.setTile(null); //killed
        }
        // move
        piece.setTile(tile);
    }

    private boolean trySetNeighbor(Tile tile, int direction){
        boolean success = false;
        Tile neighbor = null;
        try {
            neighbor = tiles[tile.getX() + deltaX[direction]][tile.getZ() + deltaZ[direction]];
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
    public Tile[][] getTiles(){
        return tiles;
    }

    public List<Piece> getPieces() {return pieces;}
    public Tile getTilePositions(int i, int j) {
        return tiles[i][j];
    }

    public Tile getTileAt(int x, int z){
        return tiles[x][z];
    }

}
