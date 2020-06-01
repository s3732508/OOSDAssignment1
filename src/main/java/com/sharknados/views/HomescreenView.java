package com.sharknados.views;

import com.sharknados.controllers.HomescreenController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class HomescreenView extends VBox {
    private final Button playButton;
    private final Button loadButton;
    private final Button exitButton;
    private HomescreenController controller;

    public HomescreenView(HomescreenController controller) {
        this.controller = controller;
        setAlignment(Pos.CENTER);
        setSpacing(20);

        Label title = new Label("Sharks vs. Eagles");
        title.setFont(Font.font(48));
        Label subtitle = new Label("by Sharknados");
        subtitle.setFont(Font.font(24));


        playButton = new Button("Play");
        playButton.setFont(Font.font(24));
        playButton.setOnAction(clickPlay());

        loadButton = new Button("Load");
        loadButton.setFont(Font.font(24));
        loadButton.setDisable(false);
        loadButton.setOnAction(clickLoad());


        exitButton = new Button("Exit");
        exitButton.setFont(Font.font(24));
        exitButton.setOnAction(clickExit());

        getChildren().addAll(title, subtitle, playButton, loadButton, exitButton);
    }

    public EventHandler<ActionEvent> clickPlay() {
        return event -> {
            controller.newGameButtonHandler();
        };
    }

    public EventHandler<ActionEvent> clickExit() {
        return event -> {
            controller.exitGame();
        };
    }
    
    public EventHandler<ActionEvent> clickLoad() {
        return event -> {
            controller.loadGameButtonHandler();
        };
    }
    

}