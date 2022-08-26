//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Tests the Course, Room, Schedule, and ExamScheduler classes
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

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class tests the Course, Room, Schedule, and ExamScheduler classes
 */
public class ExamSchedulerTester {

  /**
   * Main method
   *
   * @param args none
   */
  public static void main(String[] args) {
    System.out.println(testCourse() && testRoom() && testScheduleAccessors() && testAssignCourse()
        && testFindAllSchedules() && testFindSchedule());
  }

  /**
   * Tests the Course class
   *
   * @return true if all tests pass, false if otherwise
   */
  public static boolean testCourse() {
    // 1. Test invalid constructor arguments
    try {
      Course course = new Course("CS300", -1);
      System.out.println("Error: Course constructor failed to throw an exception when numStudents"
          + " < 0.");
      return false;
    } catch (IllegalArgumentException ignored) {
    } catch (Exception e) {
      System.out.println("Error: Course constructor threw an incorrect exception when numStudents"
          + " < 0.");
      return false;
    }
    // 2. Test getters with default values
    String expectedName = null;
    int expectedNumStudents = 0;
    try {
      Course course = new Course(null, 0);
      if (course.getName() != expectedName) {
        System.out.println("Error: getName() failed to return null.");
        return false;
      }
      if (course.getNumStudents() != expectedNumStudents) {
        System.out.println("Error: getNumStudents() failed to return the correct int (0).");
        return false;
      }
    } catch (Exception e) {
      System.out.println("Error: Course constructor incorrectly threw an exception with valid "
          + "inputs.");
      return false;
    }
    // 3. Test getters with other values
    expectedName = "CS300";
    expectedNumStudents = 200;
    try {
      Course course = new Course("CS300", 200);
      if (!course.getName().equals(expectedName)) {
        System.out.println("Error: getName() failed to return the correct String.");
        return false;
      }
      if (course.getNumStudents() != expectedNumStudents) {
        System.out.println("Error: getNumStudents() failed to return the correct int (200).");
        return false;
      }
    } catch (Exception e) {
      System.out.println("Error: Course constructor incorrectly threw an exception with valid "
          + "inputs.");
      return false;
    }
    return true;
  }

  /**
   * Tests the Room class
   *
   * @return true if all tests pass, false if otherwise
   */
  public static boolean testRoom() {
    // 1. Test invalid constructor arguments
    try {
      Room room = new Room("“Noland 168", -1);
      System.out.println("Error: Room constructor failed to throw an exception when capacity < 0.");
      return false;
    } catch (IllegalArgumentException ignored) {
    } catch (Exception e) {
      System.out.println("Error: Room constructor threw an incorrect exception when capacity < 0.");
      return false;
    }
    // 2. Test with default values
    String expectedLocation = null;
    int expectedCapacity = 0;
    try {
      Room room = new Room(null, 0);
      if (room.getLocation() != expectedLocation) {
        System.out.println("Error: getLocation() failed to return null.");
        return false;
      }
      if (room.getCapacity() != expectedCapacity) {
        System.out.println("Error: getCapacity() failed to return the correct int (0).");
        return false;
      }
    } catch (Exception e) {
      System.out.println("Error: Room constructor incorrectly threw an exception with valid inputs"
          + ".");
      return false;
    }
    // 3. Test with other values
    expectedLocation = "Noland 168";
    expectedCapacity = 40;
    try {
      Room room = new Room("Noland 168", 40);
      if (!room.getLocation().equals(expectedLocation)) {
        System.out.println("Error: getLocation() failed to return the correct String.");
        return false;
      }
      if (room.getCapacity() != expectedCapacity) {
        System.out.println("Error: getCapacity() failed to return the correct int (40).");
        return false;
      }
    } catch (Exception e) {
      System.out.println("Error: Room constructor incorrectly threw an exception with valid inputs"
        + ".");
      return false;
    }
    // 4. Test reduceCapacity() invalid argument
    try {
      Room room = new Room("Noland 168", 40);
      Room newRoom = room.reduceCapacity(41);
      System.out.println("Error: reduceCapacity() failed to throw an exception with invalid input"
          + ".");
      return false;
    } catch (IllegalArgumentException ignored) {
    } catch (Exception e) {
      System.out.println("Error: reduceCapacity() threw an incorrect exception with invalid input"
          + ".");
      return false;
    }
    // 5. Test reduceCapacity() with edge case
    expectedCapacity = 0;
    try {
      Room room = new Room("Noland 168", 40);
      Room newRoom = room.reduceCapacity(40);
      if (newRoom.getCapacity() != expectedCapacity) {
        System.out.println("Error: reduceCapacity() failed to reduce capacity by 40.");
        return false;
      }
    } catch (Exception e) {
      System.out.println("Error: reduceCapacity() incorrectly threw an exception with valid input"
          + ".");
      return false;
    }
    // 6. Test reduceCapacity() with other value
    expectedCapacity = 35;
    try {
      Room room = new Room("Noland 168", 40);
      Room newRoom = room.reduceCapacity(5);
      if (newRoom.getCapacity() != expectedCapacity) {
        System.out.println("Error: reduceCapacity() failed to reduce capacity by 5.");
        return false;
      }
    } catch (Exception e) {
      System.out.println("Error: reduceCapacity() incorrectly threw an exception with valid input"
          + ".");
      return false;
    }
    return true;
  }

