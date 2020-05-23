package com.sharknados.models.pieces.eagle;

import com.sharknados.models.pieces.Piece;
import com.sharknados.models.pieces.PieceAbstractFactory;

public class EagleFactory implements PieceAbstractFactory {
    private int x;
    private int z;
    public EagleFactory(int x, int z){
        this.x = x;
        this.z = z;
    }

    public Piece createCommander(){
        return new EagleCommander(x, z);
    }

    public Piece createSoldier(){
        return new EagleSoldier(x, z);
    }

    public Piece createTank(){
        return new EagleTank(x, z);
    }
}
