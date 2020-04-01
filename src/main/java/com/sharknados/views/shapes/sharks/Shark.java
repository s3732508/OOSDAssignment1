package com.sharknados.views.shapes.sharks;

import java.io.FileNotFoundException;
import java.util.List;

import com.sharknados.views.shapes.Piece;

import com.sharknados.views.Tile;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public  class Shark extends Piece {

	
	double size=14;

	public Shark(Tile tile, int health, int energy) throws FileNotFoundException {
		super(health, 1, energy);

		Image image = new Image(
				new FileInputStream("C:\\Users\\Vikas\\Documents\\New folder\\Assignmemt1\\src\\shapes\\Shark.PNG"));
		// Setting the image view
		piece = new ImageView(image);

		// Setting the position of the image
		piece.setX((3.4 + (3 * tile.x)) * size);
		piece.setY((7.4 + (4 * tile.y - 2 * tile.x)) * size);

		// setting the fit height and width of the image view
		piece.setFitHeight(40);
		piece.setFitWidth(40);
	}

	@Override
	public String getAbilityName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAbilityCost() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Tile> getValidCellsForAbility(Tile currentTile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void doAbility(Tile currentTile, Tile selectedTile) {
		// TODO Auto-generated method stub
		
	}
}
