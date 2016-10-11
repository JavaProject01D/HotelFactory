package dw317.hotel.business.interfaces;

import java.io.Serializable;
import java.util.Optional;
import dw317.lib.Email;
import dw317.lib.Name;
import dw317.lib.creditcard.CreditCard;

/**
 * @author Sevan Topalian
 * @version 27/09/2016
 */
public interface Customer extends Comparable<Customer>, Serializable {
	/**
	 * Returns a deep copy of the Name object.
	 * 
	 * @return Name
	 */
	Name getName(); // Must return a deep copy

	/**
	 * Returns the Email object.
	 * 
	 * @return Email
	 */
	Email getEmail();

	/**
	 * Returns the credit card. Optional because a customer is not required to
	 * have a credit card.
	 * 
	 * @return Optional<CreditCard>
	 */
	Optional<CreditCard> getCreditCard();

	/**
	 * Sets the credit card. Optional because the customer is not required to
	 * have a credit card.
	 * 
	 * @param card
	 */
	void setCreditCard(Optional<CreditCard> card);
}