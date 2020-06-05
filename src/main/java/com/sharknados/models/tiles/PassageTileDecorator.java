package com.sharknados.models.tiles;

import com.sharknados.models.pieces.Piece;


public class PassageTileDecorator extends TileDecorator {

	public PassageTileDecorator(Tile decoratedTile) {
		super(decoratedTile);
		decoratedTile.incrementDecoratorCount("PORTAL");
		System.out.println(" Constructor from PassageTileDecorator");
		// TODO Auto-generated constructor stub
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
		System.out.println("setPiece from PassageTile");
		
		notifyAllObservers();
	}

	

	

}
