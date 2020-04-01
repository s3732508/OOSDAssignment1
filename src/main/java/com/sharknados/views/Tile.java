package com.sharknados.views;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;

public class Tile {
	
	Polygon tile = new Polygon();
	public int x;
	public int y;
	boolean occupied = false;
	
	/**
	 * neigh[i] is the neighbor tile of direction i
	 	 * hexagonal tiles have 6 neighbors 	 
	*/
	public Tile neigh[] = {null, null, null, null, null, null};
	
	public  Tile(int x,int y) {
		this.x=x;
		this.y=y;
		
		double size=14;
		 
		tile.getPoints().addAll(new Double[]{ 
				 (6+(3*x))*size, (7+(4*y-2*x))*size,
				 (4+(3*x))*size, (7+(4*y-2*x))*size,
				 (3+(3*x))*size, (9+(4*y-2*x))*size,
				 (4+(3*x))*size, (11+(4*y-2*x))*size,
				 (6+(3*x))*size, (11+(4*y-2*x))*size,
				 (7+(3*x))*size, (9+(4*y-2*x))*size
	      }); 
		if(y%2==0)
		tile.setFill(Paint.valueOf("#AEB3B8"));
		else
			tile.setFill(Paint.valueOf("#BDBFB8"));
	      tile.setStroke(Color.BLACK);
	}
	
	public void setOccupied() {
		this.occupied= true;
		tile.setFill(Paint.valueOf("#F1C40F"));
		
		
		
		
	}
	
	
}