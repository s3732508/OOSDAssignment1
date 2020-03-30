package com.sharknados.client.shapes.sharks;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import com.sharknados.client.shapes.Piece;

import com.sharknados.client.application.Tile;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public  class Shark extends Piece {

	
	double size=12;

	public Shark(Tile tile, int health, int energy) throws FileNotFoundException {
		super(health, 1, energy);

		Image image = new Image(this.getClass().getResourceAsStream("/Shark.png"));
		// Setting the image view
		piece = new ImageView(image);

		// Setting the position of the image
		piece.setX((6.2 + (4 * tile.x - 2 * tile.y)) * size);
		piece.setY((3.2 + (3 * tile.y)) * size);

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