  /**
   * Tests the accessors in the Schedule class
   *
   * @return true if all tests pass, false if otherwise
   */
  public static boolean testScheduleAccessors() {
    // 1. Test with empty arrays
    Room[] rooms = new Room[0];
    Course[] courses = new Course[0];
    Schedule schedule = new Schedule(rooms, courses);

    int expectedNumRooms = 0;
    int actualNumRooms = schedule.getNumRooms();
    if (expectedNumRooms != actualNumRooms) {
      System.out.println("Error: getNumRooms() failed to return the correct number of rooms (0).");
      return false;
    }
    try {
      schedule.getRoom(0);
      System.out.println("Error: getRoom() failed to throw an exception with invalid input.");
      return false;
    } catch (IndexOutOfBoundsException ignored) {
    } catch (Exception e) {
      System.out.println("Error: getRoom() threw an incorrect exception with invalid input.");
      return false;
    }
    int expectedNumCourses = 0;
    int actualNumCourses = schedule.getNumCourses();
    if (expectedNumCourses != actualNumCourses) {
      System.out.println("Error: getNumCourses() failed to return the correct number of courses "
          + "(0).");
      return false;
    }
    try {
      schedule.getCourse(0);
      System.out.println("Error: getCourse() failed to throw an exception with invalid input.");
      return false;
    } catch (IndexOutOfBoundsException ignored) {
    } catch (Exception e) {
      System.out.println("Error: getCourse() threw an incorrect exception with invalid input.");
      return false;
    }
    try {
      schedule.getAssignment(0);
      System.out.println("Error: getAssignment() failed to throw an exception with invalid input.");
      return false;
    } catch (IndexOutOfBoundsException ignored) {
    } catch (Exception e) {
      System.out.println("Error: getAssignment() threw an incorrect exception with invalid input.");
      return false;
    }
    // 2. Test with non-empty arrays
    rooms = new Room[] {new Room("location1", 100)};
    courses = new Course[] {new Course("course1", 10),
                            new Course("course2", 20)};
    schedule = new Schedule(rooms, courses);

    expectedNumRooms = 1;
    actualNumRooms = schedule.getNumRooms();
    if (expectedNumRooms != actualNumRooms) {
      System.out.println("Error: getNumRooms() failed to return the correct number of rooms (1).");
      return false;
    }
    try {
      Room room = schedule.getRoom(0);
      if (!room.getLocation().equals("location1") || room.getCapacity() != 100) {
        System.out.println("Error: getRoom() returned the incorrect room.");
        return false;
      }
    } catch (Exception e) {
      System.out.println("Error: getRoom() incorrectly threw an exception with valid input.");
      return false;
    }
    expectedNumCourses = 2;
    actualNumCourses = schedule.getNumCourses();
    if (expectedNumCourses != actualNumCourses) {
      System.out.println("Error: getNumCourses() failed to return the correct number of courses "
          + "(2).");
      return false;
    }
    try {
      Course course = schedule.getCourse(1);
      if (!course.getName().equals("course2") || course.getNumStudents() != 20) {
        System.out.println("Error: getCourse() returned the incorrect course.");
        return false;
      }
    } catch (Exception e) {
      System.out.println("Error: getCourse() incorrectly threw an exception with valid input.");
      return false;
    }
    try {
      schedule.getAssignment(1);
      System.out.println("Error: getAssignment() failed to throw an exception with invalid input.");
      return false;
    } catch (IllegalArgumentException ignored) {
    } catch (Exception e) {
      System.out.println("Error: getAssignment() threw an incorrect exception with invalid input.");
      return false;
    }
    try {
      Schedule newSchedule = schedule.assignCourse(0, 0);
      Room newRoom = newSchedule.getAssignment(0);
      if (!newRoom.getLocation().equals("location1") || newRoom.getCapacity() != 90) {
        System.out.println("Error: getAssignment() failed to return the correct room.");
        return false;
      }
      newSchedule = newSchedule.assignCourse(1, 0);
      newRoom = newSchedule.getAssignment(1);
      if (!newRoom.getLocation().equals("location1") || newRoom.getCapacity() != 70) {
        System.out.println("Error: getAssignment() failed to return the correct room.");
        return false;
      }
    } catch (Exception e) {
      System.out.println("Error: getAssignment() incorrectly threw an exception.");
      return false;
    }
    return true;
  }

