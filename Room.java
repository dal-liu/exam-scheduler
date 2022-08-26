//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Modeling a room
// Course:   CS 300 Spring 2022
//
// Author:   Daniel Liu
// Email:    daliu2@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    (name of your pair programming partner)
// Partner Email:   (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         NONE
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class models a room for courses.
 */
public class Room {
  private final String location;
  private final int capacity;

  /**
   * Constructs a room object.
   *
   * @param location name of location of the room
   * @param capacity how many students the room can hold
   * @throws IllegalArgumentException when capacity is less than 0
   */
  public Room(String location, int capacity) throws IllegalArgumentException {
    if (capacity < 0)
      throw new IllegalArgumentException("Tried to create Room object with negative capacity.");

    this.location = location;
    this.capacity = capacity;
  }

  /**
   * Gets location of the room
   *
   * @return location of the room
   */
  public String getLocation() {
    return location;
  }

  /**
   * Gets the capacity of the room
   *
   * @return capacity of the room
   */
  public int getCapacity() {
    return capacity;
  }

  /**
   * Reduces the capacity of the room by the input amount
   *
   * @param reduceBy amount to reduce the capacity by
   * @return a new Room object with the new capacity
   * @throws IllegalArgumentException when argument is greater than the Room's original capacity
   */
  public Room reduceCapacity(int reduceBy) throws IllegalArgumentException {
    if (reduceBy > capacity)
      throw new IllegalArgumentException(
        "Number to be subtracted from capacity was larger than the capacity.");

    return new Room(location, capacity - reduceBy);
  }
}
