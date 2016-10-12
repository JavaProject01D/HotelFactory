package group5.util;

import java.io.IOException;

public class ListUtilities{
	
	/*
	 * to prevent instantiation of the class
	 */
	private ListUtilities(){}
	
	/*
	 * Sorts a list of objects in ascending natural order using 
	 * selection sort.
	 * 
	 *
	 * @param list 	A list of objects. Assumes that the
	 *             	list's capacity is equal to the list's size. 
	 * 
	* @throws  		IllegalArgumentException if the parameter is
	*			not full to capacity.
	*
	* @throws		NullPointerException if the list is null.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	 public static void sort(Comparable[] list)
				throws IllegalArgumentException, NullPointerException{
		
		if(list[list.length - 1] == null)
			throw new IllegalArgumentException("Not filled to capacity");
			
		int index;
		for(int i=0; i < list.length; i++){
			index =i;
			for(int k=1; k < list.length; k++){			
				
				if(list[i].compareTo(list[k]) > 1)
					index = k;
			}
			list[i] = list[index];
		}
	}
	
	//SEE LAB 7
	private static void saveListToTextFile(){
		
	}
	
	/*
	 * Sorts a list of objects in ascending natural order using 
	 * selection sort.
	 * 
	 * Precondition: 	Assumes that the list is not null and that the 
	 *	list's capacity is equal to the list's size.
	 * 
	 *
	 * @param list 	A list of objects. Assumes that the
	 *             	list's capacity is equal to the list's size. 
	 * 
	* @throws  		IllegalArgumentException if the parameter is      *			not full to capacity.
	*
	* @throws		NullPointerException if the list is null.
	 */
		/* @SuppressWarnings({ "rawtypes", "unchecked" })
		 public static void sort(Comparable[] list)
					throws IllegalArgumentException, NullPointerException{
			 
			 
		 }*/
	
	
	
	/*
	 * Efficiently merges two sorted lists of objects in ascending 
	 * natural order. If the duplicate objects are in both lists, 
	 * the object from list1 is merged into the resulting list, and
	 * both objects are written to the duplicate file. 
	 * 
	 * Precondition: 	Assumes that the lists are not null and that 
	 *           both lists contain objects that can be compared to 
	 *           each other and are filled to capacity.
	 * 
	 *
	 * @param list1 	A naturally sorted list of objects. Assumes 
	 *                that the list contains no duplicates and that 
	 *                its capacity is equal to its size. 
	 * @param list2 	A naturally sorted list of objects. Assumes 
	 *                that the list contains no duplicates and that 
	 *                its capacity is equal to its size. 
	 * @param duplicateFileName  The name of the file in 
	 *                datafiles\duplicates to which duplicate pairs 
	 *                will be appended.
	 * 
	 * @throws        IllegalArgumentException if either parameter is
	 *                not full to capacity.
	 *
	 * @throws		NullPointerException if the either list is 
	 *                null.
	 */

			@SuppressWarnings({ "rawtypes", "unchecked" })
			public static Comparable[] merge(Comparable[] list1,
				Comparable[] list2, String duplicateFileName)
					throws IOException{
				
				return null;
			}

	
}