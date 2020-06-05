package com.sharknados.models.tiles;

import com.sharknados.models.pieces.Piece;
import javafx.application.Platform;

public class TrapTileDecorator extends TileDecorator {

	public TrapTileDecorator(Tile decoratedTile) {
		super(decoratedTile);
		decoratedTile.incrementDecoratorCount("PIRANHAS");
	}

	public void setPiece(Piece piece) {
		decoratedTile.setPiece(piece);
		if (piece != null) {
			decoratedTile.setOccupied(true);

			int damage = piece.getDefence()+10;

			//Deal damage and check if a commander is killed
			boolean isGameOver = piece.takeDamage(damage, decoratedTile);
			if (isGameOver){
				Platform.exit();
				System.exit(0);
			}
		}
		else {
			decoratedTile.setOccupied(false);
		}
	}
}
