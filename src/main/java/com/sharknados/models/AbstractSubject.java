package com.sharknados.models;

import com.sharknados.views.AbstractObserver;
import java.util.ArrayList;
import java.util.List;

/************************************
    Implements the observer pattern
 ***********************************/

public abstract class AbstractSubject {
    private List<AbstractObserver> observers = new ArrayList<AbstractObserver>();

    public void attach(AbstractObserver observer){
        observers.add(observer);
    }

    public void notifyAllObservers(){
        for (AbstractObserver observer : observers) {
            observer.update();
        }
    }
}
