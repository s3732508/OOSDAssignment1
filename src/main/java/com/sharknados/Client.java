package com.sharknados;


import com.sharknados.controllers.GameController;

import com.sharknados.controllers.HomescreenController;
import com.sharknados.models.Game;
import com.sharknados.views.GameView;
import com.sharknados.views.HomescreenView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Client extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage mainStage) throws Exception {
        StackPane root = new StackPane();
        HomescreenController homescreenController = new HomescreenController(root);
        Scene scene = new Scene(root,490,700);
        mainStage.setTitle("Sharknados");
        mainStage.setScene(scene);
        mainStage.show();
    }
}