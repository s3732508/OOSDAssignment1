package com.sharknados.models.pieces.eagle;

import com.sharknados.models.Tile;
import com.sharknados.models.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class EagleSoldier extends EaglePiece{
    public EagleSoldier(){
        super(4, 1, 3, 2);
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
