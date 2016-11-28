package group5.hotel.business;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.HotelFactory;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.DuplicateCustomerException;
import dw317.hotel.data.DuplicateReservationException;
import dw317.hotel.data.NonExistingCustomerException;
import dw317.hotel.data.NonExistingReservationException;
import dw317.hotel.data.interfaces.CustomerDAO;
import dw317.hotel.data.interfaces.ReservationDAO;
import group5.hotel.data.CustomerListDB;
import group5.hotel.data.ReservationListDB;
import group5.hotel.data.SequentialTextFileList;
import group5.util.ListUtilities;

/**
 * @author Sevan Topalian
 *
 */
public class HotelTest {

	public static void main(String[] args) {
		testHotel();
	}

	private static void testHotel() {
		System.out.println("Testing Hotel class");

		setup();

		testCreateReservation();
		testCancelReservation();
		testRegisterCustomer();
		testFindCustomer();
		testFindReservation();
		testUpdateCreditCard();
		testCloseHotel();

		teardown();

		System.out.println("\nFinished testing Hotel class");
	}

	private static void testCreateReservation() {
		System.out.println("\nTesting createReservation()");

		HotelFactory factory = DawsonHotelFactory.DAWSON;

		SequentialTextFileList stfl = new SequentialTextFileList("testfiles\\testRooms.txt",
				"testfiles\\testCustomers.txt", "testfiles\\testReservations.txt");
		CustomerDAO customers = new CustomerListDB(stfl);
		ReservationDAO reservations = new ReservationListDB(stfl);

		Hotel hotelTest = new Hotel(factory, customers, reservations);

		// Create new Customers to test createReservation
		System.out.println("\nCreating valid reservation:");
		Customer cust = factory.getCustomerInstance("Mamadou", "Mustapha", "goatislife@outlook.com");
		LocalDate checkin = LocalDate.of(2016, 8, 03);
		LocalDate checkout = LocalDate.of(2016, 8, 15);
		Room room = factory.getRoomInstance(101, "normal");
		Optional<Reservation> newReserv = hotelTest.createReservation(cust, checkin, checkout, room.getRoomType());
		System.out.println("Created: " + newReserv);

		System.out.println("\nCreating invalid reservation:");
		Customer custTwo = factory.getCustomerInstance("Test", "Customer", "test@customer.com");
		LocalDate checkinTwo = LocalDate.of(2016, 9, 18);
		LocalDate checkoutTwo = LocalDate.of(2016, 9, 19);
		Room roomTwo = factory.getRoomInstance(801, "penthouse");
		Optional<Reservation> newReservTwo = hotelTest.createReservation(custTwo, checkinTwo, checkoutTwo, roomTwo.getRoomType());
		System.out.println("Created: " + newReservTwo);
	}

