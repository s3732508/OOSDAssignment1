package com.sharknados.controllers;

import com.sharknados.models.TeamPiece;
import com.sharknados.views.PlanningView;
import com.sharknados.views.TileView;

public class PlanningController {
    private TeamPiece currentPiece;
    private final PlanningView view;

    public PlanningController(PlanningView view) {
        this.view = view;
        for (TileView tile : view.getTiles()) {
            tile.tile.
        }

        TeamPiece eagle2 = new TeamPiece("Eagle", "Eagle 2");
        TeamPiece eagle1 = new TeamPiece("Eagle", "Eagle 1", eagle2);
        TeamPiece shark2 = new TeamPiece("Shark", "Shark 2", eagle1);
        TeamPiece shark1 = new TeamPiece("Shark", "Shark 1", shark2);

        currentPiece = shark1;
        updateLabels();
    }

    public void updateLabels() {
        view.setCurrentTeam(currentPiece.getTeam());
        view.setCurrentPiece(currentPiece.getPiece());
    }
}
