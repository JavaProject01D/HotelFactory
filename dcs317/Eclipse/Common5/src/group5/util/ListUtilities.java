package group5.util;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Comparator;
import java.io.File;


/**
 * The class will have different sort methods
 * like a selection sort and a merge method.
 * The merger will compare two list and create a third sorted list.
 * We are using the selection sort to sort all the lists before we 
 * use it in the merger sort.
 * Also, the class will provide a way to write to file.
 * 
 * @author Denis Lebedev
 *
 */
public class ListUtilities {

	private static final Charset CHARACTER_ENCODING = StandardCharsets.UTF_8;

	/**
	 * We use a private constructor to prevent instantiation of the class
	 */
	private ListUtilities() {
	}

	/**
	 * Sorts a list of objects in ascending natural order using selection sort.
	 * 
	 *@author Denis Lebedev
	 *
	 * @param list A list of objects. Assumes that the list's capacity is equal
	 * to the list's size.
	 * 
	 * @throws IllegalArgumentException if the parameter is not full to
	 * capacity.
	 *
	 * @throws NullPointerException if the list is null.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void sort(Comparable[] list) throws IllegalArgumentException, NullPointerException {
			
		if(list == null)
			throw new NullPointerException("The list given that will be sorted has a Null value.");
		
		if (list[list.length - 1] == null)
			throw new IllegalArgumentException("Not filled to capacity");
		
		Comparable temp;
		
		int index = 0;
		for (int i = 0; i < list.length; i++) {
			index = i;
			for (int k = i+1; k < list.length; k++) {

				if (list[k].compareTo(list[index]) < 0)
					index = k;
			}
			temp = list[i];
			list[i] = list[index];
			list[index] = temp;
		}
	}


	public static void saveListToTextFile(Object[] objects, String filename)
			throws FileNotFoundException, UnsupportedEncodingException {
		saveListToTextFile(objects, filename, false, CHARACTER_ENCODING);
	}

	public static void saveListToTextFile(Object[] objects, String filename, boolean append)
			throws FileNotFoundException, UnsupportedEncodingException {
		saveListToTextFile(objects, filename, append, CHARACTER_ENCODING);
	}

	public static void saveListToTextFile(Object[] objects, String filename, boolean append, Charset characterEncoding)
			throws FileNotFoundException, UnsupportedEncodingException {

		PrintWriter outputFile = null;

		try {
			FileOutputStream f = new FileOutputStream(filename, append);
			OutputStreamWriter out = new OutputStreamWriter(f, characterEncoding);
			outputFile = new PrintWriter(new BufferedWriter(out));

			for (Object obj : objects)
				if (obj != null)
					outputFile.println(obj);
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("Error saving list! Unable to access the device " + filename);
		}
	}

	/**
	 * Efficiently merges two sorted lists of objects in ascending natural
	 * order. If the duplicate objects are in both lists, the object from list1
	 * is merged into the resulting list, and both objects are written to the
	 * duplicate file.
	 * 
	 * Precondition: Assumes that the lists are not null and that both lists
	 * contain objects that can be compared to each other and are filled to
	 * capacity.
	 * 
	 *@author Denis Lebedev
	 *
	 * @param list1 A naturally sorted list of objects. Assumes that the list
	 * contains no duplicates and that its capacity is equal to its size.
	 * 
	 * @param list2 A naturally sorted list of objects. Assumes that the list
	 * contains no duplicates and that its capacity is equal to its size.
	 * 
	 * @param duplicateFileName The name of the file in datafiles\duplicates to
	 * which duplicate pairs will be appended.
	 * 
	 * @throws IllegalArgumentException if either parameter is not full to
	 * capacity.
	 *
	 * @throws NullPointerException if the either list is null.
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Comparable[] merge(Comparable[] list1, Comparable[] list2, String duplicateFileName)
			throws IOException {

		Comparable[] list3 = (Comparable[]) Array.newInstance(
				list1.getClass().getComponentType(), list1.length + list2.length);
		
		int list1Ind = 0, list2Ind = 0, list3Ind =0;

		while (list1Ind < list1.length && list2Ind < list2.length) {

			if (list1[list1Ind].compareTo(list2[list2Ind]) < 0) {
	
				list3[list3Ind] = list1[list1Ind];
				list1Ind++;	
			
			} else if (list1[list1Ind].compareTo(list2[list2Ind]) == 0){
				
				list3[list3Ind] = list1[list1Ind];
				duplicates(list3[list3Ind], duplicateFileName);
				list1Ind++;
				list2Ind++;				
				
			}else{
				list3[list3Ind] = list2[list2Ind];
				list2Ind++;
			}//end of if
			
		list3Ind++;
		
		}//end of while loop
		
		
		 if (list1Ind < list1.length ) {
		           for (int i = list1Ind; i < list1.length; i++) {
		            list3[list3Ind] = list1[i];
		            list3Ind++;
		           }
		     } else {
		           for (int i = list2Ind; i <  list2.length; i++) {
		            list3[list3Ind] = list2[i];
		            list3Ind++;
		           }
		     }
		 
	
		 if(list3.length != list3Ind)
			 list3 = Arrays.copyOf(list3, list3Ind);

		 return list3;
	}
	
	/**
	 *If there is a duplicates  the method will create
	 * a text file that will hold all your duplicates.
	 * 
	 * @author Denis Lebedev
	 * @param dup
	 */
	private static void duplicates (Comparable<?> dup, String filename){
		File duplicates = new File("dcs317/Eclipse/ReservationSys/datafiles/duplicates");
		
		if (!duplicates.exists())
			duplicates.mkdir(); 
		 
		File duplFile = new File (duplicates + "/" + filename);
		
		try{
			
			duplFile.createNewFile();

	
		}catch(IOException ioe){
			System.out.println("Could not create duplicate File: " + ioe.getMessage());
		}
		
		writeToFile(duplFile.toString(), dup);
	}
	
	
	/**
	 * If the duplicates method is invoked this method
	 * will use the created text file and write
	 * all the founded duplicates in a specific place.
	 * 
	 * @author Denis Lebedev
	 * @param filename
	 * @param dup
	 */
	private static void writeToFile(String filename, Comparable<?> dup){
		PrintWriter outputFile = null;
		try {
			outputFile = new PrintWriter (new BufferedWriter (new OutputStreamWriter 
					(new FileOutputStream (filename, true), StandardCharsets.UTF_8)));
		
			outputFile.println(dup);
			
		}
		catch (FileNotFoundException e) {
			System.out.println("Error: " + e.getMessage());
		}
	
		finally {
			if (outputFile != null)  //successfully opened
				outputFile.close();  //flushes buffer and releases resources
		}
	}

	/**
	 * Sorts a list of objects in the given order.
	 * 
	 * Precondition: Assumes that the list is not null and that the list's
	 * capacity is equal to the list's size.
	 * 
	 *
	 * @param list A list of objects. Assumes that the list's capacity is equal
	 * to the list's size.
	 * 
	 * @param sortOrder A Comparator object that defines the sort order
	 * 
	 * 
	 * @throws IllegalArgumentException if the parameter is * not full to
	 * capacity.
	 *
	 * @throws NullPointerException if the list or sortOrder * are null.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void sort(Comparable[] list, Comparator sortOrder)
			throws IllegalArgumentException, NullPointerException {
		Arrays.sort(list, sortOrder);
	}
}