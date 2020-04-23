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

public class GameController extends AbstractController {
    private Game game;
    private Board board;
    private View view;
    private Piece selectedPiece = null;
    private TileView[][] tileViews;
    private List<TileView> tileViewList;
    private EventHandler[] moveFilters, attackFilters;

    public GameController(Game game) {
        this.game = game;
        this.board = game.getBoard();
        this.view = new View(this);

        //Initalize list of tile views and bind models and controllers
        int size = board.getSize();
        this.tileViews = new TileView[2 * size + 1][2 * size + 1];
        this.moveFilters = new EventHandler[6];
        this.attackFilters = new EventHandler[6];
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

        //intialize test pieces
        List<PieceView> pieceViewList = new ArrayList<>();
        for( int i = 0; i <=3;i++){
            try{
                PieceController testPC = new PieceController();
                Piece testPM = new GreatWhite(1, 1, board.getTileAtPosition(i, 6));
                board.getTileAtPosition(i, 6).setOccupied(true);
                board.getTileAtPosition(i, 6).setPiece(testPM);
                PieceView testPV = new PieceView(testPC,i, 6,testPM.getType().toString());
                testPV.piece.addEventFilter(MouseEvent.MOUSE_CLICKED, selectPieceHandler(this, this.view, testPM));
                pieceViewList.add(testPV);
                testPC.addModel(testPM);
                testPC.addView(testPV);
            }catch (Exception e) {
                // Handle it.
            }
        }

        for( int i = 3; i <=6;i++){
            try{
                PieceController testPC = new PieceController();
                Piece testPM = new EagleOwl(1, 1, board.getTileAtPosition(i, 0));
                board.getTileAtPosition(i, 0).setOccupied(true);
                board.getTileAtPosition(i, 0).setPiece(testPM);
                PieceView testPV = new PieceView(testPC,i, 0,testPM.getType().toString());
                testPV.piece.addEventFilter(MouseEvent.MOUSE_CLICKED, selectPieceHandler(this, this.view, testPM));
                pieceViewList.add(testPV);
                testPC.addModel(testPM);
                testPC.addView(testPV);
            }catch (Exception e) {
                // Handle it.
            }
        }
/*        for (int i = 1; i <= 2; i++) {
            try {
                PieceController testPC = new PieceController();
                Piece testPM = new GreatWhite(1, 1, board.getTileAtPosition(i+1, i+1));
                board.getTileAtPosition(i+1, i+1).setOccupied(true);
                PieceView testPV = new PieceView(testPC,i+1, i+1);
                testPV.piece.addEventFilter(MouseEvent.MOUSE_CLICKED, selectPieceHandler(this, this.view, testPM));
                pieceViewList.add(testPV);
                testPC.addModel(testPM);
                testPC.addView(testPV);
            } catch (Exception e) {
                // Handle it.
            }
        }*/
        //add pieces and tiles to view
        view.addToView(tileViewList, pieceViewList);

    }

    public View getView() {
        return view;
    }

    public void setSelectedPiece(Piece selectedPiece) {
        this.selectedPiece = selectedPiece;
    }

    public void clearActionListeners(){
        // clear move/attack listeners
        for (int dir = 0; dir < 6; dir++) {
            if(selectedPiece.getTile().checkneighbor(dir)){
                if (moveFilters[dir] != null ) {
                    tileViews[selectedPiece.getTile().getNeighbor(dir).getX()][selectedPiece.getTile().getNeighbor(dir).getZ()].tile.removeEventFilter(MouseEvent.MOUSE_CLICKED, moveFilters[dir]);
                }
                if (attackFilters[dir] != null ) {
                    tileViews[selectedPiece.getTile().getNeighbor(dir).getX()][selectedPiece.getTile().getNeighbor(dir).getZ()].tile.removeEventFilter(MouseEvent.MOUSE_CLICKED, attackFilters[dir]);
                }
            }
        }

    }



