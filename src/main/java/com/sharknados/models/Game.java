package com.sharknados.models;

import com.sharknados.models.pieces.Piece;
import com.sharknados.models.pieces.eagles.EagleOwl;
import com.sharknados.models.pieces.sharks.GreatWhite;

import java.util.ArrayList;
import java.util.List;

public class Game extends AbstractSubject{

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

    public List<Piece> createNewGamePieces(){
        //Initial Setup
        List<Piece> pieceList = new ArrayList<>();
        for( int i = 0; i <=3;i++){
            Piece piece = new GreatWhite(i, 6);
            board.getTileAtPosition(i, 6).setOccupied(true);
            board.getTileAtPosition(i, 6).setPiece(piece);
            pieceList.add(piece);
        }
        for( int i = 3; i <=6;i++){
            Piece piece = new EagleOwl(i, 0);
            board.getTileAtPosition(i, 0).setOccupied(true);
            board.getTileAtPosition(i, 0).setPiece(piece);
            pieceList.add(piece);
        }
        return pieceList;
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
        notifyAllObservers();
    }

    public Team nextTurn() {
        if (turn == Team.SHARK)
            turn = Team.EAGLE;
        else
            turn = Team.SHARK;
        notifyAllObservers();
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
        if (valid) {
            Piece piece = selectedTile.getPiece();

            //deselect all tiles
            deselectAll();

            selectedTile.setOccupied(false);
            selectedTile.setPiece(null);
            piece.setX(tile.getX());
            piece.setZ(tile.getZ());
            tile.setOccupied(true);
            tile.setPiece(piece);
            selectedTile = null;
            nextTurn();
            setMode(Mode.SELECT);
        }
    }

    public void showAttackRange(){
        Piece piece = selectedTile.getPiece();
        for (Tile t : board.getTileList()) {
            if (!t.isSelected()) {
                t.setUnavailable(true);
            }

            //attack range of 1
            if(board.getDistanceBetweenTiles(selectedTile, t) <= 1){
                if (t.isOccupied()){
                    if(!piece.inTheSameArmyAs(t.getPiece())) {
                        t.setUnavailable(false);
                        t.setHighlighted(true);
                    }
                }
            }
        }

    }

    public void executeAttack(Tile tile){
        boolean valid = tile.isHighlighted();

        if (valid) {
            Piece attacker = selectedTile.getPiece();

            //deselect all tiles
            deselectAll();

            Piece target = tile.getPiece();
            int damage = attacker.getAttack() - target.getDefence();
            if (damage>0){
                if (target.getHealth()-damage <= 0){
                    //todo implement proper solution for destroying pieces
                    //hack
                    tile.setPiece(null);
                    tile.setOccupied(false);
                    target.setX(-1);
                    target.setZ(-1);
                }
                else{
                    int newHealth = target.getHealth() - damage;
                    target.setHealth(newHealth);
                }

            }
            selectedTile = null;
            nextTurn();
            setMode(Mode.SELECT);
        }
    }

    public void cancelAction(){
        deselectAll();
        selectedTile = null;
    }

}
