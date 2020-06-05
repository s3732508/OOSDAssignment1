package com.sharknados.models.pieces.eagle;

import com.sharknados.models.tiles.Tile;
import com.sharknados.models.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class EagleSoldier extends EaglePiece{
    public EagleSoldier(){
        super(40, 0, 30, 1);
    }
    @Override
    public List<Tile> getAbilityRange(Tile myTile, List<Tile> allTiles) {
        List<Tile> tilesInRange = new ArrayList<>();
        for (Tile t : allTiles) {
            if (t.isOccupied()){
                if(!inTheSameArmyAs(t.getPiece())) {
                    t.setUnavailable(false);
                    t.setHighlighted(true);
                    tilesInRange.add(t);
                }
            }
        }
        return tilesInRange;
    }

    @Override
    public boolean doAbility(Tile myTile, Tile targetTile) {
        System.out.println("---------------");
        System.out.println("ABILITY - EAGLE SOLDIER");

        Piece target = targetTile.getPiece();
        int attack = (int) Math.floor(getAttack()/2);

        System.out.println("WEAK ATTACK");
        System.out.println("Attacker: " + getClass().getSimpleName() + " -- Attack: " + attack);
        System.out.println("VS.");
        System.out.println("Defender: " + target.getClass().getSimpleName() + " -- Defense: " + target.getDefence());
        //Deal damage and check if a commander is killed
        boolean isGameOver = target.takeDamage(attack, targetTile);

        System.out.println("---------------");
        return isGameOver;
    }
}
