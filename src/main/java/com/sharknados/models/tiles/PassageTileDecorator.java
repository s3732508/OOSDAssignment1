package com.sharknados.models.tiles;

import com.sharknados.models.pieces.Piece;


public class PassageTileDecorator extends TileDecorator {

	public PassageTileDecorator(Tile decoratedTile) {
		super(decoratedTile);
		decoratedTile.incrementDecoratorCount("PORTAL");
	}

	@Override
	public void setPiece(Piece piece) {

		if(piece != null) {
			
			this.setUnavailable(true);
			this.setpieceExists(true);
			this.getPassageTile().getTile().setPiece(piece);
		}
		else {
			this.setpieceExists(false);
			setOccupied(false);
			
			this.getPassageTile().getTile().setpieceExists(false);
			this.getPassageTile().getTile().setUnavailable(false);

		}
		notifyAllObservers();
	}
}
