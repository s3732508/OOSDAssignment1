package com.sharknados.views;

import com.sharknados.controllers.AbstractController;
import com.sharknados.controllers.GameController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;


public class CommandBar extends HBox{
    private Button attackButt;
    private Button moveButt;
    private Button abilityButt;
    private GameController controller = null;

    public CommandBar(AbstractController controller){
        this.controller = (GameController) controller;
        attackButt = new Button("Attack");
        moveButt = new Button("Move");
        abilityButt = new Button("Ability");
        attackButt.setPrefSize(140,40);
        moveButt.setPrefSize(140,40);
        abilityButt.setPrefSize(140,40);
        this.setSpacing(5);
        this.setPadding(new Insets(0, 20, 10, 20));
        this.getChildren().addAll(attackButt, moveButt, abilityButt);
    }

}
