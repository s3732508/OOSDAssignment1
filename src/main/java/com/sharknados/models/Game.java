package com.sharknados.models;


import com.sharknados.models.pieces.Piece;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class Game {

    public enum Mode {
        SELECT,
        MOVE,
        ATTACK
    }

    private Board board;
    private Team turn;
    private Mode mode;
    private Tile selectedTile;
    private GameState saveState;
    private GameState loadState;


    public Game() {
        board = new Board(3);
        turn = Team.SHARK;
        mode = Mode.SELECT;
    }

    public Board getBoard() {
        return board;
    }

    public Team getTurn() {
        return turn;
    }

    public void setTurn(Team turn) {
        this.turn = turn;
    }

    public Mode getMode(){
        return mode;
    }

    public void setMode(Mode mode){
        this.mode = mode;
    }

    public Team nextTurn() {
        if (turn == Team.SHARK)
            turn = Team.EAGLE;
        else
            turn = Team.SHARK;
        return turn;
    }

    public boolean selectTile(Tile tile){
        Piece piece = tile.getPiece();
        if (piece == null || piece.getTeam() != turn){
            return false;
        }
        for (Tile t : board.getTileList()) {
            t.setSelected(false);
            t.setHighlighted(false);
            t.setUnavailable(false);
        }
        this.selectedTile = tile;
        tile.setSelected(true);
        return true;
    }

    public Tile getSelectedTile(){
        return this.selectedTile;
    }

    public void deselectAll(){
        for (Tile t : board.getTileList()) {
            t.setSelected(false);
            t.setHighlighted(false);
            t.setUnavailable(false);
        }
    }

    public void showMovementRange(){
        Piece piece = selectedTile.getPiece();
        int moveRange = piece.getMovement();
        for (Tile t : board.getTileList()) {
            if (!t.isSelected()) {
                t.setUnavailable(true);
            }
            if(board.getDistanceBetweenTiles(selectedTile, t) <= moveRange){
                if (!t.isOccupied()){
                    t.setUnavailable(false);
                    t.setHighlighted(true);
                }
            }
        }
    }
    public void executeMove(Tile tile){
        boolean valid = tile.isHighlighted();
        Piece piece = selectedTile.getPiece();

        //deselect all tiles
        deselectAll();

        if (valid) {
            selectedTile.setOccupied(false);
            selectedTile.setPiece(null);
            piece.setX(tile.getX());
            piece.setZ(tile.getZ());
            tile.setOccupied(true);
            tile.setPiece(piece);
            selectedTile = null;
            nextTurn();
            mode = Mode.SELECT;
        }
    }

    public void showAttackRange(){
        Piece piece = selectedTile.getPiece();
        for (Tile t : board.getTileList()) {
            if (!t.isSelected()) {
                t.setUnavailable(true);
            }
        }

        for (int dir = 0; dir < 6; dir++) {
            if (selectedTile.checkneighbor(dir)) {
                if (selectedTile.getNeighbor(dir).isOccupied()){
                    if(!piece.inTheSameArmyAs(selectedTile.getNeighbor(dir).getPiece())) {
                        selectedTile.getNeighbor(dir).setUnavailable(false);
                        selectedTile.getNeighbor(dir).setHighlighted(true);
                    }
                }
            }
        }
    }

    public void executeAttack(Tile tile){
        Piece piece = selectedTile.getPiece();

        //deselect all tiles
        deselectAll();

        //todo attack all targetable tiles if commander
        //todo attack single targeted tile of not commander
        System.out.println("ATTACKING TARGETED TILE");

        selectedTile = null;
        nextTurn();
        mode = Mode.SELECT;
    }

}
