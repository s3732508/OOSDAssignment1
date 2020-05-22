package com.sharknados.controllers;

import com.sharknados.models.Game;
import com.sharknados.models.Team;
import com.sharknados.models.Tile;
import com.sharknados.models.pieces.Piece;
import com.sharknados.views.PieceView;
import com.sharknados.views.TileView;
import com.sharknados.views.GameView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.io.*;
import java.util.List;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class GameController{
    private Game game;
    private GameView gameView;


    public GameController(Game game) {
        this.game = game;
        init();
    }

    public void init() {
        this.gameView = new GameView(this);

        //Initialize Tile Views
        int size = game.getBoard().getSize();
        for (int x = 0; x <= 2 * size; x++) {
            int zStart = max(0, size - x);
            int zStop = min(2 * size, 3 * size - x);
            for (int z = zStart; z <= zStop; z++) {
                TileView tileView = new TileView(game.getBoard().getTileAtPosition(x,z));
                tileView.tilePoly.addEventFilter(MouseEvent.MOUSE_CLICKED, clickTile(tileView));
                gameView.addToView(tileView.tilePoly);
            }
        }
    }

    public void newGame(){
        List<Piece> pieceList = game.createNewGamePieces();
        for(Piece p : pieceList){
            try {
                PieceView pieceView = new PieceView(p);
                gameView.addToView(pieceView.pieceImage);
                gameView.addToView(pieceView.atkText);
                gameView.addToView(pieceView.defText);
                gameView.addToView(pieceView.hpText);

            }catch (Exception e) {
                // Handle it.
            }
        }

        saveButtonHandler();
    }
    
    public void loadGame() {
    	System.out.println(" In Load Game");
    	List<Piece> pieceList = game.loadGamePieces();
        for(Piece p : pieceList){
            try {
                PieceView pieceView = new PieceView(p);
                gameView.addToView(pieceView.pieceImage);
                gameView.addToView(pieceView.atkText);
                gameView.addToView(pieceView.defText);
                gameView.addToView(pieceView.hpText);

            }catch (Exception e) {
                // Handle it.
            }
        }
    }

    public GameView getGameView() {
        return gameView;
    }

    public Game getGame() {
        return game;
    }


    public EventHandler clickTile (TileView tileView){
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                Tile tile = (Tile) tileView.getSubject();

                //Selecting a Piece
                if (game.getMode() == Game.Mode.SELECT){
                    boolean validSelection = game.selectTile(tile);
                    //returns true if tile has piece and it is that pieces turn, false otherwise
                    if(validSelection){
                        gameView.setCommandBarVisible(true);
                    }
                    else{
                        gameView.setCommandBarVisible(false);
                    }
                }

                //Moving a Piece
                if (game.getMode() == Game.Mode.MOVE){
                    game.executeMove(tile);
                    saveButtonHandler();
                }

                //Attacking a Piece
                if (game.getMode() == Game.Mode.ATTACK){
                    game.executeAttack(tile);
                    saveButtonHandler();
                }

            }
        };
        return eventHandler;
    }

    public void moveButtonHandler(){
        game.setMode(Game.Mode.MOVE);
        game.showMovementRange();
        gameView.setCommandBarVisible(false);
    }

    public void attackButtonHandler(){
        game.setMode(Game.Mode.ATTACK);
        game.showAttackRange();
        gameView.setCommandBarVisible(false);
    }

    public void cancelButtonHandler(){
        game.setMode(Game.Mode.SELECT);
        game.cancelAction();
        gameView.setCommandBarVisible(false);
    }
    
    public void saveButtonHandler(){
        try {
            FileOutputStream gameOut =
                    new FileOutputStream("src/main/Saved Game/game.ser");
            ObjectOutputStream out = new ObjectOutputStream(gameOut);
            out.writeObject(game);
            out.close();
            gameOut.close();
            System.out.printf("Serialized data is saved in /tmp/game.ser\n");

        } catch (IOException i) {
            i.printStackTrace();
        }

        // same shit for turn number
        try {
            FileOutputStream gameOut =
                    new FileOutputStream("src/main/Saved Game/" + game.getTurnNumber() + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(gameOut);
            out.writeObject(game);
            out.close();
            gameOut.close();
            System.out.printf("Serialized data is saved in /tmp/" + game.getTurnNumber() + ".ser\n");

        } catch (IOException i) {
            i.printStackTrace();
        }

        game.incTurnNumber();
    }

    public void undoButtonHandler(){

        int lastTurn = game.getTurnNumber() - 1;
        boolean eagleUndoOptionUsed = game.isEagleUndoOptionUsed();
        boolean sharkUndoOptionUsed = game.isEagleUndoOptionUsed();

        if (game.getTurn() == Team.EAGLE) {
            if (!game.isEagleUndoOptionUsed()) {
                eagleUndoOptionUsed = true;
            } else {
                return;
            }
        }


        if (game.getTurn() == Team.SHARK) {
            if (!game.isSharkUndoOptionUsed()) {
                sharkUndoOptionUsed = true;
            } else {
                return;
            }
        }

        game=null;

        System.out.println("Undo Move");

        try {
            FileInputStream fileIn = new FileInputStream("src/main/Saved Game/" + lastTurn + ".ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            game = (Game) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
            return;
        }

        //TODO reload game & UI
//        game.setSharkUndoOptionUsed(sharkUndoOptionUsed);
//        game.setEagleUndoOptionUsed(eagleUndoOptionUsed);
        init(); // reinit with new game obj
        GameController gameController = new GameController (game);
        gameController.loadGame();
        //loadGame(); // this should reload UI
        game.notifyAllObservers();
        //pieces object needs to notify all observers?
        System.out.println("Current turn is: " + game.getTurnNumber());
    }

}