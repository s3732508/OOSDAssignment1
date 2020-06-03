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
    private Button undoButton1;
    private Button undoButton2;
    private Button undoButton3;
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


        undoButton1 = new Button("Undo 1");
        undoButton1.setPrefSize(140,40);
        undoButton1.setDisable(false);
        undoButton1.setOnAction(undoButtonHandler1);

        undoButton2 = new Button("Undo 2");
        undoButton2.setPrefSize(140,40);
        undoButton2.setDisable(false);
        undoButton2.setOnAction(undoButtonHandler2);

        undoButton3 = new Button("Undo 3");
        undoButton3.setPrefSize(140,40);
        undoButton3.setDisable(false);
        undoButton3.setOnAction(undoButtonHandler3);

        saveButton = new Button("Save");
        saveButton.setPrefSize(140,40);
        saveButton.setDisable(false);
        saveButton.setOnAction(saveButtonHandler);

        this.setSpacing(5);
        this.setPadding(new Insets(0, 20, 10, 20));

        this.getChildren().addAll(currentTurn, cancelButton, undoButton1, undoButton2, undoButton3, saveButton);

        update();
    }

    EventHandler<ActionEvent> cancelButtonHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            controller.cancelButtonHandler();
        }
    };

    EventHandler<ActionEvent> undoButtonHandler1 = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            controller.undoButtonHandler(2);
        }
    };

    EventHandler<ActionEvent> undoButtonHandler2 = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            controller.undoButtonHandler(4);
        }
    };

    EventHandler<ActionEvent> undoButtonHandler3 = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            controller.undoButtonHandler(6);
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

    public Button getUndoButton1() {
        return undoButton1;
    }

    public Button getUndoButton2() {
        return undoButton2;
    }

    public Button getUndoButton3() {
        return undoButton3;
    }
}

