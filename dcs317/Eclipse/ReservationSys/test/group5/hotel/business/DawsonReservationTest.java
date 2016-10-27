package group5.hotel.business;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.business.RoomType;

/**
 * The following code will try all the
 * overrided methods and ensure data
 * validation for different case
 * 
 * @author Denis Lebedev
 * @version 9/27/2016
 */

public class DawsonReservationTest {

	public static void main(String[] args) {
		
		validation();
		
		System.out.println("\n_______________Validate Methods_________________\n");
		
		validMethod();
	}
	
	private static void validation(){
		System.out.println("Creating different Case for DawsonReservation: \n");
		
		Room aRoom = new DawsonRoom(101, RoomType.NORMAL);
		
		validation("Case 1 - Valid Reservation", "Jean", "Batiste", "Hello@gmail.com", aRoom, 2015, 10, 24, 
				2016, 1, 5, true);
		
		validation("\nCase 2 - Invalid Date (inMonth: 100) ", "Jean", "Batiste", "Hello@gmail.com", aRoom, 2015, 100, 24, 
				2016, 1, 5, false);
		
		validation("\nCase 3 - Invalid Date (outDay: 125) ", "Jean", "Batiste", "Hello@gmail.com",aRoom, 2015, 10, 24, 
				2016, 1, 125, false);
		
		validation("\nCase 4 - Invalid Date (inYear: 00) ", "Jean", "Batiste", "Hello@gmail.com",aRoom, 00, 10, 24, 
				2016, 1, 125, false);
		
		validation("\nCase 5 - Invalid Year (checkIn is AFTER checkOut) ", "Jean", "Batiste", "Hello@gmail.com", aRoom, 2017, 10, 24, 
				2016, 1, 125, false);
		
		validation("\nCase 6 - Valid Date (checkIn is EQUAL to checkOut) ", "Jean", "Batiste", "Hello@gmail.com",aRoom, 2015, 10, 24, 
				2015, 10, 24, true);
		
		validation("\nCase 7 - Invalid Email (Email is wrong) ", "Jean", "Batiste", "$Hello@gmail.com",aRoom, 2015, 10, 24, 
				2016, 1, 5, true);
		
		
	}
	
	private static void validation(String testCase, String firstName, String lastName, String email, Room aRoom, 
			int inYear, int inMonth, int inDay, int outYear, int outMonth, int outDay,
			boolean validation){
		
		System.out.println(testCase + "\nFull Name: " + (firstName + " " + lastName) + "\nEmail: " + email +
							"\nRoomNumber: " + aRoom.getRoomNumber() + "\nRoomType: " + aRoom.getRoomType() +
							"\nInYear: " + inYear + "\tInMonth: " + inMonth + "\tInDay: " + inDay + 
							"\nOutYear: " + outYear + "\tOutMonth: " + outMonth + "\tOutDay: " + outDay + "\n");
		
		
		try{
			Customer aCustomer = new DawsonCustomer(firstName, lastName, email); 
			Reservation reserve = new DawsonReservation(aCustomer, aRoom, inYear, inMonth, inDay,
														outYear, outMonth, outDay);
			if(validation){
				System.out.println("\tThe parameters are valid");
				System.out.println("\tToString: " + "\n\t---------------------------\n" + reserve.toString() + "\n\t---------------------------\n");
			}
			
			if(!validation)
				System.out.println("\tError a paramater SHOULD be INVALID please fix it. <===>FAILED TEST<===>\n");
		}
		catch (IllegalArgumentException iae){
			System.out.println("\t"  + iae.getMessage());
			if(!validation)
				System.out.println("\tError the given parameter should not be valid. <===>FAILED TEST<===>\n");
			
		}
		catch (Exception e){
			System.out.println("\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() +  " " + e.getMessage() + " <===>FAILED TEST<===>");
			if(validation)
				System.out.println("It was expected VALID");
		}
	}	
		private static void validMethod(){
			Customer cust = new DawsonCustomer("Jauge", "GooFe", "Reach@Me.go");
			Room room = new DawsonRoom(104, RoomType.NORMAL);
			validMethod(cust, room);
			
			cust = new DawsonCustomer("Hello", "Bye", "hu-g@fds.f");
			room = new DawsonRoom(108, RoomType.NORMAL);
			validMethod(cust, room);
			
		
	}
		
		private static void validMethod(Customer cust, Room aRoom){
			
			Customer cust2 = new DawsonCustomer("Jauge", "GooFe", "Reach@Me.go");
			Room room2 = new DawsonRoom(104, RoomType.NORMAL);
			
			Reservation test = new DawsonReservation(cust, aRoom, 2015, 12, 8, 2016, 10, 8);
			
			Reservation test2 = new DawsonReservation(cust2, room2, 2015, 12, 8, 2016, 10, 8);
			
			System.out.println("\nTesting Equal Method: \n");
			if(test.equals(test2))
				System.out.println("Test is equal to Test2");
			else
				System.out.println("Test is not equal to Test2");
						
			System.out.println("\n______________CompareTo____________\n");
			
			System.out.println("CompareTo return: " + test.compareTo(test2));
			
		}
}
