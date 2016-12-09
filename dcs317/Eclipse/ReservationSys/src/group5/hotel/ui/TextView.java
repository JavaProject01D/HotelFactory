package group5.hotel.ui;

import java.util.Observable;
import java.util.Observer;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import group5.hotel.business.Hotel;

public class TextView implements Observer {
	
	public TextView(Hotel model) {
		model.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		
		if (arg instanceof Customer) {
			Customer cust = (Customer) arg;
			System.out.println("\nCustomer Information: ");
			System.out.println("Name: " + cust.getName().getFullName() + "\nEmail: " + cust.getEmail());
		
			if (!cust.getCreditCard().isPresent()) 
				System.out.println("\nNo credit card on file.");
			 	else {
			 		System.out.println("Card Type: " + cust.getCreditCard().get().getType() 
			 							+ "\nCard number: " + cust.getCreditCard().get().getNumber());
			 	}
		//	o.notifyObservers(cust);
			o.notifyObservers();
		}
	
		if (arg instanceof Reservation) {
			System.out.println("Reso here!");
		}
		
		
	}

}
