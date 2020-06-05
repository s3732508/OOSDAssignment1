package com.sharknados.views;

import com.sharknados.controllers.HomescreenController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class HomescreenView extends VBox {
	private final Button playSmallButton;
	private final Button playBigButton;
	private final Button loadButton;
	private final Button exitButton;
	private HomescreenController controller;
	final String IDLE_BUTTON_STYLE_SELECTED = "-fx-background-color: #A1B86E;-fx-border-color:black; -fx-border-width: 2 2 2 2;";
	final String IDLE_BUTTON_STYLE_NOT_SELECTED = "-fx-background-color: #BA4A00; -fx-border-color:black; -fx-border-width: 2 2 2 2; ";

	public HomescreenView(HomescreenController controller) {
		this.controller = controller;
		setAlignment(Pos.CENTER);
		setSpacing(20);

		Label title = new Label("Sharks vs. Eagles");
		title.setFont(Font.font(48));
		Label subtitle = new Label("by Sharknados");
		subtitle.setFont(Font.font(24));

		playSmallButton = new Button("Play Small");
		playSmallButton.setFont(Font.font(24));
		playSmallButton.setOnAction(clickPlaySmall());

		playBigButton = new Button("Play Big");
		playBigButton.setFont(Font.font(24));
		playBigButton.setOnAction(clickPlayBig());

		loadButton = new Button("Load");
		loadButton.setFont(Font.font(24));
		loadButton.setDisable(false);
		loadButton.setOnAction(clickLoad());

		exitButton = new Button("Exit");
		exitButton.setFont(Font.font(24));
		exitButton.setOnAction(clickExit());

		ToggleButton obstaclestoggleButton = new ToggleButton("No Obstacles");

		obstaclestoggleButton.setStyle(IDLE_BUTTON_STYLE_NOT_SELECTED);
		obstaclestoggleButton.setOnAction(event -> {
			if (obstaclestoggleButton.isSelected()) {
				obstaclestoggleButton.setStyle(IDLE_BUTTON_STYLE_SELECTED);
				obstaclestoggleButton.setText("Obstacles");
				controller.AllowObstaclesToggle(true);
			} else {
				obstaclestoggleButton.setStyle(IDLE_BUTTON_STYLE_NOT_SELECTED);
				obstaclestoggleButton.setText("No Obstacles");
				controller.AllowObstaclesToggle(false);
			}
		});

		getChildren().addAll(title, subtitle, obstaclestoggleButton, playSmallButton, playBigButton, loadButton,
				exitButton);
	}

	public EventHandler<ActionEvent> clickPlaySmall() {
		return event -> {
			controller.newGameButtonHandler(false);
		};
	}

	public EventHandler<ActionEvent> clickPlayBig() {
		return event -> {
			controller.newGameButtonHandler(true);
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