package com.sharknados.views;

import java.io.FileNotFoundException;

import com.sharknados.views.shapes.eagles.Eagle;
import com.sharknados.views.shapes.sharks.Shark;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.sharknados.views.shapes.Piece;

public class Board extends Application {

	// directionS used for indices

	public final static int EAST = 0;
	public final static int NORTHEAST = 1;
	public final static int NORTHWEST = 2;
	public final static int WEST = 3;
	public final static int SOUTHWEST = 4;
	public final static int SOUTHEAST = 5;

	double size = 12;
	int sides = 4;

	Tile[][] map = new Tile[2 * sides][2 * sides];

	
	public final static int deltaR[] = { 1, 0, -1, -1, 0, 1 };
	public final static int deltaC[] = { 0, -1, -1, 0, 1, 1 };
	
	//Checks if the cell is in the maze

	protected boolean isIn(int r, int c) {
		return r >= 0 && r < 2 * sides && c >= 0 && c < 2 * sides;
	} // end of isIn()

	protected boolean isIn(Tile tile) {
		if (tile == null)
			return false;
		return isIn(tile.x, tile.y);
	} // end of isIn()

	@Override
	public void start(Stage stage) throws FileNotFoundException {

		Group root = new Group();
		int rowend = 4;
		int rowinitial = 0;
		for (int y = 0; y < 7; y++) {
			for (int x = rowinitial; x < rowend; x++) {
				map[x][y] = new Tile(x, y);
				root.getChildren().add(map[x][y].tile);

			}
			if (rowend == 7) {
				if (rowinitial == 3)
					break;
				rowinitial++;
			} else
				rowend++;
		}

		rowend = 4;
		rowinitial = 0;
		for (int y = 0; y < 7; y++) {
			Tile tile;
			Tile neighbor;
			for (int x = rowinitial; x < rowend; x++) {
				tile = map[x][y];

				for (int direction = 0; direction < 6; direction++) {
					if (x + deltaR[direction] >= 0 && y + deltaC[direction] >= 0) {
						neighbor = map[x + deltaR[direction]][y + deltaC[direction]];
						if (isIn(neighbor)) {
							tile.neigh[direction] = neighbor;

						}
					}

				}

			}
			if (rowend == 7) {
				if (rowinitial == 3)
					break;
				rowinitial++;
			} else
				rowend++;
		}

		// Adding Shark

		Piece shark = new Shark(map[3][3].neigh[WEST], 0, 0);

		root.getChildren().add(shark.piece);

		// Adding Eagle
		Eagle eagle = new Eagle(map[3][3].neigh[EAST], 0, 0);

		root.getChildren().add(eagle.piece);
//
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