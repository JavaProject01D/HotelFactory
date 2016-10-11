package dw317.lib.creditcard;

/**
 * @author Sevan Topalian
 * @version 27/09/2016
 *
 *         This class tests the creation of the 3 different types of credit
 *         cards (Amex, MasterCard, and Visa) through the CreditCard interface
 */
public class CreditCardTest {

	public static void main(String[] args) {
		creditCardTest();
	}

	private static void creditCardTest() {
		System.out.println("\nTesting Amex");
		testAmex("\nCase 1 - Valid Amex", "373806051264421", true);
		testAmex("\nCase 2 - Invalid card (MasterCard)", "5564307615500788", false);
		testAmex("\nCase 3 - Invalid card (Visa)", "4929009920060408", false);

		System.out.println("\nTesting MasterCard");
		testMasterCard("\nCase 1 - Invalid card (Amex)", "373806051264421", false);
		testMasterCard("\nCase 2 - Valid MasterCard", "5564307615500788", true);
		testMasterCard("\nCase 3 - Invalid card (Visa)", "4929009920060408", false);

		System.out.println("\nTesting Visa");
		testVisa("\nCase 1 - Invalid card (Amex)", "373806051264421", false);
		testVisa("\nCase 2 - Invalid card (MasterCard)", "5564307615500788", false);
		testVisa("\nCase 3 - Valid Visa", "4929009920060408", true);
	}

	private static void testAmex(String testCase, String cardNumber, boolean isValid) {
		System.out.println(testCase);

		try {
			CreditCard amexTest = new Amex(cardNumber);

			System.out.println("Created Amex card: " + amexTest.toString());
		} catch (IllegalArgumentException iae) {
			System.out.println("Illegal arguments\n" + iae.getMessage());
		} catch (Exception e) {
			System.out.println("Error. Unexpected exception\n" + e.getClass() + "\n" + e.getMessage());
		}

		if (isValid)
			System.out.println("The case should be valid");
		else
			System.out.println("The case should not be valid");
	}

	private static void testMasterCard(String testCase, String cardNumber, boolean isValid) {
		System.out.println(testCase);

		try {
			CreditCard masterCardTest = new MasterCard(cardNumber);

			System.out.println("Created MasterCard card: " + masterCardTest.toString());
		} catch (IllegalArgumentException iae) {
			System.out.println("Illegal arguments\n" + iae.getMessage());
		} catch (Exception e) {
			System.out.println("Error. Unexpected exception\n" + e.getClass() + "\n" + e.getMessage());
		}

		if (isValid)
			System.out.println("The case should be valid");
		else
			System.out.println("The case should not be valid");
	}

	private static void testVisa(String testCase, String cardNumber, boolean isValid) {
		System.out.println(testCase);

		try {
			CreditCard visaTest = new Visa(cardNumber);

			System.out.println("Created Amex card: " + visaTest.toString());
		} catch (IllegalArgumentException iae) {
			System.out.println("Illegal arguments\n" + iae.getMessage());
		} catch (Exception e) {
			System.out.println("Error. Unexpected exception\n" + e.getClass() + "\n" + e.getMessage());
		}

		if (isValid)
			System.out.println("The case should be valid");
		else
			System.out.println("The case should not be valid");
	}
}