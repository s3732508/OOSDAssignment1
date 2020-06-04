package com.sharknados.controllers;

import java.io.IOException;
import com.sharknados.models.Game;
import com.sharknados.models.storage.StorageAdapter;
import com.sharknados.models.storage.StorageSingleton;
import com.sharknados.views.HomescreenView;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class HomescreenController {
    private StackPane root;
    private HomescreenView homescreenView;
    private StorageAdapter storage;
    private Stage mainStage;

    public HomescreenController(Stage mainStage, Pane rootPane){
        storage = StorageSingleton.getStorageAdapter();

        this.mainStage = mainStage;
        this.root = (StackPane) rootPane;
        this.homescreenView = new HomescreenView(this);
        rootPane.getChildren().add(homescreenView);
    }

    public void newGameButtonHandler(boolean bigBoard){
        Game game;
        if (!bigBoard) {
            game = new Game(3);
        } else {
            game = new Game(4);
            mainStage.setHeight(900);
        }
        GameController gameController = new GameController (game, root);
        gameController.newGame();

        root.getChildren().remove(homescreenView);
        root.getChildren().add(gameController.getGameView());
    }

    public void loadGameButtonHandler(){
        Game game = null;
        try {
            game = storage.loadSavedGame();
        } catch (IOException e) {
            e.printStackTrace();
        }

        GameController gameController = new GameController (game, root);
        gameController.loadGame();
        root.getChildren().remove(homescreenView);
        root.getChildren().add(gameController.getGameView());
    }

    public void exitGame(){
        Platform.exit();
        System.exit(0);
    }
}
