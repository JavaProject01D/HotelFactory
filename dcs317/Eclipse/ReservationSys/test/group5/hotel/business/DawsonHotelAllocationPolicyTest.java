package group5.hotel.business;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import group5.hotel.data.ReservationListDB;
import group5.hotel.data.SequentialTextFileList;
import group5.util.ListUtilities;

public class DawsonHotelAllocationPolicyTest {

	public static void main(String[] args) {
		getAvailableRoomsTest();
	}
	
		private static void setup() {

			String[] rooms = new String[10];
			rooms[0] = "206*normal";
			rooms[1] = "401*normal";
			rooms[2] = "704*suite";
			rooms[3] = "801*penthouse";
			rooms[4] = "801*penthouse";
			rooms[5] = "101*normal";
			rooms[6] = "203*normal";
			rooms[7] = "205*normal";
	
			String[] custs = new String[7];
			custs[0] = "bbbb@aaaa*Bobby*Lee*mastercard*5458325441641567";
			custs[1] = "bbbb@bbbb*Humico*Madori*mastercard*5233382411178726";
			custs[2] = "iiiiii@iiii*Robert*Siri*Visa*4485011762777210";
			custs[3] = "bbbb@jjjjj*Johny-Laurence*Smith**";
			custs[4] = "yyyy@yyyy*Umila*Gangee**";
			
			String[] reservs = new String[7];
			reservs[0] = "bbbb@aaaa*2016*8*30*2016*12*25*206";
			reservs[1] = "bbbb@bbbb*2016*10*26*2016*12*30*401";
			reservs[2] = "iiiiii@iiii*2017*1*1*2018*1*1*704";
			reservs[3] = "bbbb@jjjjj*2016*9*20*2016*9*26*801";
			reservs[4] = "yyyy@yyyy*2017*5*5*2017*7*8*801";
			
			File dir = new File("testfiles");
			try {
				if (!dir.exists()) {
					dir.mkdirs();
				}
				ListUtilities.saveListToTextFile(rooms, "testfiles/testRooms.txt");
				ListUtilities.saveListToTextFile(custs, "testfiles/testCustomers.txt");
				ListUtilities.saveListToTextFile(reservs, "testfiles/testReservations.txt");
			} catch (IOException io) {
				System.out.println("Error creating file in setUp()");
			}
		}
		private static void setupTwoRoomsSameFloor() {

			String[] rooms = new String[15];
			rooms[0] = "206*normal";
			rooms[1] = "401*normal";
			rooms[2] = "704*suite";
			rooms[3] = "801*penthouse";
			rooms[4] = "801*penthouse";
			rooms[5] = "101*normal";
			rooms[6] = "203*normal";
			rooms[7] = "205*normal";
			rooms[8] = "404*normal";
			rooms[9] = "405*normal";

			String[] custs = new String[7];
			custs[0] = "bbbb@aaaa*Bobby*Lee*mastercard*5458325441641567";
			custs[1] = "bbbb@bbbb*Humico*Madori*mastercard*5233382411178726";
			custs[2] = "iiiiii@iiii*Robert*Siri*Visa*4485011762777210";
			custs[3] = "bbbb@jjjjj*Johny-Laurence*Smith**";
			custs[4] = "yyyy@yyyy*Umila*Gangee**";
			
			String[] reservs = new String[7];
			reservs[0] = "bbbb@aaaa*2016*8*30*2016*12*25*206";
			reservs[1] = "bbbb@bbbb*2016*10*26*2016*12*30*401";
			reservs[2] = "iiiiii@iiii*2017*1*1*2018*1*1*704";
			reservs[3] = "bbbb@jjjjj*2016*9*20*2016*9*26*801";
			reservs[4] = "yyyy@yyyy*2017*5*5*2017*7*8*801";
			
			File dir = new File("testfiles");
			try {
				if (!dir.exists()) {
					dir.mkdirs();
				}
				ListUtilities.saveListToTextFile(rooms, "testfiles/testRooms.txt");
				ListUtilities.saveListToTextFile(custs, "testfiles/testCustomers.txt");
				ListUtilities.saveListToTextFile(reservs, "testfiles/testReservations.txt");
			} catch (IOException io) {
				System.out.println("Error creating file in setUp()");
			}
		}
		private static void setupBiggest() {

			String[] rooms = new String[15];
			rooms[0] = "206*normal";
			rooms[1] = "401*normal";
			rooms[2] = "704*suite";
			rooms[3] = "801*penthouse";
			rooms[4] = "801*penthouse";
			rooms[5] = "101*normal";
			rooms[6] = "203*normal";
			rooms[7] = "205*normal";
			rooms[8] = "404*normal";
			rooms[9] = "405*normal";
			rooms[10] = "502*normal";
			rooms[11] = "503*normal";
			rooms[12] = "505*normal";

			String[] custs = new String[7];
			custs[0] = "bbbb@aaaa*Bobby*Lee*mastercard*5458325441641567";
			custs[1] = "bbbb@bbbb*Humico*Madori*mastercard*5233382411178726";
			custs[2] = "iiiiii@iiii*Robert*Siri*Visa*4485011762777210";
			custs[3] = "bbbb@jjjjj*Johny-Laurence*Smith**";
			custs[4] = "yyyy@yyyy*Umila*Gangee**";
			
			String[] reservs = new String[7];
			reservs[0] = "bbbb@aaaa*2016*8*30*2016*12*25*206";
			reservs[1] = "bbbb@bbbb*2016*10*26*2016*12*30*401";
			reservs[2] = "iiiiii@iiii*2017*1*1*2018*1*1*704";
			reservs[3] = "bbbb@jjjjj*2016*9*20*2016*9*26*801";
			reservs[4] = "yyyy@yyyy*2017*5*5*2017*7*8*801";
			
			File dir = new File("testfiles");
			try {
				if (!dir.exists()) {
					dir.mkdirs();
				}
				ListUtilities.saveListToTextFile(rooms, "testfiles/testRooms.txt");
				ListUtilities.saveListToTextFile(custs, "testfiles/testCustomers.txt");
				ListUtilities.saveListToTextFile(reservs, "testfiles/testReservations.txt");
			} catch (IOException io) {
				System.out.println("Error creating file in setUp()");
			}
		}


