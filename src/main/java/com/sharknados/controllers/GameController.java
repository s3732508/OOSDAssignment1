package com.sharknados.controllers;

import com.sharknados.models.Board;
import com.sharknados.models.Game;
import com.sharknados.models.Tile;
import com.sharknados.models.pieces.Piece;
import com.sharknados.models.pieces.eagles.EagleOwl;
import com.sharknados.models.pieces.sharks.GreatWhite;
import com.sharknados.views.PieceView;
import com.sharknados.views.TileView;
import com.sharknados.views.View;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class GameController{
    private Game game;
    private Board board;
    private View view;


    public GameController(Game game) {
        this.game = game;
        this.board = game.getBoard();
        this.view = new View(this);

        //Initialize Tile Views
        int size = board.getSize();
        List<TileView> tileViewList = new ArrayList<>();
        for (int x = 0; x <= 2 * size; x++) {
            int zStart = max(0, size - x);
            int zStop = min(2 * size, 3 * size - x);
            for (int z = zStart; z <= zStop; z++) {
                TileView tileView = new TileView(board.getTileAtPosition(x,z));
                tileView.tilePoly.addEventFilter(MouseEvent.MOUSE_CLICKED, clickTile(tileView));
                tileViewList.add(tileView);
            }
        }

        //intialize test pieces
        List<PieceView> pieceViewList = new ArrayList<>();
        for( int i = 0; i <=3;i++){
            try{
                Piece testPM = new GreatWhite(1, 1, board.getTileAtPosition(i, 6));
                board.getTileAtPosition(i, 6).setOccupied(true);
                board.getTileAtPosition(i, 6).setPiece(testPM);
                PieceView testPV = new PieceView(testPM);
                pieceViewList.add(testPV);
            }catch (Exception e) {
                // Handle it.
            }
        }

        for( int i = 3; i <=6;i++){
            try{
                Piece testPM = new EagleOwl(1, 1, board.getTileAtPosition(i, 0));
                board.getTileAtPosition(i, 0).setOccupied(true);
                board.getTileAtPosition(i, 0).setPiece(testPM);
                PieceView testPV = new PieceView(testPM);
                pieceViewList.add(testPV);
            }catch (Exception e) {
                // Handle it.
            }
        }
        view.addToView(tileViewList, pieceViewList);
    }

    public View getView() {
        return view;
    }


    public EventHandler clickTile (TileView tileView){
        List<Tile> tileList = board.getTileList();
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                Tile tile = (Tile) tileView.getSubject();

                //Selecting a Piece
                if (game.getMode() == Game.Mode.SELECT){
                    //returns true if tile has piece and it is that pieces turn, false otherwise
                    boolean validSelection = game.selectTile(tile);
                    if(validSelection){
                        view.setCommandBarVisible(true);
                    }
                    else{
                        view.setCommandBarVisible(false);
                    }
                }

                //Moving a Piece
                if (game.getMode() == Game.Mode.MOVE){
                    game.executeMove(tile);
                }

                //Attacking a Piece
                if (game.getMode() == Game.Mode.ATTACK){
                    game.executeAttack(tile);
                }

            }
        };
        return eventHandler;
    }

    public void moveButtonHandler(){
        game.setMode(Game.Mode.MOVE);
        game.showMovementRange();
        view.setCommandBarVisible(false);
    }

    public void attackButtonHandler(){
        game.setMode(Game.Mode.ATTACK);
        game.showAttackRange();
        view.setCommandBarVisible(false);
    }

}