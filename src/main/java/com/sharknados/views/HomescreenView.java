package com.sharknados.views;

import com.sharknados.Utils;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class HomescreenView extends VBox {
    private final Button playButton;
    private final Button exitButton;

    public HomescreenView() {
        setAlignment(Pos.CENTER);
        setSpacing(20);

        Label title = new Label("Sharks vs. Eagles");
        Utils.setFontSize(title, 48);
        Label subtitle = new Label("by Sharknados");
        Utils.setFontSize(subtitle, 24);

        playButton = new Button("Play");
        Utils.setFontSize(playButton, 24);

        exitButton = new Button("Exit");
        Utils.setFontSize(exitButton, 24);

        getChildren().addAll(title, subtitle, playButton, exitButton);
    }

    public Button getPlayButton() {
        return playButton;
    }

    public Button getExitButton() {
        return exitButton;
    }
}
