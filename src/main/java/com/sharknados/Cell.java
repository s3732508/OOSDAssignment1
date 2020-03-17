package com.sharknados;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;

public class Cell {
	
	Polygon cell = new Polygon();
	int x;
	int y;
	boolean occupied = false;
	
	public  Cell(int x,int y) {
		this.x=x;
		this.y=y;
		
		double size=15;
		 
		cell.getPoints().addAll(new Double[]{ 
				(6+(4*x-2*y))*size, (6+(3*y))*size,
				(6+(4*x-2*y))*size, (4+(3*y))*size,
				(8+(4*x-2*y))*size, (3+(3*y))*size,
				(10+(4*x-2*y))*size, (4+(3*y))*size,
				(10+(4*x-2*y))*size, (6+(3*y))*size,
				(8+(4*x-2*y))*size, (7+(3*y))*size
	      }); 
		cell.setFill(Paint.valueOf("#F39C12"));
	      cell.setStroke(Color.BLACK);
	}
	
	public void setOccupied() {
		this.occupied= true;
		cell.setFill(Paint.valueOf("#F1C40F"));
		
		
		
		
	}
	
	
}
