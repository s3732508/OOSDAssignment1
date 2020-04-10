package com.sharknados.models.pieces.sharks;

import com.sharknados.models.Tile;
import com.sharknados.models.pieces.Piece;

import java.util.List;

public class WhaleShark extends Shark {

    public WhaleShark(int attack, int defence, Tile tile) {
        super(attack, defence, tile);
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
    @Override
    public boolean inTheSameArmyAs(Piece piece) {return piece instanceof Shark;};
}
