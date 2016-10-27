/**
 * 
 */
package dw317.lib;

import java.io.Serializable;

/**
 * The Email class will validate the given email
 * and override the equals, the hashCode, and toString.
 * @author Denis Lebedev
 * @version 9/27/2016
 */
public class Email implements Serializable, Comparable<Email> {
	private static final long serialVersionUID = 42031768871L;
	private final String address;
	
	/**
	 * The constructor assign a value to
	 * the address and call validateEmail
	 * @param address
	 */
	public Email (String address){
		this.address = validateEmail(address);
	}
	
	/**
	 * Return the address
	 * @return String
	 */
	public String getAddress(){
		return address;
	}
	
	/**
	 * Return the userId
	 * @return String
	 */
	public String getUserId(){
		return address.substring(0, address.indexOf('@') );
	}
	
	/**
	 * Return the host name
	 * @return String
	 */
	public String getHost(){
		return address.substring(address.indexOf('@') +1);
	}
	
	/**
	 * This equals method will compare two email
	 * and return true if they are both valid
	 * I look if the position of @ is equal
	 * to the other one. Also, I am comparing the
	 * userId and the hostName before returning true.
	 * @param Object
	 * @return boolean
	 */
	@Override
	public boolean equals(Object object){
		if(object == null) return false;
		
		if(this == object) return true;
		
		if(this.getClass() != object.getClass()) return false;
		
		Email data = (Email) object;
		
		if(this.address.equalsIgnoreCase(data.address))
			return true;
		
		return false;
	}
	
	/**
	 * Override the hashCode
	 * @return Integer
	 * @author Denis Lebedev
	 */
	@Override
	public int hashCode(){
		final int prime = 37;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		return result;
	}
	
	/**
	 * This method will sort the email if
	 * their host name are equal it will
	 * compare the UserId
	 * else it is host name.
	 * @param Email
	 * @return int
	 */
	@Override
	public int compareTo(Email email){
		
		if(this.getHost().equalsIgnoreCase(email.getHost()))
			return this.getUserId().compareToIgnoreCase(email.getUserId());
				
		return getHost().compareToIgnoreCase(email.getHost());
	}
	
	/**
	 * It overriding the toString by returning the address
	 * @return String
	 */
	@Override
	public String toString(){
		return address;
	}
	
	/**
	 * This private method will validate the email by
	 * dividing the string in 2 part and sending
	 * in  other private meethods
	 * @param email
	 * @return String
	 * @throws IllegalArgumentException if the userId is invalid
	 * or the host name is invalid
	 */
	private static String validateEmail(String email) throws IllegalArgumentException{
		
		if(email == null
			|| email.isEmpty() 
			|| email.indexOf('@') == -1
			|| email.indexOf('@', email.indexOf('@') +1) != -1)
			throw new IllegalArgumentException("Your email is empty or invalid");
		String userId = email.substring(0, email.indexOf('@'));
		String hostName = email.substring(email.indexOf('@') + 1);
		
		String specialChar= ".*[$'\\^,!#%&*()+=|}{:\"\';><?/~`].*";	
		
		//specialChar2 will handle the possibility that the user has a _ in his host name
		String specialChar2= ".*[$'\\^,!#%&*()+=|}{:\"\';><?/~`_].*";
		
		if(!validateUserId(userId, specialChar))
			throw new IllegalArgumentException("The UserId is invalid");
		 if(!validateHostName(hostName, specialChar2))
			 throw new IllegalArgumentException("The Host Name is invalid");		
		
		 return email;
	}
	
	/**
	 * The method will validate the userId
	 * by using regex
	 * @param userId
	 * @param specialChar
	 * @return boolean
	 */
	private static boolean validateUserId(String userId, String specialChar){
	
		if(userId.length() > 32 || userId.length() < 1
				|| userId.matches(specialChar)
				|| !userId.matches(".*[a-zA-Z].*") 
				|| (userId.charAt(0) == '.' || userId.charAt( userId.length() - 1 ) == '.')
				|| userId.charAt(userId.indexOf('.') + 1) == '.')
			return false;
		
		return true;
	}
	
	/**
	 * The method will validate the userId
	 * by using regex
	 * @param hostName
	 * @param specialChar
	 * @return boolean
	 */
	private static boolean validateHostName(String hostName, String specialChar){
		
		if(hostName.length() > 32 || hostName.length() < 1
				|| !hostName.matches(".*[a-zA-Z].*")
				|| hostName.charAt(0) == '-'
				|| hostName.charAt(hostName.length() -1) == '-'
				|| hostName.matches(".*\\.\\..*")
				|| hostName.matches(specialChar))
			return false;
		
		return true;	
	}
	

}