import static org.junit.Assert.*;
import org.junit.*;
import voc.grader.*;

public class GraderTest {

 public static void main(String[] args) {

  final int JUNIT_TESTS_MAX = 95;
  final int CODING_STYLE_MAX = 5;

  // Grader used for assigning points to the final assignment grading criteria.
  Grader g = new Grader();
  g.setReportOutput(1);

  /* JUnit Scoring Weight */

  // Calculate the weighted score of all test cases, returned as a proportion
  // of test cases passed to total test cases run.
  double scoreWeight = JUnitScoringWeight.calculateScoreWeight(<AssignmentTest>.class);

  // Output the JUnit test points received.
  System.out.printf("JUnit Tests Score: %d / %d\n", (int) (scoreWeight * JUNIT_TESTS_MAX), JUNIT_TESTS_MAX);

  // Add the points to the "JUnit" grading criteria.
  GradeInfo gi1 = new GradeInfo("JUnit", (int) (scoreWeight * JUNIT_TESTS_MAX));
  g.addGrade(gi1);
  g.addReportString(String.format("JUnit Tests Score: %d / %d", (int) (scoreWeight * JUNIT_TESTS_MAX), JUNIT_TESTS_MAX));

  /* Coding stye with Checkstyle */

  // Get the total number of coding style errors
  int numCheckstyleErrors = CheckstyleGrading.getTotalErrors();

  // Calculate the number of points earned based on the number of errors.
  // This implementation assumes an allotment of 5 points for coding style,
  // with one point taken off per error for a maximum reduction of 5 points.
  int checkstyleScore = numCheckstyleErrors > 5 ? 0 : 5 - numCheckstyleErrors;

  // Output the coding style points received.
  System.out.println("Coding Style Score: " + checkstyleScore + " / " + CODING_STYLE_MAX);

  // Add the points to the "Coding Style" grading criteria.
  GradeInfo gi2 = new GradeInfo("Coding Style", checkstyleScore);
  g.addGrade(gi2);
  g.addReportString("Coding Style Score: " + checkstyleScore + " / " + CODING_STYLE_MAX);

 }
}
