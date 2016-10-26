package group5.hotel.data;
import java.io.File;
import java.io.IOException;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import group5.hotel.data.HotelFileLoader;
import group5.util.ListUtilities;

import java.util.Comparator;

import dw317.hotel.business.interfaces.Room;
//me
/**
 * @author Zahraa
 *
 */
public class SortMergeApp {
	
	public static void main (String[] args) {
		
		sortedRooms();
	//	sortedCustomers() ;
		
	
	}

	public static void sortedRooms(){
		
		File database = new File("dcs317/Eclipse/ReservationSys/datafiles");
		database.mkdir();
		//Rooms
		Room[] rooms;
		
		try {
			rooms = HotelFileLoader.getRoomListFromSequentialFile("dcs317\\Eclipse\\ReservationSys\\datafiles\\rooms.txt");	
			//Sorting
			//do wrapper method and return Integer and do sorting on that 
			ListUtilities.sort(rooms);
			wrapper(rooms);
			if (database.exists()) {
				
				rooms = HotelFileLoader.getRoomListFromSequentialFile("dcs317\\Eclipse\\ReservationSys\\datafiles\\rooms.txt");		
				
				File room = new File("dcs317/Eclipse/ReservationSys/datafiles/database/rooms.txt");
			
				// Write sortedRooms array to the file 
				ListUtilities.saveListToTextFile(rooms,"dcs317\\Eclipse\\ReservationSys\\datafiles\\database\\rooms.txt");
				
					}
			
				} catch (IOException e) {	
					
				System.out.print("Could not create file..\n" + e.getMessage());		
				
				}
		}
	
	public static void wrapper(Room[] rooms) {
	
		Integer[] roomNums = new Integer[rooms.length];
	
		String num = "";
		int k = 0;
		int temp;
		
		while ( k < rooms.length) {
		
		for (int i = 0; i < rooms.length; i++) {
			
				num += rooms[k].toString().charAt(i);
		
					if (num.length() == 3) 	{
						i = -1;
						roomNums[k] = Integer.parseInt(num);
						temp = Integer.parseInt(num);
						System.out.println(temp);
						num = "";
						k++;
					}
				}	
			}
		}	
	
	public static void wrappper(Room[] rooms) {

			
			Integer[] list = new Integer[rooms.length];

			int temp;
	
			int index = 0;
			for (int i = 0; i < rooms.length; i++) {
				index = i;
				for (int k = i+1; k < rooms.length; k++) {

					if (list[i].compareTo(list[k]) > 0)
						index = k;
				}
				temp = list[i];
				list[i] = list[index];
				list[index] = temp;
				
				System.out.println(list[i]);
			}
		}
		
	public static void sortedCustomers() {
			
			File sorted = new File ("dcs317/Eclipse/ReservationSys/datafiles");
			sorted.mkdir();
			//Customers
			Customer[] customers;
			Customer[][] customerList = new Customer[10][];
			
			Reservation[] reservations;
			Reservation[][] reservationList = new Reservation[10][];
		
			int k = 0;
			int index = 2;
			
			for (int i = 1; i < 11; i++){
				
				String customerFileName = "customers" + i + ".txt";
				String sortedCustFileName = "sortedCustomers" + i + ".txt";
				
				String resFileName = "reservations" + i + ".txt";
				String sortedResFileName = "sortedReservations" + i + ".txt";
				
			try {
				
				customers = HotelFileLoader.getCustomerListFromSequentialFile("dcs317\\Eclipse\\ReservationSys\\datafiles\\" + customerFileName);
				//Sorting
				ListUtilities.sort(customers);
				customerList[k] = customers;
				k++;
					
					Customer[] temp = ListUtilities.merge(customerList[0], customerList[1], "duplicateCustomers.txt");
					ListUtilities.merge(temp,customerList[index], "duplicateCustomers.txt");
						index++;		
						
						//reservations = HotelFileLoader.getReservationListFromSequentialFile(String filename, Customer[] customerList,
						//			Room[] roomList);
							
						if (sorted.exists()) {
							customers = HotelFileLoader.getCustomerListFromSequentialFile("dcs317\\Eclipse\\ReservationSys\\datafiles\\" + customerFileName);
							File cust = new File ("dcs317/Eclipse/ReservationSys/datafiles/database/sorted" + sortedCustFileName);
							//Write sortedCustomers array to the file
							ListUtilities.saveListToTextFile(customerList[i], sortedCustFileName);
						
							File allCusts = new File ("dcs317/Eclipse/ReservationSys/datafiles/database/customers.txt");
							
							File room = new File ("dcs317/Eclipse/ReservationSys/datafiles/database/sorted" + sortedResFileName);
						}					
					
					} catch (IOException e) {	
						
					System.out.print("Could not create file.." + e.getMessage());	
					
					}
		
			} 
			
			
		}
		
	public static void sortedReservations(Customer[] customerList, Room[] roomList) {
		
			Reservation[] reservations;
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
					
					Reservation[] temp = ListUtilities.merge(reservationList[0], reservationList[1], "duplicateReservations.txt");
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
		
	}	
}

