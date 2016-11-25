package group5.hotel.business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.AllocationPolicy;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.interfaces.ReservationDAO;

public class DawsonHotelAllocationPolicy implements AllocationPolicy {

	private static final long serialVersionUID = 42031768871L;
	private ReservationDAO reservationDAO;
	// NEW keyword?
	
	public DawsonHotelAllocationPolicy( ReservationDAO res ) {
		
		this.reservationDAO = res;
	}

//heh
	@Override
	public Optional<Room> getAvailableRoom(LocalDate checkin, LocalDate checkout, RoomType roomType) {

		//why cast?
		ArrayList<Room> freeRooms = (ArrayList<Room>) reservationDAO.getFreeRooms(checkin, checkout, roomType);
		
		int floorNum = search(freeRooms);
		// random
			for ( Room r : freeRooms ) {
				if (r.getFloor() == floorNum) {
					return Optional.ofNullable(r);
				}
			}
			//not sure
			return Optional.empty();
	}
		
	// helper method returns the floor with the biggest number of free rooms.
	private static int search(List<Room> list) {
		
		int counter = 0;
		int floorNum = 1;
		int biggestNumSoFar = 0;

		for ( Room r : list ) {
			if(floorNum == r.getFloor())
				counter++;
			else{
					if(counter > biggestNumSoFar) {
						biggestNumSoFar = counter;
						counter = 0;
					} else if (counter == biggestNumSoFar) {
						return floorNum - 1;
					} else 
						return floorNum;
					
				floorNum++;
			}			
		} //end of for loop 
		return 0; // ??
	}
	
/*	public static Room[] freeRooms(List<Room> list) {

		int count = 0, tempCount;
		int room = list.get(0).getRoomNumber();
		int temp = 0;

		for (int i = 0; i < list.size() - 1; i++) {
			tempCount = 0;
			temp = list.get(i).getRoomNumber();

			for (int j = 0; j < list.size(); j++) {

				if ((list.get(i).getFloor()) == (list.get(j).getFloor())) {
					tempCount++;
					// System.out.println("TempCount++: " + tempCount);
				}
			} // end of j

			// System.out.println(temp);
			if (tempCount > count) {
				System.out.println("tempCount " + tempCount + " count " + count);
				room = temp;
				// System.out.println(room + " a " + temp);
				count = tempCount;
			} // end of if
		}

		return room;

	}

}



*/








}
