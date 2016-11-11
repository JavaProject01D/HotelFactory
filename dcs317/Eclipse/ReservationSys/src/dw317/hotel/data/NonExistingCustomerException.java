package dw317.hotel.data;

public class NonExistingCustomerException extends Exception {
	private static final long  serialVersionUID = 42031768871L;
	
	public NonExistingCustomerException(){
		super("The Customer does not exist.");
	}

	public NonExistingCustomerException(String message) {
		super(message);
	}
}