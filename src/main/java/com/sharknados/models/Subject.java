package com.sharknados.models;

import com.sharknados.views.Observer;

/************************************
 Implements the observer pattern
 ***********************************/

public interface Subject {
	
	void attach(Observer observer);
	
	void notifyAllObservers();

}
