package com.sharknados.views;

import java.util.ArrayList;
import java.util.List;
import com.sharknados.views.TileView;

import javafx.scene.Group;

public class BoardView extends Group {

	double size = 12;
	int sides = 4;
	//private List<TileView> tileViewList;
	//private Group root;

	public BoardView(){
		//this.tileViewList = new ArrayList<>();
		//this.root = root;
	}



	public void refreshBoard(List<TileView> tileViewList){
		this.getChildren().clear();
		for (TileView tile : tileViewList) {
			this.getChildren().add(tile.tile);
		}
	}

}