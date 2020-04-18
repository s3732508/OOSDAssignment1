package com.sharknados.views;


import com.sharknados.controllers.AbstractController;
import com.sharknados.controllers.TileController;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

import java.beans.PropertyChangeEvent;

public class TileView implements ViewImpl {
	private TileController controller = null;
	public Polygon tile = new Polygon();
	EventHandler<MouseEvent> eventHandler;


	public TileView(AbstractController controller, int x, int z) {
		super();
		this.controller = (TileController) controller;
		double r = 22;
		double size = 2 * r;
		double pixelX = 3.0 / 2.0 * x;
		double pixelY = (Math.sqrt(3.0)) / 2.0 * x + Math.sqrt(3.0) * z;

		// Print real pixel coords
		System.out.println("Pixel coords (" + pixelX + " " + pixelY + ")");

		for (int i = 0; i < 6; i++) {
			double angle = 2.0 * Math.PI * (i) / 6.0;
			double offsetX = size * Math.cos(angle) + size;
			double offsetY = size * Math.sin(angle);
			tile.getPoints().addAll(offsetX + pixelX * size, offsetY + pixelY * size);
			tile.setStroke(Color.WHITESMOKE);
			tile.setFill(Paint.valueOf("#DAD4D7"));
		}
	}


	public void select() {
		tile.setFill(Paint.valueOf("#315B86"));
	}

	public void unselect() {
		tile.setFill(Paint.valueOf("#DAD4D7"));
	}

	public void pathallowed() {
		tile.setFill(Paint.valueOf("#929241"));
	}

	public void pathnotallowed() {
		tile.setFill(Paint.valueOf("#924141"));
	}

	public void modelPropertyChange(PropertyChangeEvent evt) {
		if (evt.getNewValue() == null) return;
		tile.setFill(Paint.valueOf("#DAD4D7"));
		//Update the colour of the tile if the tile is occupied
		if (evt.getPropertyName().equals(TileController.OCCUPIED_PROPERTY)) {
			if ((boolean) evt.getNewValue()) {
				tile.setFill(Paint.valueOf("#F1C40F"));
			}
		}

		//Update the colour of the tile if the tile is selected
		if (evt.getPropertyName().equals(TileController.SELECTED_PROPERTY)) {
			if ((boolean) evt.getNewValue()) {
				tile.setFill(Paint.valueOf("#315B86"));
			}
		}

		//Update the colour of the tile if the tile is highlighted
		if (evt.getPropertyName().equals(TileController.HIGHLIGHTED_PROPERTY)) {
			if((boolean)evt.getNewValue()){
				tile.setFill(Paint.valueOf("#929241"));
			}
		}

		//Update the colour of the tile if the tile is unavailable
		if (evt.getPropertyName().equals(TileController.UNAVAILABLE_PROPERTY)) {
			if((boolean)evt.getNewValue()){
				tile.setFill(Paint.valueOf("#924141"));
			}
		}
	}
}