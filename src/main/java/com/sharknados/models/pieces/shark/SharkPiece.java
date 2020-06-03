package com.sharknados.models.pieces.shark;

import com.sharknados.models.Team;
import com.sharknados.models.pieces.Piece;
import com.sharknados.models.pieces.modes.AttackMode;

public abstract class SharkPiece extends Piece {
    protected SharkPiece(int attack, int defence, int health, int movement) {
        super(attack, defence, health, movement, 1, new AttackMode());
    }

    @Override
    public Team getTeam(){
        return Team.SHARK;
    }

    @Override
    public boolean inTheSameArmyAs(Piece piece) {return piece instanceof SharkPiece;};
}