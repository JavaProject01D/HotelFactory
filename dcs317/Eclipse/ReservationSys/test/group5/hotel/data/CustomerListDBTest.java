package group5.hotel.data;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.data.DuplicateCustomerException;
import dw317.hotel.data.NonExistingCustomerException;
import group5.hotel.business.DawsonCustomer;
import group5.hotel.business.DawsonHotelFactory;
import group5.util.ListUtilities;
import group5.util.Utilities;

public class CustomerListDBTest {
	

	public static void main(String[] args) {
		System.out.println("\t<------> TestAddCustomer <------>");
		testAddCustomer();
		System.out.println("\n\n\t<------> TestGetCustomer <------>");
		testGetCustomer();
		System.out.println("\n\n\t<------> TestUpdate <------>");
		testUpdate();
		System.out.println("\n\n\t<------> TestDisconect <------>");
		testDisconect();
	}

	public static void setup(){
		
		String[] rooms = new String[4];
		rooms[0] = "101*normal";
		rooms[1] = "102*normal";
		rooms[2] = "301*suite";
		rooms[3] = "401*penthouse";
		
		System.out.println("\nCreating instance of Valid and Invalid Customers: ");
		
		String[] custs = new String[8];
		custs[0] = "aaa@aaa.com*Da*Ja*amex*347964972957716";
		custs[1] = "bbbb@bbbb.me*Joe*Mancini**";
		custs[2] = "cccc@ccccc.me*Joe*Mancini**";
		custs[3] = "dddd@dddd.me*YouSeeMe*NowYouDont*mastercard*520189554761329";
		custs[4] = "eeeee@eeeee.me*Horibi*Mily**";
		custs[5] = "fffff@ffffff.me*Horibi*Mily*masterCard*5201895554761329";
		custs[6] = "ggggggg@gggggg.me*Horibi*Mily*visa*4532913138654408";
		


		String[] reservs = new String[8];
		reservs [0] = "raj@aing.ru*2016*9*10*2016*9*15*101";
		reservs [1] = "joe.mancini@mail.me*2016*10*10*2016*10*20*401";
		//...
		reservs [7] = "d@zzz.com*2016*10*12*2016*10*15*102";
		
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
	
	private static void testAddCustomer() {
		setup();
		ObjectSerializedList file = new ObjectSerializedList
				("testfiles/testCustomers.ser", "testfiles/testReservations.ser",
						"testfiles/testRooms.ser");
		CustomerListDB db = new CustomerListDB(file);
		
		System.out.println("_______________________________________________\n");
		
		System.out.println("\nLIST USED:");
		
		System.out.println(db.toString());
				
		Customer[] custToAdd = new DawsonCustomer[6];  
		String[] testCase = new String[6];

			testCase[0] = new String ("\nTest Case 1 -- Invalid Email Exist! --> ggggggg@gggggg.me");
			custToAdd[0] = new DawsonCustomer("Macho","Hoho","ggggggg@gggggg.me");
		
			testCase[1] = new String ("\nTest Case 2 -- Valid Email do not Exist! --> ALoveYou@AaaMe.me");
			custToAdd[1] = new DawsonCustomer("Macho","Hoho","ALoveYou@AaaMe.me");
			
			testCase[2] = new String ("\nTest Case 3 -- Valid Customer with a CreditCard! --> Babouche@DoNotContact.Me");
			custToAdd[2] = new DawsonCustomer("Macho","Hoho","Babouche@DoNotContact.Me");
			custToAdd[2].setCreditCard(Optional.of(
							DawsonHotelFactory.DAWSON.getCard("Amex", "340872454717316")));
			
			testCase[3] = new String ("\nTest Case 4 -- Adding Valid Email! --> Perfection@IsJava.com");
			custToAdd[3] = new DawsonCustomer("ImPerfect","Hoho","Perfection@IsJava.com");
			
			testCase[4] = new String ("\nTest Case 5 -- Invalid Email Exist! --> Babouche@DoNotContact.Me");
			custToAdd[4] = new DawsonCustomer("TestMe","YesSir","Babouche@DoNotContact.Me");
			
			testCase[5] = new String ("\nTest Case 6 -- Valid Email added with a CreditCard! --> ZaaLoop@ZzZ.Me");
			custToAdd[5] = new DawsonCustomer("TestMe","YesSir","ZaaLoop@ZzZ.Me");
			custToAdd[5].setCreditCard(Optional.of(
					DawsonHotelFactory.DAWSON.getCard("visa", "4532913138654408")));
			
			for(int i=0; i < custToAdd.length; i++){
				System.out.println(testCase[i]);
				
				try{
					db.add(custToAdd[i]);
					
					
					
				}catch(DuplicateCustomerException dce){
					System.out.println("DuplicateCustomer: " + dce.getMessage());
					continue;
				}catch(Exception e){
					System.out.println("<----HANDLE ME---> " + e.getMessage() + " <----HANDLE ME---> " );
					continue;
				}
				
			}
			System.out.println("\n<---> Added Item <--->");
			System.out.println(db.toString());
			
		teardownSerialization();
	}
	
	private static void testGetCustomer(){
		setup();
		ObjectSerializedList file = new ObjectSerializedList
				("testfiles/testCustomers.ser", "testfiles/testReservations.ser",
						"testfiles/testRooms.ser");
		CustomerListDB db = new CustomerListDB(file);
		
		System.out.println("_______________________________________________\n");
		
		System.out.println("\nLIST USED:");
		
		System.out.println(db.toString());
		
		Customer[] custToSearch = new DawsonCustomer[3];  
		String[] testCase = new String[3];

			testCase[0] = new String ("\nTest Case 1 -- Valid get Email Exist in the Database --> cccc@ccccc.me");
			custToSearch[0] = new DawsonCustomer("Joe","Mancini","cccc@ccccc.me");
			
			testCase[1] = new String ("\nTest Case 2 -- Invalid Email do not Exist in the Database --> Shaco@Love.me");
			custToSearch[1] = new DawsonCustomer("Macho","Hoho","Shaco@Love.me");		
			
			testCase[2] = new String ("\nTest Case 3 -- Invalid Email do not Exist in the Database! --> gg@gggggg.me");
			custToSearch[2] = new DawsonCustomer("Macho","Hoho","gg@gggggg.me");
		
		for(int i=0; i < custToSearch.length; i++)
		
			try{
				System.out.println(testCase[i]);
				db.getCustomer(custToSearch[i].getEmail());
				System.out.println("Searching for " + custToSearch[i].getEmail().getAddress() + "\tFound: " + db.getCustomer(custToSearch[i].getEmail()));
			}catch(NonExistingCustomerException nce){
				System.out.println("CustomerNotFound: " + nce.getMessage());
				continue;
			}catch(Exception e){
				System.out.println("<----HANDLE ME---> " + e.getMessage() + " <----HANDLE ME---> " );
				continue;
			}
		
		
		teardownSerialization();	
	}
	
	private static void testUpdate(){
		setup();
		ObjectSerializedList file = new ObjectSerializedList
				("testfiles/testCustomers.ser", "testfiles/testReservations.ser",
						"testfiles/testRooms.ser");
		CustomerListDB db = new CustomerListDB(file);
		
		System.out.println("_______________________________________________\n");
		
		System.out.println("\nLIST USED:");
		
		System.out.println(db.toString());
		
		Customer[] custToUpdate = new DawsonCustomer[3];  
		String[] testCase = new String[3];
		String[] cardHolder = new String[3];
		
		testCase[0] = new String ("\nTest Case 1 -- Invalid Email is not in the DataBase --> Shaco@Love.me");
		custToUpdate[0] = new DawsonCustomer("Joe","Mancini","Shaco@Love.me");
		cardHolder[0] = new String("masterCard*5201895554761329");
		
		testCase[1] = new String ("\nTest Case 2 -- Add a CreditCard to Empty customer--> bbbb@bbbb.me");
		custToUpdate[1] = new DawsonCustomer("Macho","Hoho","bbbb@bbbb.me");
		cardHolder[1] = new String("masterCard*5201895554761329");
		
		testCase[2] = new String ("\nTest Case 3 -- Change a CreditCard from a customer--> fffff@ffffff.me");
		custToUpdate[2] = new DawsonCustomer("Macho","Hoho","fffff@ffffff.me");
		cardHolder[2] = new String("visa*4532913138654408");
		
		for(int i=0; i < custToUpdate.length; i++)
	
			try{
				System.out.println(testCase[i]);
				db.update(custToUpdate[i].getEmail(), 
						DawsonHotelFactory.DAWSON.getCard(cardHolder[i].substring(0,cardHolder[i].indexOf("*")), 
												cardHolder[i].substring(cardHolder[i].indexOf("*")+1)));
				
				System.out.println("Searching for " + custToUpdate[i].getEmail().getAddress() + "\tFound: " + db.getCustomer(custToUpdate[i].getEmail()));
				
			}catch(NonExistingCustomerException nce){
				System.out.println("CustomerNotFound: " + nce.getMessage());
				continue;
			}catch(Exception e){
				System.out.println("<----HANDLE ME---> " + e.getMessage() + " <----HANDLE ME---> " );
				continue;
		}
		
		teardownSerialization();	
	}
	
	private static void testDisconect(){
		setup();
		ObjectSerializedList file = new ObjectSerializedList
				("testfiles/testCustomers.ser", "testfiles/testReservations.ser",
						"testfiles/testRooms.ser");
		CustomerListDB db = new CustomerListDB(file);
		
		System.out.println("_______________________________________________\n");
		
		System.out.println("\nLIST USED:");
		
		System.out.println(db.toString());
		
		Customer cust = new DawsonCustomer("Shaco","Onme","SHACO@Dcode.break");
		
		try{
			System.out.println("\nAdding a customer then Disconect: ");
			db.add(cust);
			
			System.out.println(db.toString());
			
			System.out.println("\nDisconect--->");
			db.disconnect();
		}catch(DuplicateCustomerException dce){
			System.out.println("DuplicateCustomerException: " + dce.getMessage());
		}catch(IOException ioe){
			System.out.println("IOE Exception: " + ioe.getMessage());
		}catch(Exception e){
			System.out.println("<----HANDLE ME---> " + e.getMessage() + " <----HANDLE ME---> " );
		}
		
		System.out.println("\nReconnect--->");
		CustomerListDB dbTwo = new CustomerListDB(file);
		
		System.out.println(dbTwo.toString());
		
		teardownSerialization();
	}
}






