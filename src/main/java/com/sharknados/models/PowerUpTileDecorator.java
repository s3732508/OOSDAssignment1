package com.sharknados.models;

import com.sharknados.models.pieces.Piece;
import com.sharknados.views.TileView;

public class PowerUpTileDecorator extends TileDecorator {

	public PowerUpTileDecorator(Tile decoratedTile) {
		
		super(decoratedTile);
		System.out.println(" Constructor from PowewrUpTileDecorator");
		// TODO Auto-generated constructor stub
	}

	public void setPiece(Piece piece) {
		System.out.println("setPiece from PowewrUpTileDecorator");
		decoratedTile.setPiece(piece);
		
	}}
