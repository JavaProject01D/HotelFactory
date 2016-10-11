/**
 * 
 */
package dw317.lib.creditcard;

/**
 * This following code will try various
 * test case base on the Luhn algorithm
 * and on the Visa cards rules
 * 
 * @author Denis Lebedev
 * @version 9/27/2016
 */
public class VisaTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testVisa();
	}
	
	private static void testVisa(){
		System.out.println("Creating Different Case For Visa Only\n");
		
		testVisa("Case 1 - Valid data", "4539666718021199", true);
		
		testVisa("\nCase 2 - 17 digits", "45396667180211995", false);
		
		testVisa("\nCase 3 - 15 digits", "453966671802119", false);
		
		testVisa("\nCase 4 - Empty String", "", false);
		
		testVisa("\nCase 5 - Starting With 5", "5539666718021199", false);
		
		testVisa("\nCase 6 - Starting With 3", "3539666718021199", false);
		
		testVisa("\nCase 7 - Null", null, false);
		
		testVisa("\nCase 8 - String of letters", "Blahhaha", false);
		
		testVisa("\nCase 9 - Valid Visa card", "4929760044505195", true);
		
		testVisa("\nCase 10 - Master Card",  "5537901807242821", false);
		
		testVisa("\nCase 11 - Amex Card", "342752067998156", false);
		
		
	}
	
	private static void testVisa(String testCase, String number, boolean validation){
		
		System.out.println(testCase);
		
		try{
			Visa visaTest = new Visa(number);
			System.out.println("Creating a new instance: " + visaTest);
			if(validation)
				System.out.println("\tThe card given is Valid!");
			
			if(!validation)
				System.out.println("\tError the Master Card should not be valid. ----FAILED TEST----\n");
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