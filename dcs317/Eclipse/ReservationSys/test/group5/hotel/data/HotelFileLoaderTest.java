package group5.hotel.data;

import java.io.IOException;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Room;


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

}
