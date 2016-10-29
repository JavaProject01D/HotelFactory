package group5.hotel.business;

import java.util.Comparator;
import dw317.hotel.business.interfaces.Reservation;

/**
 * @author Sevan Topalian
 * 
 *         This class implements Comparator in order to sort reservations by
 *         their customer order. If the customers are the same, the reservations
 *         are ordered by check in date.
 */
public class ReservationByCustSorter implements Comparator<Reservation> {

	/**
	 * This method overrides the compare method in Comparator, and sorts
	 * reservations by customer, and if they are the same, by check in date.
	 * 
	 * @param r1
	 *            The first reservation to compare
	 * @param r2
	 *            The second reservation to compare
	 * 
	 * @return int Returns 0 if the reservations are equal, the compareTo result
	 *         of the customers if they are different, or the compareTo results
	 *         of the checkInDate if the customers are the same
	 */
	@Override
	public int compare(Reservation r1, Reservation r2) {
		if (r1.equals(r2))
			return 0;
		if (!r1.getCustomer().equals(r2.getCustomer()))
			return r1.getCustomer().compareTo(r2.getCustomer());
		// if same customer, order by check in (i.e., default)
		return r1.getCheckInDate().compareTo(r2.getCheckInDate());
	}
}