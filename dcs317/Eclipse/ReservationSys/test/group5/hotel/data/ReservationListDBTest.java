package group5.hotel.data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.DuplicateReservationException;
import dw317.hotel.data.NonExistingReservationException;
import group5.hotel.business.DawsonCustomer;
import group5.hotel.business.DawsonReservation;
import group5.hotel.business.DawsonRoom;
import group5.util.ListUtilities;
import group5.util.Utilities;

public class ReservationListDBTest {

	public static void main(String[] args) {
		testAdd();
		testDisconnect();
		testCancel();
		testGetReservations();
		testGetReservedRooms();
		testGetFreeRooms();
		testThreeParamsGetFreeRooms();
		testClearAllPast();
		
		

	}

	private static void setup() {

		String[] rooms = new String[8];
		rooms[0] = "206*normal";
		rooms[1] = "401*normal";
		rooms[2] = "406*normal";
		rooms[3] = "503*normal";
		rooms[4] = "601*normal";
		rooms[5] = "704*suite";
		rooms[6] = "801*penthouse";
		rooms[7] = "801*penthouse";

		String[] custs = new String[8];
		custs[0] = "bbbb@aaaa*Bobby*Lee*mastercard*5458325441641567";
		custs[1] = "bbbb@bbbb*Humico*Madori*mastercard*5233382411178726";
		custs[2] = "ccccc@CCCc*Jean*Gerophar*visa*4929596474756407";
		custs[3] = "eeeeee.ee@eee.com*Sheila*Kaif*AMEX*374425782767815";
		custs[4] = "gggg@gggg.com*Kathy*Perry**";
		custs[5] = "iiiiii@iiii*Robert*Siri*Visa*4485011762777210";
		custs[6] = "bbbb@jjjjj*Johny-Laurence*Smith**";
		custs[7] = "yyyy@yyyy*Umila*Gangee**";
		
		String[] reservs = new String[8];
		reservs[0] = "bbbb@aaaa*2016*8*30*2016*12*25*206";
		reservs[1] = "bbbb@bbbb*2016*10*26*2016*12*30*401";
		reservs[2] = "ccccc@CCCc*2016*11*28*2017*2*25*406";
		reservs[3] = "eeeeee.ee@eee.com*2017*5*9*2017*6*1*503";
		reservs[4] = "gggg@gggg.com*2016*12*25*2017*1*1*601";
		reservs[5] = "iiiiii@iiii*2017*1*1*2018*1*1*704";
		reservs[6] = "bbbb@jjjjj*2016*9*20*2016*9*26*801";
		reservs[7] = "yyyy@yyyy*2017*5*5*2017*7*8*801";
		
		SequentialTextFileList file = new SequentialTextFileList
				("testfiles/testRooms.txt", "testfiles/testCustomers.txt",
				"testfiles/testReservations.txt");
		File dir = new File("testfiles");
		try{
			if (!dir.exists()){  
				dir.mkdirs();
			}
			
			ListUtilities.saveListToTextFile(rooms, 
					"testfiles/testRooms.txt");
			
			Utilities.serializeObject(file.getRoomDatabase(), "testFiles\\testRooms.ser");
			
			ListUtilities.saveListToTextFile(custs, 
					"testfiles/testCustomers.txt");
			
			Utilities.serializeObject(file.getCustomerDatabase(), "testFiles\\testCustomers.ser");
			
			ListUtilities.saveListToTextFile(reservs, 
					"testfiles/testReservations.txt");
			
			Utilities.serializeObject(file.getReservationDatabase(), "testFiles\\testReservations.ser");
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
	
	private static void teardownSerialization() {
		File theFile = new File("testfiles/testRooms.ser");
		if (theFile.exists()) {
			theFile.delete();
		}
		theFile = new File("testfiles/testCustomers.ser");
		if (theFile.exists()) {
			theFile.delete();
		}
		theFile = new File("testfiles/testReservations.ser");
		if (theFile.exists()) {
			theFile.delete();
		}
	}

	private static void testAdd() {
		setup();
		ObjectSerializedList file = new ObjectSerializedList
				("testfiles/testCustomers.ser", "testfiles/testReservations.ser",
						"testfiles/testRooms.ser");

		ReservationListDB db = new ReservationListDB(file);
		System.out.println(db.toString());
		System.out.println("________________________ TESTING add() _______________________\n");

		String[] testcase = new String[3];

		Reservation[] resToAdd = new DawsonReservation[3];

		testcase[0] = " Case 1: Valid Data";
		resToAdd[0] = new DawsonReservation(new DawsonCustomer("Habiba", "Awada", "habiba_awad@hotmail.com"),
				new DawsonRoom(105, RoomType.NORMAL), 2008, 10, 5, 2010, 10, 5);

		testcase[1] = " Case 2: Invalid Data: Overlap";
		resToAdd[1] = new DawsonReservation(new DawsonCustomer("Humico", "Madori", "yyyy@yyyy"),
				new DawsonRoom(401, RoomType.NORMAL), 2016, 10, 26, 2016, 12, 30);
		
		testcase[2] = " Case 4: Valid Data: Room exist, but different checkIn";
		resToAdd[2] = new DawsonReservation(new DawsonCustomer("Humico", "Madori", "zzz@zzz"),
				new DawsonRoom(105, RoomType.NORMAL), 2019, 11, 5, 2020, 1, 5);

		for (int i = 0; i < resToAdd.length; i++) {
			System.out.println(testcase[i]);

			try {
				db.add(resToAdd[i]);
			} catch (DuplicateReservationException dce) {
				System.out.println("DuplicateReservation: " + dce.getMessage());
				continue;
			} catch (Exception e) {
				System.out.println("<----HANDLE ME---> " + e.getMessage() + " <----HANDLE ME---> ");
				continue;
			}

		}
		System.out.println("\n==== List ====");
		System.out.println(db.toString());

		teardownSerialization();

	}

	private static void testDisconnect() {
		setup();
		ObjectSerializedList file = new ObjectSerializedList
				("testfiles/testCustomers.ser", "testfiles/testReservations.ser",
						"testfiles/testRooms.ser");
		ReservationListDB db = new ReservationListDB(file);

		System.out.println("________________________ TESTING disconnect() _______________________\n");

		System.out.println("\nLIST USED:");

		System.out.println(db.toString());
		
		Reservation res = new DawsonReservation(new DawsonCustomer("Sheila", "Kaif", "eeeeee.ee@eee.com"),
				new DawsonRoom(601, RoomType.NORMAL), 2019, 10, 26, 2019, 12, 30);

		try {
			System.out.println("\nAdding a customer then Disconect: ");
			db.add(res);

			System.out.println(db.toString());

			System.out.println("\nDisconect--->");
			db.disconnect();
		} catch (DuplicateReservationException dre) {
			System.out.println("DuplicateReservationException: " + dre.getMessage());
		} catch (IOException ioe) {
			System.out.println("IOE Exception: " + ioe.getMessage());
		} catch (Exception e) {
			System.out.println("<----HANDLE ME---> " + e.getMessage() + " <----HANDLE ME---> ");
		}

		System.out.println("\nReconnect--->");
		ReservationListDB dbTwo = new ReservationListDB(file);

		System.out.println(dbTwo.toString());

		teardownSerialization();
	}

	private static void testCancel() {

		setup();
		ObjectSerializedList file = new ObjectSerializedList
				("testfiles/testCustomers.ser", "testfiles/testReservations.ser",
						"testfiles/testRooms.ser");
		ReservationListDB db = new ReservationListDB(file);

		System.out.println("________________________ TESTING cancel() _______________________\n");

		System.out.println("\nLIST USED:");

		System.out.println(db.toString());

		String[] testcase = new String[3];
		Reservation[] resToAdd = new DawsonReservation[3];
	
		testcase[0] = "\n Case 1: Valid Data";
		resToAdd[0] = new DawsonReservation(new DawsonCustomer("Bobby", "Lee", "bbbb@aaaa"),
				new DawsonRoom(206, RoomType.NORMAL), 2016, 8, 30, 2016, 12, 25);
		
		testcase[1] = "\n Case 2: Invalid Data: Only the room is invalid";
		resToAdd[1] = new DawsonReservation(new DawsonCustomer("Humico", "Madori", "bbbb@bbbb"),
				new DawsonRoom(206, RoomType.NORMAL), 2016, 10, 26, 2016, 12, 25);
		
		testcase[2] = "\n Case 3: Invalid Data: Reservation not in list";
		resToAdd[2] = new DawsonReservation(new DawsonCustomer("Dominica", "Esperente", "piquanteChiquita@hotmail.com"),
				new DawsonRoom(605, RoomType.NORMAL), 2016, 10, 26, 2016, 12, 30);

		for (int i = 0; i < resToAdd.length; i++) {
			System.out.println(testcase[i]);

			try {
				db.cancel(resToAdd[i]);
				
				
			} catch (NonExistingReservationException dce) {
				System.out.println("NonExistingReservation: " + dce.getMessage());
				continue;
			} catch (Exception e) {
				System.out.println("<----HANDLE ME---> " + e.getMessage() + " <----HANDLE ME---> ");
				continue;
			}
		}
		
		System.out.println(db.toString());

		teardownSerialization();
	}

	private static void testGetReservations() {
		setup();
		ObjectSerializedList file = new ObjectSerializedList
				("testfiles/testCustomers.ser", "testfiles/testReservations.ser",
						"testfiles/testRooms.ser");
		ReservationListDB db = new ReservationListDB(file);

		System.out.println("________________________ TESTING getReservations() _______________________\n");

		System.out.println("\nLIST USED:");

		System.out.println(db.toString());
		
		Customer[] custToSearch = new DawsonCustomer[2];
		String[] testCase = new String[2];
		
		
		testCase[0] = new String("\nTest Case 1 Valid get Email Exist in the Database ");
		custToSearch[0] = new DawsonCustomer("Humico", "Madori", "bbbb@bbbb");

		testCase[1] = new String("\nTest Case 2 Invalid Email does not exist in the Database ");
		custToSearch[1] = new DawsonCustomer("Dominica", "Esperente", "piquanteChiquita@hotmail.com");

		for (int i = 0; i < custToSearch.length; i++) {
			System.out.println(testCase[i]);

			try {
				System.out.println(db.getReservations(custToSearch[i]));
				
			} catch (Exception e) {
				System.out.println("<----HANDLE ME---> " + e.getMessage() + " <----HANDLE ME---> ");
				continue;
			}

		}
		teardownSerialization();
	}

	private static void testGetReservedRooms() {
		setup();
		ObjectSerializedList file = new ObjectSerializedList
				("testfiles/testCustomers.ser", "testfiles/testReservations.ser",
						"testfiles/testRooms.ser");
		ReservationListDB db = new ReservationListDB(file);

		System.out.println("_______________________ TESTING getReservedRooms() ________________________\n");

		System.out.println("\nLIST USED:");

		System.out.println(db.toString());
		
		List<Room> output = new ArrayList<Room>();
		String[] testcase = new String[3];
		Reservation[] resToSearch = new DawsonReservation[3];

		testcase[0] = "\n Case 1: Valid Data";
		resToSearch[0] = new DawsonReservation(new DawsonCustomer("Habiba", "Awada", "habiba_awad@hotmail.com"),
				new DawsonRoom(105, RoomType.NORMAL), 2008, 10, 5, 2010, 10, 5);

		testcase[1] = "\n Case 2: Invalid Data: Invalid dates in list";
		resToSearch[1] = new DawsonReservation(new DawsonCustomer("Habiba", "Awada", "habiba_awad@hotmail.com"),
				new DawsonRoom(206, RoomType.NORMAL), 2016, 9, 26, 2017, 12, 30);
		
		testcase[2] = "\n Case 3: Invalid Data: Invalid dates in list, but more specific date";
		resToSearch[2] = new DawsonReservation(new DawsonCustomer("Habiba", "Awada", "habiba_awad@hotmail.com"),
				new DawsonRoom(206, RoomType.NORMAL), 2017, 5, 5, 2017, 7, 8);

		for (int i = 0; i < resToSearch.length; i++) {
			System.out.println(testcase[i]);

			try {
				output = db.getReservedRooms(resToSearch[i].getCheckInDate(), resToSearch[i].getCheckOutDate());
				
				if(output.size() == 0)
					System.out.println("All the rooms are available for the given date: " + output);
				else
					System.out.println("Rooms occupied for the given date: " + output);
				
			} catch (Exception e) {
				System.out.println("<----HANDLE ME---> " + e.getMessage() + " <----HANDLE ME---> ");
				continue;
			}

		}

		teardownSerialization();
	}

	private static void testGetFreeRooms() {
		setup();
		ObjectSerializedList file = new ObjectSerializedList
				("testfiles/testCustomers.ser", "testfiles/testReservations.ser",
						"testfiles/testRooms.ser");
		ReservationListDB db = new ReservationListDB(file);

		System.out.println("_______________________ TESTING getFreeRooms() ________________________\n");

		System.out.println("\nLIST USED:");

		System.out.println(db.toString());
		
		List<Room> output = new ArrayList<Room>();
		String[] testcase = new String[3];
		Reservation[] resToSearch = new DawsonReservation[3];

		testcase[0] = "\n Case 1: Valid Data";
		resToSearch[0] = new DawsonReservation(new DawsonCustomer("Habiba", "Awada", "habiba_awad@hotmail.com"),
				new DawsonRoom(105, RoomType.NORMAL), 1995, 10, 26, 1996, 12, 30);

		testcase[1] = "\n Case 2: Invalid Data: Invalid dates in list";
		resToSearch[1] = new DawsonReservation(new DawsonCustomer("Habiba", "Awada", "habiba_awad@hotmail.com"),
				new DawsonRoom(206, RoomType.NORMAL), 2016, 9, 26, 2017, 12, 30);
		
		testcase[2] = "\n Case 3: Invalid Data: Invalid dates in list, but more specific date";
		resToSearch[2] = new DawsonReservation(new DawsonCustomer("Habiba", "Awada", "habiba_awad@hotmail.com"),
				new DawsonRoom(206, RoomType.NORMAL), 2017, 5, 5, 2017, 7, 8);

		for (int i = 0; i < resToSearch.length; i++) {
			System.out.println(testcase[i]);

			try {
				
				output = db.getFreeRooms(resToSearch[i].getCheckInDate(), resToSearch[i].getCheckOutDate());
				
				if(output.size() == 0)
					System.out.println("No Free Room for the given date: " + output);
				else
					System.out.println("Free Rooms for the given date: " + output);
								
			} catch (Exception e) {
				System.out.println("<----HANDLE ME---> " + e.getMessage() + " <----HANDLE ME---> ");
				continue;
			}

		}
		teardownSerialization();
	}

	private static void testThreeParamsGetFreeRooms() {
		setup();
		ObjectSerializedList file = new ObjectSerializedList
				("testfiles/testCustomers.ser", "testfiles/testReservations.ser",
						"testfiles/testRooms.ser");
		ReservationListDB db = new ReservationListDB(file);

		System.out.println(
				"_______________________ TESTING getFreeRooms() with three parameters ________________________\n");

		System.out.println("\nLIST USED:");

		System.out.println(db.toString());
		
		List<Room> output = new ArrayList<Room>();
		String[] testcase = new String[3];
		Reservation[] resToSearch = new DawsonReservation[3];

		testcase[0] = "\n Case 1: Valid Data";
		resToSearch[0] = new DawsonReservation(new DawsonCustomer("Habiba", "Awada", "habiba_awad@hotmail.com"),
				new DawsonRoom(105, RoomType.PENTHOUSE), 1995, 10, 26, 1996, 12, 30);

		testcase[1] = "\n Case 2: Invalid Data: Invalid dates in list";
		resToSearch[1] = new DawsonReservation(new DawsonCustomer("Habiba", "Awada", "habiba_awad@hotmail.com"),
				new DawsonRoom(206, RoomType.NORMAL), 2016, 9, 26, 2017, 12, 30);
		
		testcase[2] = "\n Case 3: Invalid Data: Invalid dates in list, but more specific date";
		resToSearch[2] = new DawsonReservation(new DawsonCustomer("Habiba", "Awada", "habiba_awad@hotmail.com"),
				new DawsonRoom(206, RoomType.NORMAL), 2017, 5, 5, 2017, 7, 8);


		for (int i = 0; i < resToSearch.length; i++) {
			System.out.println(testcase[i]);

			try {
				output = db.getFreeRooms(resToSearch[i].getCheckInDate(), resToSearch[i].getCheckOutDate(),
						resToSearch[i].getRoom().getRoomType());
				
				if(output.size() == 0)
					System.out.println("No Free Room for the given type: " + output);
				else
					System.out.println("Free Rooms for the given type: " + output);
				
			} catch (Exception e) {
				System.out.println("<----HANDLE ME---> " + e.getMessage() + " <----HANDLE ME---> ");
				continue;
			}

		}
		teardownSerialization();
	}
	

	private static void testClearAllPast() {
		setup();
		ObjectSerializedList file = new ObjectSerializedList
				("testfiles/testCustomers.ser", "testfiles/testReservations.ser",
						"testfiles/testRooms.ser");

		ReservationListDB db = new ReservationListDB(file);
		System.out.println(db.toString());
		System.out.println("________________________ TESTING clearAllPast() _______________________\n");

			try {
				db.clearAllPast();
			} catch (Exception e) {
				System.out.println("<----HANDLE ME---> " + e.getMessage() + " <----HANDLE ME---> ");
			}

		System.out.println("\n==== List After Clearing ====");
		System.out.println(db.toString());

		teardownSerialization();

	}


}
