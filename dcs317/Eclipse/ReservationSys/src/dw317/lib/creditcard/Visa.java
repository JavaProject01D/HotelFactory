package dw317.lib.creditcard;

/**
 * The following class extends the AbstractCreditCard in order to create a Visa
 * credit card object after validating the credit card's number.
 * 
 * @author Denis Lebedev
 * @version 9/27/2016
 */
public class Visa extends AbstractCreditCard {
	private static final long serialVersionUID = 42031768871L;

	/**
	 * This constructor passes to the AbstractCreditCard constructor the value
	 * of card type as Visa and the validated Visa number.
	 * 
	 * @author Denis Lebedev
	 * @param number
	 */

	public Visa(String number) {
		super(CardType.VISA, validateNumber(number));
	}

	/**
	 * This method validates the Visa Credit Card number by checking if its
	 * structure corresponds to a Visa's structure.
	 * 
	 * @author Denis Lebedev
	 * @param number
	 * @return String
	 * @throws IllegalArgumentException if it does not respect the
	 * rules for the Visa
	 */
	private static String validateNumber(String number) {
		/*Checking if the number is null, or if its length is not equals to 16
		 * or if the first digit is not equal to 4.
		 * If one of the following is not respected, an Illegal Argument
		 * Exception with an appropriate message will be thrown.
		 */
		if (number == null || number.length() != 16 || Integer.parseInt(number.substring(0, 1)) != 4)
			throw new IllegalArgumentException("The Visa card given does not respect the validation.");

		return number;
	}

}
