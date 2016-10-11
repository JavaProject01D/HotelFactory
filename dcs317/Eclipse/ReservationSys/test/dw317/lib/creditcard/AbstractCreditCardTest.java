package dw317.lib.creditcard;

import dw317.lib.creditcard.CreditCard.CardType;


/**
 * Test class of the AbstractCreditCard class containing different test cases.
 * @author Zahraa Horeibi
 * @version 9/27/2016
 */
public class AbstractCreditCardTest {

	public static void main(String[] args) {
	
		testTheTwoParameterConstructor();
		testGetNumber();
		testTheEqualsMethod();
		testTheHashCode();


	}
	
	
	public static void testTheTwoParameterConstructor() {
		
		testTheTwoParameterConstructor("Case 1 - Valid Data (Visa 4532139720295372)", CardType.VISA , "4532139720295372", true);
		testTheTwoParameterConstructor("Case 2 - Invalid Data: Missing Card Number (Visa) ", CardType.VISA , "", false);
		testTheTwoParameterConstructor("Case 3 - Invalid Data: Card Type set to null (null 4532139720295372) ", null , "4532139720295372", false);
		testTheTwoParameterConstructor("Case 4 - Invalid Data: Card number set to null (Visa null) ", CardType.VISA , null, false);
		testTheTwoParameterConstructor("Case 5 - Valid Data (MasterCard 5280894190496725)", CardType.MASTERCARD , "5280894190496725", true);
		testTheTwoParameterConstructor("Case 6 - Invalid Data: Missing Card Number (Mastercard) ", CardType.MASTERCARD , "", false);
		testTheTwoParameterConstructor("Case 7 - Invalid Data: Card Type set to null (null 5280894190496725) ", null , "5280894190496725", false);
		testTheTwoParameterConstructor("Case 8 - Invalid Data: Card number set to null (Mastercard null) ", CardType.MASTERCARD,  null, false);
		testTheTwoParameterConstructor("Case 9 - Valid Data (Amex 342263125107996)", CardType.AMEX , "342263125107996", true);
		testTheTwoParameterConstructor("Case 10 - Invalid Data: Missing Card Number (Amex) ", CardType.AMEX , "", false);
		testTheTwoParameterConstructor("Case 11 - Invalid Data: Card Type set to null (null 342263125107996) ", null, "342263125107996", false);
		testTheTwoParameterConstructor("Case 12 - Invalid Data: Card number set to null (Amex null) ", CardType.AMEX,  null, false);
	}
	
	public static void testTheTwoParameterConstructor( String testCase, CardType cardType, 
														String number, boolean expectValid ) {
		
		System.out.println("   " + testCase);
		
		try { 
	
			AbstractCreditCard card = (AbstractCreditCard) CreditCard.getInstance (cardType, number);
				   
			System.out.println("\tThe Credit Card was created: " + card.toString() );
			
			if (expectValid)
				System.out.println("\t\tYou credit card is valid!");
			
			if (!expectValid)
				System.out.println("\tError! Expected Valid. == FAILED Test ==");
		}
		
		catch ( IllegalArgumentException iae ) {
			System.out.print("\t" + iae.getMessage());
			
			if(expectValid)
				System.out.println("\tError! Expected Valid. == FAILED Test ==");
			
		}
		catch (Exception e) {
			System.out.print("\tUnexpected Exception Type" + e.getClass() +  "\t\t\n\t" + e.getMessage() + " == FAILED Test ==");
			if (expectValid)
				System.out.print(" Expected Valid.");
		}
		
		System.out.println("\n");
		
	}
	
	
	public static void testGetNumber() {
		
		System.out.println("Testing the getNumber method.");
		
		testGetNumber("Case 1: without leading/trailing spaces", "4532139720295372", "4532139720295372");
		testGetNumber("Case 2: with leading/trailing spaces", "   4532139720295372   ", "4532139720295372");
	}
	
	
	public static void testGetNumber ( String testCase, String number, 
														String expectedNumber ) {
		
		try {
		
		System.out.println("   " + testCase);
		
		AbstractCreditCard card = (AbstractCreditCard) CreditCard.getInstance( CardType.VISA, number);	

		System.out.print("\t The Credit Card instance was created: " + card.toString());
		
		if (!card.getNumber().equals(expectedNumber))
			System.out.println("  Error! Expected Invalid. == FAILED test ==");
		}
		catch ( IllegalArgumentException iae ) {
			System.out.print("\t" + iae.getMessage());

		}
		catch (Exception e) {
			System.out.print("\tUnexpected Exception Type" + e.getClass() +  "\t\t\n\t" + e.getMessage() + " == FAILED Test ==");

		}
		
		System.out.println("\n");
		
		
	}
	
	public static void testTheEqualsMethod() {
		
		AbstractCreditCard card = (AbstractCreditCard) CreditCard.getInstance (CardType.VISA , "4532139720295372");
		
		testTheEqualsMethod("Case 1 - Valid Data", card, CardType.VISA , "4532139720295372");
		testTheEqualsMethod("Case 2 - Invalid Data", card, CardType.MASTERCARD , "5280894190496725" );
		
		
	}
	
	public static void testTheEqualsMethod ( String testCase, CreditCard card, CardType cardType, String number) {
	
		try {
			System.out.println("   " + testCase);
			AbstractCreditCard createdCard = (AbstractCreditCard) CreditCard.getInstance (cardType , number);
			System.out.println("\tComparing " + card.toString() + " with " + createdCard.toString() );
			
			if (card.equals(createdCard) == true) 
				System.out.println("\t\tThe given rooms are the same.The equals method is valid!");
			
			if (card.equals(createdCard) == false) 
				System.out.println(" \t\tThe given rooms are not the same. The equals method is valid!");
			
			}
			catch ( IllegalArgumentException iae ) {
				System.out.print("\t" + iae.getMessage());

			}
			catch (Exception e) {
				System.out.print("\tUnexpected Exception Type" + e.getClass() +  "\t\t\n\t" + e.getMessage() + " == FAILED Test ==");

			}
			System.out.println("\n");	
	}
	
	public static void testTheHashCode() {
	
		System.out.println("********** Testing the hashCode method **********\n");
		
		AbstractCreditCard card = (AbstractCreditCard) CreditCard.getInstance (CardType.VISA , "4532139720295372");
		AbstractCreditCard card2 = (AbstractCreditCard) CreditCard.getInstance (CardType.VISA , "4532139720295372");
		
		System.out.println("Hashcode of first card: " + card.hashCode());
		System.out.println("Hashcode of second card: " + card2.hashCode());
				
				if ( card.hashCode() == card2.hashCode() ) {
					System.out.println("\nThe hashcodes are the same, the hashcode method is valid!");
				}
	}	
}

