package com.sharknados.models;

import com.sharknados.models.pieces.eagle.EagleFactory;
import com.sharknados.models.pieces.PieceFactory;
import com.sharknados.models.pieces.Piece;
import com.sharknados.models.pieces.shark.SharkFactory;

import static java.lang.Math.max;
import static java.lang.Math.min;

import java.util.ArrayList;
import java.util.List;

public class Game extends AbstractSubject implements java.io.Serializable{

    public enum Mode {
        SELECT,
        MOVE,
        ATTACK
    }

    private Board board;
    private Team turn;
    private Mode mode;
    private Tile selectedTile;
    private int turnNumber = 1;

    private int eagleUndoMoveLeft = 3;
    private int sharkUndoMoveLeft = 3;

    private boolean eagleUndoOptionUsed = false;
    private boolean sharkUndoOptionUsed = false;

    public Game() {
        board = new Board(3);
        turn = Team.SHARK;
        mode = Mode.SELECT;
    }

    public List<Piece> createNewGamePieces(){
        //Initial Setup
        List<Piece> pieceList = new ArrayList<>();

        PieceFactory factory = new PieceFactory();

        //Shark Setup
        pieceList.add(factory.getCommander(new SharkFactory(2,5)));
        board.getTileAtPosition(2, 5).setPiece(pieceList.get(pieceList.size() - 1));

        pieceList.add(factory.getSoldier(new SharkFactory(1,6)));
        board.getTileAtPosition(1, 6).setPiece(pieceList.get(pieceList.size() - 1));
        pieceList.add(factory.getSoldier(new SharkFactory(2,6)));
        board.getTileAtPosition(2, 6).setPiece(pieceList.get(pieceList.size() - 1));

        pieceList.add(factory.getTank(new SharkFactory(0,6)));
        board.getTileAtPosition(0, 6).setPiece(pieceList.get(pieceList.size() - 1));
        pieceList.add(factory.getTank(new SharkFactory(3,6)));
        board.getTileAtPosition(3, 6).setPiece(pieceList.get(pieceList.size() - 1));

        //Eagle Setup
        pieceList.add(factory.getCommander(new EagleFactory(4,1)));
        board.getTileAtPosition(4, 1).setPiece(pieceList.get(pieceList.size() - 1));

        pieceList.add(factory.getSoldier(new EagleFactory(4,0)));
        board.getTileAtPosition(4, 0).setPiece(pieceList.get(pieceList.size() - 1));
        pieceList.add(factory.getSoldier(new EagleFactory(5,0)));
        board.getTileAtPosition(5, 0).setPiece(pieceList.get(pieceList.size() - 1));

        pieceList.add(factory.getTank(new EagleFactory(3,0)));
        board.getTileAtPosition(3, 0).setPiece(pieceList.get(pieceList.size() - 1));
        pieceList.add(factory.getTank(new EagleFactory(6,0)));
        board.getTileAtPosition(6, 0).setPiece(pieceList.get(pieceList.size() - 1));

        return pieceList;
    }
    
    public List<Piece> loadGamePieces(){
        //Initial Setup
        List<Piece> pieceList = new ArrayList<>();
        int size=board.getSize();
        Tile[][] tiles=board.getAllTiles();
        
        for (int x = 0; x <= 2*size; x++) {
            int zStart = max(0, size - x);
            int zStop = min(2*size, 3*size - x);
            for (int z = zStart; z <= zStop; z++) {
                tiles[x][z].notifyAllObservers();
            	if(tiles[x][z].isOccupied()) {
            		board.getTileAtPosition(x,z).notifyAllObservers();
            		pieceList.add(tiles[x][z].getPiece());
            	}
            	
            }
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

    public int getTurnNumber() {
        return turnNumber;
    }

    public void setTurnNumber(int turnNumber) {
        this.turnNumber = turnNumber;
    }

    public void incTurnNumber() {
        this.turnNumber = turnNumber + 1;
    }

    public void decEagleUndoMoveLeft() {
        this.eagleUndoMoveLeft = eagleUndoMoveLeft - 1;
    }

    public void decSharkUndoMoveLeft() {
        this.sharkUndoMoveLeft = sharkUndoMoveLeft - 1;
    }

    public boolean isEagleUndoOptionUsed() {
        return eagleUndoOptionUsed;
    }

    public boolean isSharkUndoOptionUsed() {
        return sharkUndoOptionUsed;
    }

    public void setEagleUndoOptionUsed(boolean eagleUndoOptionUsed) {
        this.eagleUndoOptionUsed = eagleUndoOptionUsed;
    }

    public void setSharkUndoOptionUsed(boolean sharkUndoOptionUsed) {
        this.sharkUndoOptionUsed = sharkUndoOptionUsed;
    }

    public Team nextTurn() {
        if (turn == Team.SHARK)
            turn = Team.EAGLE;
        else
            turn = Team.SHARK;
        notifyAllObservers();
        System.out.println("Current turn is: " + getTurnNumber());
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

            selectedTile.setPiece(null);
            piece.setX(tile.getX());
            piece.setZ(tile.getZ());
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
