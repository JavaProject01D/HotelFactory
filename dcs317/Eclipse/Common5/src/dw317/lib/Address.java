 /**  *  Defines an Address type.  */ 
 
package dw317.lib; 
import java.util.Optional;

/**
 * 
 * @author Denis Lebedev
 * @version Lab2
 */
public class Address {     
	
	private String city = "";
	private String civicNumber = "";
	private String province = "";
	private String code = "";
	private String streetName = "";
	
	public Address() { }
	
	public Address(String civicNumber, String streetName, String city, 
			Optional<String> province, Optional<String> code) {
		if(!this.civicNumber.isEmpty())
		{
		this.civicNumber = validateExistence("civic number", civicNumber);
		this.streetName = validateExistence("street name", streetName);
		this.city = validateExistence("city", city);
		this.province = province.orElse("");
		this.code = code.orElse("");
		}
	}

	
	

	private String validateExistence(String fieldName, String fieldValue) {
		 
		if (fieldValue == null) 
				throw new IllegalArgumentException("Address Error - " + fieldName
					+ " must exist. Invalid value = " + fieldValue); 		 
		 
		 String trimmedString = fieldValue.trim();
		 
		if (trimmedString.isEmpty())
			throw new IllegalArgumentException("Address Error - " + fieldName
					+ " must exist. Invalid value = " + fieldValue);
		return trimmedString;
	}

	
	/**
	 * Returns a String representation of the address. 
	 * 
	 * @return address a formatted address.
	 */
	public String getAddress() {
		String address = civicNumber + " " + streetName + "\n" + city;
		address += (province.equals("")?"": (", " + province)) +
				(code.equals("")?"":("\n" + code));

		return address;
	}


	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the civicNumber
	 */
	public String getCivicNumber() {
		return civicNumber;
	}

	/**
	 * @param civicNumber the civicNumber to set
	 */
	public void setCivicNumber(String civicNumber) {
		this.civicNumber = validateExistence("civic number", civicNumber);
	}

	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the streetName
	 */
	public String getStreetName() {
		return streetName;
	}

	/**
	 * @param streetName the streetName to set
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	
	@Override
	public String toString() {
		return (civicNumber + "*" +	streetName + "*" +
				city + "*" + province + "*" +	code);
}

	

	
}