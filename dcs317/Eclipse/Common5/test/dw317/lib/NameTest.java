package dw317.lib;

import dw317.lib.Name;

/**
 * The NameTest will test the majority
 * of possibility that a name can have.
 * @author Denis Lebedev
 * @version 9/27/2016
 */

public class NameTest {

	public static void main(String[] args) {
		
		nameTest();
		System.out.println("--------------------------------------\n Testing Methods \n");
		validMethod();
	}
	
	private static void nameTest(){
		System.out.println("Creating different test case for the the Name class\n");
		
		nameTest("Case 1 Valid Name: ", "Jean", "Codworth", true);
		
		nameTest("\nCase 2 Invalid First Name: ", "J", "Codworth", false);
		
		nameTest("\nCase 3 Invalid Last Name: ", "Jean", "C", false);
		
		nameTest("\nCase 4 Invalid Start with space: ", " Jean", "Codworth", false);
		
		nameTest("\nCase 5 Valid Name with an underscore: ", "J-k", "Codworth", true);
		
		nameTest("\nCase 6 Valid Last Name with a space: ", "Jeff", "Cod Worth", true);

		nameTest("\nCase 7 Valid Last Name with an ': ", "Kougar", "Cod's", true);
		
		nameTest("\nCase 8 Valid First Name with ': ", "Kouga'r", "Cod's", true);
		
		nameTest("\nCase 9 Valid First Name with a space: ", "Kouga r", "Cod's", true);
		
		nameTest("\nCase 10 Invalid Name with two -- close: ", "Koug--ar", "Cod's", false);
	}
	
	private static void nameTest(String testCase, String fName, String lName, boolean validation){
		
		System.out.println(testCase + "\nFirst Name: " + fName + "\tLast Name: " + lName);
		
		try{
			Name name = new Name(fName, lName);
			
			if(validation){
				System.out.println("\n\tThe parameters are valid");
				System.out.println("\tToString: " + "\n\t---------------------------\n\t" + name.toString() + "\n\t---------------------------\n");
			}
			
			if(!validation)
				System.out.println("\tError a paramater SHOULD be INVALID please fix it. <===>FAILED TEST<===>\n");
		}
		catch (IllegalArgumentException iae){
			System.out.println("\n\t"  + iae.getMessage());
			if(!validation)
				System.out.println("\tError the given parameter should not be valid. <===>FAILED TEST<===>\n");
			
		}
		catch (Exception e){
			System.out.println("\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() +  " " + e.getMessage() + " <===>FAILED TEST<===>");
			if(validation)
				System.out.println("It was expected VALID");
		}
	}
	
	
	private static void validMethod(){
		
		validMethod("George", "Couja", "George", "Couja");
		
		validMethod("ComparSir", "NameOh", "Hug-Ba", "Jingle");
	}
	
	private static void validMethod(String firstName, String lastName, String fName, String lName){
		Name test = new Name(firstName, lastName);
		Name test2 = new Name(fName, lName);
		System.out.println("\nUsing the String: " + test.getFullName() + " and " + test2.getFullName());

		
		System.out.println("\nTesting Equal Method: \n");
		if(test.equals(test2))
			System.out.println(test.getFullName() + " is equal to " + test2.getFullName());
		else
			System.out.println(test.getFullName() + " is not equal to " + test2.getFullName());
		
		System.out.println("\n______________HashCode____________\n");
		
		System.out.println("First String: " + test.hashCode());
		System.out.println("Second String: " +test2.hashCode());
	}
}
