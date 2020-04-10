package com.sharknados.controllers;

import com.sharknados.models.Board;
import com.sharknados.models.Tile;
import com.sharknados.views.BoardView;
import com.sharknados.views.TileView;

import java.util.ArrayList;
import java.util.List;

public class BoardController {
    private Board board;
    private BoardView boardView;

    public BoardController(Board board, BoardView boardView){
        this.board = board;
        this.boardView = boardView;
        updateBoard();
    }

    public void updateBoard(){
        List<Tile> tileList = board.getAllTiles();
        List<TileView> tileViewList = new ArrayList<>();
        System.out.println("Tile List:");
        for(Tile tile : tileList){
            System.out.println(tile.toString());
            int x = tile.getX();
            int z = tile.getZ();
            boolean occupied = tile.isOccupied();
            tileViewList.add(new TileView(x,z,occupied));
        }
        boardView.refreshBoard(tileViewList);
    }


}

