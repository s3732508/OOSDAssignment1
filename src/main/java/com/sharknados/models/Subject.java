package com.sharknados.models;

import com.sharknados.views.Observer;

public interface Subject {
	
	void attach(Observer observer);
	
	void notifyAllObservers();

}
