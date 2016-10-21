package group5.hotel.business;

import java.lang.reflect.Constructor;

import group5.hotel.data.HotelFileLoader;

/**
 * 
 * @author Denis Lebedev
 *
 */
public class HotelFileLoaderTest {

	public static void main(String[] args) {
		
		testFileLoader();

	}
	
	private static void testFileLoader(){
		
		testFileLoader("room.txt");
	}
	
	private static void testFileLoader(String fileName){
		Constructor<?>[] load = HotelFileLoader.class.getDeclaredConstructors(); 
		load[0].setAccessible(true);
		//load[0].newInstance();
	}

}
