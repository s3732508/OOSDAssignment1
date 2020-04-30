package com.sharknados.views;

import com.sharknados.controllers.GameController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;


public class CommandBar extends HBox{
    private Button attackButt;
    private Button moveButt;
    private Button abilityButt;
    private GameController controller = null;

    public CommandBar(GameController controller){
        this.controller = controller;
        attackButt = new Button("Attack");
        attackButt.setOnAction(attackButtonHandler);
        moveButt = new Button("Move");
        moveButt.setOnAction(moveButtonHandler);
        abilityButt = new Button("Ability");
        attackButt.setPrefSize(140,40);
        moveButt.setPrefSize(140,40);
        abilityButt.setPrefSize(140,40);
        this.setSpacing(5);
        this.setPadding(new Insets(0, 20, 10, 20));
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

    public void disableButtons(boolean disable) {
        this.attackButt.setDisable(disable);
        this.moveButt.setDisable(disable);
        this.abilityButt.setDisable(disable);
    }

}
