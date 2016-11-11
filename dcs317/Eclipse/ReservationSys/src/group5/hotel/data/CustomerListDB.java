package group5.hotel.data;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.HotelFactory;
import dw317.hotel.data.DuplicateCustomerException;
import dw317.hotel.data.NonExistingCustomerException;
import dw317.hotel.data.interfaces.CustomerDAO;
import dw317.hotel.data.interfaces.ListPersistenceObject;
import dw317.lib.Email;
import dw317.lib.creditcard.CreditCard;
import group5.hotel.business.DawsonHotelFactory;
import group5.util.ListUtilities;

public class CustomerListDB implements CustomerDAO{
	private List<Customer> database;
	private final ListPersistenceObject listPersistenceObject;
	private final HotelFactory factory; 
		
	public CustomerListDB (ListPersistenceObject listPersistenceObject){
		this.listPersistenceObject = listPersistenceObject;
		this.database = this.listPersistenceObject.getCustomerDatabase();
		factory = DawsonHotelFactory.DAWSON;
	}

	public CustomerListDB (ListPersistenceObject listPersistenceObject,
						HotelFactory factory){
		
		this.listPersistenceObject = listPersistenceObject;
		this.database = this.listPersistenceObject.getCustomerDatabase();
		this.factory = factory;
	}

	@Override 
	public String toString(){
		int num = database.size();
		StringBuilder str = new StringBuilder("\nNumber of customers in database: " + num);
		for (Customer r : database) {
			str.append("\n" + r.toString());
		}
		return str.toString();
	}
	
	@Override
	public void add (Customer cust)
			throws DuplicateCustomerException{
		
		if(binarySearch(database, cust.getEmail()) != -1)
			throw new DuplicateCustomerException();
				
		database.add(factory.getCustomerInstance(cust.getName().getFirstName(), 
						cust.getName().getLastName(), cust.getEmail().getAddress()));
		//Keep their creditcard 
		cust.setCreditCard(cust.getCreditCard());
	}
	
	@Override
	public void disconnect()
			throws IOException{
		
		//Room, customer, reservation
		SequentialTextFileList save  = new SequentialTextFileList("dcs317/Eclipse/Reservation/datafiles/database/roomtest.txt"
																,"dcs317/Eclipse/Reservation/datafiles/database/customertest.txt"
																,"dcs317/Eclipse/Reservation/datafiles/database/reservationtest.txt");
		//listPersistenceObject.saveCustomerDatabase(database);
		
		save.saveCustomerDatabase(database);
		database = null;
		new CustomerListDB(this.listPersistenceObject, this.factory);

	}
	
	@Override
	public Customer getCustomer(Email email)
				throws NonExistingCustomerException{
		int index = binarySearch(database, email);
		if(index != -1)
			throw new NonExistingCustomerException();
		
		return database.get(index);
		
	}
	
	@Override 
	public void update (Email email, CreditCard card)
			throws NonExistingCustomerException{
		int index = binarySearch(database,email);
		
		if(index != -1)
			throw new NonExistingCustomerException();
		
		database.get(index).setCreditCard(Optional.ofNullable(card));
	}
	
	
	private static int binarySearch(List<Customer> list, Email cust){
				
		int first, last, middle;
		
		first = 0;
		last = list.size() -1;
		middle = (first+last)/2;
		int found = -1;
		
		/*for(int i=0; i < list.size() ; i++){
			System.out.println("List: " + list.get(i));
		}
		
		System.out.println("\nTry to find: " + cust);*/

		
		while( first <= last && found == -1){
			if(list.get(middle).getEmail().compareTo(cust) < 0){
				first = middle+1;
				middle = (first + last)/2;
			}else if(list.get(middle).getEmail().equals(cust)){
				//System.out.println(cust.getAddress() + " found at location " + (middle +1) +".");
				found = middle;
			}else{
				last = middle-1;
				middle = (first+last)/2;
			}

		}
		System.out.println("Found: " + found);
		return found; 
	}
}
