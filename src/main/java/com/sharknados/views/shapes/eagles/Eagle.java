package com.sharknados.views.shapes.eagles;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.sharknados.views.Tile;
import com.sharknados.views.shapes.Piece;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Eagle extends Piece {
	
	
	double size = 14;
	
	public Eagle(Tile tile, int attack, int energy) throws FileNotFoundException {
		super(1, attack, energy);

		Image image = new Image(new FileInputStream("src/main/resources/Eagle.png"));
//		Image image =  new Image(this.getClass().getResourceAsStream("src/main/java/resources/Eagle.png"));
		// Setting the image view
		piece = new ImageView(image);

		// Setting the position of the image
		piece.setX((3.21 + (3 * tile.x)) * size);
		piece.setY((8 + (4 * tile.y - 2 * tile.x)) * size);

		// setting the fit height and width of the image view
		piece.setFitHeight(25.5);
		piece.setFitWidth(51);

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
