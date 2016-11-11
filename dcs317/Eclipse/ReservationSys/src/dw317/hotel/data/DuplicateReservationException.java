package dw317.hotel.data;

/**
 * @author Sevan Topalian
 * 
 *         This exception is thrown if a Room being reserved already has a
 *         reservation overlapping the chosen times.
 */
public class DuplicateReservationException extends Exception {
	private static final long serialVersionUID = 42031768871L;

	public DuplicateReservationException() {
		super("The chosen room has already has a reservation during those times.");
	}

	public DuplicateReservationException(String message) {
		super(message);
	}
}