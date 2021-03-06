package group5.hotel.data;

import java.io.File;
import java.io.IOException;
import java.util.List;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Room;
import group5.util.ListUtilities;
import group5.util.Utilities;

/**
 * The code was given by Jaya.
 * 
 * @author Teacher
 *
 */
public class RoomListDBTest {

	public static void main(String[] args) {
		testGetRooms();
		testToString();
	}
	
	private static void setup()
	{
		String[] rooms = new String[4];
		rooms[0] = "101*normal";
		rooms[1] = "102*normal";
		rooms[2] = "301*suite";
		rooms[3] = "401*penthouse";
		
		String[] custs = new String[8];
		custs [0] = "raj@aing.ru*Raj*Wong*visa*4556737586899855";
		custs [1] = "joe.mancini@mail.me*Joe*Mancini**";
		//...
		custs [7] = "d@zzz.com*Da*Ja*amex*347964972957716";


		String[] reservs = new String[8];
		reservs [0] = "raj@aing.ru*2016*9*10*2016*9*15*101";
		reservs [1] = "joe.mancini@mail.me*2016*10*10*2016*10*20*101";
		//...
		reservs [7] = "d@zzz.com*2017*10*12*2017*10*15*401";
		
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
		}
		catch(IOException io){
			System.out.println
			("Error creating file in setUp()");
		}
		
		teardown();
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

	private static void testGetRooms() {
		setup();
		ObjectSerializedList file = new ObjectSerializedList
				("testfiles/testCustomers.ser", "testfiles/testReservations.ser",
						"testfiles/testRooms.ser");
		RoomListDB db = new RoomListDB(file);
		
		System.out.println("Normal rooms:");
		List<Room> normal = db.getRooms(RoomType.NORMAL);
		for (Room r : normal)
			System.out.println(r.toString());			

		System.out.println("Suites:");
		List<Room> suite = db.getRooms(RoomType.SUITE);
		for (Room r : suite)
			System.out.println(r.toString());
		
		System.out.println("Penthouse rooms:");
		List<Room> ph = db.getRooms(RoomType.PENTHOUSE);
		for (Room r : ph)
			System.out.println(r.toString());
		
		teardownSerialization();
	}

	private static void testToString(){
		setup();
		ObjectSerializedList file = new ObjectSerializedList
				("testfiles/testCustomers.ser", "testfiles/testReservations.ser",
						"testfiles/testRooms.ser");
		RoomListDB db = new RoomListDB(file);
		
		System.out.println(db.toString());

		teardownSerialization();
	}

}