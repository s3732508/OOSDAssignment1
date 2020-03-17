package com.sharknados;


import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Board extends Application {
	Cell[][] map=new Cell[5][5];
	
	@Override
	public void start(Stage stage) {
		Group root = new Group();
		for (int row=0; row < 5; row++) {
			
			
			switch (row) {
			case 0:
				for (int x = 0; x < 3; x++) {
					
					map[x][row] = new Cell(x, row);
					
					root.getChildren().add(map[x][row].cell);
					
				}
				break;
			case 1:
				for (int x = 0; x < 4; x++) {
					
					map[x][row] = new Cell(x, row);
					root.getChildren().add(map[x][row].cell);
				}
				break;
			case 2:
				for (int x = 0; x < 5; x++) {
					
					map[x][row] = new Cell(x, row);
					root.getChildren().add(map[x][row].cell);
				}
				break;
			case 3:
				for (int x = 1; x < 5; x++) {
					
					map[x][row] = new Cell(x, row);
					root.getChildren().add(map[x][row].cell);
				}
				break;
			case 4:
				for (int x = 2; x < 5; x++) {
					
					map[x][row] = new Cell(x, row);
					root.getChildren().add(map[x][row].cell);
					
				}
				break;

			}
		}
		
		
		
		for(int k=0;k<5;k++) {
			
			map[k][k].setOccupied();
			
		}
		
		

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