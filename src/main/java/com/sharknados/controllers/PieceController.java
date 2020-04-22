package com.sharknados.controllers;

import com.sharknados.models.pieces.Piece;

public class PieceController extends AbstractController {
    public static final String X_PROPERTY = "x position";
    public static final String Z_PROPERTY = "z position";

    public PieceController(){
    }

    public void setX(int x){
        setModelProperty(X_PROPERTY, x);
    }

    public void setZ(int z){
        setModelProperty(Z_PROPERTY, z);
    }

}
