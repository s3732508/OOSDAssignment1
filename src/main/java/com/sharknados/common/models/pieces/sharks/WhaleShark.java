package com.sharknados.common.models.pieces.sharks;

import com.sharknados.common.models.Tile;

import java.util.List;

public class WhaleShark extends Shark {
    public WhaleShark() {
        super(3, 2);
    }

    @Override
    public String getAbilityName() {
        return "Birth New Shark";
    }

    @Override
    public int getAbilityCost() {
        return 2;
    }

    @Override
    public List<Tile> getValidCellsForAbility(Tile currentTile) {
        return List.of(currentTile);
    }

    @Override
    public void doAbility(Tile currentTile, Tile selectedTile) {
        // TODO
    }
}
