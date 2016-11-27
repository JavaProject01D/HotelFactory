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

		
		List<Room> freeRooms = this.reservationDAO.getFreeRooms(checkin, checkout, roomType);	
		List<Room> freeRoomsOnFloor = new ArrayList<Room>();
		int floorNum = searchFloor(freeRooms);
		Random randomRoom = new Random();
		
		for (int k = 0; k < freeRooms.size() ; k++ ) {
			if (freeRooms.get(k).getFloor() == floorNum)
				freeRoomsOnFloor.add(freeRooms.get(k));
		}
		
		int rand = randomRoom.nextInt(((freeRoomsOnFloor.get(freeRoomsOnFloor.size() - 1).getRoomNumber() - freeRoomsOnFloor.get(0).getRoomNumber()) + 1) + freeRoomsOnFloor.get(0).getRoomNumber());

		
		
		
		
		return Optional.ofNullable(freeRoomsOnFloor.get(0));
	
	}

	public static int searchFloor(List<Room> list) {


		int count = 0;
		int tempCount;
		Room room = list.get(0);
		Room temp;

		for (int i = 0; i < list.size() - 1; i++) {
			tempCount = 0;
			temp = list.get(i);

			for (int j = 0; j < list.size(); j++) {

				if ((list.get(i).getFloor()) == (list.get(j).getFloor())) {
				tempCount++;
	
				}
			} // end of j

			if (tempCount > count) {
				room = temp;
				count = tempCount;
				
			} // end of if
		} // end of i 

		return room.getFloor();

	}
}
