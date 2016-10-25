
package group5.hotel.business;

import java.util.Optional;
import java.lang.String;
import dw317.hotel.business.interfaces.Customer;
import dw317.lib.Email;
import dw317.lib.Name;
import dw317.lib.creditcard.CreditCard;

/**
 * @author Sevan Topalian
 * @version 27/09/2016
 * 
 * Modifications done my Zahraa Horeibi. 
 * 
 *          This class creates a DawsonCustomer object that has a first name, a
 *          last name, an email, and optionally a credit card. It has a few
 *          getters and a setter. It also has overridden compareTo, equals,
 *          hashCode, and toString methods. There is no validation done in this
 *          class because the validation required is done by other classes.
 */
public class DawsonCustomer implements Customer {

	private static final long serialVersionUID = 42031768871L;
	private Name name;
	private Email email;
	private CreditCard creditCard;

	/**
	 * The constructor creates the DawsonCustomer object.
	 * 
	 * @param firstName
	 * @param lastName
	 * @param email
	 */
	public DawsonCustomer(String firstName, String lastName, String email) {
	
		this.name.setFirstName(firstName);
		this.name.setLastName(lastName);
	//	this.email = new Email(email);
				
		this.creditCard = null;
	}

	/**
	 * This is the overridden compareTo method. It compares the customers based
	 * on their emails.
	 * 
	 * @param customer
	 */
	@Override
	public int compareTo(Customer customer) {
		if (this.equals(customer))
			return 0;
		return this.email.getAddress().compareToIgnoreCase(customer.getEmail().getAddress());
	}

	/**
	 * This method returns a deep copy of the Name.
	 * 
	 * @return Name
	 */
	@Override
	public Name getName() {
		return new Name (this.name.getFirstName(), this.name.getLastName());
	}

	/**
	 * This method returns the email.
	 * 
	 * @return Email
	 */
	@Override
	public Email getEmail() {
		return new Email(this.email.getAddress());
	}

	/**
	 * This method returns the credit card. It is optional because the customer
	 * is not required to have a credit card.
	 * 
	 * @return Optional<CreditCard>
	 */
	@Override
	public Optional<CreditCard> getCreditCard() {
		return Optional.ofNullable(creditCard);
	}

	/**
	 * This method sets the credit card.
	 * 
	 * @param card
	 */
	@Override
	public void setCreditCard(Optional<CreditCard> card) {
		this.creditCard = card.orElse(null);
	}

	/**
	 * This method is the overridden hashcode method. It returns the hashcode
	 * value of the email.
	 * 
	 * @return int
	 */
	@Override
	public final int hashCode() {
		final int prime = 37;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	/**
	 * This is the overridden equals method. It compares two Customer objects to
	 * see if they are equal. Two objects are determined to be equal if they are
	 * instances of the same class and if they have the same email address.
	 * 
	 * @param object
	 * @return boolean
	 */
	@Override
	public final boolean equals(Object obj) {
		
		if (this == obj) 
			return true;
		
		if (obj == null) 
			return false;
		
		if (obj instanceof Customer){
		Customer cust = (Customer) obj;
	// ignoreCase?	
		if (!this.equals(cust.getEmail().getAddress()))
			return false;

		return true;
		
		}
		
		return true;
	}

	/**
	 * This is the overridden toString method. It returns a String
	 * representation of the DawsonCustomer object in the format
	 * (email*firstName*lastName*creditCardType*creditCardNumber). If there is
	 * no credit card, the card type and card number are represented as empty
	 * strings.
	 * 
	 * @return String
	 */
	@Override
	public String toString() {

		if (this.creditCard == null)
			return this.email + "*" + this.name.getFirstName() + "*" + this.name.getLastName() +
					"*" + "" + "*" + "";
		
		

		return this.email + "*" + this.name.getFirstName() + "*" + this.name.getLastName() +
				"*" + this.creditCard.getType() + "*" + this.creditCard.getNumber();
	

	}
}