package com.sharknados.controllers;

import com.sharknados.models.Game;
import com.sharknados.views.HomescreenView;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class HomescreenController {
    private StackPane root;
    private HomescreenView homescreenView;
    public HomescreenController(Pane root){
        this.root = (StackPane) root;
        this.homescreenView = new HomescreenView(this);
        root.getChildren().add(homescreenView);

    }

    public void newGame(){
        Game game = new Game();
        GameController gameController = new GameController (game);
        gameController.newGame();
        root.getChildren().remove(homescreenView);
        root.getChildren().add(gameController.getGameView());
    }

    public void loadGame(){

    }

    public void exitGame(){
        Platform.exit();
        System.exit(0);
    }
}
