package group5.hotel.data;

import java.io.IOException;
import java.util.List;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.HotelFactory;
import dw317.hotel.business.interfaces.Room;

public class CustomerListDB {
	private List<Customer> database;
	private final ListPersistenceObject listPersistenceObject;
	private final HotelFactory factory; 
		
	public CustomerListDB (ListPersistenceObject listPersistenceObject){
		this.listPersistenceObject = listPersistenceObject;
		this.database = this.listPersistenceObject.getRoomDatabase();
	}

	public CustomerListDB (ListPersistenceObject listPersistenceObject,
						HotelFactory factory){
		
		this.listPersistenceObject = listPersistenceObject;
		this.database = this.listPersistenceObject.getRoomDatabase();
		this.factory = factory;
	}

	@Override 
	public String toString(){
		int num = database.size();
		StringBuilder str = new StringBuilder("Number of rooms in database: " + num);
		for (Customer r : database) {
			str.append("\n" + r.toString());
		}
		return str.toString();
	}
	
	//@Override
	public void add (Customer cust)
			throws DuplicateCustomerException{
		
	}
	
	//@Override
	public void disconnect()
			throws IOException{
		
	}
	
	//@Override
	public Customer getCustomer(Email email)
				throws NonExistingCustomerException{
		
	}
	
	//@Override 
	public void update (Email email, CreditCard card)
			throws NonExistingCustomerException{
		
	}
	
	
	private static void binarySearch(List<? extends Food> menuList, String item){
		Food search = new Fruit(item,0,"","");
		
		int first, last, middle;
		
		first = 0;
		last = menuList.size() -1;
		middle = (first+last)/2;
		boolean found = false;
		
		while( first <= last && !found){
			if(menuList.get(middle).compareTo(search) < 0)
				first = middle+1;
			else if(menuList.get(middle).equals(search)){
				System.out.println(search.getName() + " found at location " + (middle +1) +".");
				found = true;
			}else{
				last = middle-1;
				middle = (first+last)/2;
			}
			if(first > last )
				System.out.println("The given object is not in the list Bouuuh!");
		}
	}
}
