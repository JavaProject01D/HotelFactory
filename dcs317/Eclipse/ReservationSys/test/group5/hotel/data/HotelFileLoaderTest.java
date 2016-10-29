package group5.hotel.data;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import group5.hotel.business.DawsonCustomer;
import group5.hotel.business.DawsonRoom;
import group5.hotel.data.HotelFileLoader;


/**
 * This class will test the HotelFileLoad class.
 * It will load valid or invalid data and process
 * through it.
 * 
 * @author Denis Lebedev
 *
 */
public class HotelFileLoaderTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		loadRoomFilesTest();
		loadCustomerFilesTest();
		getReservationListFromSequentialFileTest();
	}

	private static void loadRoomFilesTest(){
		
		System.out.println("\n_______________Validate The Room Loading_________________\n");
		
		String path;
		path = "dcs317\\Eclipse\\ReservationSys\\datafiles\\rooms.txt";
		
		loadRoomFilesTest("Case 1 - Valid rooms.txt", path, true);
		
		path = "dcs317\\Eclipse\\ReservationSys\\testbin\\testFiles\\rooms\\invalidRoomNumber.txt";
		loadRoomFilesTest("Case 2 - Invalid room file - Wrong Room number", path, false);
		
		path = "dcs317\\Eclipse\\ReservationSys\\testbin\\testFiles\\rooms\\invalidRoomType.txt";
		loadRoomFilesTest("Case 3 - Invalid room file - Wrong Type", path, false);
		
		path = "dcs317\\Eclipse\\ReservationSys\\testbin\\testFiles\\rooms\\invalidRoomNull.txt";
		loadRoomFilesTest("Case 4 - Invalid room file - Null Room Number", path, false);
		
		path = "dcs317\\Eclipse\\ReservationSys\\testbin\\testFiles\\rooms\\invalidRoomNumberDouble.txt";
		loadRoomFilesTest("Case 5 - Invalid room file - Double Room Number", path, false);
		
		path = "dcs317\\Eclipse\\ReservationSys\\testbin\\testFiles\\rooms\\validRoomWithBlank.txt";
		loadRoomFilesTest("Case 6 - Valid room file - Blank Line for the next Room", path, true);
		
	}
	
	private static void loadRoomFilesTest(String testCase, String path, boolean validation){
		Room[] rooms;
		
		System.out.println("\n" + testCase + " \nPath: " + path);
		
		try{
			rooms = HotelFileLoader.getRoomListFromSequentialFile(path);
			
			if(validation)
				for(int i = 0; i < rooms.length; i++)
					System.out.println("RoomsFile " + i + ": " + rooms[i]);
			
			
		}catch(NumberFormatException nfe){
			System.out.println("FormatException: " + nfe.getMessage());
			if(validation)
				System.out.println("ERROR: the given file should be Valid <--Fix It Please-->");
			
		}catch(IllegalArgumentException iae){
			System.out.println("ArgumentException: " + iae.getMessage());
			if(validation)
				System.out.println("ERROR: the given file should be Valid <--Fix It Please-->");
			
		}catch(IOException ioe){
			System.out.println("Error: " + ioe.getMessage());
			if(validation)
				System.out.println("ERROR: the given file should be Valid <--Fix It Please-->");
			
		}catch(Exception e){
			System.out.println("Error: " + e.getMessage() + "This Error should be catched somewhere!");
			if(validation)
				System.out.println("ERROR: the given file should be Valid <--Fix It Please-->");
		}		
	}
	
	
	private static void loadCustomerFilesTest(){
		
		System.out.println("\n_______________Validate The Customer Loading_________________\n");
		
		String path;
		
		for(int i =1; i <= 10 ; i++){
			path = "dcs317\\Eclipse\\ReservationSys\\datafiles\\customers" + i + ".txt";
			System.out.println(path);
			loadCustomerFilesTest("Case " + i + "--Original Data--", path, true);
		}
				
		path = "dcs317\\Eclipse\\ReservationSys\\testBin\\testFiles\\customer\\validCustBlankLine.txt";
		loadCustomerFilesTest("Case 11 - Valid with blank line", path, true);
		
		path ="dcs317\\Eclipse\\ReservationSys\\testBin\\testFiles\\customer\\invalidCustName.txt";
		loadCustomerFilesTest("Case 12 - Invalid Customer Name", path, false);
		
		path = "dcs317\\Eclipse\\ReservationSys\\testBin\\testFiles\\customer\\invalidCustEmail.txt";
		loadCustomerFilesTest("Case 13 - Invalid Customer Email", path, false);
		
		path = "dcs317\\Eclipse\\ReservationSys\\testBin\\testFiles\\customer\\invalidCustHostName.txt";
		loadCustomerFilesTest("Case 14 - Invalid Host Name", path, false);
		
		path = "dcs317\\Eclipse\\ReservationSys\\testBin\\testFiles\\customer\\invalidCustLastName.txt";
		loadCustomerFilesTest("Case 15 - Invalid Last Name", path, false);
		
		path = "dcs317\\Eclipse\\ReservationSys\\testBin\\testFiles\\customer\\invalidCardType.txt";
		loadCustomerFilesTest("Case 16 - Invalid Card Type", path, false);		
		
		path = "dcs317\\Eclipse\\ReservationSys\\testBin\\testFiles\\customer\\invalidCardNumber.txt";
		loadCustomerFilesTest("Case 17 - Invalid Card Number", path, false);
		
	}
	
	private static void loadCustomerFilesTest(String testCase, String path, boolean validation){
		Customer[] cust;
		
		System.out.println("\n" + testCase + " \nPath: " + path);
		
		try{
			cust = HotelFileLoader.getCustomerListFromSequentialFile(path);
			
			if(validation)
				for(int i = 0; i < cust.length; i++)
					System.out.println("RoomsFile " + i + ": " + cust[i]);
			
		}catch(IllegalArgumentException iae){
			System.out.println("ArgumentException: " + iae.getMessage() + " IllegalArgumentException");
			if(validation)
				System.out.println("ERROR: the given file should be Valid <--Fix It Please-->");
			
		}catch(IOException ioe){
			System.out.println("Error: " + ioe.getMessage() + " IOException");
			if(validation)
				System.out.println("ERROR: the given file should be Valid <--Fix It Please-->");
			
		}catch(Exception e){
			System.out.println("Error: " + " " + e.getMessage() + e.getClass()  + " This Error should be catched somewhere!");
			if(validation)
				System.out.println("ERROR: the given file should be Valid <--Fix It Please-->");
		}
		
	}
	
	/**
	 * @author Sevan Topalian
	 */
	private static void getReservationListFromSequentialFileTest() {
		System.out.println("\nTesting getReservationListFromSequentialFile");
		
		Customer[] customerArray = populateCustomerArray();
		Room[] roomArray = populateRoomArray();
		Reservation[] reservationArray = null;
		
		try {
			System.out.println("\nPopulating Reservation Array");
			reservationArray = HotelFileLoader.getReservationListFromSequentialFile("dcs317\\Eclipse\\ReservationSys\\datafiles\\reservations1.txt", customerArray, roomArray);
		} catch (IllegalArgumentException e) {
			System.out.println("Error! " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error! " + e.getMessage());
		}
		
		for(Reservation reservation : reservationArray){
			System.out.println("\nReservation created: " + reservation);
		}
		System.out.println("\nFinished populating Reservation array");
	}

	private static Customer[] populateCustomerArray(){
		String filename = "dcs317\\Eclipse\\ReservationSys\\datafiles\\customers1.txt";
		Customer[] customer = new Customer[2];

		Scanner inputStream = null;
		String record = null;

		BufferedReader outStream = null;
		try {
			outStream = new BufferedReader(
					new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8));
		} catch (FileNotFoundException e) {
			System.out.println("Error, file not found. " + e.getMessage());
		}

		inputStream = new Scanner(outStream);
		
		System.out.println("\nPopulating Customer array");

		int i = 0;

		while (inputStream.hasNext()) {
			record = inputStream.nextLine();
			String[] fields = record.split("\\*");

			customer[i] = new DawsonCustomer(fields[1], fields[2], fields[0]);
			System.out.println("\nCreated customer: " + customer[i]);

			i++;

			if (i >= customer.length) // resize
				customer = Arrays.copyOf(customer, customer.length * 2 + 1);
		} // end of the while loop

		// shrink
		customer = Arrays.copyOf(customer, i);

		// Close Scanner
		inputStream.close();
		
		System.out.println("\nFinished populating Customer array");
		
		return customer;
	}
	
	private static Room[] populateRoomArray() {
		String filename = "dcs317\\Eclipse\\ReservationSys\\datafiles\\rooms.txt";
		Room[] room = new Room[2];

		Scanner inputStream = null;
		String record = null;

		BufferedReader outStream = null;
		try {
			outStream = new BufferedReader(
					new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8));
		} catch (FileNotFoundException e) {
			System.out.println("Error, file not found. " + e.getMessage());
		}

		inputStream = new Scanner(outStream);
		
		System.out.println("\nPopulating Room array");

		int i = 0;

		while (inputStream.hasNext()) {
			record = inputStream.nextLine();
			String[] fields = record.split("\\*");

			room[i] = new DawsonRoom(Integer.parseInt(fields[0]), RoomType.valueOf(fields[1].toUpperCase()));
			System.out.println("\nCreated room: " + room[i]);

			i++;

			if (i >= room.length) // resize
				room = Arrays.copyOf(room, room.length * 2 + 1);
		} // end of the while loop

		// shrink
		room = Arrays.copyOf(room, i);

		// Close Scanner
		inputStream.close();
		
		System.out.println("\nFinished populating Room array");
		
		return room;
	}
}
