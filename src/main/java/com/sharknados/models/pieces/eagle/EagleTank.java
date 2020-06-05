package com.sharknados.models.pieces.eagle;

import com.sharknados.models.tiles.Tile;
import com.sharknados.models.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class EagleTank extends EaglePiece{
    public EagleTank(){
        super(30, 10, 50, 1);
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
        System.out.println("ABILITY - EAGLE TANK");
        Piece target = targetTile.getPiece();
        System.out.println(target.getClass().getSimpleName() + " Health and Attack buffed!");
        target.setHealth(target.getHealth()+10);
        target.setAttack(target.getAttack()+10);
        System.out.println("---------------");
        return false;
    }
}
