package dw317.lib;

import java.util.Optional;

/**
 * 
 * @author Denis Lebedev
 * @version Lab2
 *
 */

public class Person {
	
	private Address address;
	private Name name;
	
	public Person(String firstName, String lastName){
		name = new Name(firstName, lastName);
	}
	
	public Person(String firstName, String lastName, Address address){
		name = new Name(firstName, lastName);
		
		this.address = new Address(address.getCivicNumber(), address.getStreetName(), 
									address.getCity(), Optional.ofNullable(address.getProvince()), Optional.ofNullable(address.getCode()));
	}
	
	public Address getAddress(){
		if(this.address.getCivicNumber().isEmpty())
			return address = new Address();
		
		return new Address(address.getCivicNumber(), address.getStreetName(), 
				address.getCity(), Optional.ofNullable(address.getProvince()), Optional.ofNullable(address.getCode()));		
	}
	
	public Name getName(){
		return new Name(name.getFirstName(), name.getLastName());
	}
	
	public void setName(String firstName, String lastName){
		this.name = new Name(firstName, lastName);
	}
	
	public void setAddress(Address address){
		this.address = address;
	}
	
	@Override
	public String toString() {
		return name.toString() + "*" + 
				(address == null ? ""  : 
					address.toString());                      
	}

}
