package com.sharknados.models;

import com.sharknados.models.pieces.PieceAbstractFactory;
import com.sharknados.models.pieces.Piece;
import com.sharknados.util.Point;
import javafx.application.Platform;

import static java.lang.Math.max;
import static java.lang.Math.min;

import java.util.ArrayList;
import java.util.List;

public class Game extends AbstractSubject implements java.io.Serializable{

    public enum Mode {
        SELECT,
        MOVE,
        ATTACK,
        ABILITY
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

    public Game(int size) {
        board = new Board(size);
        turn = Team.SHARK;
        mode = Mode.SELECT;
    }

    public Piece getCommander(PieceAbstractFactory factory){
        return factory.createCommander();
    }

    public Piece getSoldier(PieceAbstractFactory factory){
        return factory.createSoldier();
    }

    public Piece getTank(PieceAbstractFactory factory){
        return factory.createTank();
    }

    // TODO: create for generic size
    public List<Piece> createPieces(Point[] positions, PieceAbstractFactory factory){
        Piece piece;
        List<Piece> pieceList = new ArrayList<>();

        //Commander
        piece = getCommander(factory);
        piece.setX(positions[0].x());
        piece.setZ(positions[0].z());
        pieceList.add(piece);
        board.getTileAtPosition(piece.getX(), piece.getZ()).setPiece(piece);

        //Soldiers
        for(int i = 1; i<= this.getBoard().getSize() - 1; i++){ //1to2 for small, 1to3 for big
            piece = getSoldier(factory);
            piece.setX(positions[i].x());
            piece.setZ(positions[i].z());
            pieceList.add(piece);
            board.getTileAtPosition(piece.getX(), piece.getZ()).setPiece(piece);
        }

        //Tanks
        for(int i = this.getBoard().getSize(); i<= (this.getBoard().getSize()-1)*2; i++) { //3to4 for small, 4to6 for big
            piece = getTank(factory);
            piece.setX(positions[i].x());
            piece.setZ(positions[i].z());
            pieceList.add(piece);
            board.getTileAtPosition(piece.getX(), piece.getZ()).setPiece(piece);
        }

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
        incTurnNumber();
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

            if(board.getDistanceBetweenTiles(selectedTile, t) <= piece.getAttackRange()){
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
            //deselect all tiles
            deselectAll();

            Piece attacker = selectedTile.getPiece();
            Piece target = tile.getPiece();

            System.out.println("---------------");
            System.out.println("ATTACK");
            System.out.println("Attacker: " + attacker.getClass().getSimpleName() + " -- Attack: " + attacker.getAttack());
            System.out.println("VS.");
            System.out.println("Defender: " + target.getClass().getSimpleName() + " -- Defense: " + target.getDefence());
            //Deal damage and check if a commander is killed
            boolean isGameOver = target.takeDamage(attacker.getAttack(), tile);
            if (isGameOver){
                gameOver();
            }
            System.out.println("---------------");

            selectedTile = null;
            nextTurn();
            setMode(Mode.SELECT);
        }
    }

    public void showAbilityRange(){
        Piece piece = selectedTile.getPiece();

        //make all tiles unavailable
        List<Tile> allTiles = board.getTileList();
        for (Tile t : allTiles) {
            if (!t.isSelected()) {
                t.setUnavailable(true);
            }
        }

        //get range of ability from piece
        List<Tile> tilesInRange = piece.getAbilityRange(selectedTile, allTiles);
        for (Tile t : tilesInRange) {
            t.setUnavailable(false);
            t.setHighlighted(true);
        }
    }

    public void executeAbility(Tile targetTile){
        boolean valid = targetTile.isHighlighted();

        if (valid) {
            //deselect all tiles
            deselectAll();

            Piece self = selectedTile.getPiece();
            boolean isGameOver = self.doAbility(targetTile);
            if (isGameOver){
                gameOver();
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

    public void gameOver(){
        System.out.println("---------------");
        System.out.println("---GAME OVER---");
        System.out.println("---------------");
        Platform.exit();
        System.exit(0);
    }
}