	private static void testCancelReservation() {
		System.out.println("\nTesting cancelReservation()");

		HotelFactory factory = DawsonHotelFactory.DAWSON;

		SequentialTextFileList stfl = new SequentialTextFileList("testfiles\\testRooms.txt",
				"testfiles\\testCustomers.txt", "testfiles\\testReservations.txt");
		CustomerDAO customers = new CustomerListDB(stfl);
		ReservationDAO reservations = new ReservationListDB(stfl);

		Hotel hotelTest = new Hotel(factory, customers, reservations);

		// Create Reservations to cancel
		System.out.println("\nGetting existing reservation to cancel:");
		Customer cust = factory.getCustomerInstance("Mamadou", "Mustapha", "goatislife@outlook.com");
		List<Reservation> res = reservations.getReservations(cust);
		System.out.println("Got: " + res);

		System.out.println("\nExisting reservations:" + reservations);
		try {
			System.out.println("\nCancelling reservation...");
			hotelTest.cancelReservation(res.get(0));
		} catch (NonExistingReservationException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("\nUpdated reservations:" + reservations);

		System.out.println("\nGetting non-existing reservation to cancel:");
		Customer custTwo = factory.getCustomerInstance("Test", "Customer", "test@customer.com");
		List<Reservation> resTwo = reservations.getReservations(custTwo);
		System.out.println("Got: " + resTwo);

		System.out.println("\nExisting reservations:" + reservations);
		try {
			System.out.println("\nCancelling reservation...");
			if (resTwo.isEmpty())
				// Reusing the first reservation because it should be cancelled and therefore is non-existent
				hotelTest.cancelReservation(factory.getReservationInstance(res.get(0)));
		} catch (NonExistingReservationException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("\nUpdated reservations:" + reservations);
	}

	private static void testRegisterCustomer() {
		System.out.println("\nTesting registerCustomer()");

		HotelFactory factory = DawsonHotelFactory.DAWSON;

		SequentialTextFileList stfl = new SequentialTextFileList("testfiles\\testRooms.txt",
				"testfiles\\testCustomers.txt", "testfiles\\testReservations.txt");
		CustomerDAO customers = new CustomerListDB(stfl);
		ReservationDAO reservations = new ReservationListDB(stfl);

		Hotel hotelTest = new Hotel(factory, customers, reservations);

		System.out.println("\nCreating non-existing Customer to register:");
		System.out.println("Test*Customer*testcustomer@email.com");

		System.out.println("\nExisting Customers:" + customers);
		try {
			System.out.println("\nAdding Customer...");
			hotelTest.registerCustomer("Test", "Customer", "testcustomer@email.com");
		} catch (DuplicateCustomerException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("\nUpdated Customers:" + customers);

		System.out.println("\nCreating existing Customer to register:");
		System.out.println("Cosmin*Sythprost*romanian554@gmail.com");

		System.out.println("\nExisting Customers:" + customers);
		try {
			System.out.println("\nAdding Customer...");
			hotelTest.registerCustomer("Cosmin", "Sythprost", "romanian554@gmail.com");
		} catch (DuplicateCustomerException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("\nUpdated Customers:" + customers);
	}

	private static void testFindCustomer() {
		System.out.println("\nTesting findCustomer()");

		HotelFactory factory = DawsonHotelFactory.DAWSON;

		SequentialTextFileList stfl = new SequentialTextFileList("testfiles\\testRooms.txt",
				"testfiles\\testCustomers.txt", "testfiles\\testReservations.txt");
		CustomerDAO customers = new CustomerListDB(stfl);
		ReservationDAO reservations = new ReservationListDB(stfl);

		Hotel hotelTest = new Hotel(factory, customers, reservations);

		System.out.println("\nLooking for Customer Michael Fassion with email 675dtab@hotmail.com");
		Customer custFound = null;
		try {
			System.out.println("\nSearching...");
			custFound = hotelTest.findCustomer("675dtab@hotmail.com");
		} catch (NonExistingCustomerException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("\nFound Customer: " + custFound);

		System.out.println("\nLooking for Customer Sevan Topalian with email sevantopalian@gmail.com");
		Customer custFoundTwo = null;
		try {
			System.out.println("\nSearching...");
			custFoundTwo = hotelTest.findCustomer("sevantopalian@gmail.com");
		} catch (NonExistingCustomerException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("\nFound Customer: " + custFoundTwo);
	}

	private static void testFindReservation() {
		System.out.println("\nTesting findReservation()");

		HotelFactory factory = DawsonHotelFactory.DAWSON;

		SequentialTextFileList stfl = new SequentialTextFileList("testfiles\\testRooms.txt",
				"testfiles\\testCustomers.txt", "testfiles\\testReservations.txt");
		CustomerDAO customers = new CustomerListDB(stfl);
		ReservationDAO reservations = new ReservationListDB(stfl);

		Hotel hotelTest = new Hotel(factory, customers, reservations);

		System.out.println("\nLooking for Reservations with Michael Fassion");
		Customer cust = factory.getCustomerInstance("Michael", "Fassion", "675dtab@hotmail.com");
		List<Reservation> reservFound = hotelTest.findReservations(cust);
		System.out.println("\nFound Reservation: " + reservFound);

		System.out.println("\nLooking for Reservations with Sevan Topalian");
		Customer custTwo = factory.getCustomerInstance("Sevan", "Topalian", "sevantopalian@gmail.com");
		List<Reservation> reservFoundTwo = hotelTest.findReservations(custTwo);
		System.out.println("\nFound Reservation: " + reservFoundTwo);
	}

	private static void testUpdateCreditCard() {
		System.out.println("\nTesting updateCreditCard()");

		HotelFactory factory = DawsonHotelFactory.DAWSON;

		SequentialTextFileList stfl = new SequentialTextFileList("testfiles\\testRooms.txt",
				"testfiles\\testCustomers.txt", "testfiles\\testReservations.txt");
		CustomerDAO customers = new CustomerListDB(stfl);
		ReservationDAO reservations = new ReservationListDB(stfl);

		Hotel hotelTest = new Hotel(factory, customers, reservations);

		System.out.println("\nUpdating Credit Card for Customer Michael Fassion with Visa 4539257662667540");
		Customer custToUpdate = null;
		try {
			System.out.println("\nUpdating...");
			custToUpdate = hotelTest.updateCreditCard("675dtab@hotmail.com", "visa", "4539257662667540");
		} catch (NonExistingCustomerException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("\nUpdated Customer: " + custToUpdate);

		System.out.println("\nUpdating Credit Card for Customer Sevan Topalian with MasterCard 5285441218320241");
		Customer custToUpdateTwo = null;
		try {
			System.out.println("\nUpdating...");
			custToUpdateTwo = hotelTest.updateCreditCard("sevantopalian@gmail.com", "mastercard", "5285441218320241");
		} catch (NonExistingCustomerException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("\nUpdated Customer: " + custToUpdateTwo);
	}

	private static void testCloseHotel() {
		System.out.println("\nTesting closeHotel()");

		HotelFactory factory = DawsonHotelFactory.DAWSON;

		SequentialTextFileList stfl = new SequentialTextFileList("testfiles\\testRooms.txt",
				"testfiles\\testCustomers.txt", "testfiles\\testReservations.txt");
		CustomerDAO customers = new CustomerListDB(stfl);
		ReservationDAO reservations = new ReservationListDB(stfl);

		Hotel hotelTest = new Hotel(factory, customers, reservations);

		System.out.println("\nCreating Customer and Reservation to add");
		Customer cust = new DawsonCustomer("Sevan", "Topalian", "sevantopalian@gmail.com");
		Reservation res = new DawsonReservation(cust, new DawsonRoom(808, RoomType.PENTHOUSE), 2019, 04, 20, 2019, 04,
				30);
		System.out.println(cust);
		System.out.println(res);

		System.out.println("\nCurrent Customers: " + customers);
		System.out.println("\nCurrent Reservations: " + reservations);

		try {
			System.out.println("\nAdding a Customer and Reservation then disconnecting");
			customers.add(cust);
			reservations.add(res);

			System.out.println("\nUpdated Customers: " + customers);
			System.out.println("\nUpdated Reservations: " + reservations);

			System.out.println("\nDisconnecting...");
			hotelTest.closeHotel();
		} catch (DuplicateReservationException dre) {
			System.out.println(dre.getMessage());
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		} catch (DuplicateCustomerException dce) {
			System.out.println(dce.getMessage());
		}

		System.out.println("\nDisconnected");
		System.out.println("\nReconnecting...");

		CustomerDAO customersTwo = new CustomerListDB(stfl);
		ReservationDAO reservationsTwo = new ReservationListDB(stfl);

		System.out.println("\nReconnected");
		System.out.println("\nCustomers: " + customersTwo);
		System.out.println("\nReservations: " + reservationsTwo);
	}

	private static void setup() {
		Customer[] custs = new Customer[15];
		custs[0] = new DawsonCustomer("Andreea", "Galchenyuk", "super_woman669@hotmail.com");
		custs[1] = new DawsonCustomer("Nicolas", "Ghallaryu", "super_man659@hotmail.fr");
		custs[2] = new DawsonCustomer("Jephthia", "Inficousin", "Chicken_lover555@hotmail.com");
		custs[3] = new DawsonCustomer("Cosmin", "Sythprost", "romanian554@gmail.com");
		custs[4] = new DawsonCustomer("Mamadou", "Mustapha", "goatislife@outlook.com");
		custs[5] = new DawsonCustomer("Michael", "Fassion", "675dtab@hotmail.com");
		custs[6] = new DawsonCustomer("Jordan", "Landry", "jordy_the_bunny@info.com");
		custs[7] = new DawsonCustomer("Hillary", "Trump", "huffypuffyduffy@gmail.ca");

		Room[] rooms = new Room[15];
		rooms[0] = new DawsonRoom(101, RoomType.NORMAL);
		rooms[1] = new DawsonRoom(202, RoomType.NORMAL);
		rooms[2] = new DawsonRoom(303, RoomType.NORMAL);
		rooms[3] = new DawsonRoom(404, RoomType.NORMAL);
		rooms[4] = new DawsonRoom(505, RoomType.NORMAL);
		rooms[5] = new DawsonRoom(606, RoomType.SUITE);
		rooms[6] = new DawsonRoom(707, RoomType.SUITE);
		rooms[7] = new DawsonRoom(808, RoomType.PENTHOUSE);

		Reservation[] reservs = new Reservation[15];
		reservs[0] = new DawsonReservation(custs[0], rooms[0], 2016, 9, 10, 2016, 9, 13);
		reservs[1] = new DawsonReservation(custs[1], rooms[1], 2016, 9, 18, 2016, 9, 19);
		reservs[2] = new DawsonReservation(custs[2], rooms[2], 2016, 9, 6, 2016, 9, 9);
		reservs[3] = new DawsonReservation(custs[3], rooms[3], 2016, 9, 30, 2016, 10, 3);
		reservs[4] = new DawsonReservation(custs[4], rooms[4], 2016, 9, 15, 2016, 9, 19);
		reservs[5] = new DawsonReservation(custs[5], rooms[5], 2016, 4, 20, 2016, 4, 30);
		reservs[6] = new DawsonReservation(custs[6], rooms[6], 2016, 9, 2, 2016, 9, 11);
		reservs[7] = new DawsonReservation(custs[7], rooms[7], 2016, 9, 4, 2016, 9, 6);

		File dir = new File("testfiles");
		try {
			if (!dir.exists()) {
				dir.mkdirs();
			}
			ListUtilities.saveListToTextFile(custs, "testfiles/testCustomers.txt");
			ListUtilities.saveListToTextFile(rooms, "testfiles/testRooms.txt");
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
}