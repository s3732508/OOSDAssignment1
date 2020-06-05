package com.sharknados.models.pieces.shark;

import com.sharknados.models.tiles.Tile;
import com.sharknados.models.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class SharkTank extends SharkPiece {
    public SharkTank(){
        super(20, 20, 50, 1);
    }

    @Override
    public List<Tile> getAbilityRange(Tile myTile, List<Tile> allTiles) {
        List<Tile> tilesInRange = new ArrayList<>();
        for (Tile t : allTiles) {
            if (t.isOccupied()){
                if(this.inTheSameArmyAs(t.getPiece())) {
                    tilesInRange.add(t);
                }
            }
        }
        return tilesInRange;
    }

    @Override
    public boolean doAbility(Tile myTile, Tile targetTile) {
        System.out.println("---------------");
        System.out.println("ABILITY - SHARK TANK");
        Piece target = targetTile.getPiece();
        System.out.println(target.getClass().getSimpleName() + " Health and Defense buffed!");
        target.setHealth(target.getHealth()+10);
        target.setDefence(target.getDefence()+10);
        System.out.println("---------------");
        return false;
    }
}
