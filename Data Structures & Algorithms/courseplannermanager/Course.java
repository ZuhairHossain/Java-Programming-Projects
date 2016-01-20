
/**
 * Dennis Sosa
 * Assignment: #1 (Course Planner)
 * CSE 214
 * Stony Brook University
 * @author DennisSosa
 * 
 * Course Class
 */

public class Course implements Cloneable{
	private String name;
	private String department;
	private int code;
	private byte section;
	private String instructor;

	/**
	 * Creates a new Course object with uninstantiated parameters
	 */
	public Course(){
		
	}

	/**
	 * Creates a new Course object using given values.
	 * Overloaded constructor
	 * @param name
	 * 		full name of the Course 
	 * @param department
	 * 		department name of the Course
	 * @param code
	 * 		code number of the Course
	 * @param section
	 * 		section number of the Course
	 * @param instructor
	 * 		instructor's name of the Course
	 * @throws IllegalArgumentException
	 * 		indicates that code or section are invalid entries
	 */
	public Course(String name, String department, int code, 
		byte section, String instructor) throws IllegalArgumentException{
		if(code < 0 || section < 0 || code>999 || code < 100)
			throw new IllegalArgumentException("Invalid value for code and/or section (cannot be a negative value).");
		
		if(department.length()>3 || department.length()<3)
			throw new IllegalArgumentException("Invalid entry for department.");
		
		this.name = name;
		this.department = department;
		this.code = code;
		this.section = section;
		this.instructor = instructor;
	}

	/**
	 * Changes a particular Course's name to a new specified name
	 * @param name
	 * 		new name of the Course
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * Receives the name of a particular Course
	 * @return
	 * 		the name of a Course as a String
	 */
	public String getName(){
		return name;
	}

	/**
	 * Changes a particular Course's department name to a new specified 
	 * department name
	 * @param department
	 * 		new department name of the Course
	 */
	public void setDepartment(String department) throws IllegalArgumentException{
		if(department.length()>3 || department.length()<3)
			throw new IllegalArgumentException("Invalid entry for department.");
		this.department = department;
	}

	/**
	 * Receives the department name of a particular Course
	 * @return
	 * 		the department name of a Course as a String
	 */
	public String getDepartment(){
		return department;
	}

	/**
	 * Changes a particular Course's code value to a new
	 * specified code value
	 * @param code
	 * 		new code value of the Course
	 * @throws IllegalArgumentException
	 * 		indicates that code is an invalid entry (negative value)
	 */
	public void setCode(int code) throws IllegalArgumentException{
		if(code < 0 || code > 999 || code < 100)
			throw new IllegalArgumentException("Invalid value for code (cannot be a negative value).");
		
		this.code = code;
	}

	/**
	 * Receives the code value of a particular Course
	 * @return
	 * 		the code number of a Course as an int
	 */
	public int getCode(){
		return code;
	}

	/**
	 * Changes a particular Course's section value to a new
	 * specified section value
	 * @param section
	 * 		new section value of the Course
	 * @throws IllegalArgumentException
	 * 		indicates that section is an invalid entry (negative value)
	 */
	public void setSection(byte section) throws IllegalArgumentException{
		if(section < 0)
			throw new IllegalArgumentException("Invalid value for section (cannot be a negative value).");
		
		this.section = section;
	}

	/**
	 * Receives the section value of a particular Course
	 * @return
	 * 		the section number of a Course as a byte
	 */
	public byte getSection(){
		return section;
	}

	/**
	 * Changes a particular Course's instructor name to
	 * a new specified instructor name
	 * @param instructor
	 * 		new instructor name of the Course
	 */
	public void setInstructor(String instructor){
		this.instructor = instructor;
	}

	/**
	 * Receives the instructor name of a particular Course
	 * @return
	 * 		the instructor name of a Course as a String
	 */
	public String getInstructor(){
		return instructor;
	}


	/**
	 * Deep copies all data from a particular Course into a new
	 * Course reference. They are both separate copies of each other.
	 * @return
	 * 		a copy of a Course as an **Object**
	 * @throws CloneNotSupportedException
	 * 		catches CloneNotSupportedException if Cloneable clone cannot clone into 'c'
	 */
	public Object clone(){
		try{
			//deep copying primitive types, and references of Strings
			Course c = (Course)(super.clone());
			//deep copying strings
			c.setName(this.name);
			c.setDepartment(this.department);
			c.setInstructor(this.instructor);
			return c;

		} catch(CloneNotSupportedException ex){
			return null;
		}
	}

	/** 
	 * Compares a specified object to a Course Object
	 * to check for logical equivalence
	 * @param obj
	 * 		the object to be compared with the current Course
	 * @return
	 * 		true if specified object is fully equivalent to current Course,
	 * 		else false
	 */
	public boolean equals(Object obj){
		if(obj instanceof Course){
			Course c = (Course) obj;

			return	(
					( (this.getName()).equals(c.getName()) ) && 
					( (this.getDepartment()).equals(c.getDepartment()) ) &&
					( this.getCode() == (c.getCode()) ) &&
					( this.getSection() == (c.getSection()) ) &&
					( (this.getInstructor()).equals(c.getInstructor()) )
					);
		}

		else
			return false;	
	}

}