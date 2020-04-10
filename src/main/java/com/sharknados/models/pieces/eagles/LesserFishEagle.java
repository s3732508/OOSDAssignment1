package com.sharknados.models.pieces.eagles;

import com.sharknados.models.Tile;
import com.sharknados.models.pieces.Piece;
import com.sharknados.models.pieces.sharks.Shark;

import java.util.List;

public class LesserFishEagle extends Eagle {

    public LesserFishEagle(int attack, int defence, Tile tile) {
        super(attack, defence, tile);
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
    @Override
    public boolean inTheSameArmyAs(Piece piece) {return piece instanceof Shark;};
}
