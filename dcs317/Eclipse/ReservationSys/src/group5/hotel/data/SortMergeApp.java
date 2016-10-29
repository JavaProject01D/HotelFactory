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
 * This class will load and sort the room file and write the output to a textfile.
 * This class will also load, sort and merge the 10 original customer files individually.
 * After, it will completely merge all the customer files into one text file.
 * It will do the same to the reservations text files. 
 * 
 * @author Zahraa Horeibi
 */
public class SortMergeApp {
	/**
	 * This main method calls all three methods that each seperately do their own task like 
	 * sorting the rooms, customers and reservations.
	 * @author Zahraa Horeibi
	 * @param args
	 * @throws IOException
	 */

	public static void main (String[] args) throws IOException {
	
		// Calling all methods 
		sortedRooms();
		sortedCustomers();	
		sortedReservations();
		
	}
	/**
	 * This method sorts an array full of rooms based on their room numbers.
	 * @author Zahraa Horeibi
	 */
	private static void sortedRooms(){
		// Creating a directory called database with the following path only if it does not exist.
		File database = new File("dcs317/Eclipse/ReservationSys/datafiles/database");
		if(!database.exists())
			database.mkdir();
	
		Room[] rooms;
		// Creating the rooms text file
		File room = new File(database + "/" +"rooms.txt");

		try {
			rooms = HotelFileLoader.getRoomListFromSequentialFile("dcs317/Eclipse/ReservationSys/datafiles/rooms.txt");	
			//Sorting
			ListUtilities.sort(rooms);
			room.createNewFile();
			
			// Write sortedRooms array to the file 
			ListUtilities.saveListToTextFile(rooms,"dcs317/Eclipse/ReservationSys/datafiles/database/rooms.txt");
		} catch (IOException e) {	
			System.out.print("Error! Could not create file..\n" + e.getMessage());		
		}

	}

/**
 * This method sorts all the customer files individually and merges them all into one 
 * text file and sorts that file.
 * @author Zahraa Horeibi 
 * @throws IOException
 */
	@SuppressWarnings("rawtypes")
	private static void sortedCustomers() throws IOException {
		//Creating a directory called database with the following path only if it does not exist.
		File database = new File("dcs317/Eclipse/ReservationSys/datafiles/database");
		if(!database.exists())
			database.mkdir();

		// Created a new directory called sorted with the following path only if it exists.
		// it will store all the sorted text files.
		File sorted = new File ("dcs317/Eclipse/ReservationSys/datafiles/sorted");
		if (!sorted.exists())
			sorted.mkdir();
		// Creating a two-dimensional array which will hold all the references to the customers information
		Customer[][] customerList = new Customer[10][];

		// The following strings with hold all the possible file names 
		String customerFileName;
		String sortedCustFileName;
		try {
			// This for loop assigns the different number for the text file names
			for (int i = 0; i < customerList.length; i++){			
				customerFileName = "customers" + (i+1) + ".txt";
				customerList[i] = HotelFileLoader.getCustomerListFromSequentialFile("dcs317\\Eclipse\\ReservationSys\\datafiles\\" + customerFileName);				
			}
			// Sorting every text file individually 
			for(int i = 0; i < customerList.length ; i++){
				ListUtilities.sort(customerList[i]);
			}

			// Assigning the first customer text file list to the fully merged list initially
			Comparable[] fullyMergedList = customerList[0];

			
			int j =0;
			for(int i = 1; i < customerList.length ; i++){
				// Creating each sorted file with its specific name
				if (sorted.exists()) {
					sortedCustFileName = "sortedCustomers" + (j+1) + ".txt";
					j++;
					File cust = new File (sorted + "/" +  sortedCustFileName);
					cust.createNewFile();	

					// Write sorted arrays to their respective file					
					ListUtilities.saveListToTextFile(customerList[i], "dcs317/Eclipse/ReservationSys/datafiles/sorted" + "/" + sortedCustFileName);

					fullyMergedList = ListUtilities.merge(fullyMergedList, customerList[i], "duplicateCustomers.txt");
					// Creating the fully merged text file and writing the completely sorted list to it.
					File allCusts = new File (database + "/customers.txt");
					allCusts.createNewFile();
					ListUtilities.saveListToTextFile(fullyMergedList, "dcs317/Eclipse/ReservationSys/datafiles/database/customers.txt");			
				}											
			}

		}	
		catch(IOException ioe){
			System.out.print("Error! Could not create file..\n" + ioe.getMessage());
		}
	}
	
	/**
	 * This method sorts all the reservations files individually and merges them into one 
	 * text file and sorts that file.
	 * @author Zahraa Horeibi 
	 * @throws IOException
	 */

	
	private static void sortedReservations() {
		//Creating a directory called database with the following path only if it does not exist.
		File database = new File("dcs317/Eclipse/ReservationSys/datafiles/database");
		if(!database.exists())
			database.mkdir();

		// Created a new directory called sorted with the following path only if it exists.
		// it will store all the sorted text files.
		File sorted = new File ("dcs317/Eclipse/ReservationSys/datafiles/sorted");
		if (!sorted.exists())
			sorted.mkdir();
		
		// Creating a two-dimensional array which will hold all the references to the customers information
		Reservation[][] reservationList = new Reservation[10][];
		
		// The following strings with hold all the possible file names 
		String reservationFileName;
		String sortedResFileName;
		try {
			// Loading the fully loaded and sorted customer and room files
			Customer[] fullyLoadedCustomer = HotelFileLoader.getCustomerListFromSequentialFile("dcs317\\Eclipse\\ReservationSys\\datafiles\\database\\customers.txt");	
			Room[] sortedRooms = HotelFileLoader.getRoomListFromSequentialFile("dcs317\\Eclipse\\ReservationSys\\datafiles\\database\\rooms.txt");	
			for (int i = 0; i < reservationList.length; i++){			
				reservationFileName = "reservations" + (i+1) + ".txt";
				reservationList[i] = HotelFileLoader.getReservationListFromSequentialFile("dcs317\\Eclipse\\ReservationSys\\datafiles\\" + reservationFileName, fullyLoadedCustomer, sortedRooms);				
			}
	
			// Sorting every text file individually 
			for(int i = 0; i < reservationList.length ; i++){
				ListUtilities.sort(reservationList[i]);
			}
			// Assigning the first reservations text file list to the fully merged list initially
			Comparable[] reservations = reservationList[0];

			int j =0;
			for(int i = 1; i < reservationList.length ; i++){

				if (sorted.exists()) {
					// Creating each sorted file with its specific name
					sortedResFileName = "sortedCustomers" + (j+1) + ".txt";
					j++;
					File res = new File (sorted + "/" +  sortedResFileName);
					res.createNewFile();	

					// Write sortedCustomers array to the file					
					ListUtilities.saveListToTextFile(reservationList[i], "dcs317/Eclipse/ReservationSys/datafiles/sorted" + "/" + sortedResFileName);

					reservations = ListUtilities.merge(reservations, reservationList[i], "duplicateReservations.txt");
					
					// Creating the fully merged text file and writing the completely sorted list to it.
					File allRes = new File (database + "/reservations.txt");
					allRes.createNewFile();
					ListUtilities.saveListToTextFile(reservations, "dcs317/Eclipse/ReservationSys/datafiles/database/reservations.txt");
				}											
			}		
		}	
		catch(IOException ioe){
			System.out.print("Error! Could not create file..\n" + ioe.getMessage());
		}
	}
}
