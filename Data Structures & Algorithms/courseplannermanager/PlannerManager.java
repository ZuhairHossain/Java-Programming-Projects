
import java.util.Scanner;

/**
 * Dennis Sosa
 * Assignment: #1 (Course Planner)
 * CSE 214
 * Stony Brook University
 * @author DennisSosa
 * 
 * PlannerManager Class
 */

public class PlannerManager {

	/**
	 * The main method runs a menu driver application which first creates an empty Planner object;
	 * the program prompts the user for a command to execute an operation
	 * in accordance to the menu
	 * @param args
	 * 		empty command-line argument
	 * @throws IllegalArgumentException
	 * 		indicates invalid entries within the Planner class 
	 * 		& within the main program
	 * @throws FullPlannerException
	 * 		indicates that Planner is full, within the Planner class methods
	 */
	public static void main(String[] args) throws IllegalArgumentException, FullPlannerException{
		Planner planner = new Planner();
		Planner backupCopy = new Planner();
		
		Scanner in = new Scanner(System.in); //Scanner for user-input
		
		boolean cont = true;

		String selection = "";
		char selChar = ' ';
		
		System.out.println("Welcome to Dennis Sosa's Course Planner.\n");
		
		while(cont==true){ //program will continue as long as cont remains true
			//Main menu of program
			System.out.println();
			System.out.println("(A) Add Course");
			System.out.println("(G) Get Course");
			System.out.println("(R) Remove Course");
			System.out.println("(P) Print Courses in Planner");
			System.out.println("(F) Filter by Department Code");
			System.out.println("(L) Look For Course");
			System.out.println("(S) Size");
			System.out.println("(B) Backup");
			System.out.println("(PB) Print Courses in Backup");
			System.out.println("(RB) Revert to Backup");
			System.out.println("(Q) Quit");
			System.out.println();
			
			System.out.print("Enter a selection: ");
			
			selection = in.nextLine().toUpperCase();
			selChar = selection.charAt(0); //reads char at position 0 of String, which should be expected
										   //to be only one character

			if(selection.length() >= 2 && !selection.equals("PB") && !selection.equals("RB"))
				System.out.println("Invalid Entry, please try again.");
			
			// two constants cannot be used as char so these two are checked separately
			else if(selection.equals("PB")){
				System.out.println("\nPlanner (Backup):");
				backupCopy.printAllCourses();
			}
			
			else if(selection.equals("RB")){
				planner = (Planner) backupCopy.clone();
				System.out.println("\nPlanner successfully reverted to the backup copy.");
			}
			
			//Goes on to the switch statement in accordance to user-input char
			else{
				switch(selChar){
					case 'A':
						System.out.print("Enter course name: ");
						String courseName = in.nextLine();
						
						Course newCourse = new Course();
						
						newCourse.setName(courseName);//assuming at most 25 characters long

						System.out.print("Enter department: ");
						String department = in.nextLine().toUpperCase();
						
						try{
							newCourse.setDepartment(department);
						} catch(IllegalArgumentException ex){
							System.out.println(ex.getMessage());
							break;
						}

						System.out.print("Enter course code: ");
						int code = in.nextInt();
						in.nextLine();
						
						try{
							newCourse.setCode(code);
						} catch(IllegalArgumentException ex){
							System.out.println(ex.getMessage());
							break;
						}

						System.out.print("Enter course section: ");
						byte section = in.nextByte();
						in.nextLine();
						
						try{
							newCourse.setSection(section);
						} catch(IllegalArgumentException ex){
							System.out.println(ex.getMessage());
							break;
						}

						System.out.print("Enter Instructor: ");
						
						String instructor = in.nextLine();

						newCourse.setInstructor(instructor);

						System.out.print("Enter position: ");
						int position = in.nextInt();
						in.nextLine();
						
						try{
							planner.addCourse(newCourse,position);
						} catch(IllegalArgumentException e){
							System.out.println(e.getMessage());
							break;
						} catch(FullPlannerException ex){
							System.out.println(ex.getMessage());
							break;
						}
						
						System.out.println();
						System.out.printf("%s %d.%02d successfully added to the planner.\n",
								newCourse.getDepartment(),newCourse.getCode(),newCourse.getSection());

						break;

					case 'R':
						System.out.print("Enter position: ");
						int pos = in.nextInt();
						in.nextLine();
						
						try{
							Course removingCourse = planner.getCourse(pos);
							planner.removeCourse(pos);
							System.out.printf("\n%s %d.%02d has been successfully removed from the planner.\n",
								removingCourse.getDepartment(),removingCourse.getCode(),removingCourse.getSection());
						} catch(IllegalArgumentException ex){
							System.out.println(ex.getMessage());
							break;
						}
						
						break;

					case 'G':
						System.out.print("Enter position: ");
						int pos1 = in.nextInt();
						in.nextLine();
						
						try{
							Course obtain = planner.getCourse(pos1);

							System.out.println("\nNo.  Course Name\t\tDepartment\tCode    Section  Instructor");
							System.out.println("----------------------------------------------------------------------------------------");

							System.out.printf(" %-4d%-27s%-16s%-8d%02d\t %-1s\n",pos1,obtain.getName(),
								obtain.getDepartment(), obtain.getCode(),obtain.getSection(),
								obtain.getInstructor());
						} catch(IllegalArgumentException ex){
							System.out.println(ex.getMessage());
							break;
						}

						break;

					case 'P':
						System.out.println("\nPlanner:");
						planner.printAllCourses();
						break;

					case 'F':
						System.out.print("Enter department code: ");
						String depart = in.nextLine().toUpperCase();

						Planner plannerCopy = (Planner) planner.clone();
						
						plannerCopy.filter(planner,depart);

						break;

					case 'L':
						Course searchCourse = new Course();
						
						System.out.print("Enter course name: ");
						String name = in.nextLine();
						
						searchCourse.setName(name); //assuming at most 25 characters long
						
						System.out.print("Enter department: ");
						String dept = in.nextLine().toUpperCase();
						
						try{
							searchCourse.setDepartment(dept);
						} catch(IllegalArgumentException ex){
							System.out.println(ex.getMessage());
							break;
						}

						System.out.print("Enter course code: ");
						int c = in.nextInt();
						in.nextLine();
						
						try{
							searchCourse.setCode(c);
						} catch(IllegalArgumentException ex){
							System.out.println(ex.getMessage());
							break;
						}
						
						System.out.print("Enter course section: ");
						byte sect = in.nextByte();
						in.nextLine();
						
						try{
							searchCourse.setSection(sect);
						} catch(IllegalArgumentException ex){
							System.out.println(ex.getMessage());
							break;
						}

						System.out.print("Enter instructor: ");
						String instruct = in.nextLine(); //assuming at most 25 characters long
						
						searchCourse.setInstructor(instruct);

						int found = 0;

						for(int i=1; i<=planner.size(); i++){
							if(planner.getCourse(i).equals(searchCourse)){
								found = i;
								break;
							}
						}
						
						System.out.println();
						
						if(found!=0)
							System.out.printf("%s %d.%02d was found in the planner at position %d.\n",
									dept,c,sect,found);

						else
							System.out.printf("%s %d.%02d was not found in the planner.\n",
									dept,c,sect);
						break;
						
					case 'S':
						System.out.println("\nThere are "+planner.size()+" courses in your planner.");
						break;

					case 'B':
						backupCopy = (Planner) planner.clone();
						System.out.println("\nCreated a backup of the current planner.");
						break;
						
					case 'Q':
						cont = false;
						System.out.println("Program terminating successfully...");
						break;

					default:
						System.out.println("Invalid Entry, please try again.");
						break;
				}
			}
			//System.out.println(planner.toString());
		}
	}

}
