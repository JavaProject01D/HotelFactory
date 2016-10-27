package group5.hotel.business;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.business.RoomType;
import dw317.lib.creditcard.CreditCard;

/**
 * This main method will try all getters provided
 * by DawsonHotelFactorial and look for valid data
 * 
 * @author Denis Lebedev
 * @version 9/27/2016
 */

public class DawsonHotelFactoryTest {

	public static void main(String[] args) {

		hotelFactoryTest();
	}
	
	private static void hotelFactoryTest(){
		System.out.println("Testing my Hotel Factory: \n");
		
		System.out.println("_____________getCustomerInstance____________\n");
		
		getCustomerInstance("getCustomerInstance Case 1 - Valid Data" , "Jeo", "Catt'g", "JokesOnYou@gg", true);
		
		getCustomerInstance("getCustomerInstance Case 2 - Invalid Name" , "Je8o", "Catt'g", "JokesOnYou@gg", false);
		
		getCustomerInstance("getCustomerInstance Case 3 - Invalid Email" , "Jeo", "Catt'g", ".JokesOnYou@gg", false);
		
		System.out.println("\n_____________getCard______________\n");
		
		getCard("getCard Case 1 - Valid Data", "MasterCard", "5169370501271822", true);
		
		getCard("getCard Case 2 - Invalid CardType", "Joerge", "5169370501271822", false);
		
		getCard("getCard Case 1 - Invalid Card", "MasterCard", "516937050127182", false);
		
		System.out.println("\n_____________getRoomInstance______________\n");
		
		getRoomInstance("getRoomInstance Case 1 - Valid Data", 101, "Normal", true);
		
		getRoomInstance("getRoomInstance Case 2 - Invalid Room Type", 101, "", false);
		
		getRoomInstance("getRoomInstance Case 3 - Invalid Room Number", 1001, "Normal", false);
		
		System.out.println("\n_____________getReservationInstance______________\n");
		
		Customer cust = new DawsonCustomer("Jokes", "Caterpilla", "JokesOnYou@gmail.com");
		Room room = new DawsonRoom(101, RoomType.NORMAL);
		
		getReservationInstance("getReservationInstance Case 1 - Valid Data", cust, room, 2015, 10, 5, 2015, 12, 5, true);
		
		getReservationInstance("getReservationInstance Case 2 - Invalid CheckOut", cust, room, 2015, 10, 5, 2015, 112, 5, false);
		
		System.out.println("\n_____________getReservationInstance______________\n");
		
		getToCopy("toCopy getReservationInstance Case 1 - Valid Date ", cust, room, 2015, 10, 5, 2015, 12, 5, true);
	
		getToCopy("toCopy getReservationInstance Case 2 - Invalid Date ", cust, room, 2015, 10, 50, 2015, 12, 5, false);
		
		cust = new DawsonCustomer("Jo--kes", "Caterpilla", "JokesOnYou@gmail.com");
		getToCopy("toCopy getReservationInstance Case 3 - Invalid First Name ", cust, room, 2015, 10, 5, 2015, 12, 5, false);
	}
	
	private static void getCustomerInstance(String testCase, String fName, String lName, String email, boolean validation){
		DawsonHotelFactory dHF = DawsonHotelFactory.DAWSON;
		
		System.out.println("\n" + testCase);
		
		try{
			Customer cust = dHF.getCustomerInstance(fName, lName, email);
			System.out.println("Valid Data: \n" + "First Name: " + cust.getName().getFirstName() + " \tLast Name: " + cust.getName().getLastName() +
								"\tEmail: " + cust.getEmail().getAddress());
		
		if(validation)
			System.out.println("\n\tThe data is VALID");

		if(!validation)
			System.out.println("\tError the data SHOULD be INVALID please fix it. <===>FAILED TEST<===>\n");
		}
		catch (IllegalArgumentException iae){
			System.out.print("\n\t"+ iae.getMessage());
			if (!validation)
				System.out.println("\n\tError the Data should not be valid. <===>FAILED TEST<===>\n");
		}
		catch (Exception b){
			System.out.print("\tUNEXPECTED EXCEPTION TYPE! " + b.getClass() +  " "  + b.getMessage() + " ==== FAILED TEST ====\n");
			if (validation)
				System.out.print("It was expected VALID");
		}
		
		
			
	}

