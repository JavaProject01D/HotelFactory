package group5.hotel.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.sun.java_cup.internal.runtime.Scanner;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Room;

/**
 * @author Denis Lebedev
 *
 */
public class HotelFileLoader {

	private HotelFileLoader(){
		
	}
	
	public static Room[] getRoomListFromSequentialFile(String filename)
			throws IOException{
		
		Scanner inputStream = null;
		String record = null;
		
		
		
		try{
			
			BufferedReader outStream = new BufferedReader(new InputStreamReader
					new FileInputStream("rooms.txt"), Standard(harsets.UTF_8));
			
			inputStream = new Scanner(outStream);
			
			int i =0;
			
			while(inputStream.hasNext()){
				
			}
			
		}catch(IllegalArgumentException iae){
			
		}
		
		
		return null;
	}
	
	public static Customer[] getCustomerListFromSequentialFile(String filename)
			throws IOException{
		
		return null;
	}


}
