package com.sharknados.models;

import com.sharknados.models.pieces.Piece;

public abstract class TileDecorator implements Tile, java.io.Serializable {
	protected Tile decoratedTile;

	public TileDecorator(Tile decoratedTile2) {
		this.decoratedTile = decoratedTile2;
	
	}

	public void setPiece(Piece piece) {
		decoratedTile.setPiece(piece);
	}
}
