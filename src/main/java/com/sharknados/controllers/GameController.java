package com.sharknados.controllers;

import com.sharknados.models.Board;
import com.sharknados.models.Game;
import com.sharknados.models.Tile;
import com.sharknados.models.pieces.Piece;
import com.sharknados.models.pieces.sharks.WhaleShark;
import com.sharknados.views.PieceView;
import com.sharknados.views.TileView;
import com.sharknados.views.View;
import javafx.event.ActionEvent;
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
    private Piece selectedPiece = null;
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

        //intialize pieces ((currently just 2 test pieces))
        List<PieceView> pieceViewList = new ArrayList<>();
        for (int i = 1; i <= 2; i++) {
            try {
                PieceController testPC = new PieceController();
                Piece testPM = new WhaleShark(1, 1, board.getTileAtPosition(i+1, i+1));
                board.getTileAtPosition(i+1, i+1).setOccupied(true);
                PieceView testPV = new PieceView(testPC,i+1, i+1);
                testPV.piece.addEventFilter(MouseEvent.MOUSE_CLICKED, selectPieceHandler(this, this.view, testPM));
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

    public void setSelectedPiece(Piece selectedPiece) {
        this.selectedPiece = selectedPiece;
    }



    public EventHandler selectPieceHandler (GameController controller, View view, Piece piece){
        List<Tile> tileList = board.getTileList();
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                controller.setSelectedPiece(piece);
                Tile tile = piece.getTile();
                for (Tile t : tileList) {
                    t.setSelected(false);
                    t.setHighlighted(false);
                    t.setUnavailable(false);
                    //todo figure out a better way to keep occupied tiles coloured
                    if(t.isOccupied()){
                        t.setOccupied(false);
                        t.setOccupied(true);
                    }
                }

                tile.setSelected(true);
                view.setCommandBarVisible(true);
            }
        };
        return eventHandler;
    }

    public void moveButtonHandler(){
        List<Tile> tileList = board.getTileList();
        Tile tile = selectedPiece.getTile();
        for (Tile t : tileList) {
            if (!t.isSelected()) {
                t.setUnavailable(true);
            }
        }

        for (int dir = 0; dir < 6; dir++) {
            if (tile.checkneighbor(dir)) {
                if (!tile.getNeighbor(dir).isOccupied()){
                    tile.getNeighbor(dir).setUnavailable(false);
                    tile.getNeighbor(dir).setHighlighted(true);
                    tileViews[tile.getNeighbor(dir).getX()][tile.getNeighbor(dir).getZ()].tile.addEventFilter(MouseEvent.MOUSE_CLICKED, moveToTileHandler(tile.getNeighbor(dir)));
                }
            }
        }
        view.setCommandBarVisible(false);
    }

    public EventHandler moveToTileHandler(Tile tile) {
        List<Tile> tileList = board.getTileList();
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                boolean valid = tile.isHighlighted();
                //deselect all tiles
                for (Tile t : tileList) {
                    t.setSelected(false);
                    t.setHighlighted(false);
                    t.setUnavailable(false);
                    //todo figure out a better way to keep occupied tiles coloured
                    if(t.isOccupied()){
                        t.setOccupied(false);
                        t.setOccupied(true);
                    }
                }

                //remove action listeners from tiles
                if (selectedPiece !=null) {
                    Tile oldTile = selectedPiece.getTile();
                    for (int dir = 0; dir < 6; dir++) {
                        if (oldTile.checkneighbor(dir)) {
                            tileViews[oldTile.getNeighbor(dir).getX()][oldTile.getNeighbor(dir).getZ()].tile.removeEventFilter(MouseEvent.MOUSE_CLICKED, moveToTileHandler(oldTile.getNeighbor(dir)));
                        }
                    }
                    if (valid) {
                        oldTile.setOccupied(false);
                        selectedPiece.setX(tile.getX());
                        selectedPiece.setZ(tile.getZ());
                        selectedPiece.setTile(tile);
                        tile.setOccupied(true);
                        selectedPiece = null;
                    }
                }
            }
        };
        return eventHandler;
    }

}