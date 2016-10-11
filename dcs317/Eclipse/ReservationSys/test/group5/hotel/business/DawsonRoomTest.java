package group5.hotel.business;

import dw317.hotel.business.interfaces.Room;
import dw317.hotel.business.interfaces.RoomType;


/**
 * Test class of the DawsonRoom class containing different test cases.
 * @author Zahraa Horeibi
 * @version 9/27/2016
 */
public class DawsonRoomTest {

	public static void main(String[] args) {

		testTheTwoParameterConstructor();
		testTheEqualsMethod();
		testTheHashCode();
		 testTheCompareTo();
		
	}
	
	public static void testTheTwoParameterConstructor() {
		
		System.out.println("********* Testing the two parameter constructor *********\n");
		testTheTwoParameterConstructor("Case 1 - Valid Data (107 Normal)", 107, RoomType.NORMAL, true);
		testTheTwoParameterConstructor("Case 2 - Invalid Data: Invalid Room Number (Normal) ", 010, RoomType.NORMAL , false);
		testTheTwoParameterConstructor("Case 3 - Invalid Data: Room Type set to null (107 null) ", 107, null, false);
		testTheTwoParameterConstructor("Case 4 - Valid Data (704 Suite)", 704, RoomType.SUITE, true); 
		testTheTwoParameterConstructor("Case 5 - Invalid Data: Invalid Room Number (Suite) ", 001, RoomType.SUITE , false);
		testTheTwoParameterConstructor("Case 6 - Invalid Data: Room Type set to null (704 null) ", 704, null, false);
		testTheTwoParameterConstructor("Case 7 - Valid Data (801 penthouse)", 801, RoomType.PENTHOUSE, true); 
		testTheTwoParameterConstructor("Case 8 - Invalid Data: Invalid Room Number (Penthouse) ", 000, RoomType.PENTHOUSE, false);
		testTheTwoParameterConstructor("Case 9 - Invalid Data: Room Type set to null (801 null) ", 801, null, false);
	
	}
	public static void testTheTwoParameterConstructor( String testCase, int roomNumber, RoomType roomType, boolean expectValid ) 	{
	
	System.out.println("   " + testCase);
	
	try { 

		DawsonRoom room = new DawsonRoom(roomNumber, roomType);
	
		
		System.out.println("\tThe Dawson Room was created: " + room.toString() );
		
		if(expectValid)
			System.out.println("\t\tThe Dawson Room is Valid!");

		if (!expectValid)
			System.out.println("\n\tError! The Dawson Room should not be valid. == FAILED Test ==");
	}
	
	catch ( IllegalArgumentException iae ) {
		System.out.print("\t" + iae.getMessage());
		
		if(!expectValid)
			System.out.println("\n\tError! The Dawson Room is not be valid == FAILED Test ==");
		
	}
	catch (Exception e) {
		System.out.print("\tUnexpected Exception Type" + e.getClass() +  "\t\t\n\t" + e.getMessage() + " == FAILED Test ==");
		if (expectValid)
			System.out.print(" Expected Valid.");
	}
	
	System.out.println("\n");
	
}
	
	public static void testTheEqualsMethod() {
		
		System.out.println("********* Testing the equals method. ********** \n");
		Room room = new DawsonRoom(801, RoomType.PENTHOUSE);
		testTheEqualsMethod("Case 1 - Valid Data", room, 801, RoomType.PENTHOUSE);
		testTheEqualsMethod("Case 2 - Invalid Data", room, 107, RoomType.NORMAL);
		
		
	}
	
	public static void testTheEqualsMethod ( String testCase, Room room, int roomNumber, RoomType roomType) {
	
		try {
			
			System.out.println("   " + testCase);
			Room createdRoom = new DawsonRoom(roomNumber, roomType);
			System.out.println("\tComparing " + room.toString() + " with " + createdRoom.toString() );
			
			if (room.equals(createdRoom) == true) 
				System.out.println("\t\tThe given rooms are the same.The equals method is valid!");
			
			if (room.equals(createdRoom) == false) 
				System.out.println(" \t\tThe given rooms are not the same. The equals method is valid!");
			
			}
			catch ( IllegalArgumentException iae ) {
				System.out.print("\t" + iae.getMessage());

			}
			catch (Exception e) {
				System.out.print("\tUnexpected Exception Type" + e.getClass() +  "\t\t\n\t" + e.getMessage() + " == FAILED Test ==");

			}
			
			System.out.println("\n");
			
		
	}
	public static void testTheHashCode() {
		
		System.out.println("********** Testing the hashCode method **********\n");
		
		Room room = new DawsonRoom(801, RoomType.PENTHOUSE);
		Room room2 = new DawsonRoom(801, RoomType.PENTHOUSE);
		
		System.out.println("Hashcode of first card: " + room.hashCode());
		System.out.println("Hashcode of second card: " + room2.hashCode());
				
				if ( room.hashCode() == room2.hashCode() ) {
					System.out.println("\nThe hashcodes are the same, the hashcode method is valid!\n");
				}
	}
	
	public static void testTheCompareTo() {
		
		System.out.println("********** Testing the CompareTo method **********\n");
		
		Room room = new DawsonRoom(801, RoomType.PENTHOUSE);
		Room room2 = new DawsonRoom(801, RoomType.PENTHOUSE);
		
			System.out.println("Room 1: " + room.toString());
			System.out.println("Room 2: " + room2.toString());
			
			System.out.println("\n Comparing result: " + room.compareTo(room2));
		
	}
	

}
