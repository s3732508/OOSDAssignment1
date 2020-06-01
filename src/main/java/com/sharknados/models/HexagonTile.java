package com.sharknados.models;

import com.sharknados.models.pieces.Piece;
import com.sharknados.views.Observer;

public class HexagonTile extends AbstractSubject implements Tile, java.io.Serializable{
    private int x, z;
    private boolean occupied = false;
    private boolean selected = false;
    private boolean highlighted = false;
    private boolean unavailable = false;
    private Piece piece;
    private Tile neighbor[] = {null, null, null, null, null, null};
   
	Tile PassageTile;

    public HexagonTile(int x, int z){
        this.x = x;
        this.z = z;
    }

    public int getX(){
        return x;
    }

    public int getZ(){
        return z;
    }

    public void setNeighbor(Tile neighbor, int direction){
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

    public boolean isOccupied() {
        return occupied;
    }

    public boolean isSelected() {
        return selected;
    }

    public boolean isHighlighted() {
        return highlighted;
    }

    public boolean isUnavailable(){
        return unavailable;
    }

    public void setPiece(Piece piece){
        this.piece = piece;
        if (piece != null) {
        	piece.setX(this.getX());
            piece.setZ(this.getZ());
            setOccupied(true);
        }
        else{
            setOccupied(false);
        }
    }

    public Piece getPiece(){
        return this.piece;
    }


    @Override
    public String toString(){
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
		this.PassageTile=tile;
	}




 




		
	
}