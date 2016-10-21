package group5.util;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ListUtilities{
	
	private static final Charset CHARACTER_ENCODING = StandardCharsets.UTF_8;   
	
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
	
	//Good for now ... ?
	public static void saveListToTextFile(Object[] objects, String filename)
			throws FileNotFoundException, UnsupportedEncodingException {
		saveListToTextFile(objects, filename, false, CHARACTER_ENCODING);
	}

	public static void saveListToTextFile(Object[] objects, String filename,
			boolean append) throws FileNotFoundException,
 				UnsupportedEncodingException {
		saveListToTextFile(objects, filename, append, CHARACTER_ENCODING);
	}


	public static void saveListToTextFile(Object[] objects, String filename,
			boolean append, Charset characterEncoding) 
				throws FileNotFoundException, UnsupportedEncodingException {
		
		PrintWriter outputFile = null;
		
		try {
			FileOutputStream f = new FileOutputStream(filename, append);
			OutputStreamWriter out = 
					new OutputStreamWriter(f, characterEncoding);
			outputFile = new PrintWriter(new BufferedWriter(out));

			for (Object obj : objects)
				if (obj != null)
					outputFile.println(obj);
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException(
					"Error saving list! Unable to access the device "
							+ filename);
		}
	}


	
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