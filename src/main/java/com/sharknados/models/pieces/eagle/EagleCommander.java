package com.sharknados.models.pieces.eagle;

import com.sharknados.models.tiles.Tile;
import com.sharknados.models.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class EagleCommander extends EaglePiece {
    public EagleCommander(){
        super(40, 0, 50, 1);
    }

    @Override
    public boolean isCommander(){
        return true;
    }

    @Override
    public List<Tile> getAbilityRange(Tile myTile, List<Tile> allTiles) {
        List<Tile> tilesInRange = new ArrayList<>();
        tilesInRange.add(myTile);
        return tilesInRange;
    }

    @Override
    public boolean doAbility(Tile tile, Tile myTile) {
        System.out.println("---------------");
        System.out.println("ABILITY - EAGLE COMMANDER");
        boolean isGameOver = false;
        for (int i=0; i<6; i++){
            if(myTile.getNeighbor(i) != null){
                Tile targetTile = myTile.getNeighbor(i);
                if(targetTile.isOccupied()){
                    Piece target = targetTile.getPiece();
                    if(!target.inTheSameArmyAs(this)){
                        //Deal damage and check if a commander is killed
                        if(target.takeDamage(getAttack(), targetTile)){
                            isGameOver = true;
                        }
                    }
                }
            }

        }
        System.out.println("---------------");
        return isGameOver;
    }
}
