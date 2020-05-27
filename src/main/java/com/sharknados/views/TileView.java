package com.sharknados.views;

import com.sharknados.models.AbstractSubject;
import com.sharknados.models.PassageTileDecorator;
import com.sharknados.models.PowerUpTileDecorator;
import com.sharknados.models.Subject;
import com.sharknados.models.Tile;
import com.sharknados.models.TrapTileDecorator;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;

public class TileView implements Observer {

	public Polygon tilePoly = new Polygon();
	private Tile subject;

	public TileView(Subject subject2) {
		this.subject = (Tile) subject2;
		this.subject.attach(this);

		//Initial Drawing
		int x = subject.getX();
		int z = subject.getZ();
		double radius = 22;
		double size = 2 * radius;
		double pixelX = 3.0 / 2.0 * x;
		double pixelY = (Math.sqrt(3.0)) / 2.0 * x + Math.sqrt(3.0) * z;

		for (int i = 0; i < 6; i++) {
			double angle = 2.0 * Math.PI * (i) / 6.0;
			double offsetX = size * Math.cos(angle) + size;
			double offsetY = size * Math.sin(angle);
			tilePoly.getPoints().addAll(offsetX + pixelX * size, offsetY + pixelY * size - size);
			tilePoly.setStroke(Color.WHITESMOKE);
			tilePoly.setFill(Paint.valueOf("#DAD4D7"));
			
		}
		if(subject instanceof TrapTileDecorator) {
			tilePoly.setFill(Paint.valueOf("#C685A5"));
			
			
		}
		if(subject instanceof PowerUpTileDecorator) {
			tilePoly.setFill(Paint.valueOf("#C0CBA7"));			
			
			
		}
		if(subject instanceof PassageTileDecorator) {
			if(subject.getTile() instanceof PowerUpTileDecorator)
				tilePoly.setFill(Paint.valueOf("#C0CBA7"));		
			else if(subject.getTile() instanceof TrapTileDecorator)
				tilePoly.setFill(Paint.valueOf("#C685A5"));
			tilePoly.setStroke(Color.BLACK);
				
			
		}
	}

	@Override
	public Subject getSubject(){	
			return (Subject) this.subject;
	}
	@Override
	public void update() {
		//Default
		tilePoly.setFill(Paint.valueOf("#DAD4D7"));

		//Update the colour of the tile if the tile is occupied
		if (subject.isOccupied()){
			tilePoly.setFill(Paint.valueOf("#F1C40F"));
		}

		//Update the colour of the tile if the tile is unavailable
		if (subject.isUnavailable()){
			tilePoly.setFill(Paint.valueOf("#924141"));
		}

		//Update the colour of the tile if the tile is highlighted
		if (subject.isHighlighted()){
			tilePoly.setFill(Paint.valueOf("#929241"));
		}

		//Update the colour of the tile if the tile is selected
		if (subject.isSelected()){
			tilePoly.setFill(Paint.valueOf("#315B86"));
		}
		if(subject instanceof TrapTileDecorator) {
			tilePoly.setFill(Paint.valueOf("#C685A5"));
			
			
		}
		if(subject instanceof PowerUpTileDecorator) {
			tilePoly.setFill(Paint.valueOf("#C0CBA7"));			
			
			
		}
		if(subject instanceof PassageTileDecorator) {
			if(subject.getTile() instanceof PowerUpTileDecorator)
				tilePoly.setFill(Paint.valueOf("#C0CBA7"));		
			else if(subject.getTile() instanceof TrapTileDecorator)
				tilePoly.setFill(Paint.valueOf("#C685A5"));
			tilePoly.setStroke(Color.BLACK);
				
			
		}
		
	}
}