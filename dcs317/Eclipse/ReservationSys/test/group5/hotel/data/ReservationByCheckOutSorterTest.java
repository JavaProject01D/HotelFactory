package group5.hotel.data;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import group5.hotel.business.DawsonCustomer;
import group5.hotel.business.DawsonReservation;
import group5.hotel.business.DawsonRoom;
import group5.hotel.business.ReservationByCheckOutSorter;

/**
 * @author Sevan Topalian
 * 
 *         This class will test the ReservationByCheckoutSorter class. It will
 *         run through the checkOutSorterTest file and sort all the reservations
 *         sequentially in an array according to the defined sorting order (by
 *         check out date, and if they are the same, by default order by using
 *         compareTo). It will print out the unsorted and sorted arrays so that
 *         it is possible to view that the array has in fact been sorted
 *         properly.
 */
public class ReservationByCheckOutSorterTest {

	public static void main(String[] args) {
		String filename = "dcs317\\Eclipse\\ReservationSys\\datafiles\\checkOutSorterTest.txt";
		Customer customer;
		Room room;
		Reservation[] arr = new Reservation[2];

		Scanner inputStream = null;
		String record = null;

		BufferedReader outStream = null;
		try {
			outStream = new BufferedReader(
					new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8));
		} catch (FileNotFoundException e) {
			System.out.println("Error, file not found. " + e.getMessage());
		}

		inputStream = new Scanner(outStream);

		System.out.println("Testing ReservationByCheckOutSorter\n");

		int i = 0;

		while (inputStream.hasNext()) {
			record = inputStream.nextLine();
			String[] fields = record.split("\\*");

			customer = new DawsonCustomer(fields[0], fields[1], fields[2]);
			System.out.println("\nCreated customer: " + customer);
			room = new DawsonRoom(Integer.parseInt(fields[3]), RoomType.NORMAL);
			System.out.println("\nCreated room: " + room);
			arr[i] = new DawsonReservation(customer, room, Integer.parseInt(fields[4]), Integer.parseInt(fields[5]),
					Integer.parseInt(fields[6]), Integer.parseInt(fields[7]), Integer.parseInt(fields[8]),
					Integer.parseInt(fields[9]));
			System.out.println("\nCreated reservation: " + arr[i]);

			i++;

			if (i >= arr.length) // resize
				arr = Arrays.copyOf(arr, arr.length * 2 + 1);
		} // end of the while loop

		// shrink
		arr = Arrays.copyOf(arr, i);

		// Close Scanner
		inputStream.close();

		System.out.println("\nFilled reservation array (unsorted):");
		for (Reservation reservation : arr) {
			System.out.println(reservation);
		}

		Arrays.sort(arr, new ReservationByCheckOutSorter());

		System.out.println("\nSorted array:");
		for (Reservation reservation : arr) {
			System.out.println(reservation);
		}
	}
}
