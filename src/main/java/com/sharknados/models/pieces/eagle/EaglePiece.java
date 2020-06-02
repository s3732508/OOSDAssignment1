package com.sharknados.models.pieces.eagle;

import com.sharknados.models.Team;
import com.sharknados.models.pieces.Piece;

public abstract class EaglePiece extends Piece {
    protected EaglePiece(int attack, int defence, int health, int movement) {
        super(attack, defence, health, movement, 1, new MoveMode());
    }

    @Override
    public Team getTeam(){
        return Team.EAGLE;
    }

    @Override
    public boolean inTheSameArmyAs(Piece piece) {return piece instanceof EaglePiece;};
}
