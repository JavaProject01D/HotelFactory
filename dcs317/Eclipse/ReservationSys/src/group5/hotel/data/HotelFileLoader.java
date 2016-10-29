package group5.hotel.data;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import group5.hotel.business.DawsonHotelFactory;

/**
 * The class HotelFileLoader will load data in arrays and they gonna be used for
 * another purpose. The class will load arrays of Customers, Rooms, and
 * Reservation.
 * 
 * @author Denis Lebedev, Sevan Topalian (Reservation method)
 *
 */
public class HotelFileLoader {

	/*
	 * Private constructor to avoid instantiation
	 */
	private HotelFileLoader() {

	}

	/**
	 * The method will read through the files given and load objects that will
	 * hold data. Also, the data will be verified.
	 * 
	 * @author Denis Lebedev
	 * 
	 * @param filename
	 * @return Room[]
	 * @throws IOException
	 *             if the filename does not exists or cannot instantiate
	 *             BufferedReader
	 */
	public static Room[] getRoomListFromSequentialFile(String filename) throws IOException {

		Room[] arr = new Room[2];

		Scanner inputStream = null;
		String record = null;
		DawsonHotelFactory dHF = DawsonHotelFactory.DAWSON;

		try {

			BufferedReader outStream = new BufferedReader(
					new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8));

			inputStream = new Scanner(outStream);

			int i = 0;

			while (inputStream.hasNext()) {
				record = inputStream.nextLine();
				String[] fields = record.split("\\*");

				// look if there is a blank line
				if (fields.length == 1 && fields[0] == null || fields[0].isEmpty())
					continue;

				try {

					arr[i] = dHF.getRoomInstance(Integer.parseInt(fields[0]), fields[1]);

				} catch (NumberFormatException nfe) {
					System.out.println(nfe.getMessage() + "\nThe given room is invalid: Parse Exception!!"
							+ "\nFileName: " + filename + "\nRecord: " + record);
					// avoid to have nulls in my array of Rooms
					continue;

				} catch (IllegalArgumentException iae) {
					System.out.println(iae.getMessage() + "\nFileName: " + filename + "\nRecord: " + record);
					// avoid to have nulls in my array of Rooms
					continue;
				}
				i++;

				if (i >= arr.length) // resize
					arr = Arrays.copyOf(arr, arr.length * 2 + 1);

			} // end of the while loop

			// shrink
			arr = Arrays.copyOf(arr, i);

		} catch (IOException io) {
			System.out.println(io.getMessage());

		}

		// Close Scanner
		finally {
			inputStream.close();
		}

