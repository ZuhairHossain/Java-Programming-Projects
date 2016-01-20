

/**
 * Dennis Sosa
 * Assignment: #1 (Course Planner)
 * CSE 214
 * Stony Brook University
 * @author DennisSosa
 *
 * FullPlannerException Custom Exception Class
 */

public class FullPlannerException extends Exception {
	private int plannerSize;

	/**
	 * Throws an exception, and displays a message to the user
	 * indicating that there is no more room in the Planner;
	 * plannerSize is instantiated
	 * @param plannerSize
	 * 		number of courses in the Planner
	 */
	public FullPlannerException(int plannerSize) {
		super("There is no more room in the Planner to record an additional Course.\nPlanner Size: " + plannerSize);
		this.plannerSize = plannerSize;
	}

	/**
	 * Receives the value of the plannerSize
	 * which caused this Custom Exception Class to be called
	 * @return
	 * 		the value of plannerSize as an int
	 */
	public int getPlannerSize(){
		return plannerSize;
	}
}

