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
		
		sortedRooms();
		sortedCustomers();
	
	
	}

	public static void sortedRooms(){
		
		File database = new File("dcs317/Eclipse/ReservationSys/datafiles/database");
		if(!database.exists())
			database.mkdir();
		//Rooms
		Room[] rooms;
		File room = new File(database + "/" +"rooms.txt");
		
		try {
			rooms = HotelFileLoader.getRoomListFromSequentialFile("dcs317/Eclipse/ReservationSys/datafiles/rooms.txt");	
			//System.out.println(rooms.length);
			
			//Sorting
			//do wrapper method and return Integer and do sorting on that 
			ListUtilities.sort(rooms);
				room.createNewFile();
				
				// Write sortedRooms array to the file 
				ListUtilities.saveListToTextFile(rooms,"dcs317/Eclipse/ReservationSys/datafiles/database/rooms.txt");
				
			
				} catch (IOException e) {	
					
				System.out.print("Could not create file..\n" + e.getMessage());		
				
				}
		}
	

	@SuppressWarnings("rawtypes")
	public static void sortedCustomers() throws IOException {
			
			File database = new File("dcs317/Eclipse/ReservationSys/datafiles/database");
			if(!database.exists())
			database.mkdir();
		
			File sorted = new File ("dcs317/Eclipse/ReservationSys/datafiles/sorted");
			if (!sorted.exists())
			sorted.mkdir();
			//Customers
		//	Customer[] customers;
			Customer[][] customerList = new Customer[10][];
			
			//Comparable[] cust = new Customer[10];
			
			String customerFileName;
			String sortedCustFileName;
			int k = 1;
			int index = 2;
			
			try {
				for (int i = 0; i < customerList.length; i++){			
					customerFileName = "customers" + (i+1) + ".txt";
					customerList[i] = HotelFileLoader.getCustomerListFromSequentialFile("dcs317\\Eclipse\\ReservationSys\\datafiles\\" + customerFileName);				
					}
			//	Comparable[] custo = customerList[1];
				
				for(int i = 0; i < customerList.length ; i++){
				ListUtilities.sort(customerList[i]);
					}
				for(int i = 0; i < customerList.length ; i++){
					for(int j = 0; j < customerList[i].length; j++)	
						if (sorted.exists()) {
								
									sortedCustFileName = "sortedCustomers" + (i+1) + ".txt";
									File cust = new File (sorted + "/" +  sortedCustFileName);
									cust.createNewFile();	
									
									// Write sortedCustomers array to the file					
									ListUtilities.saveListToTextFile(customerList[i], "dcs317/Eclipse/ReservationSys/datafiles/sorted" + "/" + sortedCustFileName);
								
									File allCusts = new File (database + "/customers.txt");
									allCusts.createNewFile();
								
					//	System.out.println("Customer: " + customerList[i][j]);
						}
					}
				
				}	catch(IOException ioe){
				
			}
				
		}
			
					
				/*for(int j = 0; j < customerList.length ; j++){
					
					//for(int i=0; i < customerList[j].length; i++)
					
					//String sortedCustFileName = "sortedCustomers" + j + ".txt";		
					
					System.out.println("J: " +j);
					
					Customer[]list2 = new Customer[10];
					Comparable[] custo = customerList[j];
					
					
					//Sorting
					ListUtilities.sort(custo);
					list2 = customerList[j];
					//customerList[k] = customers;
					//k+=2;
						//i++;
					
					// Comparable[] temp = ListUtilities.merge(customerList[j], customerList[j + 1], "duplicateCustomers.txt");
						Comparable[] temp = ListUtilities.merge(customerList[0], customerList[1], "duplicateCustomers.txt");
						ListUtilities.merge(temp,customerList[index], "duplicateCustomers.txt");
							index++;		
							
						
							if (sorted.exists()) {
						//		customers = HotelFileLoader.getCustomerListFromSequentialFile("dcs317\\Eclipse\\ReservationSys\\datafiles\\" + customerFileName);
								//File cust = new File ("dcs317/Eclipse/ReservationSys/datafiles/database/sorted" + sortedCustFileName);
								//Write sortedCustomers array to the file
								//ListUtilities.saveListToTextFile(customerList[j], sortedCustFileName);
							
								File allCusts = new File ("dcs317/Eclipse/ReservationSys/datafiles/database/customers.txt");
							}	
				}
				
			}	catch (IOException e){
					System.out.print("Could not create file.." + e.getMessage());	
			}			
		}
	//}		*/
		
	public static void sortedReservations(Customer[] customerList, Room[] roomList) {
		
		//NOT SURE 
			File sorted = new File ("dcs317/Eclipse/ReservationSys/datafiles");
			sorted.mkdir();
		
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
		
	}	
}

