package com.sharknados.models;

import com.sharknados.models.pieces.Piece;

public abstract class TileDecorator implements Tile, java.io.Serializable {
	protected HexagonTile decoratedTile;

	public TileDecorator(HexagonTile decoratedTile) {
		this.decoratedTile = decoratedTile;
	
	}

	public void setPiece(Piece piece) {
		decoratedTile.setPiece(piece);
	}
}
