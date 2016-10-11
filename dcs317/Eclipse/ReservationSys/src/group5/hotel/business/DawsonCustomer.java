
package group5.hotel.business;

import java.util.Optional;

import dw317.hotel.business.interfaces.Customer;
import dw317.lib.Email;
import dw317.lib.Name;
import dw317.lib.creditcard.CreditCard;

/**
 * @author Sevan Topalian
 * @version 27/09/2016
 * 
 *          This class creates a DawsonCustomer object that has a first name, a
 *          last name, an email, and optionally a credit card. It has a few
 *          getters and a setter. It also has overridden compareTo, equals,
 *          hashCode, and toString methods. There is no validation done in this
 *          class because the validation required is done by other classes.
 */
public class DawsonCustomer implements Customer {

	private static final long serialVersionUID = 42031768871L;
	private String firstName;
	private String lastName;
	private String email;
	private Optional<CreditCard> creditCard;

	/**
	 * The constructor creates the DawsonCustomer object.
	 * 
	 * @param firstName
	 * @param lastName
	 * @param email
	 */
	public DawsonCustomer(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.creditCard = Optional.ofNullable(null);
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
		return this.email.compareToIgnoreCase(customer.getEmail().getAddress());
	}

	/**
	 * This method returns a deep copy of the Name.
	 * 
	 * @return Name
	 */
	@Override
	public Name getName() {
		return new Name(firstName, lastName);
	}

	/**
	 * This method returns the email.
	 * 
	 * @return Email
	 */
	@Override
	public Email getEmail() {
		return new Email(email);
	}

	/**
	 * This method returns the credit card. It is optional because the customer
	 * is not required to have a credit card.
	 * 
	 * @return Optional<CreditCard>
	 */
	@Override
	public Optional<CreditCard> getCreditCard() {
		return creditCard;
	}

	/**
	 * This method sets the credit card.
	 * 
	 * @param card
	 */
	@Override
	public void setCreditCard(Optional<CreditCard> card) {
		this.creditCard = card;
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
	public final boolean equals(Object object) {
		Customer customer = (Customer) object;

		if (this.getClass().equals(customer.getClass()))
			if (this.getEmail().getAddress().equals(customer.getEmail().getAddress()))
				return true;
		return false;
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
		String cardType = "";
		String cardNumber = "";

		if (creditCard.isPresent()) {
			cardType = creditCard.get().toString();
			cardNumber = creditCard.get().getNumber();
		}

		String customerInfo = (this.email + "*" + this.firstName + "*" + this.lastName + "*" + cardType + "*"
				+ cardNumber);

		return customerInfo;
	}
}