		private static void teardown() {
			File theFile = new File("testfiles/testRooms.txt");
			if (theFile.exists()) {
				theFile.delete();
			}
			theFile = new File("testfiles/testCustomers.txt");
			if (theFile.exists()) {
				theFile.delete();
			}
			theFile = new File("testfiles/testReservations.txt");
			if (theFile.exists()) {
				theFile.delete();
			}
		}
	private static void getAvailableRoomsTest() {
		
	/*	setup();
		SequentialTextFileList file = new SequentialTextFileList("testfiles/testRooms.txt",
				"testfiles/testCustomers.txt", "testfiles/testReservations.txt");

		ReservationListDB db = new ReservationListDB(file);
		DawsonHotelAllocationPolicy dhap = new DawsonHotelAllocationPolicy(db);
		System.out.println(db.toString() + "\n");
	*/	
		String[] testcase = new String[8];
		LocalDate[] checkin = new LocalDate[8];
		LocalDate[] checkout = new LocalDate[8];
		RoomType[] roomType = new RoomType[8];
		
		testcase[0] = "Case 1: Valid data -- No rooms available with given information";
		checkin[0] = LocalDate.of(2016, 9, 20);
		checkout[0] = LocalDate.of(2016, 9, 26);
		roomType[0] = RoomType.PENTHOUSE;
		
		testcase[1] = "Case 2: Valid data -- One room available on one floor";
		checkin[1] = LocalDate.of(2016, 7, 20);
		checkout[1] = LocalDate.of(2016, 11, 26);
		roomType[1] = RoomType.NORMAL;
		
		testcase[2] = "Case 3: Valid data -- Same amount of rooms available on two different floors";
		checkin[2] = LocalDate.of(2016, 10, 26);
		checkout[2] = LocalDate.of(2016, 12, 30);
		roomType[2] = RoomType.NORMAL;

		testcase[3] = "Case 4: Valid data -- More than one room available on one floor";
		checkin[3] = LocalDate.of(2016, 10, 20);
		checkout[3] = LocalDate.of(2016, 11, 26);
		roomType[3] = RoomType.NORMAL;
		
	//	Optional<Room> r = dhap.getAvailableRoom(checkin[3], checkout[3], roomType[3]);
	//	System.out.println("List of Free Rooms: " + db.getFreeRooms(checkin[3], checkout[3], roomType[3]));
	//	System.out.println("Chosen Free Room: " + r.get() + "\n");
		
		for (int i = 0; i < 4; i++) {
			if (i == 0 || i == 1) {
			setup();
			SequentialTextFileList file = new SequentialTextFileList("testfiles/testRooms.txt",
					"testfiles/testCustomers.txt", "testfiles/testReservations.txt");

			ReservationListDB db = new ReservationListDB(file);
			DawsonHotelAllocationPolicy dhap = new DawsonHotelAllocationPolicy(db);
			
			System.out.println(testcase[i]);
			try {
	
				Optional<Room>r = dhap.getAvailableRoom(checkin[i], checkout[i], roomType[i]);
				System.out.println("\t List of Free Rooms: " + db.getFreeRooms(checkin[i], checkout[i], roomType[i]));
				System.out.println("\t Chosen Free Room: " + r.get() + "\n");
					
			} catch (Exception e) {
				System.out.println("\t\t Error: " + e.getMessage() + "\n");
				continue;
			}
			teardown();
		   }
			
		
			if ( i == 2) {
			
					setupTwoRoomsSameFloor();
					SequentialTextFileList file = new SequentialTextFileList("testfiles/testRooms.txt",
							"testfiles/testCustomers.txt", "testfiles/testReservations.txt");

					ReservationListDB db = new ReservationListDB(file);
					DawsonHotelAllocationPolicy dhap = new DawsonHotelAllocationPolicy(db);
					
					System.out.println(testcase[i]);
					try {
			
						Optional<Room>r = dhap.getAvailableRoom(checkin[i], checkout[i], roomType[i]);
						System.out.println("\t List of Free Rooms: " + db.getFreeRooms(checkin[i], checkout[i], roomType[i]));
						System.out.println("\t Chosen Free Room: " + r.get() + "\n");
							
					} catch (Exception e) {
						System.out.println("\t\t Error: " + e.getMessage() + "\n");
						continue;
					}
					teardown();
				}
			
			if (i == 3) {
					setupBiggest();
					SequentialTextFileList file = new SequentialTextFileList("testfiles/testRooms.txt",
							"testfiles/testCustomers.txt", "testfiles/testReservations.txt");

					ReservationListDB db = new ReservationListDB(file);
					DawsonHotelAllocationPolicy dhap = new DawsonHotelAllocationPolicy(db);
					
					System.out.println(testcase[i]);
					try {
			
						Optional<Room>r = dhap.getAvailableRoom(checkin[i], checkout[i], roomType[i]);
						System.out.println("\t List of Free Rooms: " + db.getFreeRooms(checkin[i], checkout[i], roomType[i]));
						System.out.println("\t Chosen Free Room: " + r.get() + "\n");
							
					} catch (Exception e) {
						System.out.println("\t\t Error: " + e.getMessage() + "\n");
						continue;
					}
					teardown();
				 
			}
		} 
	}
}
