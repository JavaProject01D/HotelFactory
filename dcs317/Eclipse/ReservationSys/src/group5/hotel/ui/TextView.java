package group5.hotel.ui;

import java.util.Observable;
import java.util.Observer;

import group5.hotel.business.Hotel;

public class TextView implements Observer {

	
	public TextView(Hotel model) {
		model.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		
		
		
		
	}

}
