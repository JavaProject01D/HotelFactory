package group5.hotel.data;

import java.io.IOException;

import dw317.hotel.business.interfaces.Room;

public class HotelFileLoaderTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		loadFilesTest();
	}
	
	
	private static void loadFilesTest(){
		loadFilesTest("k");
	}
	
	private static void loadFilesTest(String testCase){
		Room[] rooms;
		try{
			rooms = HotelFileLoader.getRoomListFromSequentialFile("dcs317\\Eclipse\\ReservationSys\\datafiles\\rooms.txt");
			
			for(int i = 0; i < rooms.length; i++)
				System.out.println("RoomsFile " + i + ": " + rooms[i]);
			
			
		}catch(NumberFormatException nfe){
			System.out.println("FormatException: " + nfe.getMessage());
			
		}catch(IllegalArgumentException iae){
			System.out.println("ArgumentException: " + iae.getMessage());
			
		}catch(IOException ioe){
			System.out.println("Error: " + ioe.getMessage());
		}
		
		
		
	}

}
