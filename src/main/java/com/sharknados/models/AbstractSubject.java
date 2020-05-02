package com.sharknados.models;

import com.sharknados.views.Observer;
import java.util.ArrayList;
import java.util.List;

/************************************
    Implements the observer pattern
 ***********************************/

public abstract class AbstractSubject {
    private List<Observer> observers = new ArrayList<Observer>();

    public void attach(Observer observer){
        observers.add(observer);
    }

    public void notifyAllObservers(){
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