    public EventHandler selectPieceHandler (GameController controller, View view, Piece piece){
        List<Tile> tileList = board.getTileList();
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                //changing selection, remove waiting listeners
                if (controller.selectedPiece != null) {
                    clearActionListeners();
                }
                controller.setSelectedPiece(piece);
                Tile tile = piece.getTile();
                for (Tile t : tileList) {
                    t.setSelected(false);
                    t.setHighlighted(false);
                    t.setUnavailable(false);
                    //todo figure out a better way to keep occupied tiles coloured
                    if (t.isOccupied()) {
                        t.setOccupied(false);
                        t.setOccupied(true);
                    }
                }

                tile.setSelected(true);

                view.setCommandBarDisable(true);
                if (piece.getTeam() == Piece.Team.SHARK && game.getTurn() == Game.Turn.SHARK
                    || piece.getTeam() == Piece.Team.EAGLE && game.getTurn() == Game.Turn.EAGLE) {
                    view.setCommandBarDisable(false);
                }
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
                    this.moveFilters[dir] = moveToTileHandler(tile.getNeighbor(dir));
                    tileViews[tile.getNeighbor(dir).getX()][tile.getNeighbor(dir).getZ()].tile.addEventFilter(MouseEvent.MOUSE_CLICKED, moveFilters[dir]);
                }
            }
        }
        view.setCommandBarVisible(false);
    }

    public EventHandler moveToTileHandler(Tile tile) {
        List<Tile> tileList = board.getTileList();
        GameController controller = this;
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
                    controller.clearActionListeners();

                    if (valid) {
                        oldTile.setOccupied(false);
                        oldTile.setPiece(null);
                        selectedPiece.setX(tile.getX());
                        selectedPiece.setZ(tile.getZ());
                        selectedPiece.setTile(tile);
                        tile.setOccupied(true);
                        tile.setPiece(selectedPiece);
                        // end turn
                        selectedPiece = null;
                        game.nextTurn();
                    }
                }
            }
        };
        return eventHandler;
    }

    public void attackButtonHandler(){
        List<Tile> tileList = board.getTileList();
        List<Tile> threatenedTiles = new ArrayList<>();
        Tile tile = selectedPiece.getTile();
        for (Tile t : tileList) {
            if (!t.isSelected()) {
                t.setUnavailable(true);
            }
        }
        System.out.println("--------------");
        for (int dir = 0; dir < 6; dir++) {
            if (tile.checkneighbor(dir)) {
                if (tile.getNeighbor(dir).isOccupied()){
                    if(!selectedPiece.inTheSameArmyAs(tile.getNeighbor(dir).getPiece())) {
                        System.out.println("..");
                        System.out.println("Cell" + dir);
                        System.out.println("Selected piece " +selectedPiece.getType().toString() + " - Target Piece " + tile.getNeighbor(dir).getPiece().getType().toString() + " - Same team: " + selectedPiece.inTheSameArmyAs(tile.getNeighbor(dir).getPiece()));
                        tile.getNeighbor(dir).setUnavailable(false);
                        tile.getNeighbor(dir).setHighlighted(true);
                        this.attackFilters[dir] = attackTilesHandler(tile.getNeighbor(dir), threatenedTiles);
                        tileViews[tile.getNeighbor(dir).getX()][tile.getNeighbor(dir).getZ()].tile.addEventFilter(MouseEvent.MOUSE_CLICKED, attackFilters[dir]);
                    }
                }
            }
        }
        view.setCommandBarVisible(false);
    }

    public EventHandler attackTilesHandler(Tile targetedTile, List<Tile> threatenedTiles) {
        List<Tile> tileList = board.getTileList();
        GameController controller = this;
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
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
                controller.clearActionListeners();
                //Attack all tiles
                if (selectedPiece.isCommander()){
                    //todo attack all targetable tiles if commander (threatenedTiles)
                    System.out.println("ATTACKING ALL TILES");
                }

                //attack only targeted tile
                else{
                    //todo attack single targeted tile of not commander (targetTile)
                    System.out.println("ATTACKING TARGETED TILE");
                }
                // end turn
                selectedPiece = null;
                game.nextTurn();
            }
        };
        return eventHandler;
    }

}