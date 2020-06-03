package com.sharknados.controllers;

import com.sharknados.models.Game;
import com.sharknados.models.Team;
import com.sharknados.models.Tile;
import com.sharknados.models.pieces.Piece;
import com.sharknados.models.pieces.PieceMode;
import com.sharknados.models.pieces.eagle.EagleFactory;
import com.sharknados.models.pieces.shark.SharkFactory;
import com.sharknados.util.Point;
import com.sharknados.views.GameView;
import com.sharknados.views.PieceView;
import com.sharknados.views.TileView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class GameController{
    private Game game;
    private GameView gameView;
    private Pane rootPane;


    public GameController(Game game, Pane rootPane) {
        this.game = game;
        this.rootPane = rootPane;
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
                for(Text t : tileView.decoratorList){
                    gameView.addToView(t);
                }
            }
        }
    }

    public void newGame(){
        Point playerOnePieces[] = {new Point(2,5), new Point(1,6), new Point(2,6), new Point(0,6), new Point(3,6)};
        Point playerTwoPieces[] = {new Point(4,1), new Point(4,0), new Point(5,0), new Point(3,0), new Point(6,0)};

        List<Piece> pieceList = new ArrayList<>();
        pieceList.addAll(game.createPieces(playerOnePieces, new SharkFactory()));
        pieceList.addAll(game.createPieces(playerTwoPieces, new EagleFactory()));

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
                System.out.println("shit happens");
            }
        }
    }

    public GameView getGameView() {
        return gameView;
    }

    public Game getGame() {
        return game;
    }

    public Piece getSelectedPiece() {
        return game.getSelectedTile().getPiece();
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
                        gameView.showCommandBar(getSelectedPiece());
                    }
                    else{
                        gameView.setCommandBarVisible(false);
                    }
                }

                //Moving a Piece
                if (game.getMode() == Game.Mode.MOVE){
                    game.executeMove(tile);//nextTurn() is part of executeMove() and is invoked so can save as a "turn"
                    saveButtonHandler();
                }

                //Attacking a Piece
                if (game.getMode() == Game.Mode.ATTACK){
                    game.executeAttack(tile);//same with this
                    saveButtonHandler();
                }

                //Piece Special Ability
                if (game.getMode() == Game.Mode.ABILITY){
                    game.executeAbility(tile);//same with this
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

    public void abilityButtonHandler(){
        game.setMode(Game.Mode.ABILITY);
        game.showAbilityRange();
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
//            System.out.printf("Serialized data is saved in /tmp/game.ser\n");

        } catch (IOException i) {
            i.printStackTrace();
        }

        // same for turn number
        try {
            FileOutputStream gameOut =
                    new FileOutputStream("src/main/Saved Game/" + game.getTurnNumber() + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(gameOut);
            out.writeObject(game);
            out.close();
            gameOut.close();
//            System.out.printf("Serialized data is saved in /tmp/" + game.getTurnNumber() + ".ser\n");

        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public void undoButtonHandler(){

        System.out.println("Current turn is: " + game.getTurnNumber());

        int lastTurn = game.getTurnNumber() - 1;

        if (lastTurn < 1) return;

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

        System.out.println("Undo Move, loading turn " + lastTurn);

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

//        game.setSharkUndoOptionUsed(sharkUndoOptionUsed);
//        game.setEagleUndoOptionUsed(eagleUndoOptionUsed);

        rootPane.getChildren().remove(gameView);

        init(); // reinit with new game obj
        loadGame();

        rootPane.getChildren().add(gameView);

        System.out.println("Current turn is: " + game.getTurnNumber());
    }

    public void updateSelectedPieceMode(PieceMode mode) {
        Piece piece = getSelectedPiece();
        piece.setMode(mode);
        gameView.showCommandBar(piece);
    }
}