package com.sharknados.models.tiles;

import com.sharknados.models.pieces.Piece;
import com.sharknados.models.tiles.Tile;
import com.sharknados.models.tiles.TileDecorator;
import javafx.application.Platform;

public class TrapTileDecorator extends TileDecorator {

	public TrapTileDecorator(Tile decoratedTile) {
		super(decoratedTile);
		decoratedTile.incrementDecoratorCount("(TRAP)");
		System.out.println(" Constructor from TrapTileDecorator");
		// TODO Auto-generated constructor stub
	}

	public void setPiece(Piece piece) {
		System.out.println("setPiece from Trap");
		decoratedTile.setPiece(piece);
		if (piece != null) {
			decoratedTile.setOccupied(true);

			int damage = 10;
			//Deal damage and check if a commander is killed
			boolean isGameOver = piece.takeDamage(damage, decoratedTile);
			if (isGameOver){
				//gameOver();
				Platform.exit();
				System.exit(0);
			}
		}
		else {
			decoratedTile.setOccupied(false);
		}
	}
}
