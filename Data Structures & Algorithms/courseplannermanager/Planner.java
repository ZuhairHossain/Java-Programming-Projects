

/**
 * Dennis Sosa
 * Assignment: #1 (Course Planner)
 * CSE 214
 * Stony Brook University
 * @author DennisSosa
 * 
 * Planner Class
 */

public class Planner {
	private Course[] courses;
	private int size = 0; // size is NOT the size of the courses array (length);
						  // it is the number of courses currently in 'courses'
	public final static int MAX_COURSES = 50; //constant

	/**
	 * Creates a new Planner object with instantiated courses
	 * <dt><b>Postconditions:</b><dd>
	 * 		this Planner has been initialized to an 
	 * 		empty list of Courses
	 */
	public Planner(){
		this.courses = new Course[MAX_COURSES+1];
	}

	/**
	 * Creates a new Planner object with instantiated empty courses & size
	 * @param size
	 * 		specified size value of courses in Planner object; only used for clone method
	 * <dt><b>Postconditions:</b><dd>
	 * 		this Planner has been initialized to an 
	 * 		empty list of Courses
	 */
	public Planner(int size){
		this.courses = new Course[MAX_COURSES+1];
		this.size = size;
	}

	/**
	 * Determines the number of courses currently in the list
	 * <dt><b>Preconditions:</b><dd>
	 * 		current Planner has been instantiated
	 * @return
	 * 		the number of Courses in current Planner as an int
	 *
	 */
	public int size() {
		if(courses==null)
			System.out.println("Planner is not instantiated.");
		return size;
	}

	/**
	 * Allows the user to add a course to a Planner object at a specified position
	 * @param newCourse
	 * 		the new Course object to add to the list
	 * @param position
	 * 		the preferred position to place newCourse in the list
	 * <dt><b>Preconditions:</b><dd>
	 * 		this Course object has been instantiated & 1<=position<=items_currently_in_list+1
	 * 		the number of Course objects in this Planner is < MAX_COURSES
	 * <dt><b>Postconditions:</b><dd>
	 * 		the new Course is now listed in the correct preference on the list
	 * @throws IllegalArgumentException
	 * 		indicates that position is not within the valid range
	 * @throws FullPlannerException
	 * 		indicates that there is no more room in the Planner to record an additional Course
	 */
	public void addCourse(Course newCourse, int position)
		throws IllegalArgumentException, FullPlannerException
	{
		if(newCourse!=null){

			if(this.size() >= MAX_COURSES)
				throw new FullPlannerException(size);

			if(position > MAX_COURSES || position < 1 || position > this.size()+1)
				throw new IllegalArgumentException("Position is out of range.");

			else{
				size++;

				for(int i=size; i>position; i--)
					courses[i] = courses[i-1];

				courses[position] = newCourse;
			}
		}

		else
			System.out.println("Course object newCourse is not instantiated.");
	}

	/**
	 * Allows the user to add a course to a Planner object at the end of the list
	 * uses addCourse(Course newCourse, int position) method directly
	 * @param newCourse
	 * 		the new Course object to add to the list
	 * @throws IllegalArgumentException
	 * 		indicates that position is not within the valid range
	 * @throws FullPlannerException
	 * 		indicates that there is no more room in the Planner to record an additional Course
	 */
	public void addCourse(Course newCourse) throws IllegalArgumentException, FullPlannerException{
		this.addCourse(newCourse,this.size()+1);
	}

	/**
	 * Removes a Course object at a specified position in the Planner
	 * @param position
	 * 		the position in the Planner where the Course will be removed from
	 * <dt><b>Preconditions:</b><dd>
	 * 		current Planner has been instantiated & 1<=position<=items_currently_in_list
	 * <dt><b>Postconditions:</b><dd>
	 * 		the Course at the desired position has been removed &
	 * 		all Courses that were originally greater than or equal to position
	 * 		are moved backward one position
	 * @throws IllegalArgumentException
	 * 		indicates that position is not within the valid range
	 */
	public void removeCourse(int position) 
		throws IllegalArgumentException
	{
		if(courses!=null){
			if(position > MAX_COURSES || position < 1 || position > this.size())
				throw new IllegalArgumentException("Indicated position is out of range.");

			else{
				for(int i=position; i<size; i++)
					courses[i] = courses[i+1];

				courses[size] = null;
				size--;
			}
		}

		else
			System.out.println("Planner is not instantiated.");
	}

