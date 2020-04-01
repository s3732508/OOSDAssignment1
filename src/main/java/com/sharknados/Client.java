package com.sharknados;

import com.sharknados.controllers.BoardController;
import com.sharknados.models.Board;
import com.sharknados.views.BoardView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Client extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage mainStage) throws Exception {
        Board board = new Board(3);
        //Group root = new Group();
        BoardView boardView = new BoardView();
        BoardController boardController = new BoardController(board, boardView);
        Scene scene = new Scene(boardView, 600, 600);
        mainStage.setTitle("Sharknados");
        mainStage.setScene(scene);
        mainStage.show();
    }
}