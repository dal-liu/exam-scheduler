//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Modeling a course
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
 * This class models a course.
 */
public class Course {
  private final String name;
  private final int numStudents;

  /**
   * Constructs a Course object.
   *
   * @param name        name of the course
   * @param numStudents number of students in the course
   * @throws IllegalArgumentException when numStudents is less than 0
   */
  public Course(String name, int numStudents) throws IllegalArgumentException {
    if (numStudents < 0)
      throw new IllegalArgumentException("Tried to create Course object with negative students.");

    this.name = name;
    this.numStudents = numStudents;
  }

  /**
   * Gets the name of the course.
   *
   * @return name of the course
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the number of students.
   *
   * @return number of students
   */
  public int getNumStudents() {
    return numStudents;
  }
}
