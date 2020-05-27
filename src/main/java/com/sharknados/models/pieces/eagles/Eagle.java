package com.sharknados.models.pieces.eagles;

import com.sharknados.models.Team;
import com.sharknados.models.Tile;
import com.sharknados.models.pieces.Piece;

public abstract class Eagle extends Piece {
    private Type type;

    public Eagle(int x, int z, int attack, int defence, int health, int movement) {
        super(x, z,  attack,  defence,  health,  movement);
        setTeam(Team.EAGLE);
    }
}
