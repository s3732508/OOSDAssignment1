package com.sharknados.controllers;

import com.sharknados.models.Game;
import com.sharknados.views.HomescreenView;
import com.sharknados.views.PlanningView;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class HomescreenController {
    private final Stage stage;

    public HomescreenController(Stage stage, HomescreenView view) {
        this.stage = stage;

        view.getPlayButton().setOnMouseClicked(this.getPlayClickedHandler());
        view.getExitButton().setOnMouseClicked(this.getExitClickedHandler());
    }

    public EventHandler<MouseEvent> getPlayClickedHandler() {
        return event -> {
            Game game = new Game();
            PlanningView view = new PlanningView(game);
            PlanningController controller = new PlanningController(view);

            stage.setScene(new Scene(view, 490, 700));
        };
    }

    public EventHandler<MouseEvent> getExitClickedHandler() {
        return event -> {
            Platform.exit();
            System.exit(0);
        };
    }
}
