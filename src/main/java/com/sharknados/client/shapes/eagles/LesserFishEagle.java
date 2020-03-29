package com.sharknados.common.models.pieces.eagles;

import com.sharknados.common.models.Tile;

import java.util.List;

public class LesserFishEagle extends Eagle {
    public LesserFishEagle() {
        super(1, 14);
    }

    @Override
    public String getAbilityName() {
        return "Reveal";
    }

    @Override
    public int getAbilityCost() {
        return 1;
    }

    @Override
    public List<Tile> getValidCellsForAbility(Tile currentTile) {
        return List.of(currentTile);
    }

    @Override
    public void doAbility(Tile currentTile, Tile selectedTile) {
        // TODO:
    }
}
