package group5.hotel.ui;

import dw317.hotel.business.interfaces.*;
import dw317.hotel.data.interfaces.CustomerDAO;
import dw317.hotel.data.interfaces.ReservationDAO;
import group5.hotel.business.*;
import group5.hotel.data.*;


public class GUIApp {

	public static void main(String[] args) {
		HotelFactory factory = 
				DawsonHotelFactory.DAWSON;
		CustomerDAO customerDb =
				new CustomerListDB(new ObjectSerializedList
						("dcs317\\Eclipse\\ReservationSys\\datafiles\\database\\customers.ser",
							"dcs317\\Eclipse\\ReservationSys\\datafiles\\database\\reservations.ser",
							"dcs317\\Eclipse\\ReservationSys\\datafiles\\database\\rooms.ser"));
		ReservationDAO reservationDb = 
				new ReservationListDB(new ObjectSerializedList
						("dcs317\\Eclipse\\ReservationSys\\datafiles\\database\\customers.ser",
							"dcs317\\Eclipse\\ReservationSys\\datafiles\\database\\reservations.ser", 
							"dcs317\\Eclipse\\ReservationSys\\datafiles\\database\\rooms.ser"));

		Hotel model = new Hotel(factory, customerDb, reservationDb);
		GUIViewController app = new GUIViewController(model);
	}
}

