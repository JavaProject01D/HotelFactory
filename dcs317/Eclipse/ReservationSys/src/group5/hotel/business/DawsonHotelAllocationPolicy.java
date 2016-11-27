package group5.hotel.business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.AllocationPolicy;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.interfaces.ReservationDAO;
/**
 * This class tries to spread out its guest evenly through the hotel according to a specific algorithm.
 * @author Zahraa Horeibi
 *
 */
public class DawsonHotelAllocationPolicy implements AllocationPolicy {

	private static final long serialVersionUID = 42031768871L;
	private ReservationDAO reservationDAO;
	
	/** 
	 * The DawsonHotelAllocationPolicy class gets a ReservationDAO object in 
	 * its constructor and it keeps a reference to this object as an instance variable. 
	 * @author Zahraa Horeibi
	 * @param res
	 */
	public DawsonHotelAllocationPolicy( ReservationDAO res ) {
		this.reservationDAO = res;
	}
	
	/**
	 * This method finds the floor with the most number of free rooms of the given room type during the desired period and 
	 * randomly choose one of the free rooms on that floor. If there are no free rooms available with the search criteria, return an empty Optional
	 * @author Zahraa Horeibi
	 * @param checkin, checkout, roomType
	 * @return Optional<Room>
	 */
	@Override
	public Optional<Room> getAvailableRoom(LocalDate checkin, LocalDate checkout, RoomType roomType) {		
		List<Room> freeRooms = this.reservationDAO.getFreeRooms(checkin, checkout, roomType);	
		List<Room> freeRoomsOnFloor = new ArrayList<Room>();
		
		Room someFreeRoom = searchFloor(freeRooms);
			
			if (someFreeRoom == null)
				return Optional.empty();
			
		int floorNum = someFreeRoom.getFloor();
			// Assigning all the free rooms on the same floor to a list. 
		for (int k = 0; k < freeRooms.size() ; k++ ) {
			if (freeRooms.get(k).getFloor() == floorNum)
				freeRoomsOnFloor.add(freeRooms.get(k));
		}
			Random rand = new Random();
		 	int index = rand.nextInt(freeRoomsOnFloor.size());
		 
		 	//gets a random room using the random int value of index
		 return Optional.of(freeRoomsOnFloor.get(index));
	}
/**
 * This helper method returns the first room number of the floor which contains the most number of free rooms. 
 * If multiple floors have the same positive number of available rooms, it choose the lowest floor.
 * @author Zahraa Horeibi
 * @param list
 * @return Room
 */
	private static Room searchFloor(List<Room> list) {
		int count = 0;
		int tempCount = 0;
		Room room = null;
		Room temp = null;
		try {
		room = list.get(0);
		//checks if the list is empty it throws an IndexOutOfBoundsException to inform the user
		} catch (IndexOutOfBoundsException e) {
			System.out.println("No rooms are available with given information.");
		}
			for (int i = 0; i < list.size() - 1; i++) {
				tempCount = 0;
				temp = list.get(i);

			for (int j = 0; j < list.size(); j++) {
				if ((list.get(i).getFloor()) == (list.get(j).getFloor())) 
					tempCount++;
			} // end of j for loop
			if (tempCount > count) {
				count = tempCount;
				room = temp;		
			} // end of if statement
		} // end of i for loop 
		return room;
	}
}
