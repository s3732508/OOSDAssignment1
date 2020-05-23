package com.sharknados.views;

import com.sharknados.models.Tile;

/************************************
 Implements the observer pattern
 ***********************************/

public interface Observer {
    void update();
    Tile getSubject();
}
