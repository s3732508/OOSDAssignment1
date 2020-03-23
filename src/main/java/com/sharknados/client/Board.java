package com.sharknados.client;


import javafx.application.Application;
import javafx.scene.Group;

import javafx.scene.Scene;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class Board extends Application {
	double size=15;
	
	public Circle AddCircle(int x, int y) {
		Circle circle = new Circle(((8+(4*x-2*y))*size), (5+(3*y))*size, 13.f);
		circle.setFill(Paint.valueOf("#7d0505"));
		
		return circle;
		
	}
	
	public Polygon AddSquare(int x, int y) {
		
		Polygon rectangle = new Polygon();
		rectangle.getPoints().addAll(new Double[]{ 
				(7+(4*x-2*y))*size, (6+(3*y))*size,
				(7+(4*x-2*y))*size, (4+(3*y))*size,
				
				(9+(4*x-2*y))*size, (4+(3*y))*size,
				(9+(4*x-2*y))*size, (6+(3*y))*size,
				
	      }); 
		rectangle.setFill(Paint.valueOf("#7d0505"));
		
		return rectangle;
	}
	
	public Polygon AddTriangle(int x, int y) {
		Polygon triangle = new Polygon();
		
		triangle.getPoints().addAll(new Double[]{ 
				(8+(4*x-2*y))*size, (6+(3*y))*size,
				(7+(4*x-2*y))*size, (4+(3*y))*size,
				
				(9+(4*x-2*y))*size, (4+(3*y))*size,
				
				
	      }); 
		triangle.setFill(Paint.valueOf("#7d0505"));
		
		return triangle;
		
	}
	
	
	Cell[][] map=new Cell[8][8];
	
	@Override
	public void start(Stage stage) {
		Group root = new Group();
		for (int row=0; row < 7; row++) {
			
			
			switch (row) {
			case 0:
				for (int x = 0; x < 4; x++) {
					
					map[x][row] = new Cell(x, row);
					
					root.getChildren().add(map[x][row].cell);
					
				}
				break;
			case 1:
				for (int x = 0; x < 5; x++) {
					
					map[x][row] = new Cell(x, row);
					root.getChildren().add(map[x][row].cell);
				}
				break;
			case 2:
				for (int x = 0; x < 6; x++) {
					
					map[x][row] = new Cell(x, row);
					root.getChildren().add(map[x][row].cell);
				}
				break;
			case 3:
				for (int x = 0; x < 7; x++) {
					
					map[x][row] = new Cell(x, row);
					root.getChildren().add(map[x][row].cell);
				}
				break;
			case 4:
				for (int x = 1; x < 7; x++) {
					
					map[x][row] = new Cell(x, row);
					root.getChildren().add(map[x][row].cell);
					
				}
				break;
			case 5:
				for (int x = 2; x < 7; x++) {
					
					map[x][row] = new Cell(x, row);
					root.getChildren().add(map[x][row].cell);
					
				}
				break;
			case 6:
				for (int x = 3; x <7 ; x++) {
					
					map[x][row] = new Cell(x, row);
					root.getChildren().add(map[x][row].cell);
					
				}
				break;
				

			}
		}
		
		
			root.getChildren().add(AddSquare(0,0));
			root.getChildren().add(AddCircle(1, 0));
			root.getChildren().add(AddTriangle(2, 0));
			
			
		
		
		
		
		
		
		
		

		// Creating a scene object
		Scene scene = new Scene(root, 600, 300);

		// Setting title to the Stage
		stage.setTitle("Drawing a Polygon");

		// Adding scene to the stage
		stage.setScene(scene);

		// Displaying the contents of the stage
		stage.show();
	}

	public static void main(String args[]) {
		launch(args);
	}
}