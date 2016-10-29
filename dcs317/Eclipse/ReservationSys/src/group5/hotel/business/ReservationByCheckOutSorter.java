package group5.hotel.business;

import java.util.Comparator;
import dw317.hotel.business.interfaces.Reservation;

/**
 * @author Sevan Topalian
 * 
 *         This class implements Comparator in order to sort reservations by
 *         their checkOut order. If the checkOut dates are the same, the
 *         reservations are ordered by their compareTo method.
 */
public class ReservationByCheckOutSorter implements Comparator<Reservation> {

	/**
	 * This method overrides the compare method in Comparator, and sorts
	 * reservations by checkOut date, and if they are the same, by the
	 * reservation compareTo method.
	 * 
	 * @param r1
	 *            The first reservation to compare
	 * @param r2
	 *            The second reservation to compare
	 * 
	 * @return int Returns the result of the reservation compareTo method if the
	 *         two checkOut dates are the same, 1 if the first checkOut date is
	 *         earlier than the second, -1 if the second checkOut date is
	 *         earlier than the first
	 */
	@Override
	public int compare(Reservation r1, Reservation r2) {
		if (r1.getCheckOutDate().equals(r2.getCheckOutDate()))
			return r1.compareTo(r2);
		else {
			if (r1.getCheckOutDate().compareTo(r2.getCheckOutDate()) > 0)
				return 1;
			else
				return -1;
		}
	}
}