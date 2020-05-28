package com.sharknados.models;

import com.sharknados.models.pieces.Piece;
import com.sharknados.views.Observer;
import com.sharknados.views.TileView;

public class PassageTileDecorator extends TileDecorator {
	

	
	public PassageTileDecorator(Tile decoratedTile) {
		super(decoratedTile);
		System.out.println(" Constructor from PassageTileDecorator");
		
		// TODO Auto-generated constructor stub
	}
	@Override
	public void setPiece(Piece piece) {
		if(piece != null) {
			this.setUnavailable(true);
			this.getPassageTile().getTile().setPiece(piece);
			
		}
		else {
			 setOccupied(false);
			 this.getPassageTile().getTile().setOccupied(false);
			 
		}
		System.out.println("setPiece from PassageTile");
		
		notifyAllObservers();
	}

	
	

	

	
	
}
