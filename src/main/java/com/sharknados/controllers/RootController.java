package com.sharknados.controllers;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class RootController {
    private StackPane root;

    public RootController(Pane root){
        this.root = (StackPane) root;
    }

    public StackPane getRoot() {
        return root;
    }
} //close class
