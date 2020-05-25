package com.sharknados.models;

import com.sharknados.models.pieces.Piece;
import com.sharknados.views.TileView;

public class ObstacleTileDecorator extends TileDecorator {

	public ObstacleTileDecorator(Tile decoratedTile) {
		super(decoratedTile);
		// TODO Auto-generated constructor stub
	}

	public void setPiece(Piece piece) {
		System.out.println("setPiece from ObstacleTile");
		decoratedTile.setPiece(piece);
		
	}}
