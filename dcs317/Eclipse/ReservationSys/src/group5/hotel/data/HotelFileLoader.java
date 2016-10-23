package group5.hotel.data;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import group5.hotel.business.DawsonHotelFactory;

/**
 * @author Denis Lebedev, Sevan Topalian
 *
 */
public class HotelFileLoader {

	private HotelFileLoader() {

	}

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

				try {

					// CATCH PARSE EXCEPTION!!!
					arr[i] = dHF.getRoomInstance(Integer.parseInt(fields[0]), fields[1]);

				} catch (IllegalArgumentException iae) {
					System.out.println(iae.getMessage() + "\nFileName: " + filename + "\nRecord: " + record);
				}

				if (i > arr.length) // resize
					arr = Arrays.copyOf(arr, arr.length * 2 + 1);

			} // end of the while loop

			// shrink
			arr = Arrays.copyOf(arr, i);

		} catch (IOException io) {
		}

		// Close Scanner
		finally {
			inputStream.close();
		}

		return arr;
	}

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

				try {

					// first, last, email - instance
					// email,FN,LN,Card,Card#
					// IF ** = they are ignored = *** and not *****
					// IF *''*'' = ERROR THROW
					arr[i] = dHF.getCustomerInstance(fields[1], fields[2], fields[0]);

				} catch (IllegalArgumentException iae) {
					System.out.println(iae.getMessage() + "\nFileName: " + filename + "\nRecord: " + record);
				}

				if (i > arr.length) // resize
					arr = Arrays.copyOf(arr, arr.length * 2 + 1);

			} // end of the while loop

			// shrink
			arr = Arrays.copyOf(arr, i);

		} catch (IOException io) {
		}

		// Close Scanner
		finally {
			inputStream.close();
		}

		return arr;
	}

	public static Reservation[] getReservationListFromSequentialFile(String filename, Customer[] customerList,
			Room[] roomList) throws IOException, IllegalArgumentException {
		return null;

	}
}
