package dw317.lib;

import dw317.lib.Email;

/**
 * The EmailTest will test the majority
 * of the test case.
 * @author Denis Lebedev
 * @version 9/27/2016
 *
 */

public class EmailTest {

	public static void main(String[] args) {
		
		validateEmail();
		
		System.out.println("\n_______________Validate Methods_________________\n");
		
		validMethod();

	}
	
	private static void validateEmail(){
		System.out.println("Creating test case for different Email: \n");
		
		validateEmail("Case 1 Valid Email","hiho@gmail.coj", true);
		
		validateEmail("\nCase 2 Null Email", null , false);
		
		validateEmail("\nCase 3 Empty Email", "" , false);
		
		validateEmail("\nCase 4 Empty Email with a space", " " , false);
		
		validateEmail("\nCase 5 no UserId", "@gmail.cod" , false);
		
		validateEmail("\nCase 6 no Host Name", "bnla@" , false);
		
		validateEmail("\nCase 7 Dot at the end", "hello.@gmai.asd" , false);
		
		validateEmail("\nCase 8 Dot in the front", ".hello@gmai.asd" , false);
		
		validateEmail("\nCase 9 two Dot close each other", "hel..lo@gmai.asd" , false);
		
		validateEmail("\nCase 10 two dot at the End", "hello..@gmai.asd" , false);
		
		validateEmail("\nCase 11 Two dot at the beginning", "..hello@gmai.asd" , true);
		
		validateEmail("\nCase 12 hyphen at the begining", "-hello@gmai.asd" , true);
		
		validateEmail("\nCase 13 underscore", "hel_lo@gmai.asd" , true);
		
		validateEmail("\nCase 14 host name start with an hypen", "hel_lo@-gmai.asd" , false);
		
		validateEmail("\nCase 15 host name has two dot", "hel_lo@hehe..asd" , false);
		
		validateEmail("\nCase 16 host name ends with an hypen", "hel_lo@gmai.asd-" , false);
		
		validateEmail("\nCase 17 Invalid character in Host Name", "hel_lo@g,mai.asd" , false);
		
		validateEmail("\nCase 18 Invalid character in Host Name", "he$l_l$o@gmai.asd" , false);
		
	}
	
	private static void validateEmail(String testCase, String email, boolean validation){
		
		System.out.println(testCase + "\nEmail: " + email);	
		
		try{
			
			Email mail = new Email(email);
					
			if(validation){
				System.out.println("\n\tThe current email is VALID");
				System.out.println("\tToString: " + "\n\t---------------------\n\t" +
									mail.toString() + "\n\t---------------------\n");
			}
			
			if(!validation)
				System.out.println("\tError the email SHOULD be INVALID please fix it. <===>FAILED TEST<===>\n");
		}
		catch (IllegalArgumentException iae){
			System.out.print("\n\t"+ iae.getMessage());
			if (!validation)
				System.out.println("\n\tError the Email should not be valid. <===>FAILED TEST<===>\n");
		}
		catch (Exception b){
			System.out.print("\tUNEXPECTED EXCEPTION TYPE! " + b.getClass() +  " "  + b.getMessage() + " ==== FAILED TEST ====\n");
			if (validation)
				System.out.print("It was expected VALID");
		}
		
	}
	
	private static void validMethod(){
		validMethod("Testing@gmai.com" , "Testing@gmai.com");
		
		validMethod("Hahaha@pff.com", "JokesOnYou@sdf");
	}
	
	private static void validMethod(String email, String mail){
		Email test = new Email(email);
		Email test2 = new Email(mail);
		System.out.println("\nUsing the String: " + test.getAddress() + " and " + test2.getAddress());
		
		System.out.println("\nTesting Equal Method: \n");
		if(test.equals(test2))
			System.out.println(test.getAddress() + " is equal to " + test2.getAddress());
		else
			System.out.println(test.getAddress() + " is not equal to " + test2.getAddress());
		
		System.out.println("\n______________HashCode____________\n");
		
		System.out.println("First String: " + test.hashCode());
		System.out.println("Second String: " +test2.hashCode());
		
		System.out.println("\n______________CompareTo____________\n");
		
		System.out.println("CompareTo return: " + test.compareTo(test2));
	}
	

}
