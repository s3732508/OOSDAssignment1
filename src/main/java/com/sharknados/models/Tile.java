package com.sharknados.models;

import com.sharknados.models.pieces.Piece;
import com.sharknados.views.TileView;

public interface Tile {

	int getZ();

	void setOccupied(boolean b);

	void setPiece(Piece piece);

	void setNeighbor(Tile neighbor, int direction);



	boolean isOccupied();

	Piece getPiece();

	void setSelected(boolean b);

	void setUnavailable(boolean b);

	void setHighlighted(boolean b);

	boolean isSelected();

	boolean isHighlighted();

	int getX();







}
