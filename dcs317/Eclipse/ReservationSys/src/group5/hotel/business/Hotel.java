package group5.hotel.business;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Observable;
import java.util.Optional;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.HotelFactory;
import dw317.hotel.business.interfaces.HotelManager;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.DuplicateCustomerException;
import dw317.hotel.data.DuplicateReservationException;
import dw317.hotel.data.NonExistingCustomerException;
import dw317.hotel.data.NonExistingReservationException;
import dw317.hotel.data.interfaces.CustomerDAO;
import dw317.hotel.data.interfaces.ReservationDAO;
import dw317.lib.Email;
import dw317.lib.creditcard.CreditCard;
import group5.hotel.ui.TextView;

/**
 * This class represents the front desk of the Hotel and allows operations to be
 * performed on the Customers and Reservations.
 * 
 * @author Sevan Topalian
 * @version 11/28/2016
 */
public class Hotel extends Observable implements HotelManager {

	private final HotelFactory factory;
	private final CustomerDAO customers;
	private final ReservationDAO reservations;
	private static final long serialVersionUID = 42031768871L;

	/**
	 * This constructor instantiates the HotelFactory, CustomerDAO, and
	 * ReservationDAO.
	 * 
	 * @param factory
	 * @param customers
	 * @param reservations
	 */
	public Hotel(HotelFactory factory, CustomerDAO customers, ReservationDAO reservations) {
		this.factory = factory;
		this.customers = customers;
		this.reservations = reservations;
	}

	/**
	 * This method cancels the given Reservation if it exists. If not, it throws
	 * a NonExistingReservationException.
	 * 
	 * @param reservation
	 * @throws NonExistingReservationException
	 *             If the Reservation given does not exist.
	 */
	@Override
	public void cancelReservation(Reservation reservation) throws NonExistingReservationException {
		try {
			reservations.cancel(reservation);
		} catch (NonExistingReservationException ner) {
			System.out.println(ner.getMessage());
		}
	}

	/**
	 * This method closes the Hotel and disconnects from the customers and
	 * reservations databases.
	 * 
	 * @throws IOException
	 *             If there is a problem disconnecting.
	 */
	@Override
	public void closeHotel() throws IOException {
		try {
			customers.disconnect();
			reservations.disconnect();
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
	}

	/**
	 * This method creates a Reservation if there is an available Room of the
	 * given type on the given days.
	 * 
	 * @param customer
	 * @param checkIn
	 * @param checkOut
	 * @param roomType
	 */
	@Override
	public Optional<Reservation> createReservation(Customer customer, LocalDate checkin, LocalDate checkout,
			RoomType roomType) {

		Optional<Room> availableRoom = factory.getAllocationPolicy(reservations).getAvailableRoom(checkin, checkout,
				roomType);
		Reservation reservToCreate = null;

		if (availableRoom.isPresent()) {
			
			try { 
					reservToCreate = factory.getReservationInstance(customer, availableRoom.get(),
					checkin.getYear(), checkin.getMonthValue(), checkin.getDayOfMonth(), checkout.getYear(),
					checkout.getMonthValue(), checkout.getDayOfMonth());	
					reservations.add(reservToCreate);
			
				} catch ( DuplicateReservationException dre) {
					setChanged();
					notifyObservers(Optional.empty());
					return Optional.empty();
				}
		}
		setChanged();
		notifyObservers(reservToCreate);
		return Optional.of(reservToCreate);
			
	}

	/**
	 * This method looks for a Customer in the Customer database with the given
	 * email address. The method throws a NonExistingCustomerException if the
	 * Customer does not exist.
	 * 
	 * @param email
	 * @return Customer
	 * @throws NonExistingCustomerException
	 *             If the Customer does not exist.
	 */
	@Override
	public Customer findCustomer(String email) throws NonExistingCustomerException {
		Customer customerFound = null;

		try {
			customerFound = customers.getCustomer(new Email(email));
		} catch (NonExistingCustomerException nec) {
			System.out.println(nec.getMessage());
		}
		
		setChanged();
		notifyObservers(customerFound);
		return customerFound;
	}

	/**
	 * This method looks for Reservations made by the given Customer.
	 * 
	 * @param customer
	 * @return List<Reservation>
	 */
	@Override
	public List<Reservation> findReservations(Customer customer) {
		List<Reservation> reservationList = reservations.getReservations(customer);
		setChanged();
		notifyObservers(reservationList);
		return reservationList;
	}

	/**
	 * This method registers a Customer into the Customer database. It throws a
	 * DuplicateCustomerException if the Customer already exists.
	 * 
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @return Customer
	 * @throws DuplicateCustomerException
	 *             If the Customer already exists in the database.
	 */
	@Override
	public Customer registerCustomer(String firstName, String lastName, String email)
			throws DuplicateCustomerException {
		Customer custToRegister = factory.getCustomerInstance(firstName, lastName, email);

		try {
			customers.add(custToRegister);
		} catch (DuplicateCustomerException dce) {
			System.out.println(dce.getMessage());
		}
		
		setChanged();
		notifyObservers(custToRegister);
		return custToRegister;
	}

	/**
	 * This method updates a Customer's Credit Card information. It throws a
	 * NonExistingCustomerException if the Customer does not exist.
	 * 
	 * @param email
	 * @param cardType
	 * @param cardNumber
	 * @return Customer
	 * @throws NonExistingCustomerException
	 *             If the Customer does not exist in the database.
	 */
	@Override
	public Customer updateCreditCard(String email, String cardType, String cardNumber)
			throws NonExistingCustomerException {

		CreditCard card = factory.getCard(cardType, cardNumber);
		Email custEmail = new Email(email);
		Customer custToUpdate = findCustomer(email);

		try {
			customers.update(custEmail, card);
		} catch (NonExistingCustomerException nec) {
			System.out.println(nec.getMessage());
		}
		
		setChanged();
		notifyObservers(custToUpdate);
		return custToUpdate;
	}

}