package com.sharknados.models.tiles;

import com.sharknados.models.pieces.Piece;
import com.sharknados.views.Observer;

import java.util.Map;

public abstract class TileDecorator implements Tile, java.io.Serializable {
	protected Tile decoratedTile;

	public TileDecorator(Tile decoratedTile) {
		this.decoratedTile = decoratedTile;
		notifyAllObservers();
	}

	public Tile getTile() {
		return this.decoratedTile;
	}
	@Override
	public int getZ() {
		return decoratedTile.getZ();
	}

	@Override
	public void setOccupied(boolean b) {
		decoratedTile.setOccupied(b);
	}

	@Override
	public boolean isOccupied() {
		return decoratedTile.isOccupied();
	}

	@Override
	public Piece getPiece() {
		return decoratedTile.getPiece();
	}

	@Override
	public Tile getNeighbor(int direction) {
		return decoratedTile.getNeighbor(direction);
	}

	@Override
	public void setSelected(boolean b) {
		decoratedTile.setSelected(b);
	}

	@Override
	public void setUnavailable(boolean b) {
		decoratedTile.setUnavailable(b);
	}

	@Override
	public void setHighlighted(boolean b) {
		decoratedTile.setHighlighted(b);
	}

	@Override
	public boolean isSelected() {
		return decoratedTile.isSelected();
	}

	@Override
	public boolean isHighlighted() {
		return decoratedTile.isHighlighted();
	}

	@Override
	public int getX() {
		return decoratedTile.getX();
	}

	@Override
	public void setNeighbor(Tile neighbor, int direction) {
		decoratedTile.setNeighbor(neighbor, direction);
	}

	@Override
	public boolean isUnavailable() {
		return decoratedTile.isUnavailable();
	}

	@Override
	public void notifyAllObservers() {
		decoratedTile.notifyAllObservers();
	}

	@Override
	public void attach(Observer observer) {
		decoratedTile.attach(observer);
	}
	
	@Override
	public Tile getPassageTile() {
		return decoratedTile.getPassageTile();
	}

	@Override
	public void setPassageTile(Tile tile) {
		decoratedTile.setPassageTile(tile);
	}

	@Override
	public Map<String, Integer> getDecorators() {
		return decoratedTile.getDecorators();
	}

	@Override
	public void incrementDecoratorCount(String decoratorType) {
		decoratedTile.incrementDecoratorCount(decoratorType);
	}
	
	@Override
	public void setpieceExists(boolean passageExists) {
		decoratedTile.setpieceExists(passageExists);
	}
	
	@Override
	public boolean pieceExists() {
		return decoratedTile.pieceExists();
	}
	


}
