package com.sharknados.models;

import com.sharknados.models.pieces.Piece;
import com.sharknados.views.Observer;
import com.sharknados.views.TileView;

public class TrapTileDecorator extends TileDecorator {

	public TrapTileDecorator(Tile decoratedTile) {
		super(decoratedTile);
		System.out.println(" Constructor from TrapTileDecorator");

		// TODO Auto-generated constructor stub
	}

	public void setPiece(Piece piece) {
		decoratedTile.setPiece(piece);
		if (piece != null) {
			int damage = 1;
			if (piece.getHealth() - damage <= 0) {
				// todo implement proper solution for destroying pieces
				// hack
				decoratedTile.setPiece(null);
				setOccupied(false);
				piece.setX(-1);
				piece.setZ(-1);
			} else {
				int newHealth = piece.getHealth() - damage;
				piece.setHealth(newHealth);
				setOccupied(true);

			}
			
		} else {
			setOccupied(false);
		}

	}

}
