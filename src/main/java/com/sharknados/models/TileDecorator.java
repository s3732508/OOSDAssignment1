package com.sharknados.models;

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
		// TODO Auto-generated method stub
		return decoratedTile.getZ();
	}

	@Override
	public void setOccupied(boolean b) {
		// TODO Auto-generated method stub
		decoratedTile.setOccupied(b);
	}

	@Override
	public boolean isOccupied() {
		// TODO Auto-generated method stub
		return decoratedTile.isOccupied();
	}

	@Override
	public Piece getPiece() {
		// TODO Auto-generated method stub
		return decoratedTile.getPiece();
	}

	@Override
	public Tile getNeighbor(int direction) {
		return decoratedTile.getNeighbor(direction);
	}

	@Override
	public void setSelected(boolean b) {
		// TODO Auto-generated method stub
		decoratedTile.setSelected(b);
	}

	@Override
	public void setUnavailable(boolean b) {
		// TODO Auto-generated method stub
		decoratedTile.setUnavailable(b);
	}

	@Override
	public void setHighlighted(boolean b) {
		// TODO Auto-generated method stub
		decoratedTile.setHighlighted(b);
	}

	@Override
	public boolean isSelected() {
		// TODO Auto-generated method stub
		return decoratedTile.isSelected();
	}

	@Override
	public boolean isHighlighted() {
		// TODO Auto-generated method stub
		return decoratedTile.isHighlighted();
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return decoratedTile.getX();
	}

	@Override
	public void setNeighbor(Tile neighbor, int direction) {
		// TODO Auto-generated method stub
		decoratedTile.setNeighbor(neighbor, direction);
	}

	@Override
	public boolean isUnavailable() {
		// TODO Auto-generated method stub
		return decoratedTile.isUnavailable();
	}

	@Override
	public void notifyAllObservers() {
		decoratedTile.notifyAllObservers();
	}

	@Override
	public void attach(Observer observer) {
		decoratedTile.attach(observer);
		// TODO Auto-generated method stub
	}
	
	@Override
	public Tile getPassageTile() {
		// TODO Auto-generated method stub
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

}