	private static void getCard(String testCase, String cardType, String number, boolean validation){
		DawsonHotelFactory dHF = DawsonHotelFactory.DAWSON;
		
		try{
			System.out.println(testCase);
			CreditCard card = dHF.getCard(cardType, number);
			
			System.out.println("Valid Data: \n" + "Card Type: " + card.getType().toString() + " \tNumber: " + card.getNumber());
		
		if(validation)
			System.out.println("\n\tThe current Data is VALID");

		if(!validation)
			System.out.println("\tError the Data SHOULD be INVALID please fix it. <===>FAILED TEST<===>\n");
		}
		catch (IllegalArgumentException iae){
			System.out.print("\n\t"+ iae.getMessage());
			if (!validation)
				System.out.println("\n\tError the Data should not be valid. <===>FAILED TEST<===>\n");
		}
		catch (Exception b){
			System.out.print("\tUNEXPECTED EXCEPTION TYPE! " + b.getClass() +  " "  + b.getMessage() + " ==== FAILED TEST ====\n");
			if (validation)
				System.out.print("It was expected VALID");
		}
		
		
	}
	
	private static void getRoomInstance(String testCase, int roomNumber, String roomType, boolean validation){
		DawsonHotelFactory dHF = DawsonHotelFactory.DAWSON;
		
		System.out.println("\n" + testCase);
		
		try{
			Room room = dHF.getRoomInstance(roomNumber, roomType);
			System.out.println("Valid Data: \n" + "Room Number (3 digits): " + room.getRoomNumber() + " \tRoom Type: " + room.getRoomType());
		
		if(validation)
			System.out.println("\n\tThe data is VALID");

		if(!validation)
			System.out.println("\tError the data SHOULD be INVALID please fix it. <===>FAILED TEST<===>\n");
		}
		catch (IllegalArgumentException iae){
			System.out.print("\n\t"+ iae.getMessage());
			if (!validation)
				System.out.println("\n\tError the Data should not be valid. <===>FAILED TEST<===>\n");
		}
		catch (Exception b){
			System.out.print("\tUNEXPECTED EXCEPTION TYPE! " + b.getClass() +  " "  + b.getMessage() + " ==== FAILED TEST ====\n");
			if (validation)
				System.out.print("It was expected VALID");
		}
	}
	
	private static void getReservationInstance(String testCase,Customer aCustomer, Room aRoom, int inYear, int inMonth, int inDay, 
												int outYear, int outMonth, int outDay, boolean validation){
		DawsonHotelFactory dHF = DawsonHotelFactory.DAWSON;
		
		System.out.println("\n" + testCase);
		
		try{
			Reservation reserve = dHF.getReservationInstance(aCustomer, aRoom, inYear, inMonth, inDay,
																outYear, outMonth, outDay);
		
		if(validation){
			System.out.println("\n\tThe data is VALID");
			System.out.println("\tToString: " + "\n\t---------------------\n\t" +
					reserve.toString() + "\n\t---------------------\n");
		}
			
		if(!validation)
			System.out.println("\tError the data SHOULD be INVALID please fix it. <===>FAILED TEST<===>\n");
		}
		catch (IllegalArgumentException iae){
			System.out.print("\n\t"+ iae.getMessage());
			if (!validation)
				System.out.println("\n\tError the Data should not be valid. <===>FAILED TEST<===>\n");
		}
		catch (Exception b){
			System.out.print("\tUNEXPECTED EXCEPTION TYPE! " + b.getClass() +  " "  + b.getMessage() + " ==== FAILED TEST ====\n");
			if (validation)
				System.out.print("It was expected VALID");
		}
	}
	
	private static void getToCopy(String testCase, Customer aCustomer, Room aRoom, int inYear, int inMonth, int inDay,
												int outYear, int outMonth, int outDay, boolean validation){
		DawsonHotelFactory dHF = DawsonHotelFactory.DAWSON;
		
		System.out.println("\n" + testCase);
		
		try{
			Reservation toCopy = new DawsonReservation(aCustomer, aRoom, inYear, inMonth, inDay, 
														outYear, outMonth, outDay);
			Reservation reserve = dHF.getReservationInstance(toCopy);

		if(validation)
			System.out.println("\n\tThe data is VALID");

		if(!validation)
			System.out.println("\tError the data SHOULD be INVALID please fix it. <===>FAILED TEST<===>\n");
		}
		catch (IllegalArgumentException iae){
			System.out.print("\n\t"+ iae.getMessage());
			if (!validation)
				System.out.println("\n\tError the Data should not be valid. <===>FAILED TEST<===>\n");
		}
		catch (Exception b){
			System.out.print("\tUNEXPECTED EXCEPTION TYPE! " + b.getClass() +  " "  + b.getMessage() + " ==== FAILED TEST ====\n");
			if (validation)
				System.out.print("It was expected VALID");
		}
	}
}
