package group5.hotel.data;

import java.io.File;
import java.io.IOException;
import java.util.List;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.data.DuplicateCustomerException;
import group5.hotel.business.DawsonCustomer;
import group5.util.ListUtilities;

public class CustomerListDBTest {
	

	public static void main(String[] args) {
		testAddCustomer();
		
	}

	public static void setup(){
	
		String[] rooms = new String[4];
		rooms[0] = "101*normal";
		rooms[1] = "102*normal";
		rooms[2] = "301*suite";
		rooms[3] = "401*penthouse";
		
		/*String[] custs = new String[8];
		//Invalid Card
		custs [0] = "d@zzz.com*Da*Ja*amex*347964972957716";
		//Same name but different email
		custs [1] = "Horibi@mail.me*Joe*Mancini**";
		//Valid Customer instance
		custs [2] = "joe.mancini@mail.me*Joe*Mancini**";
		//Invalid Master Card
		custs [3] = "JokesOn@You.haha*YouSeeMe*NowYouDont*mastercard*520189554761329";
		//Invalid Name
		custs [4] = "madeBySir@gmail.com*ImIll3gal*Ohhhhhhh**";
		//Valid Customer instance		
		custs [5] = "MyLing@Chi.me*Horibi*Mily**";
		//Valid Customer instance
		custs [6] = "Macho@hostMe*JokesOnYou*Shaco**";
		//Valid Customer instance
		custs [7] = "raj@aing.ru*Raj*Wong*visa*4556737586899855";*/
		
		String[] custs = new String[8];
		custs[0] = "aaa@aaa.com*Da*Ja*amex*347964972957716";
		custs[1] = "bbbb@bbbb.me*Joe*Mancini**";
		custs[2] = "cccc@ccccc.me*Joe*Mancini**";
		custs[3] = "dddd@dddd.me*YouSeeMe*NowYouDont*mastercard*520189554761329";
		custs[4] = "eeeee@eeeee.me*Horibi*Mily**";
		custs[5] = "fffff@ffffff.me*Horibi*Mily**";
		custs[6] = "ggggggg@gggggg.me*Horibi*Mily**";
		


		String[] reservs = new String[8];
		reservs [0] = "raj@aing.ru*2016*9*10*2016*9*15*101";
		reservs [1] = "joe.mancini@mail.me*2016*10*10*2016*10*20*401";
		//...
		reservs [7] = "d@zzz.com*2016*10*12*2016*10*15*102";
		
		
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
	
	
	private static void testAddCustomer() {
		setup();
		SequentialTextFileList file = new SequentialTextFileList
				("testfiles/testRooms.txt", "testfiles/testCustomers.txt",
						"testfiles/testReservations.txt");
		CustomerListDB db = new CustomerListDB(file);
		
		System.out.println(db.toString());
		
		System.out.println("_______________________________________________\n");
		
		//List<Customer> listC;
		//Customer does not EXIST
		Customer custToAdd = new DawsonCustomer("Macho","Hoho","ggggggg@gggggg.me");
		try{
		
			db.add(custToAdd);
		}catch(DuplicateCustomerException dce){
			System.out.println("DuplicateCustomer: " + dce.getMessage());
			
		}catch(Exception e){
			System.out.println("<----HANDLE ME---> " + e.getMessage() + " <----HANDLE ME---> " );
		}
		
		teardown();
	}
}






