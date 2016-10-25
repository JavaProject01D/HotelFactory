package group5.hotel.data;

import java.io.IOException;

import dw317.hotel.business.interfaces.Room;

public class HotelFileLoaderTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		loadFilesTest();
	}
	
	
	private static void loadFilesTest(){
		
		System.out.println("\n_______________Validate The Room Loading_________________\n");
		
		String path;
		path = "dcs317\\Eclipse\\ReservationSys\\datafiles\\rooms.txt";
		
		loadFilesTest("Case 1 - Valid rooms.txt", path, true);
		
		path = "dcs317\\Eclipse\\ReservationSys\\testbin\\testFiles\\invalidRoomNumber.txt";
		loadFilesTest("Case 2 - Invalid room file - Wrong Room number", path, false);
		
		path = "dcs317\\Eclipse\\ReservationSys\\testbin\\testFiles\\invalidRoomType.txt";
		loadFilesTest("Case 3 - Invalid room file - Wrong Type", path, false);
		
		path = "dcs317\\Eclipse\\ReservationSys\\testbin\\testFiles\\invalidRoomNull.txt";
		loadFilesTest("Case 4 - Invalid room file - Null Room Number", path, false);
		
		path = "dcs317\\Eclipse\\ReservationSys\\testbin\\testFiles\\invalidRoomNumberDouble.txt";
		loadFilesTest("Case 5 - Invalid room file - Double Room Number", path, false);
		
		path = "dcs317\\Eclipse\\ReservationSys\\testbin\\testFiles\\validRoomWithBlank.txt";
		loadFilesTest("Case 6 - Valid room file - Blank Line for the next Room", path, true);
	}
	
	private static void loadFilesTest(String testCase, String path, boolean validation){
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
			System.out.println("Error: " + e.getMessage());
			if(validation)
				System.out.println("ERROR: the given file should be Valid <--Fix It Please-->");
		}
		
		
		
	}

}
