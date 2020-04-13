package com.sharknados.views;

import javafx.scene.layout.BorderPane;

public class View extends BorderPane {
    private BoardView boardView;
    private CommandBar commandBar;

    public View(){
        this.boardView = new BoardView();
        this.commandBar = new CommandBar();
        this.setCenter(boardView);
        this.setBottom(commandBar);
    }

    public BoardView getBoardView(){
        return boardView;
    }
}
