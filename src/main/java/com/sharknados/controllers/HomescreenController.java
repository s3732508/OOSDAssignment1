package com.sharknados.controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

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
    	Game game=null;
    	System.out.println("Load Game");
    	
    	try {
            FileInputStream fileIn = new FileInputStream("src/main/Saved Game/game.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            game = (Game) in.readObject();
            in.close();
            fileIn.close();
         } catch (IOException i) {
            i.printStackTrace();
            return;
         } catch (ClassNotFoundException c) {
            System.out.println("Game Object not Found");
            c.printStackTrace();
            return;
         }
         
         System.out.println("Deserialized Game...");
         GameController gameController = new GameController (game);
         gameController.loadGame();
         root.getChildren().remove(homescreenView);
         root.getChildren().add(gameController.getGameView());

    }

    public void exitGame(){
        Platform.exit();
        System.exit(0);
    }
}
