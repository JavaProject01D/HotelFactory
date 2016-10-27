package dw317.hotel.business.interfaces;

import java.io.Serializable;

import dw317.hotel.business.RoomType;

/**
 * @author Sevan Topalian
 * @version 27/09/2016
 */
public interface Room extends Comparable<Room>, Serializable {
	/**
	 * Returns the type of room.
	 * 
	 * @return RoomType
	 */
	RoomType getRoomType();

	/**
	 * Returns the room number made of the floor number and room number.
	 * 
	 * @return int
	 */
	int getRoomNumber();

	/**
	 * Returns the floor number.
	 * 
	 * @return int
	 */
	int getFloor();

	/**
	 * Returns only the room number on a given floor.
	 * 
	 * @return int
	 */
	int getNumber();
}