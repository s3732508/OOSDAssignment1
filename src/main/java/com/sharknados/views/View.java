package com.sharknados.views;

import com.sharknados.controllers.GameController;
import javafx.scene.layout.BorderPane;
import java.util.List;

public class View extends BorderPane{
    private BorderPane boardPane;
    private CommandBar commandBar;
    private GameController controller = null;

    public View(GameController controller){
        this.controller = controller;
        this.boardPane = new BorderPane();
        this.commandBar = new CommandBar(controller);
        this.setCenter(boardPane);
        this.setBottom(commandBar);
        this.commandBar.setVisible(false);
    }

    public void setCommandBarVisible(boolean vis){
        this.commandBar.setVisible(vis);
    }

    public void setCommandBarDisable(boolean disable){
        this.commandBar.disableButtons(disable);
    }

    public void addToView(List<TileView> tileViewList, List<PieceView> pieceViewList){
        boardPane.getChildren().clear();
        for (TileView tile : tileViewList) {
            boardPane.getChildren().add(tile.tilePoly);
        }

        for (PieceView piece : pieceViewList) {
            boardPane.getChildren().add(piece.pieceImage);
            boardPane.getChildren().add(piece.atkText);
            boardPane.getChildren().add(piece.defText);
            boardPane.getChildren().add(piece.hpText);
        }
    }
}
