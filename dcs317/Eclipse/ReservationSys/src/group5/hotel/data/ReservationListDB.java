package group5.hotel.data;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.HotelFactory;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.DuplicateReservationException;
import dw317.hotel.data.NonExistingReservationException;
import dw317.hotel.data.interfaces.ListPersistenceObject;
import dw317.hotel.data.interfaces.ReservationDAO;
import group5.hotel.business.DawsonHotelFactory;
import group5.hotel.business.DawsonReservation;

/**
 * @author 1537595
 *
 */
public class ReservationListDB implements ReservationDAO {
	
	private List<Reservation> database;
	private List<Room> allRooms;
	private final ListPersistenceObject listPersistenceObject;
	private final HotelFactory factory; 
		
	public ReservationListDB(ListPersistenceObject listPersistenceObject){
		
		this.listPersistenceObject = listPersistenceObject;
		this.allRooms = this.listPersistenceObject.getRoomDatabase();
		factory = DawsonHotelFactory.DAWSON;
	}
	
	public ReservationListDB (ListPersistenceObject listPersistenceObject,
			HotelFactory factory) {
		
		this.listPersistenceObject = listPersistenceObject;
		this.allRooms = this.listPersistenceObject.getRoomDatabase();
		this.factory = factory;
		
	}

	@Override
	public String toString() {
		
		int num = database.size();
		StringBuilder str = new StringBuilder("Number of reservations in database: " + num);
		for (Reservation r : database) {
			str.append("\n" + r.toString());
		}
		return str.toString();
	}
	
	
	@Override
	public void add(Reservation reserv) throws DuplicateReservationException {
			for (Reservation r : database) {
				if (r.equals(reserv))
					throw new DuplicateReservationException();
			}
			// Creating a deep copy of reserv
			Reservation copyReserv = new DawsonReservation(reserv.getCustomer(), reserv.getRoom(), reserv.getCheckInDate().getYear(),
															reserv.getCheckInDate().getMonthValue(), reserv.getCheckInDate().getDayOfMonth(),
															reserv.getCheckOutDate().getYear(), reserv.getCheckOutDate().getMonthValue(),											
															reserv.getCheckOutDate().getDayOfMonth());										
				
			//binary search so we can insert it at the correct position
			database.add(copyReserv);			
				
				
	}

	@Override
	public void disconnect() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Reservation> getReservations(Customer cust) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cancel(Reservation reserv) throws NonExistingReservationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Room> getReservedRooms(LocalDate checkin, LocalDate checkout) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Room> getFreeRooms(LocalDate checkin, LocalDate checkout) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Room> getFreeRooms(LocalDate checkin, LocalDate checkout, RoomType roomType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clearAllPast() {
		// TODO Auto-generated method stub
		
	}
}
	
	
	