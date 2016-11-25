package group5.hotel.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.interfaces.ListPersistenceObject;
import group5.util.Utilities;

public class ObjectSerializedList implements ListPersistenceObject {
	
	private String customerFile;
	private String reservationFile;
	private String roomFile;
	
	public ObjectSerializedList(String customerFile, String reservationFile,
								String roomFile){
		this.customerFile = customerFile;
		this.reservationFile = reservationFile;
		this.roomFile = roomFile;
	}

	@Override
	public List<Room> getRoomDatabase() {
		Room[] rooms = null;
		try{
			rooms = (Room[]) Utilities.deserializeObject(roomFile);
		}catch(IOException oie){
			return new ArrayList<Room>();
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage() + "in ObjectSerializedList");
		}
		
		// Create the adapter object that will be used as an argument to 
		// instantiate an ArrayList instance.

		List<Room> listAdapter = java.util.Arrays.asList(rooms);

		// return a reference to an ArrayList instance.
		return new ArrayList<Room>(listAdapter);
	}

	@Override
	public List<Customer> getCustomerDatabase() {
		Customer[] customers = null;
		try{
			customers = (Customer[]) Utilities.deserializeObject(customerFile);
		}catch(IOException oie){
			return new ArrayList<Customer>();
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage() + "in ObjectSerializedList");
		}
		
		// Create the adapter object that will be used as an argument to 
		// instantiate an ArrayList instance.

		List<Customer> listAdapter = java.util.Arrays.asList(customers);

		// return a reference to an ArrayList instance.
		return new ArrayList<Customer>(listAdapter);
	}

	@SuppressWarnings("unused")
	@Override
	public List<Reservation> getReservationDatabase() {
		Room[] rooms = null;
		Customer[] customer = null;
		Reservation[] reserve = null;

		try{
			rooms = (Room[]) Utilities.deserializeObject(roomFile);
			customer = (Customer[]) Utilities.deserializeObject(customerFile);
			reserve = (Reservation[]) Utilities.deserializeObject(reservationFile);					
		}
		catch (IOException e) {
			return new ArrayList<Reservation>();
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage() + "in ObjectSerializedList");
		}
		
		// Create the adapter object that will be used as an argument to 
		// instantiate an ArrayList instance.

		List<Reservation> listAdapter = java.util.Arrays.asList(reserve);

		// return a reference to an ArrayList instance.
		return new ArrayList<Reservation>(listAdapter);
	}

	@Override
	public void saveCustomerDatabase(List<Customer> custs) throws IOException {
		
		Customer[] custArray = custs.toArray(new Customer[custs.size()]);
		Utilities.serializeObject(custArray, customerFile);
		
	}

	@Override
	public void saveReservationDatabase(List<Reservation> reserve) throws IOException {
		
		Reservation[] resArray = reserve.toArray(new Reservation[reserve.size()]);
		Utilities.serializeObject(resArray, reservationFile);
		
	}	
}
