package com.sharknados.models.tiles;

import com.sharknados.models.pieces.Piece;

public class AttackDownTileDecorator extends TileDecorator {

	public AttackDownTileDecorator(Tile decoratedTile) {
		super(decoratedTile);
		decoratedTile.incrementDecoratorCount("REMORA");
	}

	public void setPiece(Piece piece) {
		decoratedTile.setPiece(piece);
        if (piece != null) {
        	int powerDown = 10;
			decoratedTile.setOccupied(true);
			int newAttack = piece.getAttack() - powerDown;
			if(newAttack < 0){
				newAttack = 0;
			}
			piece.setAttack(newAttack);
        }
        else{
			decoratedTile.setOccupied(false);
        }
	}
}
