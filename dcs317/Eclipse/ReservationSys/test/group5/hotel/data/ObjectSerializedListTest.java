package group5.hotel.data;

/**
 * The class will test the getter that
 * have been override in the 
 * ObjectSerialiedList. Saving methods are not
 * tested because they are used often.
 * 
 * @author Denis Lebedev
 *
 */
public class ObjectSerializedListTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String roomFile = "dcs317\\Eclipse\\ReservationSys\\datafiles\\database\\rooms.ser";
		String customerFile = "dcs317\\Eclipse\\ReservationSys\\datafiles\\database\\customers.ser";
		String reservFile = "dcs317\\Eclipse\\ReservationSys\\datafiles\\database\\reservations.ser";
		
		ObjectSerializedList serializedList = new ObjectSerializedList(roomFile, customerFile, reservFile);
		
		testGetRoomDatabase(serializedList);
		testGetCustomerDatabase(serializedList);
		testGetReservationDatabase(serializedList);
	}
	
	private static void testGetRoomDatabase(ObjectSerializedList serializedList){
		System.out.println("\nPrinting a list of rooms: ");
		try{
			System.out.println(serializedList.getRoomDatabase());
		}catch(Exception e){
			System.out.println("<===>HANDLE ME<===> " + e.getMessage() + " <===>HANDLE ME<===>");
		}
		
	}
	
	private static void testGetCustomerDatabase(ObjectSerializedList serializedList){
		System.out.println("\nPrinting a list of customers: ");
		try{
			System.out.println(serializedList.getCustomerDatabase());
		}catch(Exception e){
			System.out.println("<===>HANDLE ME<===> " + e.getMessage() + " <===>HANDLE ME<===>");
		}
	}

	private static void testGetReservationDatabase(ObjectSerializedList serializedList){
		System.out.println("\nPrinting a list of reservation: ");
		try{
			System.out.println(serializedList.getReservationDatabase());
		}catch(Exception e){
			System.out.println("<===>HANDLE ME<===> " + e.getMessage() + " <===>HANDLE ME<===>");
		}
	}

}
