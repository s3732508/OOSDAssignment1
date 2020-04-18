package com.sharknados;


import com.sharknados.controllers.GameController;

import com.sharknados.models.Game;
import com.sharknados.views.View;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Client extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage mainStage) throws Exception {
        Game game = new Game();
        StackPane root = new StackPane();
        GameController gameController = new GameController (game);
        View view = gameController.getView();
        root.getChildren().add(view);
        Scene scene = new Scene(root,490,700);
        mainStage.setTitle("Sharknados");
        mainStage.setScene(scene);
        mainStage.show();
    }
}