		return arr;
	}

	/**
	 * The method will read through the files given and load objects that will
	 * hold data. Also, the data will be verified.
	 * 
	 * @author Denis Lebedev
	 * 
	 * @param filename
	 * @return Customer[]
	 * @throws IOException
	 *             if the filename does not exists or cannot instantiate
	 *             BufferedReader
	 */
	public static Customer[] getCustomerListFromSequentialFile(String filename) throws IOException {

		Customer[] arr = new Customer[2];

		Scanner inputStream = null;
		String record = null;
		DawsonHotelFactory dHF = DawsonHotelFactory.DAWSON;

		try {

			BufferedReader outStream = new BufferedReader(
					new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8));

			inputStream = new Scanner(outStream);

			int i = 0;

			while (inputStream.hasNext()) {
				record = inputStream.nextLine();

				String[] fields = record.split("\\*");

				// look for blank line
				if (fields.length == 1 && fields[0] == null || fields[0].isEmpty())
					continue;

				// It looks if a record have at LEAST 5 *
				if (record.length() - record.replace("*", "").length() != 4) {
					System.out.println("Error: the record contain less than four *");
					continue;
				}

				try {

					// first, last, email - instance
					// email,FN,LN,Card,Card#
					// IF ** = they are ignored = *** and not *****
					// IF *''*'' = ERROR THROW
					arr[i] = dHF.getCustomerInstance(fields[1], fields[2], fields[0]);

					if (lengthVerification(fields))
						arr[i].setCreditCard(Optional.of(dHF.getCard(fields[3], fields[4])));

				} catch (IllegalArgumentException iae) {
					System.out.println(iae.getMessage() + "\nFileName: " + filename + "\nRecord: " + record);
					// If I found a null I will not add it to the array
					continue;
				}

				i++;
				if (i >= arr.length) // resize
					arr = Arrays.copyOf(arr, arr.length * 2 + 1);

			} // end of the while loop

			// shrink
			arr = Arrays.copyOf(arr, i);

		} catch (IOException io) {
			System.out.println(io.getMessage());
		}

		// Close Scanner
		finally {
			inputStream.close();
		}

		return arr;
	}

	/**
	 * Verify if the length is the right one If not i will not instantiate the
	 * method, but only for cutomers file
	 * 
	 * @author Denis Lebedev
	 * 
	 * @param arr
	 * @return boolean
	 */
	private static boolean lengthVerification(String[] arr) {
		final int maxSize = 5;

		// If the size is not equal that mean the array does not contain a Card
		if (maxSize != arr.length)
			return false;

		// If the array is loaded, BUT there is blank spaces or nulls there is
		// no Card
		if (maxSize == arr.length)
			for (int i = 3; i < arr.length; i++)
				if (arr[i] == null || arr[i].isEmpty())
					return false;

		// Else there is a Card
		return true;
	}

	/**
	 * Returns an array of Reservations found in a file by matching them with
	 * the Customer and Room arrays given to the method.
	 * 
	 * @param filename
	 *            The path to the file containing the reservations
	 * @param customerList
	 *            An array of customers to match the reservations with
	 * @param roomList
	 *            An array of rooms to match the reservations with
	 * @return Reservation[] An array of reservations found in the specified
	 *         file, matched with the customers and rooms found in their
	 *         respective arrays
	 * @throws IOException
	 *             If there is a problem with the file
	 * @throws IllegalArgumentException
	 *             If the customer or room from the reservation cannot be found
	 *             in the array
	 */
	public static Reservation[] getReservationListFromSequentialFile(String filename, Customer[] customerList,
			Room[] roomList) throws IOException, IllegalArgumentException {

		Reservation[] arr = new Reservation[2];
		Scanner inputStream = null;
		String record = null;
		DawsonHotelFactory dHF = DawsonHotelFactory.DAWSON;

		try {

			BufferedReader outStream = new BufferedReader(
					new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8));

			inputStream = new Scanner(outStream);

			int i = 0;

			while (inputStream.hasNext()) {
				Boolean customerExists = false;
				Boolean roomExists = false;
				int customerPosition = -1;
				int roomPosition = -1;
				record = inputStream.nextLine();
				String[] fields = record.split("\\*");

				// look for blank line
				if (fields.length == 1 && fields[0] == null || fields[0].isEmpty())
					continue;

				try {
					for (int c = 0; c < customerList.length; c++) {
						if (customerList[c].getEmail().getAddress().equals(fields[0])) {
							customerExists = true;
							customerPosition = c;

							for (int r = 0; r < roomList.length; r++) {
								if (roomList[r].getRoomNumber() == (Integer.parseInt(fields[7]))) {
									roomExists = true;
									roomPosition = r;
									break;
								} else
									roomExists = false;
							}
							break;
						} else
							customerExists = false;
					}

					if (customerExists && roomExists)
						arr[i] = dHF.getReservationInstance(customerList[customerPosition], roomList[roomPosition],
								Integer.parseInt(fields[1]), Integer.parseInt(fields[2]), Integer.parseInt(fields[3]),
								Integer.parseInt(fields[4]), Integer.parseInt(fields[5]), Integer.parseInt(fields[6]));
					else {
						if (!customerExists)
							throw new IllegalArgumentException("Customer does not exist");
						else if (!roomExists)
							throw new IllegalArgumentException("Room does not exist");
					}

					// added
				} catch (NumberFormatException nfe) {
					System.out.println(nfe.getMessage() + "\nFileName: " + filename + "\nRecord: " + record);
					continue;
				} catch (IllegalArgumentException iae) {
					System.out.println(iae.getMessage() + "\nFileName: " + filename + "\nRecord: " + record);
					continue;
				}

				i++;
				if (i >= arr.length) // resize
					arr = Arrays.copyOf(arr, arr.length * 2 + 1);

			} // end of the while loop

			// shrink
			arr = Arrays.copyOf(arr, i);

		} catch (IOException io) {
			System.out.println(io.getMessage());
		}

		// Close Scanner
		finally {
			inputStream.close();
		}

		return arr;
	}
}
