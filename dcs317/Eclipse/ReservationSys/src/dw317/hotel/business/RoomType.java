
package dw317.hotel.business;

/**
 * The class is an Enum that
 * define final values which repesents
 * the room type.
 * 
 * @author Zahraa Horeibi
 * @version 9/27/2016
 */
public enum RoomType {
		NORMAL, 
		SUITE, 
		PENTHOUSE;
	
		@Override 
		public String toString() {
			return super.toString().toLowerCase();
			
		}
	
}
