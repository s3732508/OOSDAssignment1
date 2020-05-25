package com.sharknados.models;

import com.sharknados.models.pieces.Piece;
import com.sharknados.views.Observer;
import com.sharknados.views.TileView;

public class TrapTileDecorator extends TileDecorator {

	public TrapTileDecorator(HexagonTile decoratedTile) {
		super(decoratedTile);
		
		// TODO Auto-generated constructor stub
	}
	public void setPiece(Piece piece) {
		System.out.println("setPiece from TrapTile");
		decoratedTile.setPiece(piece);
		
	}

	}
