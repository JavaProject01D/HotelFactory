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
		
		int position = binarySearch(this.database, reserv);
			// Creating a deep copy of reserv
			Reservation copyReserv = factory.getReservationInstance(reserv);									
				
			//binary search so we can insert it at the correct position, not sure
			database.add(binarySearch(this.database, copyReserv),copyReserv);			
			
	}

	@Override
	public void disconnect() throws IOException {		
		this.listPersistenceObject.saveReservationDatabase(this.database);
		this.database = null;
		new ReservationListDB(this.listPersistenceObject, this.factory);
	}

	@Override
	public List<Reservation> getReservations(Customer cust) {
		
		
		
		
		
		
		
		
		
		return null;
	}

	@Override
	public void cancel(Reservation reserv) throws NonExistingReservationException {
	
		int index = binarySearchForIndex(this.database, reserv);
		int result = binarySearch(this.database, reserv);	
			
			if ( result == 0 ) 
				this.database.remove(index);
		
			if (result == -1)  // fix -1 return
				throw new NonExistingReservationException();		
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
					return 0;
				}
				else if (result < 0) {
					low = mid + 1;
					return -1;
				}
				else 
					high = mid - 1;	
				return 1;
			}	
			return -1;
	}
	
	private static int binarySearchForIndex(List<? extends Reservation> reservationList, Reservation res) {

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
				return -1;
			}
			else 
				high = mid - 1;	
			return 1;
		}	
		return -1;
}
}
	
	
	