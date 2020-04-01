package com.sharknados.views;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;

public class TileView {
	
	Polygon tile = new Polygon();

	
	public TileView(int x, int y, boolean occupied) {
		double size=12;
		 
		tile.getPoints().addAll(new Double[]{ 
				(6+(4*x-2*y))*size, (6+(3*y))*size,
				(6+(4*x-2*y))*size, (4+(3*y))*size,
				(8+(4*x-2*y))*size, (3+(3*y))*size,
				(10+(4*x-2*y))*size, (4+(3*y))*size,
				(10+(4*x-2*y))*size, (6+(3*y))*size,
				(8+(4*x-2*y))*size, (7+(3*y))*size
	      }); 
		updateTile(occupied);
		tile.setStroke(Color.BLACK);
	}
	
	public void updateTile(boolean occupied) {
		if(occupied) {
			tile.setFill(Paint.valueOf("#F1C40F"));
		}
		else{
			tile.setFill(Paint.valueOf("#DAD4D7"));
		}

	}

}