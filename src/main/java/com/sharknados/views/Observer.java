package com.sharknados.views;


import com.sharknados.models.Subject;

/************************************
 Implements the observer pattern
 ***********************************/

public interface Observer {
    void update();
    Subject getSubject();
}
