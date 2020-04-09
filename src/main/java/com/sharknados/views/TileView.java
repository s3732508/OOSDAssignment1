package com.sharknados.views;

import com.sharknados.models.Tile;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

import java.util.List;

public class TileView {
	public Polygon tile = new Polygon();
	EventHandler<MouseEvent> eventHandler;


	public TileView(int x, int z, boolean occupied) {
		double r = 16;
		double size = 2*r;
		double pixelX = 3.0/2.0*x;
		double pixelY = (Math.sqrt(3.0))/2.0*x + Math.sqrt(3.0)*z;

		// Print real pixel coords
		System.out.println("Pixel coords (" + pixelX + " " + pixelY +")");

		for (int i = 0; i < 6; i++) {
			double angle = 2.0 * Math.PI *(i) / 6.0;
			double offsetX = size*Math.cos(angle) + size;
			double offsetY = size*Math.sin(angle);
			tile.getPoints().addAll(pixelX + offsetX + pixelX*size, pixelY + offsetY + pixelY*size);
			tile.setStroke(Color.WHITESMOKE);
		}
		updateTile(occupied);

	}

	public void updateTile(boolean occupied) {
		if(occupied) {
			tile.setFill(Paint.valueOf("#F1C40F"));
		}
		else{
			tile.setFill(Paint.valueOf("#DAD4D7"));
		}

	}



	public void select(){
		tile.setFill(Paint.valueOf("#315B86"));
	}
	public void unselect(){
		tile.setFill(Paint.valueOf("#DAD4D7"));
	}

	public void pathallowed(){
		tile.setFill(Paint.valueOf("#929241"));
	}
	public void pathnotallowed(){
		tile.setFill(Paint.valueOf("#924141"));
	}


}