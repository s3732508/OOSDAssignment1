package com.sharknados.views;

import com.sharknados.models.AbstractSubject;

/************************************
 Implements the observer pattern
 ***********************************/

public interface Observer {
    void update();
    AbstractSubject getSubject();
}
