//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Modeling a tool that generates schedules
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
 * This class contains ways to generate a schedule or all possible schedules.
 */
public class ExamScheduler {

  /**
   * Returns a valid Schedule for the given set of rooms and courses
   *
   * @param rooms array of Rooms
   * @param courses array of Courses
   * @return a valid Schedule in which each course has been assigned a room
   * @throws IllegalStateException when no valid Schedule exists
   */
  public static Schedule findSchedule(Room[] rooms, Course[] courses) throws IllegalStateException {
    Room[] roomsCopy = Arrays.copyOf(rooms, rooms.length);
    Course[] coursesCopy = Arrays.copyOf(courses, courses.length);
    return findScheduleHelper(new Schedule(roomsCopy, coursesCopy), 0);
  }

  /**
   * A recursive method that assigns all unassigned courses in a Schedule beginning from the
   * index provided as an argument
   *
   * @param schedule schedule containing the courses and rooms to assign
   * @param index index of the course
   * @return a valid Schedule
   * @throws IllegalStateException when the Schedule is invalid
   */
  private static Schedule findScheduleHelper(Schedule schedule, int index)
      throws IllegalStateException {
    // Base case: check if index is equal to number of courses
    if (index == schedule.getNumCourses()) {
      if (schedule.isComplete()) {
        return schedule;
      } else {
        throw new IllegalStateException("Schedule is invalid.");
      }
    }
    // Recursive cases: if course at index hasn't been assigned, assign it to the first valid
    // room and then recursively call the method. If course is assigned, recursively call the
    // method.
    Schedule newSchedule = null;
    if (!schedule.isAssigned(index)) {
      Schedule tempSchedule = null;
      for (int i = 0; i < schedule.getNumRooms(); i++) {
        try {
          tempSchedule = schedule.assignCourse(index, i);
          return findScheduleHelper(tempSchedule, index + 1);
        } catch (IllegalArgumentException | IllegalStateException ignored) {
        } catch (IndexOutOfBoundsException e) {
          break;
        }
      }
    }
    return findScheduleHelper(schedule, index + 1);
  }

  /**
   * This method returns an ArrayList containing all possible Schedules for the given set of
   * rooms and courses
   *
   * @param rooms array of Rooms
   * @param courses array of Courses
   * @return an ArrayList containing all possible combinations of courses and rooms
   */
  public static ArrayList<Schedule> findAllSchedules(Room[] rooms, Course[] courses) {
    Room[] roomsCopy = Arrays.copyOf(rooms, rooms.length);
    Course[] coursesCopy = Arrays.copyOf(courses, courses.length);
    return findAllSchedulesHelper(new Schedule(roomsCopy, coursesCopy), 0);
  }

  /**
   * A recursive method that assigns all unassigned courses in a Schedule in all possible ways,
   * beginning from the index provided as an argument.
   *
   * @param schedule schedule containing rooms and courses
   * @param index index of a course
   * @return ArrayList containing all possible valid schedules
   */
  private static ArrayList<Schedule> findAllSchedulesHelper(Schedule schedule, int index) {
    ArrayList<Schedule> scheduleList = new ArrayList<>();
    // Base case: index equals number of courses and schedule is complete
    if (schedule.getNumCourses() == index && schedule.isComplete()) {
      scheduleList.add(schedule);
      return scheduleList;
    }
    // Recursive case: find valid schedule by recursively calling this method. If schedule
    // complete, add it to the ArrayList. Loop for each room.
    Schedule tempSchedule = null;
    for (int i = 0; i < schedule.getNumRooms(); i++) {
      if (!schedule.isAssigned(index)) {
        try {
          tempSchedule = schedule.assignCourse(index, i);
        } catch (IllegalArgumentException e) {
          continue;
        } catch (IndexOutOfBoundsException e) {
          break;
        }
        scheduleList.addAll(findAllSchedulesHelper(tempSchedule, index + 1));
      } else {
        scheduleList.addAll(findAllSchedulesHelper(schedule, index + 1));
      }
    }
    return scheduleList;
  }
}
