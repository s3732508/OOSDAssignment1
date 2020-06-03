package com.sharknados.views;

import com.sharknados.controllers.GameController;
import com.sharknados.models.pieces.Piece;
import com.sharknados.models.pieces.PieceMode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class CommandBar extends HBox implements java.io.Serializable{
    private Button attackButt;
    private Button moveButt;
    private Button abilityButt;
    private GameController controller;

    public CommandBar(GameController controller){
        this.controller = controller;

        this.setSpacing(5);
        this.setPadding(new Insets(0, 20, 10, 20));
    }

    public void init() {
        attackButt = new Button("Attack");
        attackButt.setPrefSize(140,40);
        attackButt.setOnAction(attackButtonHandler);


        moveButt = new Button("Move");
        moveButt.setPrefSize(140,40);
        moveButt.setOnAction(moveButtonHandler);

        abilityButt = new Button("Ability");
        abilityButt.setPrefSize(140,40);
        abilityButt.setOnAction(abilityButtonHandler);

        this.getChildren().addAll(attackButt, moveButt, abilityButt);
    }

    EventHandler<ActionEvent> moveButtonHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            controller.moveButtonHandler();
        }
    };

    EventHandler<ActionEvent> attackButtonHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            controller.attackButtonHandler();
        }
    };

    EventHandler<ActionEvent> abilityButtonHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            controller.abilityButtonHandler();
        }
    };

    public void disableButtons(boolean disable) {
        this.attackButt.setDisable(disable);
        this.moveButt.setDisable(disable);
        this.abilityButt.setDisable(disable);
    }

    public void refreshSelectableModes(Piece piece) {
        getChildren().clear();
        init();

        for (PieceMode mode : piece.getMode().connectsTo()) {
            Button modeButton = new Button("Switch to " + mode.getName() + " Mode");
            modeButton.setPrefHeight(40);

            EventHandler<ActionEvent> modeChangedHandler = event -> controller.updateSelectedPieceMode(mode);

            modeButton.setOnAction(modeChangedHandler);
            getChildren().add(modeButton);
        }
    }
}
