package com.sharknados.views;

import com.sharknados.models.AbstractSubject;

/************************************
 Implements the observer pattern
 ***********************************/

public abstract class AbstractObserver {
    protected AbstractSubject subject;
    public abstract void update();
    public abstract AbstractSubject getSubject();
}
