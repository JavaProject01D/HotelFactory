package dw317.lib.creditcard;

/**
 * The following class extends the AbstractCreditCard in order to create an amex
 * credit card object after validating the credit card's number.
 * 
 * @author Denis Lebedev
 * @version 9/27/2016
 */

public class Amex extends AbstractCreditCard {
	private static final long serialVersionUID = 42031768871L;

	/**
	 * This constructor passes to the AbstractCreditCard constructor the value
	 * of card type as Amex and the validated amex number.
	 * 
	 * @author Denis Lebedev
	 * @param number
	 */
	public Amex(String number) {
		super(CardType.AMEX, validateNumber(number));
	}

	/**
	 * This method validates the Amex Credit Card number by checking if its
	 * structure corresponds to a Amex's structure.
	 * 
	 * @author Denis Lebedev
	 * @param number
	 * @return String
	 * @throws IllegalArgumentException if it does not respect the
	 * rules for the Amex
	 */
	private static String validateNumber(String number) {

		/* Checking if the number is null, or if its length is not equals to 15
		 * or if the first two digits are not between 34 and 37.
		 * If one of the following is not respected, an Illegal Argument
		 * Exception with an appropriate message will be thrown.
		 */
		if (number == null 
				|| number.length() != 15 
				|| Integer.parseInt(number.substring(0, 2)) < 34
				|| Integer.parseInt(number.substring(0, 2)) > 37)
			throw new IllegalArgumentException("The Amex card given does not respect the validation.");

		return number;
	}

}
