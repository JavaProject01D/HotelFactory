package dw317.hotel.data;

public class DuplicateReservationException extends Exception {
	private static final long  serialVersionUID = 42031768871L;
	
	public DuplicateReservationException(){
		super("The chosen room has already has a reservation during those times.");
	}

	public DuplicateReservationException(String message) {
		super(message);
	}
}