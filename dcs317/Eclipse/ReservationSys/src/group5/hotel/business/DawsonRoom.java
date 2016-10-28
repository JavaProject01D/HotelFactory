package group5.hotel.business;

import dw317.hotel.business.interfaces.Room;
import dw317.hotel.business.RoomType;

/**
 * The following abstract class implements the Room class to allow us to created
 * DawsonRooms and get the floor number and room number of each possible room.
 * 
 * @author Zahraa Horeibi
 * @version 9/27/2016
 *
 */
public class DawsonRoom implements Room {

	// Variables instantiation

	private final RoomType roomType;
	private final int roomNumber;
	private static final long serialVersionUID = 42031768871L;

	/**
	 * This constructor instantiates the Room Type and Room Number. It checks if
	 * one of them is null or if the floor number does not correspond to the
	 * rules which will throw an IllegalArgumentException.
	 * 
	 * @param roomNumber
	 * @param roomType
	 * @throws IllegalArgumentException if the floorNumber is higher then 8 exclusive
	 * or 1 exclusive. Also, if it null there is a throw
	 */
	public DawsonRoom(int roomNumber, RoomType roomType) {
		// Assigns the floor number to the following variable
		int floorNumber = roomNumber / 100;

		//Validates that floor number is between 1 and 8, if its not, it will
		if (floorNumber > 8 || floorNumber < 1)
			throw new IllegalArgumentException(
					"Invalid room number. The floors must be within the range of 1 to 8 inclusive!");

		try {
			/*
			 *Checks if the given room type is null, if so, throws an illegal
			 *argument exception.
			 */
			if (roomType.equals(null))
				throw new IllegalArgumentException("Room Type cannot be null!");
		}
		/*
		 *if there is a null pointer exception in the try block, the following
		 *statement catches it and informs the user that the room type cannot
		 *be null
		 **/
		catch (NullPointerException iae) {
			throw new IllegalArgumentException("Room Type cannot be null!");

		}
		// Assigning each passed arguments to the class's fields value.
		this.roomType = roomType;
		this.roomNumber = roomNumber;

	}

	/**
	 * This accessor method gets the floor number which is the hundredths
	 * position of the variable roomNumber
	 * 
	 * @author Zahraa Horeibi
	 * @return int
	 */
	public int getFloor() {
		return this.roomNumber / 100;

	}

	/**
	 * This accessor method gets the room number which is the unit position of
	 * the variable roomNumber
	 * 
	 * @author Zahraa Horeibi
	 * @return int
	 */
	public int getNumber() {

		return this.roomNumber % 100;

	}

	/**
	 * This overridden equals method checks for equality between two objects of
	 * type Room
	 * 
	 * @author Zahraa
	 * @return boolean
	 */
	@Override
	public final boolean equals(Object obj) {
		// Comparing the hashCodes of the class' object with the passed one.
		if (this == obj)
			return true;
		// Checking if the passed object if null.
		if (obj == null)
			return false;
		/*Checking if the passed object is an instance of a Room. If so,
		 *we are casting it so we can treat it as a Room.
		 */
		if (obj instanceof Room) {

			Room room = (Room) obj;

			/*
			 *  Checking if the class' Credit Card Type is the same as the
			 *  previously instantiated Credit Card which was mainly the passed
			 *  object.
			 */			
			if (!this.equals(room.getRoomNumber()))
				return false;

			if (!this.equals(room.getRoomType()))
				return false;

			return true;

		}

		return true;

	}

	/**
	 * This overridden hashCode method returns the haschCode value of the
	 * cardType and number fields.
	 *
	 * @return int
	 */
	@Override
	public final int hashCode() {
		final int prime = 37; // We choose a random number and assign it to
		int result = 1; // We choose a random number and assign it to result.

		result = prime * this.roomNumber;
		result = prime * result + ((this.roomType == null) ? 0 : this.roomType.hashCode());

		return result;

	}

	/**
	 * This overridden toString method returns the Room Number and Room Type
	 * with an asterisk between both values as a String.
	 * 
	 * @author Zahraa Horeibi
	 * @return String
	 */

	@Override
	public final String toString() {
		return this.roomNumber + "*" + this.roomType;

	}

	/**
	 * This overridden compareTo method returns an int between -1 and 1 which
	 * will determine whether the passed room number is greater than, less than
	 * or equal to the roomNumber field.
	 * 
	 * @author Zahraa Horeibi
	 * @return int
	 */

	@Override
	public final int compareTo(Room room) {
		if (this.getRoomNumber() > room.getRoomNumber()) {
			return 1;
		} else if (this.getRoomNumber() == room.getRoomNumber()) {
			return 0;
		} else {
			return -1;
		}
	}

	/**
	 * This accessor method gets the Room Type.
	 * 
	 * @author Zahraa Horeibi
	 * @return CardType
	 */
	@Override
	public RoomType getRoomType() {
		return this.roomType;
	}

	/**
	 * This accessor method gets the room number.
	 * 
	 * @author Zahraa Horeibi
	 * @return int
	 */

	@Override
	public int getRoomNumber() {
		return this.roomNumber;
	}
}
