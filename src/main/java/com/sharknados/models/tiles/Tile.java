package com.sharknados.models.tiles;

import com.sharknados.models.Subject;
import com.sharknados.models.pieces.Piece;
import com.sharknados.views.Observer;

import java.util.Map;


public interface Tile extends Subject {

	int getZ();

	void setOccupied(boolean b);

	void setPiece(Piece piece);

	void setNeighbor(Tile neighbor, int direction);

	boolean isOccupied();

	Piece getPiece();

	Tile getNeighbor(int direction);

	void setSelected(boolean b);

	void setUnavailable(boolean b);

	void setHighlighted(boolean b);

	boolean isSelected();

	boolean isHighlighted();

	int getX();

	boolean isUnavailable();

	void notifyAllObservers();

	void attach(Observer observer);

	Tile getTile();

	Tile getPassageTile();

	void setPassageTile(Tile decorateTile2);

	Map<String, Integer> getDecorators();

	void incrementDecoratorCount(String decoratorType);
	
	void setpieceExists(boolean passageExists);
	
	boolean pieceExists();

}
