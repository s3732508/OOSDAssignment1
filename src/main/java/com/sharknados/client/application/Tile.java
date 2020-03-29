package application;

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
		
		double size=12;
		 
		tile.getPoints().addAll(new Double[]{ 
				(6+(4*x-2*y))*size, (6+(3*y))*size,
				(6+(4*x-2*y))*size, (4+(3*y))*size,
				(8+(4*x-2*y))*size, (3+(3*y))*size,
				(10+(4*x-2*y))*size, (4+(3*y))*size,
				(10+(4*x-2*y))*size, (6+(3*y))*size,
				(8+(4*x-2*y))*size, (7+(3*y))*size
	      }); 
		tile.setFill(Paint.valueOf("#DAD4D7"));
	      tile.setStroke(Color.BLACK);
	}
	
	public void setOccupied() {
		this.occupied= true;
		tile.setFill(Paint.valueOf("#F1C40F"));
		
		
		
		
	}
	
	
}