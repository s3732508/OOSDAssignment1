package com.sharknados.views;

import com.sharknados.controllers.GameController;
import com.sharknados.models.Game;
import com.sharknados.models.Subject;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class StatusBar extends HBox implements Observer, java.io.Serializable{
    private Game subject;
    private Button cancelButton;
    private Button undoButton;
    private Button saveButton;
    private Text currentTurn;
    private GameController controller;

    public StatusBar(GameController controller){
        this.controller = controller;
        this.subject = controller.getGame();
        this.subject.attach(this);

        this.currentTurn= new Text();
        currentTurn.setTranslateY(10);
        currentTurn.setFont(new Font("ARIAL", 20));

        cancelButton = new Button("Cancel Action");
        cancelButton.setPrefSize(140,40);
        cancelButton.setOnAction(cancelButtonHandler);


        undoButton = new Button("Undo");
        undoButton.setPrefSize(140,40);
        undoButton.setDisable(false);
        undoButton.setOnAction(undoButtonHandler);

        saveButton = new Button("Save");
        saveButton.setPrefSize(140,40);
        saveButton.setDisable(false);
        saveButton.setOnAction(saveButtonHandler);

        this.setSpacing(5);
        this.setPadding(new Insets(0, 20, 10, 20));

        this.getChildren().addAll(currentTurn, cancelButton, undoButton, saveButton);

        update();
    }

    EventHandler<ActionEvent> cancelButtonHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            controller.cancelButtonHandler();
        }
    };

    EventHandler<ActionEvent> undoButtonHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            controller.undoButtonHandler();
        }
    };

    EventHandler<ActionEvent> saveButtonHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            controller.saveButtonHandler();
        }
    };

    @Override
    public void update() {
        String turn = "Turn: " + subject.getTurn().toString();
        currentTurn.setText(turn);

        if (subject.getMode() == Game.Mode.SELECT){
            cancelButton.setDisable(true);
        }
        else{
            cancelButton.setDisable(false);
        }
    }

    @Override
    public Subject getSubject() {
        return this.subject;
    }
}

