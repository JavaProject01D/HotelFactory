package group5.hotel.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.interfaces.ListPersistenceObject;
import group5.util.Utilities;
/**
 * This class will get and save any
 * object. The getters will only return a 
 * List of all elements that is available
 * 
 * @author Denis Lebedev
 *
 */
public class ObjectSerializedList implements ListPersistenceObject {
	
	private String customerFile;
	private String reservationFile;
	private String roomFile;
	
	/**
	 * The constructor will initialize the 
	 * path for all different files
	 * 
	 * @param customerFile
	 * @param reservationFile
	 * @param roomFile
	 */
	public ObjectSerializedList(String customerFile, String reservationFile,
								String roomFile){
		this.customerFile = customerFile;
		this.reservationFile = reservationFile;
		this.roomFile = roomFile;
	}
	
	/**
	 * The method will give a list
	 * from the serialized file that 
	 * represent all our data.
	 * 
	 * @author Denis Lebedev
	 * @return List<Room>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Room> getRoomDatabase() {
		List<Room> rooms = null;
		try{
			rooms = (List<Room>) Utilities.deserializeObject(roomFile);
		}catch(IOException oie){
			return new ArrayList<Room>();
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage() + "in ObjectSerializedList");
		} 
		return rooms;
	}
	
	/**
	 * The method will give a list
	 * from the serialized file that 
	 * represent all our data. 
	 * 
	 * @author Denis Lebedev
	 * @return List<Customer>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getCustomerDatabase() {
		List<Customer> customers = null;
		try{
			customers = (List<Customer>) Utilities.deserializeObject(customerFile);
		}catch(IOException oie){
			return new ArrayList<Customer>();
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage() + "in ObjectSerializedList");
		}

		return customers;
	}
	
	/**
	 * The method will give a list
	 * from the serialized file that 
	 * represent all our data. 
	 * 
	 * @author Denis Lebedev
	 * @return List<Reservation>
	 */
	@SuppressWarnings({ "unused", "unchecked" })
	@Override
	public List<Reservation> getReservationDatabase() {
		List<Room> rooms = null;
		List<Customer> customer = null;
		List<Reservation> reserve = null;

		try{
			rooms = (List<Room>) Utilities.deserializeObject(roomFile);
			customer = (List<Customer>) Utilities.deserializeObject(customerFile);
			reserve = (List<Reservation>) Utilities.deserializeObject(reservationFile);					
		}
		catch (IOException e) {
			return new ArrayList<Reservation>();
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage() + "in ObjectSerializedList");
		}

		return reserve;
	}
	
	/**
	 * The method will save to disk, but
	 * it uses serialization
	 * 
	 * @author Denis Lebedev
	 * @param List<Customer>
	 */
	@Override
	public void saveCustomerDatabase(List<Customer> custs) throws IOException {
		
		Utilities.serializeObject(custs, customerFile);
		
	}
	
	/**
	 * The method will save to disk, but
	 * it uses serialization
	 * 
	 * @author Denis Lebedev
	 * @param List<Reservation>
	 */
	@Override
	public void saveReservationDatabase(List<Reservation> reserve) throws IOException {
		
		Utilities.serializeObject(reserve, reservationFile);
		
	}	
}
