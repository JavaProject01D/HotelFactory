package dw317.lib;

import java.io.Serializable;

/** This class validates the first and last name,
 * There are also setters and getters.
 * We override the equals, compareTo and the hashCode. 
 * @author Denis Lebedev
 * @version 9/26/2016
 */
public class Name implements Comparable<Name>, Serializable{
	private static final long serialVersionUID = 42031768871L;
	private String firstName;
	private String lastName;
	private String fullName;

	/** The constructor assigns the validated 
	 * values and places them into the variables
	 * @author Denis
	 * @param firstName
	 * @param lastName
	 */
	public Name(String firstName, String lastName){
		this.firstName = validateName(firstName);
		this.lastName = validateName(lastName);
	}
	
	/**This method returns the firstName.
	 * @return String
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**This method sets the firstName.
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/** This method returns the lastName.
	 * @return String
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**This method sets the lastName
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**This method will return the firstName plus the lastName
	 * to make the fullName.
	 * @author Denis
	 * @return String
	 */
	public String getFullName() {
		fullName = firstName + " " + lastName;
		return fullName;
	}
		
	@Override
	public String toString() {
		return (firstName + "*" + lastName);
	}
	
	@Override
	public int compareTo (Name name){
		return this.fullName.compareToIgnoreCase(name.fullName);
	}
	
	/**This method validates the variable name and it will 
	 * throw an exception if the rules are not followed. 
	 * @throws IllegalArgumentException if name matches 
	 * the regex pattern, the name length is less than 2
	 * letters, or name is null.
	 * @throws IllegalArgumentException if the regex 
	 * pattern does not match. 
	 * @author Denis
	 * @param name
	 * @return String
	 */
	private String validateName (String name){
		if(name.matches(".*[0-9].*") || name == null || name.length() < 2)
			throw new IllegalArgumentException("The name contain a number or is too short");
		
		if(name.matches(".*(--|  | '').*"))
			throw new IllegalArgumentException("Valid characters repeated is invalid");
		
		if(!(name.matches("^([a-zA-Z][- '  ]*[a-zA-Z].*|[a-zA-Z]{2,}[- ' ]*[a-zA-Z].*)")))
				throw new IllegalArgumentException("The name is invalid by the rules");
		
		return name;
	}
	
	/**
	 * The equal method will look if two emails
	 * are equals by comparing the first name
	 * and the last name.
	 * @param Object
	 * @return boolean
	 * @author Denis Lebedev
	 */
	@Override
	public final boolean equals(Object obj){
		if(this == obj) return false;
		
		if(obj == null) return false;
		
		if(this.getClass() != obj.getClass()) return false;
		
		Name data = (Name) obj;
		
		if(this.firstName.equalsIgnoreCase(data.firstName))
			if(this.lastName.equalsIgnoreCase(data.lastName))
					return true;
		return false;
	}
	
	/**
	 * This method override hashCode
	 * @return Integer
	 * @author Denis Lebedev
	 */
	@Override
	public final int hashCode(){
		final int prime = 37;
		int result = 1;
		result = prime * result + (((firstName == null) ? 0 : firstName.toUpperCase().hashCode()) +
				((lastName == null) ? 0 : lastName.toUpperCase().hashCode()) +
				((fullName == null) ? 0 : fullName.toUpperCase().hashCode()));
		return result;
	}
}