  /**
   * Tests the assignCourse() method in the Schedule class
   *
   * @return true if all tests pass, false if otherwise
   */
  public static boolean testAssignCourse() {
    Room[] rooms = new Room[] {new Room("location1", 100),
                               new Room("location2", 5),
                               new Room("location3", 50)};
    Course[] courses = new Course[] {new Course("course1", 10),
                                     new Course("course2", 20),
                                     new Course("course3", 40)};
    Schedule schedule = new Schedule(rooms, courses);
    // 1. Test with negative index
    try {
      schedule.assignCourse(-1, 0);
      System.out.println("Error: assignCourse() failed to throw an exception when courseIndex is "
          + "negative.");
      return false;
    } catch (IndexOutOfBoundsException ignored) {
    } catch (Exception e) {
      System.out.println("Error: assignCourse() threw an incorrect exception when courseIndex is "
          + "negative.");
      return false;
    }
    try {
      schedule.assignCourse(0, -1);
      System.out.println("Error: assignCourse() failed to throw an exception when roomsIndex is "
          + "negative.");
      return false;
    } catch (IndexOutOfBoundsException ignored) {
    } catch (Exception e) {
      System.out.println("Error: assignCourse() threw an incorrect exception when roomsIndex is "
          + "negative.");
      return false;
    }
    // 2. Test with too large index
    try {
      schedule.assignCourse(3, 0);
      System.out.println("Error: assignCourse() failed to throw an exception when courseIndex is "
          + "too large.");
      return false;
    } catch (IndexOutOfBoundsException ignored) {
    } catch (Exception e) {
      System.out.println("Error: assignCourse() threw an incorrect exception when courseIndex is "
          + "too large.");
      return false;
    }
    try {
      schedule.assignCourse(0, 3);
      System.out.println("Error: assignCourse() failed to throw an exception when roomsIndex is "
          + "too large.");
      return false;
    } catch (IndexOutOfBoundsException ignored) {
    } catch (Exception e) {
      System.out.println("Error: assignCourse() threw an incorrect exception when roomsIndex is "
          + "too large.");
      return false;
    }
    // 3. Test when Room doesn't have enough capacity
    try {
      schedule.assignCourse(0, 1);
      System.out.println("Error: assignCourse() failed to throw an exception when Room doesn't "
          + "have enough capacity.");
      return false;
    } catch (IllegalArgumentException ignored) {
    } catch (Exception e) {
      System.out.println("Error: assignCourse() threw an incorrect exception when Room doesn't "
          + "have enough capacity.");
      return false;
    }
    // 4. Test when Course has already been assigned
    try {
      schedule = schedule.assignCourse(2, 0);
      schedule.assignCourse(2, 2);
      System.out.println("Error: assignCourse() failed to throw an exception when Course has "
          + "already been assigned a Room.");
      return false;
    } catch (IllegalArgumentException ignored) {
    } catch (Exception e) {
      System.out.println("Error: assignCourse() threw an incorrect exception when Course has "
          + "already been assigned a Room.");
      return false;
    }
    // 5. Test edge cases
    rooms = new Room[] {new Room("location1", 100),
                        new Room("location2", 5),
                        new Room("location3", 50)};
    courses = new Course[] {new Course("course1", 10),
                            new Course("course2", 20),
                            new Course("course3", 40)};
    schedule = new Schedule(rooms, courses);
    try {
      Schedule newSchedule = schedule.assignCourse(0, 0);
      Room newRoom = newSchedule.getAssignment(0);
      String expectedLocation = "location1";
      int expectedCapacity = 90;
      if (!newRoom.getLocation().equals(expectedLocation)
          || newRoom.getCapacity() != expectedCapacity) {
        System.out.println(newRoom.getLocation() + newRoom.getCapacity());
        System.out.println("Error: assignCourse() failed to assign the correct Room and decrease "
            + "its capacity.");
        return false;
      }
    } catch (Exception e) {
      System.out.println("Error: assignCourse() incorrectly threw an exception with valid input.");
      return false;
    }
    try {
      Schedule newSchedule = schedule.assignCourse(2, 2);
      Room newRoom = newSchedule.getAssignment(2);
      String expectedLocation = "location3";
      int expectedCapacity = 10;
      if (!newRoom.getLocation().equals(expectedLocation)
          || newRoom.getCapacity() != expectedCapacity) {
        System.out.println("Error: assignCourse() failed to assign the correct Room and decrease "
            + "its capacity.");
        return false;
      }
    } catch (Exception e) {
      System.out.println("Error: assignCourse() incorrectly threw an exception with valid input.");
      return false;
    }
    return true;
  }

