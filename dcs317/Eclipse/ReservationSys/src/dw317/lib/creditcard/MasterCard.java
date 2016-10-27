package dw317.lib.creditcard;

/**
 * The following class extends the AbstractCreditCard in order to create a
 * mastercard credit card object after validating the credit card's number.
 * 
 * @author Denis Lebedev
 * @version 9/27/2016
 */

public class MasterCard extends AbstractCreditCard {
	private static final long serialVersionUID = 42031768871L;

	/**
	 * This constructor passes to the AbstractCreditCard constructor the value
	 * of card type as Mastercard and the validated mastercard number.
	 * 
	 * @author Denis Lebedev
	 * @param number
	 */
	public MasterCard(String number) {
		super(CardType.MASTERCARD, validateNumber(number));
	}

	/**
	 * This method validates the Mastercard Credit Card number by checking if
	 * its structure corresponds to a Mastercard's structure.
	 * 
	 * @author Denis Lebedev
	 * @param number
	 * @return String
	 * @throws IllegalArgumentException if it does not respect the
	 * rules for the Master Card
	 */
	private static String validateNumber(String number) {

		/*Checking if the number is null, or if its length is not equals to 16
		 * or if the first two digits are not between 51 and 55.
		 * If one of the following is not respected, an Illegal Argument
		 * Exception with an appropriate message will be thrown.
	 	 */
		try{
			if (number == null 
					|| number.length() != 16 
					|| Integer.parseInt(number.substring(0, 2)) < 51
					|| Integer.parseInt(number.substring(0, 2)) > 55)
				throw new IllegalArgumentException("The Master card given does not respect the validation.");
		}catch(NumberFormatException npe){
			throw new IllegalArgumentException("The Master card given does not respect the validation.");
		}
		return number;
	}

}
