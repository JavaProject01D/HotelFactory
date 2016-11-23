package group5.hotel.data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.DuplicateCustomerException;
import dw317.hotel.data.DuplicateReservationException;
import dw317.hotel.data.NonExistingCustomerException;
import dw317.hotel.data.NonExistingReservationException;
import group5.hotel.business.DawsonCustomer;
import group5.hotel.business.DawsonReservation;
import group5.hotel.business.DawsonRoom;
import group5.util.ListUtilities;

public class ReservationListDBTest {

	public static void main(String[] args) {
		testAdd();
		/*testDisconnect();
		testCancel();
		testGetReservations();
		testGetReservedRooms();
		testGetFreeRooms();
		testThreeParamsGetFreeRooms();
		testClearAllPast();*/

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

	private static void testAdd() {
		setup();
		SequentialTextFileList file = new SequentialTextFileList("testfiles/testRooms.txt",
				"testfiles/testCustomers.txt", "testfiles/testReservations.txt");

		ReservationListDB db = new ReservationListDB(file);
		System.out.println(db.toString());
		System.out.println("________________________ TESTING add() _______________________\n");

		String[] testcase = new String[2];

		Reservation[] resToAdd = new DawsonReservation[2];

		testcase[0] = " Case 1: Valid Data";
		resToAdd[0] = new DawsonReservation(new DawsonCustomer("Habiba", "Awada", "habiba_awad@hotmail.com"),
				new DawsonRoom(105, RoomType.NORMAL), 2008, 10, 5, 2010, 10, 5);

		testcase[1] = " Case 2: Invalid Data: Reservation already in list";
		resToAdd[1] = new DawsonReservation(new DawsonCustomer("Humico", "Madori", "yyyy@yyyy"),
				new DawsonRoom(401, RoomType.NORMAL), 2016, 10, 26, 2016, 12, 30);

		for (int i = 0; i < resToAdd.length; i++) {
			System.out.println(testcase[i]);

			try {
				db.add(resToAdd[i]);
			} catch (DuplicateReservationException dce) {
				System.out.println("DuplicateReservation: " + dce.getMessage());
				continue;
			} catch (IllegalArgumentException iae){
				System.out.println("IllegalArgumentException: " + iae.getMessage());
				continue;
			} catch (Exception e) {
				System.out.println("<----HANDLE ME---> " + e.getMessage() + " <----HANDLE ME---> ");
				continue;
			}

		}
		System.out.println("\n==== List ====");
		System.out.println(db.toString());

		teardown();

	}

	private static void testDisconnect() {
		setup();
		SequentialTextFileList file = new SequentialTextFileList("testfiles/testRooms.txt",
				"testfiles/testCustomers.txt", "testfiles/testReservations.txt");
		ReservationListDB db = new ReservationListDB(file);

		System.out.println("________________________ TESTING disconnect() _______________________\n");

		System.out.println("\nLIST USED:");

		System.out.println(db.toString());

		Reservation res = new DawsonReservation(new DawsonCustomer("Hum", "Mad", "mad@host.com"),
				new DawsonRoom(602, RoomType.NORMAL), 2016, 10, 26, 2016, 12, 30);

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

		teardown();
	}

	private static void testCancel() {

		setup();
		SequentialTextFileList file = new SequentialTextFileList("testfiles/testRooms.txt",
				"testfiles/testCustomers.txt", "testfiles/testReservations.txt");
		ReservationListDB db = new ReservationListDB(file);

		System.out.println("________________________ TESTING cancel() _______________________\n");

		System.out.println("\nLIST USED:");

		System.out.println(db.toString());

		String[] testcase = new String[6];
		Reservation[] resToAdd = new DawsonReservation[6];

		testcase[0] = "\n Case 1: Valid Data";
		resToAdd[0] = new DawsonReservation(new DawsonCustomer("Habiba", "Awada", "habiba_awad@hotmail.com"),
				new DawsonRoom(105, RoomType.NORMAL), 2008, 10, 5, 2010, 10, 5);

		testcase[1] = "\n Case 2: Invalid Data: Reservation not in list";
		resToAdd[1] = new DawsonReservation(new DawsonCustomer("Dominica", "Esperente", "piquanteChiquita@hotmail.com"),
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

		teardown();
	}

	private static void testGetReservations() {
		setup();
		SequentialTextFileList file = new SequentialTextFileList("testfiles/testRooms.txt",
				"testfiles/testCustomers.txt", "testfiles/testReservations.txt");
		ReservationListDB db = new ReservationListDB(file);

		System.out.println("________________________ TESTING getReservations() _______________________\n");

		System.out.println("\nLIST USED:");

		System.out.println(db.toString());

		Customer[] custToSearch = new DawsonCustomer[3];
		String[] testCase = new String[3];

		testCase[0] = new String("\nTest Case 1 Valid get Email Exist in the Database ");
		custToSearch[0] = new DawsonCustomer("Habiba", "Awada", "habiba_awad@hotmail.com");

		testCase[1] = new String("\nTest Case 2 Invalid Email does not exist in the Database ");
		custToSearch[1] = new DawsonCustomer("Dominica", "Esperente", "piquanteChiquita@hotmail.com");

		for (int i = 0; i < custToSearch.length; i++) {
			System.out.println(testCase[i]);

			try {
				db.getReservations(custToSearch[i]);
			} catch (Exception e) {
				System.out.println("<----HANDLE ME---> " + e.getMessage() + " <----HANDLE ME---> ");
				continue;
			}

		}
		teardown();
	}

	private static void testGetReservedRooms() {
		setup();
		SequentialTextFileList file = new SequentialTextFileList("testfiles/testRooms.txt",
				"testfiles/testCustomers.txt", "testfiles/testReservations.txt");
		ReservationListDB db = new ReservationListDB(file);

		System.out.println("_______________________ TESTING getReservedRooms() ________________________\n");

		System.out.println("\nLIST USED:");

		System.out.println(db.toString());

		String[] testcase = new String[6];
		Reservation[] resToSearch = new DawsonReservation[6];

		testcase[0] = "\n Case 1: Valid Data";
		resToSearch[0] = new DawsonReservation(new DawsonCustomer("Habiba", "Awada", "habiba_awad@hotmail.com"),
				new DawsonRoom(105, RoomType.NORMAL), 2008, 10, 5, 2010, 10, 5);

		testcase[1] = "\n Case 2: Invalid Data: Invalid dates in list";
		resToSearch[1] = new DawsonReservation(new DawsonCustomer("Habiba", "Awada", "habiba_awad@hotmail.com"),
				new DawsonRoom(105, RoomType.NORMAL), 1995, 10, 26, 1996, 12, 30);

		for (int i = 0; i < resToSearch.length; i++) {
			System.out.println(testcase[i]);

			try {
				db.getReservedRooms(resToSearch[i].getCheckInDate(), resToSearch[i].getCheckOutDate());
			} catch (Exception e) {
				System.out.println("<----HANDLE ME---> " + e.getMessage() + " <----HANDLE ME---> ");
				continue;
			}

		}

		teardown();
	}

	private static void testGetFreeRooms() {
		setup();
		SequentialTextFileList file = new SequentialTextFileList("testfiles/testRooms.txt",
				"testfiles/testCustomers.txt", "testfiles/testReservations.txt");
		ReservationListDB db = new ReservationListDB(file);

		System.out.println("_______________________ TESTING getFreeRooms() ________________________\n");

		System.out.println("\nLIST USED:");

		System.out.println(db.toString());

		String[] testcase = new String[6];
		Reservation[] resToSearch = new DawsonReservation[6];

		testcase[0] = "\n Case 1: Valid Data";
		resToSearch[0] = new DawsonReservation(new DawsonCustomer("Habiba", "Awada", "habiba_awad@hotmail.com"),
				new DawsonRoom(105, RoomType.NORMAL), 1995, 10, 26, 1996, 12, 30);

		testcase[1] = "\n Case 2: Invalid Data: Date used already";
		resToSearch[1] = new DawsonReservation(new DawsonCustomer("Habiba", "Awada", "habiba_awad@hotmail.com"),
				new DawsonRoom(105, RoomType.NORMAL), 2008, 10, 5, 2010, 10, 5);

		for (int i = 0; i < resToSearch.length; i++) {
			System.out.println(testcase[i]);

			try {
				db.getFreeRooms(resToSearch[i].getCheckInDate(), resToSearch[i].getCheckOutDate());
			} catch (Exception e) {
				System.out.println("<----HANDLE ME---> " + e.getMessage() + " <----HANDLE ME---> ");
				continue;
			}

		}
		teardown();
	}

	private static void testThreeParamsGetFreeRooms() {
		setup();
		SequentialTextFileList file = new SequentialTextFileList("testfiles/testRooms.txt",
				"testfiles/testCustomers.txt", "testfiles/testReservations.txt");
		ReservationListDB db = new ReservationListDB(file);

		System.out.println(
				"_______________________ TESTING getFreeRooms() with three parameters ________________________\n");

		System.out.println("\nLIST USED:");

		System.out.println(db.toString());

		String[] testcase = new String[6];
		Reservation[] resToSearch = new DawsonReservation[6];

		testcase[0] = "\n Case 1: Valid Data";
		resToSearch[0] = new DawsonReservation(new DawsonCustomer("Habiba", "Awada", "habiba_awad@hotmail.com"),
				new DawsonRoom(105, RoomType.NORMAL), 1995, 10, 26, 1996, 12, 30);

		testcase[1] = "\n Case 2: Invalid Data: Date used already";
		resToSearch[1] = new DawsonReservation(new DawsonCustomer("Habiba", "Awada", "habiba_awad@hotmail.com"),
				new DawsonRoom(105, RoomType.NORMAL), 2008, 10, 5, 2010, 10, 5);

		testcase[2] = "\n Case 3: Invalid Data: Wrong Room Type";
		resToSearch[2] = new DawsonReservation(new DawsonCustomer("Habiba", "Awada", "habiba_awad@hotmail.com"),
				new DawsonRoom(105, RoomType.PENTHOUSE), 2008, 10, 5, 2010, 10, 5);

		for (int i = 0; i < resToSearch.length; i++) {
			System.out.println(testcase[i]);

			try {
				db.getFreeRooms(resToSearch[i].getCheckInDate(), resToSearch[i].getCheckOutDate(),
						resToSearch[i].getRoom().getRoomType());
			} catch (Exception e) {
				System.out.println("<----HANDLE ME---> " + e.getMessage() + " <----HANDLE ME---> ");
				continue;
			}

		}
		teardown();
	}
	

	private static void testClearAllPast() {
		setup();
		SequentialTextFileList file = new SequentialTextFileList("testfiles/testRooms.txt",
				"testfiles/testCustomers.txt", "testfiles/testReservations.txt");

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

		teardown();

	}


}
