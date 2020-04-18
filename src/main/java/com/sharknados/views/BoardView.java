package com.sharknados.views;

import java.util.List;
import javafx.scene.layout.BorderPane;

public class BoardView extends BorderPane {
	public BoardView(){
	}

	public void refreshBoard(List<TileView> tileViewList, List<PieceView> pieceViewList){
		this.getChildren().clear();
		for (TileView tile : tileViewList) {
			this.getChildren().add(tile.tile);
		}

		for (PieceView piece : pieceViewList) {
			this.getChildren().add(piece.piece);
			this.getChildren().add(piece.pieceBackground);
		}
	}

}