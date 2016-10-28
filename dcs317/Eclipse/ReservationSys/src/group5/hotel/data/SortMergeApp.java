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

	public static void main (String[] args) throws IOException {
	
		Room[] sortedRooms = sortedRooms();
		Customer[] fullySortedCustomers = sortedCustomers();
		
	//	System.out.println(sortedRooms[3]);
	//	System.out.println(fullySortedCustomers[3]);
		
		sortedReservations(fullySortedCustomers, sortedRooms);
	}

	public static Room[] sortedRooms(){

		File database = new File("dcs317/Eclipse/ReservationSys/datafiles/database");
		if(!database.exists())
			database.mkdir();
		//Rooms
		Room[] rooms;
		File room = new File(database + "/" +"rooms.txt");

		try {
			rooms = HotelFileLoader.getRoomListFromSequentialFile("dcs317/Eclipse/ReservationSys/datafiles/rooms.txt");	

			//Sorting 
			ListUtilities.sort(rooms);
			room.createNewFile();

			
			// Write sortedRooms array to the file 
			ListUtilities.saveListToTextFile(rooms,"dcs317/Eclipse/ReservationSys/datafiles/database/rooms.txt");

			return rooms;
			
		} catch (IOException e) {	

			System.out.print("Could not create file..\n" + e.getMessage());		

		}
		
		return null;
	}


	@SuppressWarnings("rawtypes")
	public static Customer[] sortedCustomers() throws IOException {

		File database = new File("dcs317/Eclipse/ReservationSys/datafiles/database");
		if(!database.exists())
			database.mkdir();

		File sorted = new File ("dcs317/Eclipse/ReservationSys/datafiles/sorted");
		if (!sorted.exists())
			sorted.mkdir();

		Customer[][] customerList = new Customer[10][];

		String customerFileName;
		String sortedCustFileName;
		try {
			for (int i = 0; i < customerList.length; i++){			
				customerFileName = "customers" + (i+1) + ".txt";
				customerList[i] = HotelFileLoader.getCustomerListFromSequentialFile("dcs317\\Eclipse\\ReservationSys\\datafiles\\" + customerFileName);				
			}
	
			for(int i = 0; i < customerList.length ; i++){
				ListUtilities.sort(customerList[i]);
			}

			Comparable[] fullyMergedList = customerList[0];

			int j =0;
			for(int i = 1; i < customerList.length ; i++){

				if (sorted.exists()) {

					sortedCustFileName = "sortedCustomers" + (j+1) + ".txt";
					j++;
					File cust = new File (sorted + "/" +  sortedCustFileName);
					cust.createNewFile();	

					// Write sortedCustomers array to the file					
					ListUtilities.saveListToTextFile(customerList[i], "dcs317/Eclipse/ReservationSys/datafiles/sorted" + "/" + sortedCustFileName);

					fullyMergedList = ListUtilities.merge(fullyMergedList, customerList[i], "duplicateCustomers.txt");

					File allCusts = new File (database + "/customers.txt");
					allCusts.createNewFile();
					ListUtilities.saveListToTextFile(fullyMergedList, "dcs317/Eclipse/ReservationSys/datafiles/database/customers.txt");
				
				
				}											
			}
			
			return (Customer[]) fullyMergedList;
		}	
		catch(IOException ioe){

		}

		return null;
	}

	public static void sortedReservations(Customer[] customerList, Room[] roomList) {


		File database = new File("dcs317/Eclipse/ReservationSys/datafiles/database");
		if(!database.exists())
			database.mkdir();

		File sorted = new File ("dcs317/Eclipse/ReservationSys/datafiles/sorted");
		if (!sorted.exists())
			sorted.mkdir();

		Reservation[][] reservationList = new Reservation[10][];

		String reservationFileName;
		String sortedResFileName;
		try {
			for (int i = 0; i < reservationList.length; i++){			
				reservationFileName = "reservations" + (i+1) + ".txt";
				reservationList[i] = HotelFileLoader.getReservationListFromSequentialFile("dcs317\\Eclipse\\ReservationSys\\datafiles\\" + reservationFileName, customerList, roomList);				
			}
	
			for(int i = 0; i < reservationList.length ; i++){
				ListUtilities.sort(reservationList[i]);
			}

			Comparable[] list = reservationList[0];

			int j =0;
			for(int i = 1; i < customerList.length ; i++){

				if (sorted.exists()) {

					sortedResFileName = "sortedCustomers" + (j+1) + ".txt";
					j++;
					File res = new File (sorted + "/" +  sortedResFileName);
					res.createNewFile();	

					// Write sortedCustomers array to the file					
					ListUtilities.saveListToTextFile(reservationList[i], "dcs317/Eclipse/ReservationSys/datafiles/sorted" + "/" + sortedResFileName);

					list = ListUtilities.merge(list, reservationList[i], "duplicateReservations.txt");

					File allRes = new File (database + "/reservations.txt");
					allRes.createNewFile();
					ListUtilities.saveListToTextFile(list, "dcs317/Eclipse/ReservationSys/datafiles/database/reservations.txt");
				}											
			}		
		}	
		catch(IOException ioe){

		}
	}
		//NOT SURE 
	//	File sorted = new File ("dcs317/Eclipse/ReservationSys/datafiles");
	// sorted.mkdir();

		/*Reservation[] reservations;
		Reservation[][] reservationList = new Reservation[10][];

		int k = 0;
		int index = 2;

		for (int i = 1; i < 11; i++){

			String resFileName = "reservations" + i + ".txt";
			String sortedResFileName = "sortedReservations" + i + ".txt";

			try {

				reservations = HotelFileLoader.getReservationListFromSequentialFile("dcs317\\Eclipse\\ReservationSys\\datafiles\\" + resFileName, customerList, roomList);

				//Sorting
				ListUtilities.sort(reservations);
				reservationList[k] = reservations;
				k++;

				Comparable[] temp = ListUtilities.merge(reservationList[0], reservationList[1], "duplicateReservations.txt");
				ListUtilities.merge(temp,reservationList[index], "duplicateReservations.txt");
				index++;		



				if (sorted.exists()) {
					reservations = HotelFileLoader.getReservationListFromSequentialFile("dcs317\\Eclipse\\ReservationSys\\datafiles\\" + resFileName, customerList, roomList);
					File cust = new File ("dcs317/Eclipse/ReservationSys/datafiles/database/sorted" + sortedResFileName);
					//Write sortedCustomers array to the file
					ListUtilities.saveListToTextFile(reservationList[i], sortedResFileName);

					File allReservations = new File ("dcs317/Eclipse/ReservationSys/datafiles/database/reservations.txt");

				}					

			} catch (IOException e) {	

				System.out.print("Could not create file.." + e.getMessage());	
			}

		} 

	} */	
}

