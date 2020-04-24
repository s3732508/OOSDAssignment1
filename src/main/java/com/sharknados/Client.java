package com.sharknados;


import com.sharknados.controllers.HomescreenController;
import com.sharknados.views.HomescreenView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Client extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        stage.setTitle("Sharknados");
        stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/Shark.png")));
        stage.setResizable(false);

//        Game game = new Game();
//        StackPane root = new StackPane();
//        GameController gameController = new GameController(game);
//        GameView view = gameController.getView();
//        root.getChildren().add(view);
//        Scene scene = new Scene(root,490,700);

        HomescreenView root = new HomescreenView();
        HomescreenController controller = new HomescreenController(stage, root);

        stage.setScene(new Scene(root, 490, 700));
        stage.show();
    }
}