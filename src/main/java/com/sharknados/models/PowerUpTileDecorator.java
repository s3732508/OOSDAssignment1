package com.sharknados.models;

import com.sharknados.models.pieces.Piece;

public class PowerUpTileDecorator extends TileDecorator {

	public PowerUpTileDecorator(Tile decoratedTile) {
		super(decoratedTile);
		// TODO Auto-generated constructor stub
	}

	public void setPiece(Piece piece) {
		System.out.println("setPiece from PowerUp");
		decoratedTile.setPiece(piece);
        if (piece != null) {
        	int powerUp = 1;
            setOccupied(true);
            if (piece.getHealth() + powerUp <=5 ) {
            	int newHealth = piece.getHealth() + powerUp;
				piece.setHealth(newHealth);
			}
        }
        else{
            setOccupied(false);
        }
	}
}
