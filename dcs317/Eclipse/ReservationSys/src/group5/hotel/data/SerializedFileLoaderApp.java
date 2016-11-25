package group5.hotel.data;

import java.io.IOException;
import java.util.List;

import group5.util.Utilities;

public class SerializedFileLoaderApp {
	
	public static void main (String[] args) throws IOException {
		SequentialTextFileList sequentialFile = 
			new SequentialTextFileList("dcs317\\Eclipse\\ReservationSys\\datafiles\\database\\rooms.txt",
									   "dcs317\\Eclipse\\ReservationSys\\datafiles\\database\\customers.txt", 
									   "dcs317\\Eclipse\\ReservationSys\\datafiles\\database\\reservations.txt");
		
		String roomFile = "dcs317\\Eclipse\\ReservationSys\\datafiles\\database\\rooms.ser";
		String customerFile = "dcs317\\Eclipse\\ReservationSys\\datafiles\\database\\customers.ser";
		String reservFile = "dcs317\\Eclipse\\ReservationSys\\datafiles\\database\\reservations.ser" ;
		
		saveRoomObject(sequentialFile, roomFile);
		saveCustObject(sequentialFile, customerFile);
		saveReservObject(sequentialFile, reservFile);
		
		
		
		
	}
	
	private static void saveRoomObject(SequentialTextFileList sequentialFile, String path){
		
		try{
			Utilities.serializeObject(sequentialFile.getRoomDatabase(),path);
		}catch(IOException ioe){
			System.out.println("IOE Exception has been thrown: " + ioe.getMessage());
		}
	}
	
	private static void saveCustObject(SequentialTextFileList sequentialFile, String path){
		
		try{
			Utilities.serializeObject(sequentialFile.getCustomerDatabase(),path);
		}catch(IOException ioe){
			System.out.println("IOE Exception has been thrown: " + ioe.getMessage());
		}
	}
	
	private static void saveReservObject(SequentialTextFileList sequentialFile, String path){
		
		try{
			Utilities.serializeObject(sequentialFile.getReservationDatabase(),path);
		}catch(IOException ioe){
			System.out.println("IOE Exception has been thrown: " + ioe.getMessage());
		}
	}
}
