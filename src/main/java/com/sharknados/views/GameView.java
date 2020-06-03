package com.sharknados.views;

import com.sharknados.controllers.GameController;
import com.sharknados.models.pieces.Piece;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

public class GameView extends BorderPane implements java.io.Serializable{
    private BorderPane boardPane;
    private CommandBar commandBar;
    private StatusBar statusBar;
    private GameController controller;

    public GameView(GameController controller){
        this.controller = controller;
        this.boardPane = new BorderPane();

        this.commandBar = new CommandBar(controller);
        this.statusBar = new StatusBar(controller);

        this.setCenter(boardPane);
        this.setBottom(commandBar);
        this.setTop(statusBar);

        this.commandBar.setVisible(false);
    }

    public void setCommandBarVisible(boolean vis){
        this.commandBar.setVisible(vis);
    }

    public void showCommandBar(Piece selectedPiece) {
        commandBar.setVisible(true);
        commandBar.refreshSelectableModes(selectedPiece);
    }

    public void setCommandBarDisable(boolean disable){
        this.commandBar.disableButtons(disable);
    }

    public void addToView(Node node){
        boardPane.getChildren().add(node);
    }
}
