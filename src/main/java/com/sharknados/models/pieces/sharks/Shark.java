package com.sharknados.models.pieces.sharks;

import com.sharknados.models.Team;
import com.sharknados.models.Tile;
import com.sharknados.models.pieces.Piece;

public abstract class Shark extends Piece {
    public Shark(int x, int z, int attack, int defence, int health, int movement) {
        super(x, z,  attack,  defence,  health,  movement);
        setTeam(Team.SHARK);
    }
}
