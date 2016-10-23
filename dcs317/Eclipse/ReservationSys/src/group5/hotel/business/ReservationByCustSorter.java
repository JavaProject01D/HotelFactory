package group5.hotel.business;

import java.util.Comparator;
import dw317.hotel.business.interfaces.Reservation;

public class ReservationByCustSorter implements Comparator<Reservation> {

	@Override
	public int compare(Reservation r1, Reservation r2) {
		if (r1.equals(r2))
			return 0;
		if (!r1.getCustomer().equals(r2.getCustomer()))
			return r1.getCustomer().compareTo(r2.getCustomer());
		// if same customer, order by check in (i.e., default)
		return r1.compareTo(r2);
	}
}