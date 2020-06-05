package com.sharknados.models.tiles;

import com.sharknados.models.pieces.Piece;

public class StormTileDecorator extends TileDecorator {

	public StormTileDecorator(Tile decoratedTile) {
		super(decoratedTile);
		decoratedTile.incrementDecoratorCount("STORM");
	}

	public void setPiece(Piece piece) {
		decoratedTile.setPiece(piece);
		if (piece != null) {
			decoratedTile.setOccupied(true);
			piece.setMovement(0);
		}
		else {
			decoratedTile.setOccupied(false);
		}
	}
}
