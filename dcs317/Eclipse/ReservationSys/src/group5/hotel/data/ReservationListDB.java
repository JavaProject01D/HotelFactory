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


/**
 * 
 * @author Zahraa Horeibi
 * @version 11/13/2016
 */
public class ReservationListDB implements ReservationDAO {

	private List<Reservation> database;
	private List<Room> allRooms;
	private final ListPersistenceObject listPersistenceObject;
	private final HotelFactory factory;

	/**
	 * We use the ListPersistenceObject to load our database for needed data. We
	 * do not have a HotelFactory given so we set a default factory by using
	 * DawsonHotelFactory
	 * 
	 * @author Zahraa Horeibi
	 * @param listPersistenceObject
	 * 
	 */

	public ReservationListDB(ListPersistenceObject listPersistenceObject) {

		this.listPersistenceObject = listPersistenceObject;
		this.allRooms = this.listPersistenceObject.getRoomDatabase();
		this.database = this.listPersistenceObject.getReservationDatabase();
		factory = DawsonHotelFactory.DAWSON;
	}

	/**
	 * We use the ListPersistenceObject to load our database for needed data.
	 * 
	 * @author Zahraa Horeibi
	 * @param listPersistenceObject
	 * @param factory
	 * 
	 */

	public ReservationListDB(ListPersistenceObject listPersistenceObject, HotelFactory factory) {

		this.listPersistenceObject = listPersistenceObject;
		this.allRooms = this.listPersistenceObject.getRoomDatabase();
		this.database = this.listPersistenceObject.getReservationDatabase();
		this.factory = factory;

	}

	/**
	 * Overridden toString method so that when invoked, it returns a String
	 * representation of the contents of the database, one element per line.
	 * 
	 * @author Zahraa Horeibi
	 * @return String
	 */
	@Override
	public String toString() {

		int num = database.size();
		StringBuilder str = new StringBuilder("\nNumber of reservations in database: " + num);
		for (Reservation r : database) {
			str.append("\n" + r.toString());
		}
		return str.toString();
	}

	/**
	 * Overridden add method which first checks if the reservation overlaps with
	 * an existing reservation. If there is an overlap, throws a
	 * DuplicateReservationExcption. If the reservation does not overlap, adds a
	 * reference to a copy of the object referenced by the reserv and not the
	 * actual object being referenced by the parameter.
	 * 
	 * Updated by Denis Lebedev
	 * @author Zahraa Horeibi
	 * @param reserv
	 * @throws DuplicateReservationException
	 * 
	 */

	@Override
	public void add(Reservation reserv) throws DuplicateReservationException {
		
		for(int i =0; i < database.size(); i++){
			if(database.get(i).overlap(reserv) && database.get(i).getRoom().getNumber() == reserv.getRoom().getNumber())
				throw new IllegalArgumentException("Error overlap");
		}
		
		int index = binarySearch(this.database, reserv);
		
		if (index > 0)
			throw new DuplicateReservationException();
		
		index = -(index) -1;
		
		// Creating a deep copy of reserv
		Reservation copyReserv = factory.getReservationInstance(reserv);
		database.add(index, copyReserv);

	}

	/**
	 * This method saves the database to disk and assign null to the database
	 * field.
	 * 
	 * @author Zahraa
	 * @throws IOException
	 */
	@Override
	public void disconnect() throws IOException {

		this.listPersistenceObject.saveReservationDatabase(this.database);
		this.database = null;
	}

	/**
	 * Returns a copy of the Reservations belonging to the given customer, or an
	 * empty ArrayList.
	 * 
	 * @author Zahraa Horeibi
	 * @param cust
	 * @return ArrayList
	 */
	@Override
	public ArrayList<Reservation> getReservations(Customer cust) {
		
		//ERROR ELEMENTS ARE NOT DEEP COPIED ~~~ java notes 
		ArrayList<Reservation> res = new ArrayList<Reservation>();

		for (int i = 0; i < database.size(); i++) {
			res.add(database.get(i));
			}
		
		return res;
	}

	/**
	 * Removes the given reservation from the database if it is found; otherwise
	 * throw a NonExistingReservationException.
	 * 
	 * @author Zahraa Horeibi
	 * @param reserv
	 * @throws NonExistingReservationException
	 */

	@Override
	public void cancel(Reservation reserv) throws NonExistingReservationException {
		int index = binarySearch(this.database, reserv);
			
		if (index < 0)
			throw new NonExistingReservationException("The reservation given does not exist.");
		
		this.database.remove(index);
	}