  /**
   * Tests the findAllSchedules() method in the ExamScheduler class
   *
   * @return true if all tests pass, false if otherwise
   */
  public static boolean testFindAllSchedules() {
    // 1. Test with no possible schedules
    Room[] rooms = new Room[] {new Room("location1", 10),
                               new Room("location2", 20),
                               new Room("location3", 5)};
    Course[] courses = new Course[] {new Course("course1", 30),
                                     new Course("course2", 40),
                                     new Course("course3", 15)};
    int expectedSize = 0;
    ArrayList<Schedule> actualList = ExamScheduler.findAllSchedules(rooms, courses);
    if (actualList.size() != expectedSize) {
      System.out.println("Error: ExamScheduler.findAllSchedules() failed to return an empty "
          + "ArrayList.");
      return false;
    }
    // 2. Test with one possible schedule
    rooms = new Room[] {new Room("location1", 15),
                        new Room("location2", 30),
                        new Room("location3", 40)};
    courses = new Course[] {new Course("course1", 30),
                            new Course("course2", 40),
                            new Course("course3", 15)};
    expectedSize = 1;
    actualList = ExamScheduler.findAllSchedules(rooms, courses);
    if (expectedSize != actualList.size()) {
      System.out.println("Error: ExamScheduler.findAllSchedules() failed to return the correct "
          + "ArrayList with one Schedule.");
      return false;
    }
    // 3. Test with multiple possible schedules
    rooms = new Room[] {new Room("location1", 100),
                        new Room("location2", 150),
                        new Room("location3", 75)};
    courses = new Course[] {new Course("course1", 50),
                            new Course("course2", 110),
                            new Course("course3", 75)};
    expectedSize = 2;
    actualList = ExamScheduler.findAllSchedules(rooms, courses);
    if (expectedSize != actualList.size()) {
      System.out.println("Error: ExamScheduler.findAllSchedules() failed to return the correct "
          + "ArrayList with two Schedules.");
      return false;
    }
    return true;
  }

  /**
   * Tests the findSchedule() method in the ExamScheduler class
   *
   * @return true if all tests pass, false if otherwise
   */
  public static boolean testFindSchedule() {
    // 1. Test with no valid schedules
    Room[] rooms = new Room[] {new Room("location1", 10),
                               new Room("location2", 20),
                               new Room("location3", 5)};
    Course[] courses = new Course[] {new Course("course1", 30),
                                     new Course("course2", 40),
                                     new Course("course3", 15)};
    try {
      ExamScheduler.findSchedule(rooms, courses);
      System.out.println("Error: ExamScheduler.findSchedule() failed to throw an exception when "
          + "there are no valid schedules.");
      System.out.println(ExamScheduler.findSchedule(rooms, courses));
      return false;
    } catch (IllegalStateException ignored) {
    } catch (Exception e) {
      System.out.println("Error: ExamScheduler.findSchedule() threw an incorrect exception when "
          + "there are no valid schedules.");
      return false;
    }
    // 2. Test with valid schedule
    rooms = new Room[] {new Room("location1", 20),
                        new Room("location2", 200),
                        new Room("location3", 50)};
    courses = new Course[] {new Course("course1", 40),
                            new Course("course2", 20),
                            new Course("course3", 185)};
    try {
      Schedule schedule = ExamScheduler.findSchedule(rooms, courses);
      String[] expectedAssignments = new String[] {"location3", "location1", "location2"};
      String[] actualAssignments = new String[3];
      for (int i = 0; i < actualAssignments.length; i++) {
        actualAssignments[i] = schedule.getAssignment(i).getLocation();
      }
      if (!Arrays.equals(expectedAssignments, actualAssignments)) {
        System.out.println("Error: ExamScheduler.findSchedule() failed to build a complete "
            + "schedule.");
        return false;
      }
    } catch (Exception e) {
      System.out.println("Error: ExamScheduler.findSchedule() incorrectly threw an exception.");
      return false;
    }
    return true;
  }
}
