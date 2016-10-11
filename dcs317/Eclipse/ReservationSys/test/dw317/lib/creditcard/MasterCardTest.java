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
public class MasterCardTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		testMasterCard();
	}
	
	private static void testMasterCard(){
		System.out.println("Creating Different Case For MasterCard Only\n");
		
		testMasterCard("Case 1 - Valid data", "5169370501271822", true);
		
		testMasterCard("\nCase 2 - 17 digits", "5169370501271825", false);
		
		testMasterCard("\nCase 3 - 15 digits", "516937050127182", false);
		
		testMasterCard("\nCase 4 - Empty String", "", false);
		
		testMasterCard("\nCase 5 - Starting With 56", "566937050127182", false);
		
		testMasterCard("\nCase 6 - Starting With 50", "5090896485570575", false);
		
		testMasterCard("\nCase 7 - Null", null, false);
		
		testMasterCard("\nCase 8 - String of letters", "Blahhaha", false);
		
		testMasterCard("\nCase 9 - Valid Master Card", "5537901807242821", true);
		
		testMasterCard("\nCase 10 - Visa Card",  "4929760044505195", false);
		
		testMasterCard("\nCase 11 - Amex Card", "342752067998156", false);
		
		testMasterCard("\nCase 12 - Long String", "asdfghjklzxcvbnmqwe", false);
	}
	
	private static void testMasterCard(String testCase, String number, boolean validation){
		
		System.out.println(testCase);
		
		try{
			MasterCard masterCardTest = new MasterCard(number);
			System.out.println("Creating a new instance: " + masterCardTest);
			
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
			System.out.print("\tUNEXPECTED EXCEPTION TYPE! " + b.getClass() +  " "  + b.getMessage() + " ==== FAILED TEST ====");
			if (validation)
				System.out.print(" Expected Valid.");
		}
	}

}