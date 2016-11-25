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
import dw317.hotel.data.NonExistingCustomerException;
import dw317.hotel.data.NonExistingReservationException;
import dw317.hotel.data.interfaces.CustomerDAO;
import dw317.hotel.data.interfaces.ReservationDAO;
import dw317.lib.Email;
import dw317.lib.creditcard.CreditCard;

public class Hotel extends Observable implements HotelManager {

	private final HotelFactory factory;
	private final CustomerDAO customers;
	private final ReservationDAO reservations;
	private static final long serialVersionUID = 42031768871L;

	public Hotel(HotelFactory factory, CustomerDAO customers, ReservationDAO reservations) {
		this.factory = factory;
		this.customers = customers;
		this.reservations = reservations;
	}

	@Override
	public void cancelReservation(Reservation reservation) throws NonExistingReservationException {
		try {
			reservations.cancel(reservation);
		} catch (NonExistingReservationException ner) {
			System.out.println(ner.getMessage());
		}
	}

	@Override
	public void closeHotel() throws IOException {
		try {
			customers.disconnect();
			reservations.disconnect();
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
	}

	@Override
	public Optional<Reservation> createReservation(Customer customer, LocalDate checkin, LocalDate checkout,
			RoomType roomType) {

		Optional<Room> availableRoom = factory.getAllocationPolicy(reservations).getAvailableRoom(checkin, checkout,
				roomType);

		if (availableRoom.isPresent()) {
			Reservation reservToCreate = factory.getReservationInstance(customer, availableRoom.get(),
					checkin.getYear(), checkin.getMonthValue(), checkin.getDayOfMonth(), checkout.getYear(),
					checkout.getMonthValue(), checkout.getDayOfMonth());
			return Optional.of(reservToCreate);
		} else
			return Optional.ofNullable(null);
	}

	@Override
	public Customer findCustomer(String email) throws NonExistingCustomerException {
		Customer customerFound = null;

		try {
			customerFound = customers.getCustomer(new Email(email));
		} catch (NonExistingCustomerException nec) {
			System.out.println(nec.getMessage());
		}

		return customerFound;
	}

	@Override
	public List<Reservation> findReservations(Customer customer) {
		return reservations.getReservations(customer);
	}

	@Override
	public Customer registerCustomer(String firstName, String lastName, String email)
			throws DuplicateCustomerException {
		Customer custToRegister = factory.getCustomerInstance(firstName, lastName, email);

		try {
			customers.add(custToRegister);
		} catch (DuplicateCustomerException dce) {
			System.out.println(dce.getMessage());
		}

		return custToRegister;
	}

	@Override
	public Customer updateCreditCard(String email, String cardType, String cardNumber)
			throws NonExistingCustomerException {

		try {
			CreditCard card = factory.getCard(cardType, cardNumber);
			customers.update(new Email(email), card);
		} catch (NonExistingCustomerException nec) {
			System.out.println(nec.getMessage());
		}
		
		Customer custToUpdate = findCustomer(email);

		return custToUpdate;
	}
}