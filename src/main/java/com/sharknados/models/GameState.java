package com.sharknados.models;

import com.sharknados.models.pieces.Piece;

public class GameState {
    private Piece[] pieces; //particularly their positions, dead/alive state
    private int turnNumber;
    private int turnsElapsed;
    private Team whoseTurnNext;

    public GameState() {
    }

    public GameState(Piece[] pieces, int turnNumber, int turnsElapsed, Team whoseTurnNext) {
        this.pieces = pieces;
        this.turnNumber = turnNumber;
        this.turnsElapsed = turnsElapsed;
        this.whoseTurnNext = whoseTurnNext;
    }


}
