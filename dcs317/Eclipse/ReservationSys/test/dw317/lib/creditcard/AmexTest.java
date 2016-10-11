/**
 * 
 */
package dw317.lib.creditcard;

/**
 * This following code will try various
 * test case base on the Luhn algorithm
 * and on the Master Card cards rules
 * 
 * @author Denis Lebedev
 * @version 9/27/2016
 */
public class AmexTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testAmex();//commentBlabh
	}
	
	private static void testAmex(){
		System.out.println("Creating Different Case For Visa Only\n");
		
		testAmex("Case 1 - Valid data", "347262714441704", true);
		
		testAmex("\nCase 2 - 16 digits", "3472627144417045", false);
		
		testAmex("\nCase 3 - 13 digits", "34726271444170", false);
		
		testAmex("\nCase 4 - Empty String", "", false);
		
		testAmex("\nCase 5 - Starting With 38", "387262714441704", false);
		
		testAmex("\nCase 6 - Starting With 33", "337262714441704", false);
		
		testAmex("\nCase 7 - Empty String", null, false);
		
		testAmex("\nCase 8 - String of letters", "Blahhaha", false);
		
		testAmex("\nCase 9 - Valid Amex card", "342752067998156", true);
		
		testAmex("\nCase 10 - Master Card",  "5537901807242821", false);
		
		testAmex("\nCase 11 - Visa Card", "4929760044505195", false);
		
	}
	
	private static void testAmex(String testCase, String number, boolean validation){
		
		System.out.println(testCase);
		
		try{
			Amex amexTest = new Amex(number);
			System.out.println("Creating a new instance: " + amexTest);
			
			if(validation)
				System.out.println("\tThe card given is Valid!");
				
			if(!validation)
				System.out.println("\tError the Master Card should not be valid and no throw have been made. ----FAILED TEST----\n");
		}
		catch (IllegalArgumentException iae){
			System.out.print("\t"+ iae.getMessage());
			if (!validation)
				System.out.println("\n\tError the Master Card should not be valid. ----FAILED TEST----\n");
		}
		catch (Exception b){
			System.out.print("\tUNEXPECTED EXCEPTION TYPE! " + b.getClass() +  " "  + 				b.getMessage() + " ==== FAILED TEST ====");
			if (validation)
				System.out.print(" Expected Valid.");
		}
	}

}
