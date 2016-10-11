package group5.hotel.business;

import java.util.Optional;

import dw317.lib.creditcard.Amex;
import dw317.lib.creditcard.CreditCard;
import dw317.lib.creditcard.MasterCard;
import dw317.lib.creditcard.Visa;

/**
 * @author Sevan Topalian
 * @version 27/09/2016
 * 
 *         This class tests the DawsonCustomer methods. The methods tested are
 *         getName, getEmail, getCreditCard, setCreditCard, equals, and
 *         compareTo
 */
public class DawsonCustomerTest {

	public static void main(String[] args) {
		testDawsonCustomer();
	}

	private static void testDawsonCustomer() {
		System.out.println("Creating Different Test Cases for DawsonCustomer");

		System.out.println("\nTesting getName");
		testGetName("\nCase 1 - Valid data", "Sevan", "Topalian", "sevantopalian@test.com", true);

		System.out.println("\nTesting getEmail");
		testGetEmail("\nCase 1 - Valid data", "Sevan", "Topalian", "sevantopalian@test.com", true);

		System.out.println("\nTesting setCreditCard and getCreditCard");
		System.out.println("\nTesting Amex");
		testAmexMethods("\nCase 1 - Valid Amex", "Sevan", "Topalian", "sevantopalian@test.com", "373806051264421",
				true);
		testAmexMethods("\nCase 2 - Invalid card (MasterCard)", "Sevan", "Topalian", "sevantopalian@test.com",
				"5564307615500788", false);
		testAmexMethods("\nCase 3 - Invalid card (Visa)", "Sevan", "Topalian", "sevantopalian@test.com",
				"4929009920060408", false);

		System.out.println("\nTesting MasterCard");
		testMasterCardMethods("\nCase 1 - Invalid card (Amex)", "Sevan", "Topalian", "sevantopalian@test.com",
				"373806051264421", false);
		testMasterCardMethods("\nCase 2 - Valid MasterCard", "Sevan", "Topalian", "sevantopalian@test.com",
				"5564307615500788", true);
		testMasterCardMethods("\nCase 3 - Invalid card (Visa)", "Sevan", "Topalian", "sevantopalian@test.com",
				"4929009920060408", false);

		System.out.println("\nTesting Visa");
		testVisaMethods("\nCase 1 - Invalid card (Amex)", "Sevan", "Topalian", "sevantopalian@test.com",
				"373806051264421", false);
		testVisaMethods("\nCase 2 - Invalid card (MasterCard)", "Sevan", "Topalian", "sevantopalian@test.com",
				"5564307615500788", false);
		testVisaMethods("\nCase 3 - Valid Visa", "Sevan", "Topalian", "sevantopalian@test.com", "4929009920060408",
				true);

		System.out.println("\nTesting equals");
		testEquals("\nCase 1 - Two equal customers", "Sevan", "Topalian", "sevantopalian@test.com", "Sevan", "Topalian",
				"sevantopalian@test.com", true);
		testEquals("\nCase 2 - Different names and emails", "Sevan", "Topalian", "sevantopalian@test.com", "NotSevan",
				"NotTopalian", "notsevantopalian@test.com", false);
		testEquals("\nCase 3 - Same name, different emails", "Sevan", "Topalian", "sevantopalian@test.com", "Sevan",
				"Topalian", "notsevantopalian@test.com", false);

		System.out.println("\nTesting compareTo");
		testCompareTo("\nCase 1 - Two equal customers", "Sevan", "Topalian", "sevantopalian@test.com", "Sevan",
				"Topalian", "sevantopalian@test.com");
		testCompareTo("\nCase 2 - Different names and emails", "Sevan", "Topalian", "sevantopalian@test.com",
				"NotSevan", "NotTopalian", "notsevantopalian@test.com");
		testCompareTo("\nCase 3 - Same name, different emails", "Sevan", "Topalian", "sevantopalian@test.com", "Sevan",
				"Topalian", "notsevantopalian@test.com");
	}

	private static void testGetName(String testCase, String firstName, String lastName, String email, boolean isValid) {
		System.out.println(testCase);

		try {
			DawsonCustomer customerTest = new DawsonCustomer(firstName, lastName, email);

			System.out.println("Got name: " + customerTest.getName());
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

	private static void testGetEmail(String testCase, String firstName, String lastName, String email,
			boolean isValid) {
		System.out.println(testCase);

		try {
			DawsonCustomer customerTest = new DawsonCustomer(firstName, lastName, email);

			System.out.println("Got email: " + customerTest.getEmail());
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

	private static void testAmexMethods(String testCase, String firstName, String lastName, String email,
			String cardNumber, boolean isValid) {
		System.out.println(testCase);

		try {
			DawsonCustomer customerTest = new DawsonCustomer(firstName, lastName, email);

			Optional<CreditCard> amexTest = Optional.of(new Amex(cardNumber));

			customerTest.setCreditCard(amexTest);
			System.out.println("Set Amex card: " + cardNumber);

			System.out.println("Got credit card: " + customerTest.getCreditCard());
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

	private static void testMasterCardMethods(String testCase, String firstName, String lastName, String email,
			String cardNumber, boolean isValid) {
		System.out.println(testCase);

		try {
			DawsonCustomer customerTest = new DawsonCustomer(firstName, lastName, email);

			Optional<CreditCard> masterCardTest = Optional.of(new MasterCard(cardNumber));

			customerTest.setCreditCard(masterCardTest);
			System.out.println("Set MasterCard card: " + cardNumber);

			System.out.println("Got credit card: " + customerTest.getCreditCard());
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

	private static void testVisaMethods(String testCase, String firstName, String lastName, String email,
			String cardNumber, boolean isValid) {
		System.out.println(testCase);

		try {
			DawsonCustomer customerTest = new DawsonCustomer(firstName, lastName, email);

			Optional<CreditCard> visaTest = Optional.of(new Visa(cardNumber));

			customerTest.setCreditCard(visaTest);
			System.out.println("Set Visa card: " + cardNumber);

			System.out.println("Got credit card: " + customerTest.getCreditCard());
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

	private static void testEquals(String testCase, String firstName1, String lastName1, String email1,
			String firstName2, String lastName2, String email2, boolean isValid) {
		System.out.println(testCase);

		try {
			DawsonCustomer customerTest1 = new DawsonCustomer(firstName1, lastName1, email1);
			DawsonCustomer customerTest2 = new DawsonCustomer(firstName2, lastName2, email2);

			if (customerTest1.equals(customerTest2))
				System.out.println("The two customers are equal");
			else
				System.out.println("The two customers are not equal");
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

	private static void testCompareTo(String testCase, String firstName1, String lastName1, String email1,
			String firstName2, String lastName2, String email2) {
		System.out.println(testCase);

		try {
			DawsonCustomer customerTest1 = new DawsonCustomer(firstName1, lastName1, email1);
			DawsonCustomer customerTest2 = new DawsonCustomer(firstName2, lastName2, email2);

			System.out.println(customerTest1.compareTo(customerTest2));
		} catch (IllegalArgumentException iae) {
			System.out.println("Illegal arguments\n" + iae.getMessage());
		} catch (Exception e) {
			System.out.println("Error. Unexpected exception\n" + e.getClass() + "\n" + e.getMessage());
		}
	}
}