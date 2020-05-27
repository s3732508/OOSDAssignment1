package com.sharknados.models;

import com.sharknados.models.pieces.Piece;
import com.sharknados.views.Observer;
import com.sharknados.views.TileView;

public class PassageTileDecorator extends TileDecorator {

	public PassageTileDecorator(Tile decoratedTile) {
		super(decoratedTile);
		System.out.println(" Constructor from PassageTileDecorator");
		decoratedTile.setPassageTile(true);
		// TODO Auto-generated constructor stub
	}

	public void setPiece(Piece piece) {
		System.out.println("setPiece from PassageTile");
		decoratedTile.setPiece(piece);
	}

	
	
	
}
