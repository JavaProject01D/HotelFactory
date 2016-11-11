package dw317.hotel.data;

public class NonExistingReservationException extends Exception {
	private static final long  serialVersionUID = 42031768871L;
	
	public NonExistingReservationException(){
		super("The Reservation does not exist.");
	}

	public NonExistingReservationException(String message) {
		super(message);
	}
}