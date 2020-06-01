package com.sharknados.models;

import com.sharknados.views.Observer;
import java.util.ArrayList;
import java.util.List;

/************************************
    Implements the observer pattern
 ***********************************/

public abstract class AbstractSubject implements Subject {
    private List<Observer> observers = new ArrayList<Observer>();
    
    @Override
    public void attach(Observer observer){
        observers.add(observer);
    }
    @Override
    public void notifyAllObservers(){
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