	/**
	 * Returns an ArrayList with all reserved Rooms overlapping during the time
	 * period. You must check if an existing reservation has a room that has
	 * been reserved with the checkin date of the reservation before the
	 * checkout date provided, and the checkout date of the reservation is after
	 * the checkin date provided: in this case the room is reserved.
	 * Updated by Denis Lebedev
	 * 
	 * @author Zahraa Horeibi
	 * @param checkin
	 * @param checkout
	 * @return ArrayList
	 */

	@Override
	public ArrayList<Room> getReservedRooms(LocalDate checkin, LocalDate checkout) {

		ArrayList<Room> rooms = new ArrayList<Room>();

		for (int i = 0; i < database.size(); i++) {
			for (Reservation r : this.database) {
				if ((r.getCheckInDate().isBefore(checkout) && r.getCheckOutDate().isAfter(checkin))){
					//Give at least one element	
					if(rooms.size() == 0)
						rooms.add(r.getRoom());
					else										
						if(binarySearch(rooms, r.getRoom()) < 0)
							rooms.add(r.getRoom());											
				}
			}
		}
		return rooms;
	}

	/**
	 * Returns an ArrayList with all unreserved Rooms overlapping during the
	 * time period
	 * 
	 * @author Zahraa Horeibi
	 * @param checkin
	 * @param checkout
	 * @return ArrayList
	 */
	@Override
	public ArrayList<Room> getFreeRooms(LocalDate checkin, LocalDate checkout) {
		ArrayList<Room> emptyRooms = new ArrayList<Room>();
		List<Room> occupiedRoom = getReservedRooms(checkin, checkout);
		
		//NOT A DEEP COPY CHANGE AFTER
		if(occupiedRoom.size() == 0)
			return (ArrayList<Room>) allRooms;
				
		for(int i  =0; i < allRooms.size() ; i++){
			
			if(binarySearch(occupiedRoom, allRooms.get(i)) < 0)
				emptyRooms.add(allRooms.get(i));
		}
		
		return emptyRooms;
	}

	/**
	 * Returns an ArrayList with all unreserved Rooms with the given room type
	 * overlapping during the time period
	 * 
	 * Updated by Denis Lebedev
	 * @author Zahraa Horeibi
	 * @param checkin
	 * @param checkout
	 * @param roomType
	 * @return ArrayList
	 */
	@Override
	public ArrayList<Room> getFreeRooms(LocalDate checkin, LocalDate checkout, RoomType roomType) {
		ArrayList<Room> emptyRooms = new ArrayList<Room>();
		ArrayList<Room> freeRoom = getFreeRooms(checkin, checkout);
				
		//NOT A DEEP COPY CHANGE AFTER
		if(freeRoom.size() == 0)
			return freeRoom;
								
		for(int i  =0; i < freeRoom.size(); i++){			
			if(freeRoom.get(i).getRoomType().equals(roomType))
				emptyRooms.add(freeRoom.get(i));
		}			
		return emptyRooms;
	}

	/**
	 * This method removes all Reservations whose checkout date is before the
	 * current date
	 * 
	 * @author Zahraa Horeibi
	 */

	@Override
	public void clearAllPast() {
		for (int i =0; i < database.size() ; i++) {
			if (database.get(i).getCheckOutDate().isBefore(LocalDate.now())) {
				database.remove(i);
				i--;
			}
		}
	}

	/**
	 * This method is a helper method which returns the index of a given
	 * reservation inside of the reservation list (database) using a binary
	 * search.
	 * 
	 * @author Denis Lebedev
	 * @param reservationList
	 * @param res
	 * @return int
	 */

	private static int binarySearch(List<? extends Reservation> reservationList, Reservation res) {

		int first, last, middle;
		
		first = 0;
		last = reservationList.size() - 1;
		middle =0;
	
		
		while( first <= last){
			middle = (first + last)/2;
			if(reservationList.get(middle).equals(res)){
				//I found so i return the middle
				return middle;
			}else if(reservationList.get(middle).compareTo(res) < 0){
				first = middle+1;
			}else{
				last = middle-1;			
			}
	
		}
		return -(first +1); 
	}
	
	/**
	 * Overload binarySearch
	 * 
	 * @author Denis Lebedev
	 * 
	 * @param roomList
	 * @param room
	 * @return int
	 */
	private static int binarySearch(List<Room> roomList, Room room){
		
		int first, last, middle;
		
		first = 0;
		last = roomList.size() - 1;
		middle =0;
		
		while( first <= last){
			middle = (first + last)/2;
			
			if(roomList.get(middle).getRoomNumber() == room.getRoomNumber())
				return middle;
			else if(roomList.get(middle).getRoomNumber() < room.getRoomNumber())
				first = middle+1;
			else
				last = middle-1;			
		}
		
		return -(first +1);
	}
}
