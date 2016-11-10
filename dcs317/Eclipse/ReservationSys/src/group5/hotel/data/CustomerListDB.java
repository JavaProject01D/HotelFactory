package group5.hotel.data;

import java.io.IOException;
import java.util.List;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.HotelFactory;
import dw317.hotel.data.DuplicateCustomerException;
import dw317.hotel.data.NonExistingCustomerException;
import dw317.hotel.data.interfaces.CustomerDAO;
import dw317.hotel.data.interfaces.ListPersistenceObject;
import dw317.lib.Email;
import dw317.lib.creditcard.CreditCard;
import group5.hotel.business.DawsonHotelFactory;

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
		
		if(binarySearch(database, cust.getEmail()))
			throw new DuplicateCustomerException();
				
		database.add(factory.getCustomerInstance(cust.getName().getFirstName(), 
						cust.getName().getLastName(), cust.getEmail().getAddress()));
		//Keep their creditcard 
		cust.setCreditCard(cust.getCreditCard());
	}
	
	@Override
	public void disconnect()
			throws IOException{
		
	}
	
	@Override
	public Customer getCustomer(Email email)
				throws NonExistingCustomerException{
		return null;
		
	}
	
	@Override 
	public void update (Email email, CreditCard card)
			throws NonExistingCustomerException{
		
	}
	
	
	private static boolean binarySearch(List<Customer> list, Email cust){
		
		System.out.println("Start BinarySearch");
		
		int first, last, middle;
		
		first = 0;
		last = list.size() -1;
		middle = (first+last)/2;
		boolean found = false;
		
		for(int i=0; i < list.size() ; i++){
			System.out.println("List: " + list.get(i));
		}
		
		System.out.println("\nTry to find: " + cust);

		
		while( first <= last && !found ){
			//middle = (first + last)/2;
			if(list.get(middle).getEmail().compareTo(cust) < 0){
				first = middle+1;
				middle = (first + last)/2;
			}else if(list.get(middle).getEmail().equals(cust)){
				System.out.println(cust.getAddress() + " found at location " + (middle +1) +".");
				found = true;
				break;
			}else{
				last = middle-1;
				middle = (first+last)/2;
			}
			if(first > last )
				System.out.println("The given object is not in the list Bouuuh!");
		}
		
		System.out.println("Found: " + found);
		return found; 
	}
}
