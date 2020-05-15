package com.sharknados.models.pieces.shark;

import com.sharknados.models.Team;
import com.sharknados.models.pieces.Piece;

public abstract class SharkPiece extends Piece {

    @Override
    public Team getTeam(){
        return Team.SHARK;
    }

    @Override
    public boolean inTheSameArmyAs(Piece piece) {return piece instanceof SharkPiece;};
}