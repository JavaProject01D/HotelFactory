package dw317.lib;

import java.util.Optional;

/**
 * 
 * @author Denis Lebedev
 * @version Lab3
 */
public class AddressTest {

	public static void main(String[] args) {
		
		
		testTheThreeParameterConstructor();
		
		testGetCivicNumber();		

		testSetCivicNumber();

	}

	private static void testTheThreeParameterConstructor() {
		
		System.out.println("\nTesting the three parameter constructor.");
		
		testTheThreeParameterConstructor(
			   "Case 1 - Valid data (3040 Sherbrooke Westmount)", "3040",
				   "Sherbrooke", "Westmount","","", true);
		
		testTheThreeParameterConstructor(
				"Case 2 - Invalid data � empty civicNumber ( Sherbrooke Westmount)","",
					"Sherbrooke","Westmount","","", false);

		testTheThreeParameterConstructor( 
				" Case 3 - Invalid data - empty civicNumber ( Maisonneuve Westmount )" , " ",
				"Maisonneuve", "Westmount","","", false);
		
		testTheThreeParameterConstructor(
				"Case 4 - Invalid data - empty StreetName ( 2123 Westmount )" , "2123",
				"", "Westmount","","", false);
		
		testTheThreeParameterConstructor(
				"Case 5 - Invalid data - empty StreetName ( 2123 Westmount )" , "2123",
				"       ", "Westmount","","", false);
		
		testTheThreeParameterConstructor( 
				"Case 6 - Invalid data - empty city ( 2123 Maisonneuve )" , "2123", 
				"Maisonneuve", "","","", false);
			
		testTheThreeParameterConstructor( 
				"Case 7 - Invalid data - empty city ( 2123 Maisonneuve )" , "2123", 
				"Maisonneuve", "       ","","", false);
		

		testTheThreeParameterConstructor(
				"Case 8 - Invalid data � null civicNumber (null Sherbrooke Westmount)",
				null,"Sherbrooke","Westmount","","", false);

		
		testTheThreeParameterConstructor(
				"Case 9 - Invalid data � null streetName (2123 null Westmount)",
				"2123", null , "Westmount" ,"","", false );
		
		testTheThreeParameterConstructor(
				"Case 10 - Invalid data � null city (2123 Sherbrooke null)",
				"2123" ,"Sherbrooke", null,"","", false);	
		
	}


	private static void testTheThreeParameterConstructor(String testCase,
			String civicNumber, String streetName, String city, String province, String code,
			boolean expectValid) {

		System.out.println("   " + testCase);

		try {
			Address theAddress = new Address(civicNumber, streetName, city, Optional.ofNullable(province), Optional.ofNullable(code));
			System.out.print("\tThe Address instance was created: " + theAddress);
			
			if (!expectValid)
				System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
		}
		catch (IllegalArgumentException iae)	{
			System.out.print("\t"+ iae.getMessage());
			if (expectValid)
				System.out.print("  Error! Expected Valid. ==== FAILED TEST ====");
		}
		catch (Exception e) {
			System.out.print("\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() +  " "  + 				e.getMessage() + " ==== FAILED TEST ====");
			if (expectValid)
				System.out.print(" Expected Valid.");
		}

		System.out.println("\n");
    }


	private static void testGetCivicNumber()
	{
		System.out.println("\nTesting the getCivicNumber method.");
		testGetCivicNumber("Case 1: 3040 without leading/trailing spaces",
				"3040","3040");
		testGetCivicNumber("Case 2: 3040 with leading/trailing spaces",
				"    3040    ","3040");
	}
	
	private static void testGetCivicNumber(String testCase, 
			String civicNumber, String expectedCivicNumber)
	{
		System.out.println("   " + testCase);
		Address theAddress = 
				new Address (civicNumber, "Sherbrooke","Westmount", Optional.ofNullable(null), Optional.ofNullable(null));
		System.out.print("\tThe Address instance was created: " + theAddress);

		if (!theAddress.getCivicNumber().equals(expectedCivicNumber))
			System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");

		System.out.println("\n");
	}

	private static void testSetCivicNumber() {
		System.out.println("\nTesting the setCivicNumber method.");
		testSetCivicNumber("Case 1: Valid - 2086 without leading/trailing spaces",
				"2086","2086",true);
		testSetCivicNumber("Case 2: Invalid null civic number",
				null,"",false);
	}
	
	private static void testSetCivicNumber(String testCase, 
			String civicNumber, String expectedCivicNumber,boolean expectValid){
		System.out.println("   " + testCase);
		Address theAddress = 
				new Address ("3040", "Sherbrooke","Westmount", Optional.ofNullable(null), Optional.ofNullable(null));
		try {
			theAddress.setCivicNumber(civicNumber);
			System.out.print("\tThe Address instance was created: " + theAddress);
			
			if (!theAddress.getCivicNumber().equals(expectedCivicNumber))
				System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
		}
		catch (IllegalArgumentException iae) {
			System.out.print("\t"+ iae.getMessage());
			if (expectValid)
				System.out.print("  Error! Expected Valid. ==== FAILED TEST ====");
		}
		catch (Exception e) {
			System.out.print("\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " +
					e.getMessage() + " ==== FAILED TEST ====");
			if (expectValid)
				System.out.print(" Expected Valid.");
		}

		System.out.println("\n"); //zahraa was heredeee
	}


	
	
}
