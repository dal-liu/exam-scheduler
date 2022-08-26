//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Modeling a schedule of courses and rooms
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

import java.util.Arrays;

/**
 * Models a schedule of classes and their rooms
 */
public class Schedule {
  private final Room[] rooms;
  private final Course[] courses;
  private final int[] assignments;

  /**
   * Constructs a Schedule object with rooms and courses
   *
   * @param rooms   array of possible rooms
   * @param courses array containing the courses
   */
  public Schedule(Room[] rooms, Course[] courses) {
    this.rooms = rooms;
    this.courses = courses;
    assignments = new int[courses.length];
    Arrays.fill(assignments, -1);
  }

  /**
   * Constructs a Schedule object with room assignments
   *
   * @param rooms       array of possible rooms
   * @param courses     array containing the courses
   * @param assignments int array containing the indexes of rooms corresponding to the course of the
   *                    same index as the assignment
   */
  private Schedule(Room[] rooms, Course[] courses, int[] assignments) {
    this.rooms = rooms;
    this.courses = courses;
    this.assignments = assignments;
  }

  /**
   * Gets the number of rooms in the schedule
   *
   * @return the number of rooms
   */
  public int getNumRooms() {
    return rooms.length;
  }

  /**
   * Gets a room from the array of rooms
   *
   * @param index index of the room to return
   * @return a room at the specified index
   * @throws IndexOutOfBoundsException when the given index is invalid
   */
  public Room getRoom(int index) throws IndexOutOfBoundsException {
    if (index >= rooms.length || index < 0)
      throw new IndexOutOfBoundsException("Input index for rooms[] is out of bounds.");

    return rooms[index];
  }

  /**
   * Gets the number of courses in the schedule
   *
   * @return the number of courses
   */
  public int getNumCourses() {
    return courses.length;
  }

  /**
   * Gets a course from the array of courses in the schedule
   *
   * @param index index of the course to return
   * @return a course at the specified index
   * @throws IndexOutOfBoundsException when the given index is invalid
   */
  public Course getCourse(int index) throws IndexOutOfBoundsException {
    if (index >= courses.length || index < 0)
      throw new IndexOutOfBoundsException("Input index is out of bounds.");

    return courses[index];
  }

  /**
   * Returns a boolean determining if a course has been assigned a room.
   *
   * @param index index of the course
   * @return true if it has been assigned, false if it hasn't
   */
  public boolean isAssigned(int index) {
    return assignments[index] != -1;
  }

  /**
   * Gets the room that a course has been assigned to
   *
   * @param index index of the course
   * @return the room that the course is assigned to
   * @throws IllegalArgumentException  when the course has not been assigned a room
   * @throws IndexOutOfBoundsException when the given index is invalid
   */
  public Room getAssignment(int index) throws IllegalArgumentException, IndexOutOfBoundsException {
    if (index >= courses.length || index < 0)
      throw new IndexOutOfBoundsException("Input index is out of bounds.");
    if (!this.isAssigned(index))
      throw new IllegalArgumentException("Course has not been assigned a room.");

    return rooms[assignments[index]];
  }

  /**
   * Returns whether the schedule is complete or not.
   *
   * @return true if schedule is complete, false if it isn't
   */
  public boolean isComplete() {
    for (int i = 0; i < courses.length; i++) {
      if (!this.isAssigned(i))
        return false;
    }
    return true;
  }

  /**
   * Returns a new Schedule object with the course at the first argument index assigned to the room
   * at the second argument index. Reduces the capacity of the room if successful.
   *
   * @param coursesIndex index of the course
   * @param roomsIndex   index of the room
   * @return a new Schedule with the course assigned to the room
   * @throws IndexOutOfBoundsException when courseIndex or roomIndex is invalid
   * @throws IllegalArgumentException  when course has already been assigned a room or when the room
   *                                   doesn't have enough capacity
   */
  public Schedule assignCourse(int coursesIndex, int roomsIndex)
    throws IndexOutOfBoundsException, IllegalArgumentException {
    // Check invalid cases
    if (coursesIndex >= courses.length || coursesIndex < 0)
      throw new IndexOutOfBoundsException("coursesIndex was out of bounds.");
    if (roomsIndex >= rooms.length || roomsIndex < 0)
      throw new IndexOutOfBoundsException("roomsIndex was out of bounds.");
    if (this.isAssigned(coursesIndex))
      throw new IllegalArgumentException("Course has already been assigned a room.");
    if (rooms[roomsIndex].getCapacity() < courses[coursesIndex].getNumStudents())
      throw new IllegalArgumentException("Room does not have enough capacity.");
    // Create NEW Schedule object
    Course[] coursesCopy = Arrays.copyOf(courses, courses.length);
    Room[] roomsCopy = Arrays.copyOf(rooms, rooms.length);
    int[] assignmentsCopy = Arrays.copyOf(assignments, assignments.length);

    assignmentsCopy[coursesIndex] = roomsIndex;
    roomsCopy[roomsIndex] =
      rooms[roomsIndex].reduceCapacity(coursesCopy[coursesIndex].getNumStudents());
    return new Schedule(roomsCopy, coursesCopy, assignmentsCopy);
  }

  /**
   * Returns a string representation of a schedule
   *
   * @return string representation of the schedule
   */
  @Override public String toString() {
    String output = "{";
    for (int i = 0; i < courses.length - 1; i++) {
      output += courses[i].getName() + ": ";
      if (!this.isAssigned(i)) {
        output += "Unassigned";
      } else {
        output += rooms[assignments[i]].getLocation();
      }
      output += ", ";
    }
    output += courses[courses.length - 1].getName() + ": ";
    if (!this.isAssigned(courses.length - 1)) {
      output += "Unassigned";
    } else {
      output += rooms[assignments[courses.length - 1]].getLocation();
    }
    output += "}";
    return output;
  }
}
