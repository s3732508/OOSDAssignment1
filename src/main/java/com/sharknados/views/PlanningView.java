package com.sharknados.views;

import com.sharknados.Utils;
import com.sharknados.controllers.TileController;
import com.sharknados.models.Board;
import com.sharknados.models.Game;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class PlanningView extends VBox {
    private final Label currentTeam;
    private final Label currentPiece;
    private final List<TileView> tileViewList;

    public PlanningView(Game game) {
        setAlignment(Pos.TOP_CENTER);

        currentTeam = new Label();
        currentPiece = new Label();

        Utils.setFontSize(currentTeam, 24);
        Utils.setFontSize(currentPiece, 12);

        Board board = game.getBoard();
        int size = board.getSize();

        TileView[][] tileViews = new TileView[2 * size + 1][2 * size + 1];
        tileViewList = new ArrayList<>();
        for (int x = 0; x <= 2 * size; x++) {
            int zStart = max(0, size - x);
            int zStop = min(2 * size, 3 * size - x);
            for (int z = zStart; z <= zStop; z++) {
                TileController tileController = new TileController();
                TileView tileView = new TileView(tileController, x, z);
                tileViews[x][z] = tileView;
                tileController.addModel(board.getTileAtPosition(x, z));
                tileController.addView(tileView);
                tileViewList.add(tileViews[x][z]);
            }
        }

        getChildren().addAll(currentTeam, currentPiece);

        BorderPane pane = new BorderPane();

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(pane);

        getChildren().add(borderPane);
        for (TileView tileView : tileViewList) {
            pane.getChildren().add(tileView.tile);
        }
    }

    public void setCurrentTeam(String team) {
        currentTeam.setText(team + " Planning Phase");
    }

    public void setCurrentPiece(String piece) {
        currentPiece.setText("Place " + piece);
    }

    public List<TileView> getTiles() {
        return tileViewList;
    }
}
