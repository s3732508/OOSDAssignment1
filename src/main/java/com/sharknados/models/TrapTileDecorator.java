package com.sharknados.models;

import com.sharknados.models.pieces.Piece;
import com.sharknados.views.Observer;
import com.sharknados.views.TileView;

public class TrapTileDecorator extends TileDecorator {

	public TrapTileDecorator(HexagonTile decoratedTile) {
		super(decoratedTile);
		
		// TODO Auto-generated constructor stub
	}

	public void setPiece(Piece piece) {
		System.out.println("setPiece from TrapTile");
		decoratedTile.setPiece(piece);
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
	public void setNeighbor(Tile neighbor, int direction) {
		// TODO Auto-generated method stub
		decoratedTile.setNeighbor(neighbor, direction);
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
	public void attach(TileView tileView) {
		// TODO Auto-generated method stub
		decoratedTile.attach(tileView);
	}

	@Override
	public boolean isUnavailable() {
		// TODO Auto-generated method stub
		return decoratedTile.isUnavailable();
	}

	

}
