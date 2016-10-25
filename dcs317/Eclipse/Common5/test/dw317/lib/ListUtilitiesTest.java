package dw317.lib;

import dw317.hotel.business.interfaces.Room;
import group5.util.ListUtilities;

public class ListUtilitiesTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		selectionSortTest();
	}
	
	private static void selectionSortTest(){
		Room[] arr = new Room[5];
		selectionSortTest("Case 1", arr, true);
	}
	
	private static void selectionSortTest(String testCase, Object[] arr, boolean validation){
		//@SuppressWarnings("rawtypes")
		Integer[] testInt = new Integer[]{1,8,5,6,7,2,9};
		
			
		try{
			ListUtilities.sort(testInt);
			
			for(int i = 0; i < testInt.length; i++)
				System.out.println(testInt[i]);
			
		}catch(Exception e){
			System.out.println("Error: " + e.getMessage() + " " + e.getClass());
		}
	}

}
