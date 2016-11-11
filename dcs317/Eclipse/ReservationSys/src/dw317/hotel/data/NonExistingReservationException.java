package dw317.hotel.data;

/**
 * @author Sevan Topalian
 * 
 *         This exception is thrown if a search for a Reservation doesn't find a
 *         match.
 */
public class NonExistingReservationException extends Exception {
	private static final long serialVersionUID = 42031768871L;

	public NonExistingReservationException() {
		super("The Reservation does not exist.");
	}

	public NonExistingReservationException(String message) {
		super(message);
	}
}