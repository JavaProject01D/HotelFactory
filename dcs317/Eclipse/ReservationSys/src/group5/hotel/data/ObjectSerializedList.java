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

	@Override
	public void saveCustomerDatabase(List<Customer> custs) throws IOException {
		
		Utilities.serializeObject(custs, customerFile);
		
	}

	@Override
	public void saveReservationDatabase(List<Reservation> reserve) throws IOException {
		
		Utilities.serializeObject(reserve, reservationFile);
		
	}	
}
