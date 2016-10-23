package group5.hotel.business;

import java.util.Comparator;
import dw317.hotel.business.interfaces.Reservation;

public class ReservationByCheckOutSorter implements Comparator<Reservation> {
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