	/**
	 * Obtains a Course from the Planner at a specified position
	 * @param position
	 * 		position of the desired Course to be retrieved
	 * <dt><b>Preconditions:</b><dd>
	 * 		the Planner object has been instantiated &
	 * 		1<=position<=items_currently_in_list
	 * @return
	 * 		the Course at the specified position in this Planner object
	 * @throws IllegalArgumentException
	 * 		indicates that position is not within the valid range 
	 */
	public Course getCourse(int position)
		throws IllegalArgumentException
	{
		if(courses!=null){
			if(position > MAX_COURSES || position < 1 || position > this.size())
				throw new IllegalArgumentException("Indicated position is out of range.");

			else{
				for(int i=1; i<=size; i++){
					if(i==position)
						return courses[i];
				}
			}
		}
		
		else
			System.out.println("Planner is not instantiated.");
		
		return null;
	}

	/**
	 * Prints all Courses that are within the specified department
	 * @param planner
	 * 		the list of courses to search in
	 * @param department
	 * 		the 3 letter department code for a Course
	 * <dt><b>Preconditions:</b><dd>
	 * 		this Planner object has been instantiated
	 * <dt><b>Postconditions:</b><dd>
	 * 		displays a neatly formatted table of each course filtered from the Planner;
	 * 		keep preference numbers the same
	 */
	public static void filter(Planner planner, String department)
	{

		if(planner!=null){
			System.out.println();
			System.out.println("No.  Course Name\t\tDepartment\tCode    Section  Instructor");
			System.out.println("----------------------------------------------------------------------------------------");

			for(int i=1; i<=planner.size(); i++){
				if( (planner.courses[i].getDepartment()).equals(department) )
					System.out.printf(" %-4d%-27s%-16s%-8d%02d\t %-1s\n",i,planner.courses[i].getName(),
							planner.courses[i].getDepartment(), planner.courses[i].getCode(),planner.courses[i].getSection(),
							planner.courses[i].getInstructor());
			}
		}

		else
			System.out.println("Planner is not instantiated.");
	}

	/**
	 * Checks whether a certain Course is already in the Planner's list
	 * @param course
	 * 		the Course object that we are searching for
	 * <dt><b>Preconditions:</b><dd>
	 * 		this Planner and Course have been both instantiated
	 * @return
	 * 		true if the Planner contains parameter course, else false
	 */
	public boolean exists(Course course) {
		if(courses!=null && course!=null){

			if(this.size()==0){
				return false;
			}

			for(int i=1; i<=this.size(); i++){
				if(courses[i].equals(course))
					return true;
			}
		}

		else
			System.out.println("Planner or Course have not been instantiated.");

		return false;
	}

	/** 
	 * Creates a copy of this Planner. Subsequent changes to the copy 
	 * will not affect the original & vice versa
	 * <dt><b>Preconditions:</b><dd>
	 * 		this Planner object has been instantiated
	 * @return
	 * 		a copy(backup) of this Planner object
	 */
	public Object clone() {
		if(courses==null)
			System.out.println("Planner object has not been instantiated.");
		
		Planner p = new Planner(this.size());
			
		for(int i=1; i<=this.size(); i++)
			p.courses[i] = (Course)this.courses[i].clone();
			
		return p;
	}

	/**
	 * Prints a neatly formatted table of each item in the list with its position number
	 * <dt><b>Preconditions:</b><dd>
	 * 		this Planner has been instantiated
	 * <dt><b>Postconditions:</b><dd>
	 * 		displays a neatly formatted table of each course from the Planner 
	 */
	public void printAllCourses(){
		if(courses!=null){
			System.out.println("No.  Course Name\t\tDepartment\tCode    Section  Instructor");
			System.out.println("----------------------------------------------------------------------------------------");
			for(int i=1; i<=size; i++){
				System.out.printf(" %-4d%-27s%-16s%-8d%02d\t %-1s\n",i,courses[i].getName(),
						courses[i].getDepartment(), courses[i].getCode(),courses[i].getSection(),
						courses[i].getInstructor());
			}
		}

		else
			System.out.println("Planner object has not been instantiated.");
	}

	/** 
	 * Obtains the String representation if this Planner object, which is
	 * a neatly formatted table of each Course in the Planner with position number
	 * @return
	 * 		the representation of this Planner object as a String
	 */
	public String toString(){
		String a = "No.  Course Name\t\tDepartment\tCode    Section  Instructor\n";
		String b = "----------------------------------------------------------------------------------------\n";
		String c = "";

		for(int i=1; i<=size; i++){
			c += String.format(" %-4d%-27s%-16s%-8d%02d\t %-1s\n",i,courses[i].getName(),
						courses[i].getDepartment(), courses[i].getCode(),courses[i].getSection(),
						courses[i].getInstructor());
		}

		return a+b+c+"\n";
	}

}