package com.sharknados.controllers;

import com.sharknados.models.Board;
import com.sharknados.models.Tile;
import com.sharknados.views.BoardView;
import com.sharknados.views.TileView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class BoardController {
    private Board board;
    private BoardView boardView;
    Tile[][] tiles;
    TileView[][] tileViews ;

    public BoardController(Board board, BoardView boardView){

        this.board = board;
        this.boardView = boardView;
        this.tiles= board.getAllTiles();
        this.tileViews = new TileView[2*board.getSize()+1][2*board.getSize()+1];
        updateBoard();
    }

    public void updateBoard(){
        int size=board.getSize();
        System.out.println("In BoardController Size is " + size);
        List<TileView> tileViewList = new ArrayList<>();
        System.out.println("Tile List:");
        for (int x = 0; x <= 2*size; x++) {
            int zStart = max(0, size - x);
            int zStop = min(2*size, 3*size - x);
            for (int z = zStart; z <= zStop; z++) {
                boolean occupied = tiles[x][z].isOccupied();
                tileViews[x][z]= new TileView(x,z,occupied);
                tileViews[x][z].tile.addEventFilter(MouseEvent.MOUSE_CLICKED, selectTileHandler(tiles[x][z]));
                tileViewList.add(tileViews[x][z]);
            }
        }

        boardView.refreshBoard(tileViewList);
    }

    public EventHandler selectTileHandler(Tile tile) {
        int size=board.getSize();
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                for (int x = 0; x <= 2*size; x++) {
                    int zStart = max(0, size - x);
                    int zStop = min(2 * size, 3 * size - x);
                    for (int z = zStart; z <= zStop; z++) {
                        tileViews[x][z].unselect();
                        tileViews[x][z].pathnotallowed();
                    }
                }
                tileViews[tile.getX()][tile.getZ()].select();
                for(int dir=0; dir < 6; dir++) {
                    if(tile.checkneighbor(dir)){
                        tileViews[tile.getNeighbor(dir).getX()][tile.getNeighbor(dir).getZ()].pathallowed();
                    }
                }

            }
        };
        return eventHandler;
    }
}

