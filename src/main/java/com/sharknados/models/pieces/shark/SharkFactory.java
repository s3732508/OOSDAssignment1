package com.sharknados.models.pieces.shark;

import com.sharknados.models.pieces.Piece;
import com.sharknados.models.pieces.PieceAbstractFactory;

public class SharkFactory implements PieceAbstractFactory {
    private int x;
    private int z;
    public SharkFactory(int x, int z){
        this.x = x;
        this.z = z;
    }

    public Piece createCommander(){
        return new SharkCommander(x, z);
    }

    public Piece createSoldier(){
        return new SharkSoldier(x, z);
    }

    public Piece createTank(){
        return new SharkTank(x, z);
    }
}
