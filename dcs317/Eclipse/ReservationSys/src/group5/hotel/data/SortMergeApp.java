package group5.hotel.data;
import java.io.File;
import java.io.IOException;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import group5.hotel.data.HotelFileLoader;
import group5.util.ListUtilities;

import java.util.Comparator;

import dw317.hotel.business.interfaces.Room;

/**
 * @author Zahraa
 *
 */
public class SortMergeApp {
	
	public static void main (String[] args) {
		
		File sorted = new File ("datafiles");
		sorted.mkdir();
		//Denis
		//Customers
		Customer[] customers;
		Customer[][] customerList;
	
		int k = 0;
		
		for (int i = 1; i < 11; i++){
			
			String customerFileName = "customers" + i + ".txt";
			String sortedCustFileName = "sortedCustomers" + i + ".txt";
			
		try {
			
			customers = HotelFileLoader.getCustomerListFromSequentialFile(customerFileName);
			//Sorting
			ListUtilities.sort(customers);
			customerList[k] = customers;
			k++;
			} catch (IOException e) {
				
			System.out.println(e.getMessage()); //??
			
			}
		
		if (sorted.exists()) 
			
			try {
				
				File room = new File (sorted + sortedCustFileName);
				//Write sortedCustomers array to the file
				ListUtilities.saveListToTextFile(customerList[i], sortedCustFileName);
				
				} catch (IOException e) {	
					
				System.out.print("Could not create file.." + e.getMessage());	
				
				}
	
		} 
		
		Reservation[] reservations;

		for (int i = 1; i <= 10; i++){
			
			String resFileName = "reservations" + i + ".txt";
			String sortedResFileName = "sortedReservations" + i + ".txt";
			
		try {
			
		//	reservations = HotelFileLoader.getReservationListFromSequentialFile(String filename, Customer[] customerList,
		//			Room[] roomList);
			
			} catch (IOException e) {
				
			System.out.println(e.getMessage()); //??
			
			}
		
		if (sorted.exists()) 
			
			try {
				
				File room = new File (sorted + sortedResFileName);
				
				} catch (IOException e) {	
					
				System.out.print("Could not create file.." + e.getMessage());	
				
				}
	
		} 
			
		
	} // end of main method
	
	public static void sortedRooms(){
		
		File database = new File ("datafiles");
		database.mkdir();
		//Rooms
		Room[] rooms;
		
		try {
			rooms = HotelFileLoader.getRoomListFromSequentialFile("rooms.txt");	
			//Sorting
			ListUtilities.sort(rooms);
			
			} catch (IOException e) {
			System.out.println(e.getMessage()); //??
		}
	
		if (database.exists()) 
			try {
				rooms = HotelFileLoader.getRoomListFromSequentialFile("rooms.txt");	

				File room = new File (database + "/rooms.txt");
				// Write sortedRooms array to the file 
				ListUtilities.saveListToTextFile(rooms, "rooms.txt");
				
				} catch (IOException e) {	
					
				System.out.print("Could not create file.." + e.getMessage());		
				
				}
		
		
		
		
		
		File sorted = new File ("datafiles");
		sorted.mkdir();
		
		
	}
	
	
	
	
	
	
	
	
}
