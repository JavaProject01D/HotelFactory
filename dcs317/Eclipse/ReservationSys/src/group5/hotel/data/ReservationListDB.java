package group5.hotel.data;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
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
		this.database = this.listPersistenceObject.getReservationDatabase();
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
		StringBuilder str = new StringBuilder("\nNumber of reservations in database: " + num);
		for (Reservation r : database) {
			str.append("\n" + r.toString());
		}
		return str.toString();
	}
	
	
	@Override
	public void add(Reservation reserv) throws DuplicateReservationException {
			// Creating a deep copy of reserv
			Reservation copyReserv = factory.getReservationInstance(reserv);	
			
			int index = binarySearch(this.database, copyReserv);
				index = -(index);
				
				if (index > 0)
					throw new DuplicateReservationException();
			//binary search so we can insert it at the correct position, not sure
			database.add(index,copyReserv);			
			
	}

	@Override
	public void disconnect() throws IOException {
		
		try {
			this.listPersistenceObject.saveReservationDatabase(this.database);
			} catch ( IOException ie ) {
			throw new IOException("Error saving to file!");
		}
		this.database = null;
	}

	@Override
	public List<Reservation> getReservations(Customer cust) {
		
		
		
		cust.getEmail();
		//complete
		
		return null;
	}

	@Override
	public void cancel(Reservation reserv) throws NonExistingReservationException {
		int index = binarySearch(this.database, reserv);
			if (index > 0) 
				this.database.remove(index);	
			if (index < 0)  
				throw new NonExistingReservationException();		
	}

	@Override
	public ArrayList<Room> getReservedRooms(LocalDate checkin, LocalDate checkout) {
		
		ArrayList<Room> rooms = new ArrayList<Room>();
		
		for ( int i = 0; i < database.size(); i++) {	
			for ( Reservation r : this.database) {
				if (checkin.isBefore(checkout) && checkout.isAfter(checkin)) {
					if ( r.getCheckInDate().equals(checkin) && r.getCheckOutDate().equals(checkout)){
						rooms.set(i, r.getRoom());
					}
				}	
			}
		}
		return rooms;
	}

	@Override
	public ArrayList<Room> getFreeRooms(LocalDate checkin, LocalDate checkout) {
	 
		ArrayList<Room> emptyRooms = new ArrayList<Room>(20);
		
		for ( int i = 0; i < database.size(); i++) {		
			for ( Reservation r : this.database) {
				if (!checkin.isBefore(checkout) && !checkout.isAfter(checkin)) {
						emptyRooms.set(i, r.getRoom());	
				}	
			}
		}	
			return emptyRooms;
	}

	@Override
	public List<Room> getFreeRooms(LocalDate checkin, LocalDate checkout, RoomType roomType) {

		
		
		return null;
	}

	@Override
	public void clearAllPast() {
		for ( Reservation r : this.database ) {
			
		}			
	}

	private static int binarySearch(List<? extends Reservation> reservationList, Reservation res) {

		int low = 0;
		int high = reservationList.size() - 1;
		int mid = (low + high) / 2;
		int result;


		while ( low <= high) {
			
			mid = (low + high) / 2;
			result = reservationList.get(mid).compareTo(res);
			System.out.println(result);
			
			if (result == 0) {
				return mid;
			}
			else if (result < 0) {
				low = mid + 1;
			}
			else 
				high = mid - 1;	
		}	
		return -(high + 1);
	}
}
	
	
	