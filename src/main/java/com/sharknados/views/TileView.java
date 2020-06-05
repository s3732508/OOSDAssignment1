package com.sharknados.views;

import com.sharknados.models.*;

import com.sharknados.models.tiles.Tile;
import com.sharknados.models.tiles.TileDecorator;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TileView implements Observer {

	public Polygon tilePoly = new Polygon();
	public List<Text> decoratorList = new ArrayList<>();
	private Tile subject;

	public TileView(Subject tile) {
		this.subject = (Tile) tile;
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
			tilePoly.setStrokeWidth(0.8);
			tilePoly.setStrokeType(StrokeType.INSIDE);
			tilePoly.setFill(Paint.valueOf("#010752"));
		}

		// Get decorators
		Font font = new Font("ARIAL", 12);
		int yDis = 0;
		Map<String, Integer> decorators = subject.getDecorators();
		for (Map.Entry<String,Integer> entry : decorators.entrySet()){
			String decorator = entry.getKey() + " x" + entry.getValue();
			Text text = new Text(decorator);
			text.setFont(font);
			text.setFill(Color.WHITE);
			text.setStyle("-fx-font-weight: bold;");
			text.setDisable(true);
			text.setX(pixelX*2*radius + radius);
			text.setY(pixelY*2*radius - 3*radius + yDis);
			decoratorList.add(text);
			yDis += 10;
			System.out.println(decorator);
		}

		if(subject instanceof TileDecorator) {
			tilePoly.setFill(Paint.valueOf("#2C127F"));
		}

	}

	@Override
	public Subject getSubject(){	
			return this.subject;
	}

	@Override
	public void update() {
		//Default
		tilePoly.setFill(Paint.valueOf("#010752"));

		if(subject instanceof TileDecorator) {
			tilePoly.setFill(Paint.valueOf("#2C127F"));
		}

		//Update the colour of the tile if the tile is occupied
		if (subject.isOccupied()){
			tilePoly.setFill(Paint.valueOf("#096262"));
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
			//tilePoly.setFill(Paint.valueOf("#315B86"));
			tilePoly.setStroke(Paint.valueOf("#F1C40F"));
			tilePoly.setStrokeWidth(5);
			tilePoly.setStrokeType(StrokeType.INSIDE);
		}
		else{
			tilePoly.setStroke(Color.WHITESMOKE);
			tilePoly.setStrokeWidth(1);
		}

	}
}