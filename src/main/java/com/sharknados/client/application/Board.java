package com.sharknados.client.application;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.sharknados.client.shapes.Eagle;
import com.sharknados.client.shapes.Shark;

import java.io.FileNotFoundException;

public class Board extends Application {
	// directions used for indices

	public final static int EAST = 0;
	public final static int NORTHEAST = 1;
	public final static int NORTHWEST = 2;
	public final static int NORTH = 2;
	public final static int WEST = 3;
	public final static int SOUTHWEST = 4;
	public final static int SOUTHEAST = 5;
	public final static int SOUTH = 5;

	double size = 12;

	Tile[][] map = new Tile[8][8];

	@Override
	public void start(Stage stage) throws FileNotFoundException {
		
		
		Group root = new Group();
		for (int row = 0; row < 7; row++) {

			switch (row) {
			case 0:
				for (int x = 0; x < 4; x++) {

					map[x][row] = new Tile(x, row);

					root.getChildren().add(map[x][row].tile);

				}
				break;
			case 1:
				for (int x = 0; x < 5; x++) {

					map[x][row] = new Tile(x, row);
					root.getChildren().add(map[x][row].tile);
				}
				break;
			case 2:
				for (int x = 0; x < 6; x++) {

					map[x][row] = new Tile(x, row);
					root.getChildren().add(map[x][row].tile);
				}
				break;
			case 3:
				for (int x = 0; x < 7; x++) {

					map[x][row] = new Tile(x, row);
					root.getChildren().add(map[x][row].tile);
				}
				break;
			case 4:
				for (int x = 1; x < 7; x++) {

					map[x][row] = new Tile(x, row);
					root.getChildren().add(map[x][row].tile);

				}
				break;
			case 5:
				for (int x = 2; x < 7; x++) {

					map[x][row] = new Tile(x, row);
					root.getChildren().add(map[x][row].tile);

				}
				break;
			case 6:
				for (int x = 3; x < 7; x++) {

					map[x][row] = new Tile(x, row);
					root.getChildren().add(map[x][row].tile);

				}
				break;

			}
		}

		// Adding Shark
		Shark shark = new Shark(3, 3, size);

		root.getChildren().add(shark.shark);

		// Adding Eagle
		Eagle eagle = new Eagle(1, 0, size);

		root.getChildren().add(eagle.eagle);
		
		

		// Creating a scene object
		Scene scene = new Scene(root, 600, 600);

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