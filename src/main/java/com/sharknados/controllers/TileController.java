package com.sharknados.controllers;

public class TileController extends AbstractController {
    public static final String OCCUPIED_PROPERTY = "Piece occupying tile";
    public static final String SELECTED_PROPERTY = "tile selected";
    public static final String HIGHLIGHTED_PROPERTY = "tile available";
    public static final String UNAVAILABLE_PROPERTY = "tile unavailable";

    public TileController(){
    }

    public void setOccupied(boolean occupied){
        setModelProperty(OCCUPIED_PROPERTY, occupied);
    }

    public void setSelected(boolean selected){
        setModelProperty(SELECTED_PROPERTY, selected);
    }

    public void setHighlighted(boolean highlighted){
        setModelProperty(HIGHLIGHTED_PROPERTY, highlighted);
    }

    public void setUnavailable(boolean unavailable){
        setModelProperty(UNAVAILABLE_PROPERTY, unavailable);
    }

}
