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
/**
 * 
 * 
 * @author Denis Lebedev
 *
 */
public class CustomerListDB implements CustomerDAO{
	private List<Customer> database;
	private final ListPersistenceObject listPersistenceObject;
	private final HotelFactory factory; 
	
	/**
	 * We use the ListPersistenceObject to load our database
	 * and because we do not have a HotelFactory given we 
	 * set a default factory by using DawsonHotelFactory
	 * @param listPersistenceObject
	 */
	public CustomerListDB (ListPersistenceObject listPersistenceObject){
		this.listPersistenceObject = listPersistenceObject;
		this.database = this.listPersistenceObject.getCustomerDatabase();
		factory = DawsonHotelFactory.DAWSON;
	}
	/**
	 * Overload constructor.
	 * The only diference between the two contructor is that
	 * here we take as input a HotelFactory object and assign
	 * to our HotelFactory
	 * 
	 * @author Denis Lebedev
	 *
	 * @param listPersistenceObject
	 * @param factory
	 */
	public CustomerListDB (ListPersistenceObject listPersistenceObject,
						HotelFactory factory){
		
		this.listPersistenceObject = listPersistenceObject;
		this.database = this.listPersistenceObject.getCustomerDatabase();
		this.factory = factory;
	}

	/**
	 * We override the toString to show
	 * EVERYTHING that we have in our database.
	 * Also, we use StringBuilder to be more efficient.
	 * 
	 * @author Denis Lebedev
	 */
	@Override 
	public String toString(){
		int num = database.size();
		StringBuilder str = new StringBuilder("\nNumber of customers in database: " + num);
		for (Customer r : database) {
			str.append("\n" + r.toString());
		}
		return str.toString();
	}
	
	/**
	 * The method will add a customer to your database,
	 * but in a ordered way. They are naturally orded 
	 * by their email. To find them we use a binary search
	 * 
	 * @author Denis Lebedev
	 * 
	 * @param cust
	 * @throws DuplicateCustomerException the throw happen
	 * if the bianry reseach found already a customer in
	 * our database.
	 */
	@Override
	public void add (Customer cust)
			throws DuplicateCustomerException{
		int index =(binarySearch(database, cust.getEmail()));
		if(index > 0)
			throw new DuplicateCustomerException("Customer already exits. It impossible to add it!");
		
		/*
		 * the binary research return a neg number if you follow
		 *the conventional way to so. 
		 */
		index = -(index) -1;
				
		database.add(index, factory.getCustomerInstance(cust.getName().getFirstName(), 
						cust.getName().getLastName(), cust.getEmail().getAddress()));
		//Keep their creditcard 
		database.get(index).setCreditCard(cust.getCreditCard());
	}
	
	/**
	 * To be able to save any changes you
	 * MUST save first and that why the disconect
	 * method is for. We use the listPersistenceObject 
	 * that we assign in the constructor and call
	 * the override method.
	 * 
	 * @author Denis Lebedev
	 * 
	 * @throws IOException is thrown if the 
	 * SequentialTextFileList when he calls the 
	 * HotelFileLoader because the file given is invalid
	 */
	@Override
	public void disconnect()
			throws IOException{
		
			this.listPersistenceObject.saveCustomerDatabase(this.database);
			this.database = null;
	}
	
	/**
	 * In this method we will look for a customer by his
	 * email and search with a binary search. If the customer
	 * does not exist we gonna throw.
	 * 
	 * @author Denis Lebedev
	 * 
	 * @param Email 
	 * @return Customer object
	 * @throws NonExistingCustomerException is thrown if we
	 * do not foudn the customer in our database. We use 
	 * a normal binary search to find it by his email.
	 */
	@Override
	public Customer getCustomer(Email email)
				throws NonExistingCustomerException{
		int index = binarySearch(database, email);
		if(index < 0)
			throw new NonExistingCustomerException();
		
		return database.get(index);
		
	}
	
	/**
	 * This method allow us to update a creditcard from 
	 * any customer if we can find him in our database. 
	 * Their email is used as a key in our binary search.
	 * If the customer exist we will change his creditcard.
	 * 
	 * @author Denis Lebedev
	 * 
	 * @param email
	 * @param card
	 * @throws NonExistingCustomerException is thrown if we
	 * do not foudn the customer in our database. We use 
	 * a normal binary search to find it by his email.
	 */
	@Override 
	public void update (Email email, CreditCard card)
			throws NonExistingCustomerException{
		int index = binarySearch(database,email);
		if(index < 0)
			throw new NonExistingCustomerException();
		
		database.get(index).setCreditCard(Optional.ofNullable(card));
	}
	
	/**
	 * This method is used to search through a list 
	 * of Customers only. We use the email of a cutsomer
	 * as a unique key. If the email is not found
	 * the mthod will return a negative number. Else
	 * we will return his position.
	 * 
	 * @author Denis Lebedev
	 * 
	 * @param list
	 * @param cust
	 * @return int
	 */
	private static int binarySearch(List<Customer> list, Email cust){
				
		int first, last, middle;
		
		first = 0;
		last = list.size() -1;
		middle =0;

		
		while( first <= last){
			middle = (first + last)/2;
			if(list.get(middle).getEmail().equals(cust)){
				//I found so i return the middle
				return middle;
			}else if(list.get(middle).getEmail().compareTo(cust) < 0){
				first = middle+1;
			}else{
				last = middle-1;			
			}

		}
		return -(first +1); 
	}
}
