package dw317.hotel.business.interfaces;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Sevan Topalian
 * @version 27/09/2016
 */
public interface Reservation extends Comparable<Reservation>, Serializable {
	/**
	 * Returns a deep copy of the customer object.
	 * 
	 * @return Customer
	 */
	Customer getCustomer(); // Must return a deep copy

	/**
	 * Returns the room object.
	 * 
	 * @return Room
	 */
	Room getRoom();

	/**
	 * Returns the checkin date.
	 * 
	 * @return LocalDate
	 */
	LocalDate getCheckInDate();

	/**
	 * Returns checkout date.
	 * 
	 * @return LocalDate
	 */
	LocalDate getCheckOutDate();

	/**
	 * Returns the number of days between two specified dates.
	 * 
	 * @return int
	 */
	int getNumberDays();

	/**
	 * Returns whether there is an overlap of dates between two reservations.
	 * 
	 * @param other
	 * @return boolean
	 */
	boolean overlap(Reservation other);
}