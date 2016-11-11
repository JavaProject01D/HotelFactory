package group5.hotel.data;

import java.io.File;
import java.io.IOException;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;

import dw317.hotel.data.DuplicateReservationException;
import group5.hotel.business.DawsonCustomer;
import group5.hotel.business.DawsonReservation;
import group5.hotel.business.DawsonRoom;
import group5.util.ListUtilities;

public class ReservationListDBTest {

	public static void main(String[] args) {
		testAdd();

		
		
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
		custs [0] = "bob.b.y_lee@hotmail.ca*Bobby*Lee*mastercard*5458325441641567";
		custs [1] = "madoriHu@host.com*Humico*Madori*mastercard*5233382411178726";
		custs [2] = "Jean_Gero@gg.fr*Jean*Gerophar*visa*4929596474756407";
		custs [3] = "MyNameIsSheila@localhost*Sheila*Kaif*AMEX*374425782767815";
		custs [4] = "cuty.kathy.2008@yahoo.ca*Kathy*Perry**";
		custs [5] = "robert.is-awesome@mail.me*Robert*Siri*Visa*4485011762777210";
		custs [6] = "john.bussiness@gmail.ca*Johny-Laurence*Smith**";
		custs [7] = "Umila-Gangee@local.ca*Umila*Gangee**";
		
		

		String[] reservs = new String[8];	
		reservs [0] = "bob.b.y_lee@hotmail.ca*2016*8*30*2016*12*25*206";
		reservs [1] = "madoriHu@host.com*2016*10*26*2016*12*30*401";
		reservs [2] = "Jean_Gero@gg.fr*2016*11*28*2017*2*25*406";
		reservs [3] = "MyNameIsSheila@localhost*2017*5*9*2017*6*1*503";
		reservs [4] = "cuty.kathy.2008@yahoo.ca*2016*12*25*2017*1*1*601";
		reservs [5] = "robert.is-awesome@mail.me*2017*1*1*2018*1*1*704";
		reservs [6] = "john.bussiness@gmail.ca*2016*9*20*2016*9*26*801";
		reservs [7] = "Umila-Gangee@local.ca*2017*5*5*2017*7*8*801";
		
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
		
		String[] testcase = new String[6];
	
		Reservation[] resToAdd = new DawsonReservation[6];

		testcase[0] = " Case 1: Valid Data";
		resToAdd[0] = new DawsonReservation(new DawsonCustomer("Habiba", "Awada", "habiba_awad@hotmail.com"), new DawsonRoom(105, RoomType.NORMAL), 2008, 10, 5, 2010, 10, 5);
				
		testcase[1] = " Case 2: Invalid Data: Customer already in list";
		resToAdd[1] = new DawsonReservation(new DawsonCustomer("Humico", "Madori", "madoriHu@host.com"), new DawsonRoom(401, RoomType.NORMAL), 2016, 10, 26, 2016, 12, 30);

		for(int i=0; i < resToAdd.length; i++){
			System.out.println(testcase[i]);
			
			try{
				db.add(resToAdd[i]);				
			}catch(DuplicateReservationException dce){
				System.out.println("DuplicateReservation: " + dce.getMessage());
				continue;
			}catch(Exception e){
				System.out.println("<----HANDLE ME---> " + e.getMessage() + " <----HANDLE ME---> " );
				continue;
			}
			
		}
		System.out.println("\n==== List ====");
		System.out.println(db.toString());
		
	teardown();
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	

	//new ReservationListDB(this.listPersistenceObject, this.factory);
	
	
	/*private static void testToString() {
		setup();
	}
	*/
	
	
	
	
	
	

}