package com.sharknados.models.tiles;

import com.sharknados.models.AbstractSubject;
import com.sharknados.models.pieces.Piece;
import com.sharknados.views.Observer;

import java.util.HashMap;
import java.util.Map;

public class HexagonTile extends AbstractSubject implements Tile, java.io.Serializable {
	private int x, z;
	private Map<String, Integer> decorators;
	private boolean occupied = false;
	private boolean selected = false;
	private boolean highlighted = false;
	private boolean unavailable = false;
	private boolean pieceExists = false;
	private Piece piece;
	private Tile neighbor[] = { null, null, null, null, null, null };
	private int PassageIdentifier =1;

	Tile PassageTile;

	public HexagonTile(int x, int z) {
		this.decorators = new HashMap<String, Integer>();
		this.x = x;
		this.z = z;
	}

	public int getX() {
		return x;
	}

	public int getZ() {
		return z;
	}

	public void setNeighbor(Tile neighbor, int direction) {
		this.neighbor[direction] = neighbor;
	}

	public Tile getNeighbor(int direction) {
		return neighbor[direction];
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
		notifyAllObservers();
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
		if (occupied) {
			this.piece.setSelected(selected);
		}
		notifyAllObservers();
	}

	public void setHighlighted(boolean highlighted) {
		this.highlighted = highlighted;
		notifyAllObservers();
	}

	public void setUnavailable(boolean unavailable) {
		this.unavailable = unavailable;
		notifyAllObservers();
	}

	public void setpieceExists(boolean pieceExists) {
		this.pieceExists = pieceExists;
		notifyAllObservers();
	}

	public boolean isOccupied() {
		return occupied;
	}

	public boolean isSelected() {
		return selected;
	}

	public boolean isHighlighted() {
		return highlighted;
	}

	public boolean isUnavailable() {
		return unavailable;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
		if (piece != null) {
			piece.setX(this.getX());
			piece.setZ(this.getZ());
			setOccupied(true);
		} else {
			setOccupied(false);
		}
	}

	public Piece getPiece() {
		return this.piece;
	}

	@Override
	public String toString() {
		return String.format("Axial coords (" + x + ", " + z + ")");
	}

	@Override
	public void attach(Observer observer) {
		super.attach(observer);
		// TODO Auto-generated method stub
	}

	@Override
	public Tile getTile() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public void notifyAllObservers() {
		super.notifyAllObservers();
		// TODO Auto-generated method stub
	}

	@Override
	public Tile getPassageTile() {
		return PassageTile;
	}

	@Override
	public void setPassageTile(Tile tile) {
		this.PassageTile = tile;
	}

	@Override
	public Map<String, Integer> getDecorators() {
		return this.decorators;
	}

	@Override
	public void incrementDecoratorCount(String decoratorType) {
		decorators.merge(decoratorType, this.PassageIdentifier, Integer::sum);
	}

	@Override
	public boolean pieceExists() {
		// TODO Auto-generated method stub
		return this.pieceExists;
	}

	@Override
	public void setPassageIdentifier(int identifier) {
		this.PassageIdentifier=identifier;
		
	}
}