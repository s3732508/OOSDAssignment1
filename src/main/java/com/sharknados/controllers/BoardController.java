package com.sharknados.controllers;

import com.sharknados.models.Board;
import com.sharknados.models.Tile;
import com.sharknados.views.BoardView;
import com.sharknados.views.PieceView;
import com.sharknados.views.TileView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class BoardController {
    private Board board;
    private BoardView boardView;

    TileView[][] tileViews ;

    public BoardController(Board board, BoardView boardView){

        this.board = board;
        this.boardView = boardView;

        this.tileViews = new TileView[2*board.getSize()+1][2*board.getSize()+1];
        updateBoard();
    }

    public void updateBoard(){
        int size=board.getSize();
        System.out.println("In BoardController Size is " + size);
        List<TileView> tileViewList = new ArrayList<>();
        List<PieceView> pieceViewList = new ArrayList<>();
        System.out.println("Tile List:");
        for (int x = 0; x <= 2*size; x++) {
            int zStart = max(0, size - x);
            int zStop = min(2*size, 3*size - x);
            for (int z = zStart; z <= zStop; z++) {
                boolean occupied = board.getTilePositions(x,z).isOccupied();
                tileViews[x][z]= new TileView(x,z,occupied);
                tileViews[x][z].tile.addEventFilter(MouseEvent.MOUSE_CLICKED, selectTileHandler(board.getTilePositions(x,z)));
                tileViewList.add(tileViews[x][z]);
            }

            try {
                pieceViewList.add(new PieceView(0 , 8, "src/main/resources/Shark.png"));
                pieceViewList.add(new PieceView(1 , 8, "src/main/resources/Shark.png"));
                pieceViewList.add(new PieceView(2 , 8, "src/main/resources/Shark.png"));
                pieceViewList.add(new PieceView(3 , 8, "src/main/resources/Shark.png"));
                pieceViewList.add(new PieceView(4 , 8, "src/main/resources/Shark.png"));
                pieceViewList.add(new PieceView(5 , 7, "src/main/resources/Shark.png"));
                pieceViewList.add(new PieceView(6 , 6, "src/main/resources/Shark.png"));
                pieceViewList.add(new PieceView(7 , 5, "src/main/resources/Shark.png"));
                pieceViewList.add(new PieceView(8 , 4, "src/main/resources/Shark.png"));


                pieceViewList.add(new PieceView(0 , 4, "src/main/resources/Eagle.png"));
                pieceViewList.add(new PieceView(1 , 3, "src/main/resources/Eagle.png"));
                pieceViewList.add(new PieceView(2 , 2, "src/main/resources/Eagle.png"));
                pieceViewList.add(new PieceView(3 , 1, "src/main/resources/Eagle.png"));
                pieceViewList.add(new PieceView(4 , 0, "src/main/resources/Eagle.png"));
                pieceViewList.add(new PieceView(5 , 0, "src/main/resources/Eagle.png"));
                pieceViewList.add(new PieceView(6 , 0, "src/main/resources/Eagle.png"));
                pieceViewList.add(new PieceView(7 , 0, "src/main/resources/Eagle.png"));
                pieceViewList.add(new PieceView(8 , 0, "src/main/resources/Eagle.png"));
            } catch (Exception e) {
                // Handle it.
            }
        }

        boardView.refreshBoard(tileViewList, pieceViewList);
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

