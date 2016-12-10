package group5.hotel.ui;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.data.interfaces.ReservationDAO;
import group5.hotel.business.Hotel;
import group5.hotel.data.ObjectSerializedList;
import group5.hotel.data.ReservationListDB;

/**
 * This class displays the desired information for a specific customer.
 * @author Zahraa Horeibi
 * @version 12/10/2016
 */


public class TextView implements Observer {
	
	ReservationDAO reservations;
	
	public TextView(Hotel model) {
		model.addObserver(this);
		
	}

	/**
	 * This method is an update method which is responsible to display the desired information that the customer asks for. 
	 * It notifies all observers every time there is a modification.
	 * @author Zahraa Horeibi
	 * @param o 
	 * @param arg
	 */
	
	@SuppressWarnings("unchecked")
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
		
		if(arg instanceof ArrayList<?>){
			ArrayList<Reservation> list = (ArrayList<Reservation>) arg;
			
			if(list.size() != 0){
								
				System.out.println("\nReservations: ");
				
				for(Reservation item : list)
					System.out.println("Room: " + item.getRoom().getRoomNumber() 
									+ "\nCheck in date: " + item.getCheckInDate() 
										+ "\nCheck out date: " + item.getCheckOutDate());
			}
			o.notifyObservers();
		}		
	}
}
