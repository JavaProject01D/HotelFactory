package group5.hotel.business;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;

/** This class define the reservation date by
 * using LocalDate. The class override equals,
 * compareTo, hashCode, toString, and the overlap. 
 * @author Denis Lebedev
 * @version 9/26/2016
 */

public class DawsonReservation implements Reservation{
	private static final long serialVersionUID = 42031768871L;
	private final Customer aCustomer;
	private final Room aRoom;
	private LocalDate checkIn;
	private LocalDate checkOut;
	
	/**
	 * @throws IllegalArguentException if we catch DateException because
	 * the date is invalid
	 * All the Integers parameters will be assign in checkIn or checkOut.
	 * @param aCustomer
	 * @param aRoom
	 * @param inYear
	 * @param inMonth
	 * @param inDay
	 * @param outYear
	 * @param outMonth
	 * @param outDay
	 */
	
	public DawsonReservation(Customer aCustomer, Room aRoom, int inYear, int inMonth, 
							int inDay, int outYear, int outMonth, int outDay){
		
		this.aCustomer = aCustomer;
		this.aRoom = aRoom;
		
		try{
			checkIn = LocalDate.of(inYear, inMonth, inDay);
			checkOut = LocalDate.of(outYear, outMonth, outDay);
			
			if(!checkIn.isBefore(checkOut))
				throw new DateTimeException("");
		}catch (DateTimeException dte){
			throw new IllegalArgumentException("Date given is invalid");
		}
	}
	
	/**
	 * The get method will return a deep copy of Customer
	 * @return DawsonCustomer
	 * @author Denis Lebedev
	 */
	@Override
	public Customer getCustomer() {
		aCustomer.setCreditCard(aCustomer.getCreditCard());

		return new DawsonCustomer(aCustomer.getName().getFirstName(),
								   aCustomer.getName().getLastName(),
								   aCustomer.getEmail().getAddress());
								   
	}

	/**
	 * The get method will return a deep copy of Room
	 * @return Room
	 * @author Denis Lebedev
	 */
	@Override
	public Room getRoom() {
		return new DawsonRoom(aRoom.getRoomNumber(), aRoom.getRoomType());
	}
	
	/**
	 * The method equals verify is the full name is 
	 * equal, the address, the RoomNumber, the checkIn,
	 *  and the checkOut either
	 * @param Object
	 * @return boolean
	 * @author Denis Lebedev
	 */
	@Override
	public final boolean equals(Object object){
		if(object == this) return true;
		
		if(object == null) return false;
		
		if(!(object instanceof DawsonReservation)) return false;
		
		DawsonReservation data = (DawsonReservation) object;
		
		if(!(this.getCustomer().equals(data.getCustomer()))) return false;
				
		if(!(this.getRoom().equals(data.getRoom()))) return false;
		
		if(!(this.checkIn.isEqual(data.checkIn))) return false;
		
		if(this.checkOut.isEqual(data.checkOut)) return true;
		
		return false;
	}
	
	/**
	 * Override the hashCode
	 * @author Denis Lebedev
	 * @return Integers
	 */
	@Override
	public final int hashCode(){
		int prime = 37;
		int result = 1;
		return result = result * prime + ((aCustomer == null) ? 0 : aCustomer.hashCode()) +
				((aRoom == null) ? 0 : aRoom.hashCode()) + 
				((checkIn == null) ? 0 : checkIn.hashCode()) + 
				((checkOut == null) ? 0 : checkOut.hashCode());
	}
	
	/**
	 * This method verify is the date overlap
	 * over another reservation
	 * @param Reservation
	 * @return boolean
	 * @author Denis Lebedev
	 */
	@Override
	public boolean overlap(Reservation other) {
		
		if(this.equals(other))
			return true;
		
			if(this.checkIn.isBefore(other.getCheckOutDate())
				&& this.checkOut.isAfter(other.getCheckInDate()))	
				return true;
				
		return false;
	}
	
	/**
	 * This method override the toString to make
	 * a specific output
	 * @return String
	 * @author Denis Lebedev
	 */
	@Override
	public String toString(){
		return aCustomer.getEmail().getAddress() + "*" + this.checkIn.getYear() + "*" + this.checkIn.getMonthValue() +
				"*" + this.checkIn.getDayOfMonth() + "*" + this.checkOut.getYear() +
				"*" + this.checkOut.getMonthValue() + "*" + this.checkOut.getDayOfMonth() + 
				"*" + aRoom.getRoomNumber();
	}
	
	/**
	 * This method override the compareTo
	 * if Room is equal to the other Room
	 * else with the checkIn.
	 * @param Reservation
	 * @return Integer
	 * @author Denis Lebedev
	 */
	@Override
	public int compareTo(Reservation reserve) {
		if(this.aRoom.getRoomNumber() == reserve.getRoom().getRoomNumber())				
			return checkIn.compareTo(reserve.getCheckInDate());
		return this.aRoom.compareTo(reserve.getRoom());
	}
	
	/**
	 * The method override the getter
	 * it return the difference between two dates
	 * @return Integer
	 * @author Denis Lebedev
	 */
	@Override
	public int getNumberDays() {
		int day = Period.between(checkIn, checkOut).getDays();
		return day;
	}
	
	/**
	 * This method return checkIn 
	 * @return LocalDate
	 * @author Denis Lebedev
	 */
	@Override
	public LocalDate getCheckInDate() {
		return checkIn;
	}
	
	/**
	 * This method return checkOut
	 * @return LocalDate
	 * @author Denis Lebedev
	 */
	@Override
	public LocalDate getCheckOutDate() {
		return checkOut;
	}
	
}
