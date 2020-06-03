package com.sharknados;

import com.sharknados.controllers.HomescreenController;
import com.sharknados.controllers.RootController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Client extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage mainStage) throws Exception {
        StackPane rootPane = new StackPane();
        RootController rootController = new RootController(rootPane);

        HomescreenController homescreenController = new HomescreenController(rootController.getRoot());
        Scene scene = new Scene(rootPane,650,700);
        mainStage.setTitle("Sharknados");
        mainStage.setScene(scene);
        mainStage.show();
    }
}