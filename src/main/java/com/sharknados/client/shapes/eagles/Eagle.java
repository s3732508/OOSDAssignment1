package com.sharknados.client.shapes.eagles;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import com.sharknados.client.application.Tile;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Eagle extends Piece {
	
	
	double size = 12;
	
	public Eagle(Tile tile, int attack, int energy) throws FileNotFoundException {
		super(1, attack, energy);
		
		Image image = new Image(
				new FileInputStream("C:\\Users\\Vikas\\Documents\\New folder\\Assignmemt1\\src\\shapes\\Eagle.PNG"));
		// Setting the image view
		piece = new ImageView(image);

		// Setting the position of the image
		piece.setX((5.91 + (4 * tile.x - 2 * tile.y)) * size);
		piece.setY((4.0 + (3 * tile.y)) * size);

		// setting the fit height and width of the image view
		piece.setFitHeight(25);
		piece.setFitWidth(50);

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
	
//    public Eagle(int attack, int energy) {
//        super(1, attack, energy);
//    }
}
