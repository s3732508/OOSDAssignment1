package com.sharknados.models.pieces.shark;

import com.sharknados.models.Tile;

import java.util.ArrayList;
import java.util.List;

public class SharkSoldier extends SharkPiece {
    public SharkSoldier(){
        super(2, 2, 5, 1);
    }

    @Override
    public List<Tile> getAbilityRange(Tile myTile, List<Tile> allTiles) {
        List<Tile> tilesInRange = new ArrayList<>();
        tilesInRange.add(myTile);
        return tilesInRange;
    }

    @Override
    public boolean doAbility(Tile targetTile) {
        return false;
    }
}