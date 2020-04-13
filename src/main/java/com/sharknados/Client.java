package com.sharknados;

import com.sharknados.controllers.BoardController;
import com.sharknados.models.Board;
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
        Board board = new Board(4);
        StackPane root = new StackPane();
        View view = new View();
        root.getChildren().add(view);
        BoardController boardController = new BoardController(board, view.getBoardView());
        Scene scene = new Scene(root,480,700);
        mainStage.setTitle("Sharknados");
        mainStage.setScene(scene);
        mainStage.show();
    }
}