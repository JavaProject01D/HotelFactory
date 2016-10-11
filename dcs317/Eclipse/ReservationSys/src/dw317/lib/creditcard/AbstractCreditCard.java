package dw317.lib.creditcard;

/**
 * The following abstract class implements the CreditCard class to allow us to created Credit Cards 
 * and validate their numbers using the Luhn Algorithm. 
 * 
 * @author Zahraa Horeibi
 * @version 9/27/2016
 */

public abstract class AbstractCreditCard implements CreditCard {

	// Variables instantiation.

	private static final long serialVersionUID = 42031768871L;
	private final CardType cardType;
	private final String number;

	/**
	 * This constructor instantiates the Credit Card Type and Number. It throws
	 * an IllegalArgumentException if one of them is null.
	 * 
	 * @author Zahraa Horeibi
	 * @param cardType
	 * @param number
	 * @throws IllegalArgumentException if number is equal to null
	 * or cardType is equal
	 */

	public AbstractCreditCard(CardType cardType, String number) throws IllegalArgumentException {

		// Assigning each passed arguments to the class's fields value.
		this.number = number;
		this.cardType = cardType;

		/*Checking if the passed values are null. If they are, throwing an
		 * illegal argument exception.
		 */
		if (number.equals(null))
			throw new IllegalArgumentException("Number cannot be null!");

		if (cardType.equals(null))
			throw new IllegalArgumentException("Card Type cannot be null!");
	}

	/**
	 * This method validates the credit card numbers using the Luhn Algorithm
	 * and returns the credit card number if its valid. If its not valid, it
	 * throws an IllegalArgumentException.
	 * 
	 * @param number
	 * @return String
	 * @author Zahraa Horeibi, Denis Lebedev
	 * @throws IllegalArgumentException if the card does
	 * not respects the Luhn algorithm.
	 */

	private static String validateLuhnAlgorithm(String number) throws IllegalArgumentException {

		/*Validating if the given Credit Card Number respects the basic rules
		 * of any Credit Card Number. If it does not, an illegal argument
		 * exception is being thrown.
		 */
		if (number.indexOf(" ") != -1)
			number = number.replaceAll(" ", "");

		if (number.isEmpty() || number == null || number.matches(".*[a-zA-Z].*") || number.length() > 18)
			throw new IllegalArgumentException("The card given do not respect the rules");

		// Assigning variable. temp will hold the number
		int temp = 0;

		// total will be the value of all the credit card numbers added.
		int total = 0;

		/*Using a for loop, we are going through the Credit Card Number from
		 * right to left
		 */
		for (int i = number.length() - 1; i >= 0; i--) {
			/* We are converting each character to a numerical value of the
			 * string which contains the Credit Card Number.
			 */
			temp = Character.getNumericValue(number.charAt(i));

			// We are looking for the values which are positioned at odd indices
			if (((number.length() - 1) - i) % 2 == 1)
				temp *= 2; // Multiplying by 2 the digits at odd indices and
							// temporarily assigning it to temp.
			/* Checking if the value held by temp is greater than 9, if so, we
			 * are subtracting 9 from it.
			 */
			if (temp > 9)
				temp -= 9;
			// Appending the different values held by temp to the total.
			total += temp;

		} // End of for loop

		/* Checking whether the modulus 10 of total is equal to 0, if not, we
		 * are throwing an Illegal Argument Exception informing the user that
		 * their credit card number is invalid.
		 * If modulus 10 of total is equal to 0, the Credit Card Number respects
		 * the luhn algorithm which makes it a valid credit card number so we
		 * return that number.
		 */
		if (total % 10 != 0)
			throw new IllegalArgumentException("Invalid card!" + temp + "\n" + number.toString());
		else
			return number;
	}

	/**
	 * This method is a helper method for the validateLuhnAlgorithm method which
	 * returns a boolean checking if the given String only contains numeric
	 * values.
	 * 
	 * @param numbers
	 * @author Zahraa Horeibi
	 * @return boolean
	 */
	public static boolean isNumeric(String numbers) {

		// Using a loop, we can get the index of every character from the String
		for (int i = 0; i < numbers.length(); i++) {

			// Checking whether the character the index i is a digit.
			if (!Character.isDigit(numbers.charAt(i)))
				return false;
		}

		return true;
	}

	/**
	 * This overridden equals method checks for equality between two objects of
	 * type Credit Card.
	 * 
	 * @author Zahraa
	 * @return boolean
	 */
	@Override
	public final boolean equals(Object object) {

		// Comparing the hashCodes of the class' object with the passed one.
		if (this == object)
			return true;

		// Checking if the passed object if null.
		if (object == null)
			return false;

		/* Checking if the passed object is an instance of a Credit Card. If so,
		 * we are casting it so we can treat it as a Credit Card.
		 */
		if (object instanceof CreditCard) {
			CreditCard card = (CreditCard) object;

			/* Checking if the class' Credit Card Type is the same as the
			 * previously instantiated Credit Card which was mainly the passed
			 * object.
			 */
			if (!this.cardType.equals(card.getType()))
				return false;

			if (!this.number.equals(card.getNumber()))
				return false;

			return true;

		}

		return true;

	}

	/**
	 * This overridden hashCode method returns the haschCode value of the
	 * cardType and number fields.
	 * 
	 * @author Zahraa
	 * @return int
	 */
	@Override
	public final int hashCode() {
		final int prime = 37; // We choose a random number and assign it to
								// prime.
		int result = 1; // We choose a random number and assign it to result.

		result = prime * result + ((cardType == null) ? 0 : cardType.hashCode());

		return result;

	}

	/**
	 * This accessor method returns the Credit Card number.
	 * 
	 * @author Zahraa
	 * @return String
	 */

	@Override
	public String getNumber() {
		return this.number;

	}

	/**
	 * This accessor method returns the Credit Card Type.
	 * 
	 * @author Zahraa
	 * @return CardType
	 */

	@Override
	public CardType getType() {
		return this.cardType;

	}

	/**
	 * This overridden toString method returns the Credit Card Type and Number
	 * with an asterisk between both values as a String.
	 * 
	 * @author Zahraa Horeibi
	 * @return String
	 */

	@Override
	public String toString() {
		return this.cardType + "*" + this.number;

	}

}
