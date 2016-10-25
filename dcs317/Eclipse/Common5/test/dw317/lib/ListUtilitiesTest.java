package dw317.lib;

import java.io.IOException;

import group5.util.ListUtilities;

public class ListUtilitiesTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		selectionSortTest();
		
		mergeTest();
	}
	
	private static void selectionSortTest(){
		Integer[] list = new Integer[]{5,9,1,4,3,2};
		String[] stringList = new String[]{"z","a","y","h"};
		
		selectionSortTest("Case 1 - Valid Integers", list, true);
		
		selectionSortTest("Case 2 - Valid Strings", stringList, true);
		
		Integer[] list2 = new Integer[]{null,null,null,65};
		selectionSortTest("Case 3 - Invalid filled wiht nulls", list2, false);
		
		Integer[] list3 = new Integer[]{null,null,null,65,null};
		selectionSortTest("Case 4 - Invalid not Fully Loaded", list3, false);
		
	}
	
	@SuppressWarnings("rawtypes")
	private static void selectionSortTest(String testCase, Comparable[] arr, boolean validation){
		
			System.out.println("\n" + testCase);
		try{
			ListUtilities.sort(arr);
			
			if(validation)
				for(int i = 0; i < arr.length; i++)
					System.out.println(arr[i]);
			
		}catch(NullPointerException npe){
			System.out.println("Error: " + npe.getMessage());
			if(validation)
				System.out.println("The answer should be valid <--Fix It Please-->");
			
		}catch(IllegalArgumentException iae){
			System.out.println("Error: " + iae.getMessage());
			if(validation)
				System.out.println("The answer should be valid <--Fix It Please-->");
			
		}catch(Exception e){
			System.out.println("Error: " + e.getMessage() + " " + e.getClass() + "The Error should be catch by something! <--Fix It Please-->");
		}
	}
	
	private static void mergeTest(){
		Integer[] listOneTest = new Integer[]{5,6,1,4,2,3};
		Integer[] listTwoTest = new Integer[]{99,96,140,145,160};
		
		mergeTest("Case 1 - Valid - Merge two Integer List", listOneTest, listTwoTest, true);
		
		String[] stringListOne = new String[]{"z","a","y","h"};
		String[] stringListTwo = new String[]{"b","v","g","r","u","l"};
		
		mergeTest("Case 2 - Valid - Merge two Srting List", stringListOne, stringListTwo, true);
				
		String[] stringListOneDupl= new String[]{"z","a","y","h"};
		String[] stringListTwoDupl = new String[]{"z","a","y","h"};
		mergeTest("Case 3 - Valid - Merge two Srting List", stringListOneDupl, stringListTwoDupl, true);
		
	}
	
	@SuppressWarnings({ "rawtypes" })
	private static void mergeTest(String testCase, Comparable[] list1, Comparable[] list2, boolean validation){
		
		System.out.println("\n" + testCase);
		Comparable list3[];
		
		ListUtilities.sort(list1);
		ListUtilities.sort(list2);
		
		try{
			list3 = ListUtilities.merge(list1, list2, "testDup.txt");
			
			if(validation)
				for(int i = 0; i < list3.length; i++)
					System.out.println(list3[i]);
						
		}catch(IOException ioe){
			System.out.println("Error: " + ioe.getMessage());
			if(validation)
				System.out.println("The answer should be valid <--Fix It Please-->");
			
		}catch(Exception e){
			System.out.println("Error: " + e.getMessage() + " " + e.getClass() + "The Error should be catch by something! <--Fix It Please-->");
		}
		
	}

}
