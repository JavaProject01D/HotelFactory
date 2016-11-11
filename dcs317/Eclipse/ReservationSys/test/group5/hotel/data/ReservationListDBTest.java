package group5.hotel.data;

import java.io.File;
import java.io.IOException;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import group5.hotel.business.DawsonCustomer;
import group5.hotel.business.DawsonReservation;
import group5.util.ListUtilities;

public class ReservationListDBTest {

	public static void main(String[] args) {
		testAdd();

		
		
	}
	
	private static void setup() {
		
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
		reservs [1] = "meow@cats.com*2017*5*20*2015*4*20*101";
		reservs [2] = "weee@double_u.com*2015*6*10*2016*7*19*305";
		reservs [3] = "nothefeds@fbi.gov*1979*1*1*2016*9*6*404";
		reservs [4] = "food@fedora.com*2010*5*20*2011*4*20*208";		
		reservs [5] = "joe.mancini@mail.me*2016*10*10*2016*10*20*401";
		reservs [6] = "flowerpower2@peace.net*2016*10*11*2016*11*2*704";	
		reservs [7] = "yas@yahoo.com*2018*5*20*2019*4*20*801";
		
		
		
		File dir = new File("testfiles");
		try{
			if (!dir.exists()){  
				dir.mkdirs();
			}
			ListUtilities.saveListToTextFile(rooms, 
					"testfiles/testRooms.txt");
			ListUtilities.saveListToTextFile(custs, 
					"testfiles/testCustomers.txt");
			ListUtilities.saveListToTextFile(reservs, 
					"testfiles/testReservations.txt");
		}
		catch(IOException io){
			System.out.println
			("Error creating file in setUp()");
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
		SequentialTextFileList file = new SequentialTextFileList
				("testfiles/testRooms.txt", "testfiles/testCustomers.txt",
						"testfiles/testReservations.txt");
		ReservationListDB db = new ReservationListDB(file);
		
		System.out.println(db.toString());
		
		//ListPersistentObject
		
		Reservation[] resToAdd = new DawsonReservation[6];
		String[] testcase = new String[6];
		Customer someCust = new DawsonCustomer("Igor","Chekovski", "darkestSoul@mail.ru");
	
		//new ReservationListDB(this.listPersistenceObject, this.factory);
		
		testcase[0] = " Case 1: Invalid room number";
		//resToAdd[0] = new DawsonReservation(someCust, , 1979,1,1, 2016,9,6);

		
	}
	
	
	
	
	/*private static void testToString() {
		setup();
	}
	*/
	
	
	
	
	
	

}
