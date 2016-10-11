package group5.util;

public class ListUtilities{
	
	public ListUtilities(){}
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
				if(list[i] == null)
					throw new NullPointerException("There is a null element");
				
				if(list[i].compareTo(list[k]) > 1)
					index = k;
			}
			list[i] = list[index];
		}
	}
}