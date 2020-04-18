package com.sharknados.controllers;

import com.sharknados.models.Board;
import com.sharknados.models.Game;
import com.sharknados.models.Tile;
import com.sharknados.models.pieces.Piece;
import com.sharknados.models.pieces.sharks.WhaleShark;
import com.sharknados.views.PieceView;
import com.sharknados.views.TileView;
import com.sharknados.views.View;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class GameController extends AbstractController {
    private Game game;
    private Board board;
    private View view;
    TileView[][] tileViews;

    List<TileView> tileViewList;

    public GameController(Game game) {
        this.game = game;
        this.board = game.getBoard();
        this.view = new View(this);

        //Initalize list of tile views and bind models and controllers
        int size = board.getSize();
        this.tileViews = new TileView[2 * size + 1][2 * size + 1];
        this.tileViewList = new ArrayList<>();
        for (int x = 0; x <= 2 * size; x++) {
            int zStart = max(0, size - x);
            int zStop = min(2 * size, 3 * size - x);
            for (int z = zStart; z <= zStop; z++) {
                TileController tileController = new TileController();
                TileView tileView = new TileView(tileController, x, z);
                tileViews[x][z] = tileView;
                tileController.addModel(board.getTileAtPosition(x, z));
                tileController.addView(tileView);
                tileViewList.add(tileViews[x][z]);
            }
        }

        //intialize pieces
        List<PieceView> pieceViewList = new ArrayList<>();
        for (int i = 1; i <= 2; i++) {
            try {
                PieceController testPC = new PieceController();
                Piece testPM = new WhaleShark(1, 1, board.getTileAtPosition(i+1, i+1));
                board.getTileAtPosition(i+1, i+1).setOccupied(true);
                PieceView testPV = new PieceView(i+1, i+1);
                testPV.pieceBackground.addEventFilter(MouseEvent.MOUSE_CLICKED, selectPieceHandler(this.view, tileViewList, testPM));
                pieceViewList.add(testPV);
                testPC.addModel(testPM);
                testPC.addView(testPV);
            } catch (Exception e) {
                // Handle it.
            }
        }
        //add pieces and tiles to view
        view.addToView(tileViewList, pieceViewList);

    }

    public View getView() {
        return view;
    }



    public EventHandler selectPieceHandler (View view, List < TileView > tileViewList, Piece piece){
        List<Tile> tileList = board.getTileList();
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                Tile tile = piece.getTile();
                for (Tile t : tileList) {
                    t.setSelected(false);
                    t.setHighlighted(false);
                    t.setUnavailable(true);
                }

                tile.setUnavailable(false);
                tile.setSelected(true);
                for (int dir = 0; dir < 6; dir++) {
                    if (tile.checkneighbor(dir)) {
                        tile.getNeighbor(dir).setUnavailable(false);
                        tile.getNeighbor(dir).setHighlighted(true);
                    }
                }
                view.setCommandBarVisible(true);
            }
        };
        return eventHandler;
    }
}