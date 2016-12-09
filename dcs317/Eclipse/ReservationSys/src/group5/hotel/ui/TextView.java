package group5.hotel.ui;

import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.data.interfaces.ReservationDAO;
import group5.hotel.business.Hotel;
import group5.hotel.data.ObjectSerializedList;
import group5.hotel.data.ReservationListDB;

public class TextView implements Observer {
	
	ReservationDAO reservations;
	
	public TextView(Hotel model) {
		model.addObserver(this);
		reservations  = new ReservationListDB(new ObjectSerializedList	("dcs317\\Eclipse\\ReservationSys\\datafiles\\database\\customers.ser",
																		 "dcs317\\Eclipse\\ReservationSys\\datafiles\\database\\reservations.ser", 
																		 "dcs317\\Eclipse\\ReservationSys\\datafiles\\database\\rooms.ser"));
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
			System.out.println("You have " + reservations.getReservations(cust).size() + " reservations.");	
			o.notifyObservers();
		}
		
	}

}
