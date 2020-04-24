package com.sharknados.views;

import com.sharknados.controllers.AbstractController;
import com.sharknados.controllers.GameController;
import javafx.scene.layout.BorderPane;
import java.util.List;

public class GameView extends BorderPane{
    private BorderPane boardPane;
    private CommandBar commandBar;
    private GameController controller = null;

    public GameView(AbstractController controller){
        this.controller = (GameController) controller;
        this.boardPane = new BorderPane();
        this.commandBar = new CommandBar(controller);
        this.setCenter(boardPane);
        this.setBottom(commandBar);
        this.commandBar.setVisible(false);
    }

    public void setCommandBarVisible(boolean vis){
        this.commandBar.setVisible(vis);
    }

    public void addToView(List<TileView> tileViewList, List<PieceView> pieceViewList){
        boardPane.getChildren().clear();
        for (TileView tile : tileViewList) {
            boardPane.getChildren().add(tile.tile);
        }

        for (PieceView piece : pieceViewList) {
            boardPane.getChildren().add(piece.piece);
            boardPane.getChildren().add(piece.pieceBackground);
        }
    }
}
