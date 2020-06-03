package com.sharknados.models.pieces.shark;

import com.sharknados.models.Tile;
import com.sharknados.models.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class SharkCommander extends SharkPiece {
    public SharkCommander(){
        super(30, 10, 50, 1);
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
        System.out.println("ABILITY - SHARK COMMANDER");
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
