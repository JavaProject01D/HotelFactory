package group5.hotel.data;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Room;
import group5.hotel.business.DawsonHotelFactory;

/**
 * @author Denis Lebedev
 *
 */
public class HotelFileLoader {

	private HotelFileLoader(){
		
	}
	
	public static Room[] getRoomListFromSequentialFile(String filename)
			throws IOException{
		
		Room [] arr = new Room[2];
		
		Scanner inputStream = null ;
		String record = null;
		DawsonHotelFactory dHF = DawsonHotelFactory.DAWSON;
		
		try{
			
			BufferedReader outStream = new BufferedReader(new InputStreamReader(
					new FileInputStream(filename), StandardCharsets.UTF_8));
			
			inputStream = new Scanner(outStream);
			
			int i =0;
			
			while(inputStream.hasNext()){
				record = inputStream.nextLine();
				String [] fields = record.split("\\*");
				
				try{
					
					//CATCH PARSE EXCEPTION!!!
					arr[i] = dHF.getRoomInstance(Integer.parseInt(fields[i]), fields[i]);
					
				}catch(IllegalArgumentException iae){
					System.out.println(iae.getMessage() + "\nFileName: " + filename + "\nRecord: " + record);
				}
				
				if(i > arr.length) // resize
					arr = Arrays.copyOf(arr, arr.length * 2 + 1);
				
			}//end of the while loop
			
			//shrink
			arr = Arrays.copyOf(arr,i);
				
		}catch(IOException io){		}
		
		//Close Scanner
		finally{ inputStream.close();}
		
		
		return arr;
	}
	
	public static Customer[] getCustomerListFromSequentialFile(String filename)
			throws IOException{
		
		Customer [] arr = new Customer[2];
		
		Scanner inputStream = null;
		String record = null;
		DawsonHotelFactory dHF = DawsonHotelFactory.DAWSON;
		
		try{
			
			BufferedReader outStream = new BufferedReader(new InputStreamReader(
					new FileInputStream(filename), StandardCharsets.UTF_8));
			
			inputStream = new Scanner(outStream);
			
			int i =0;
			
			while(inputStream.hasNext()){
				record = inputStream.nextLine();
				String [] fields = record.split("\\*");
				
				try{
					
					//first, last, email - instance
					//email,FN,LN,Card,Card#
					//IF ** = they are ignored
					//IF *''*'' = ERROR THROW
					arr[i] = dHF.getCustomerInstance();
					
				}catch(IllegalArgumentException iae){
					System.out.println(iae.getMessage() + "\nFileName: " + filename + "\nRecord: " + record);
				}
				
				if(i > arr.length) // resize
					arr = Arrays.copyOf(arr, arr.length * 2 + 1);
				
			}//end of the while loop
			
			//shrink
			arr = Arrays.copyOf(arr,i);
				
		}catch(IOException io){		}
		
		//Close Scanner
		finally{ inputStream.close();}
		
		
		return arr;
	}


}
