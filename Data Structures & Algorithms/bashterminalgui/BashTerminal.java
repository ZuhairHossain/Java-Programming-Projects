package bashterminalgui;
/**
 * Dennis Sosa
 * Assignment: #5 (Ternary Tree File System)
 * CSE 214 
 * Stony Brook University
 * @author DennisSosa
 * 
 * BashTerminal Class
 */

import java.util.Scanner;

public class BashTerminal{

	/**
	 * The main method runs a menu driven application which takes user input
	 * and builds a DirectoryTree using the commands indicated so the user
	 * can interact with a file system implemented by the DirectoryTree;
	 * These commands all have the same effect as if they were executed in a 
	 * live bash shell on any Unix-based OS
	 * @param args
	 * 		empty command-line argument
	 */
	public static void main(String[] args){
		DirectoryTree root = new DirectoryTree();
		
		Scanner in = new Scanner(System.in); //Scanner for user-input
		
		boolean cont = true;

		String selection = "";
		
		System.out.println("Starting bash terminal.");
		
		while(cont==true){ //program will continue as long as cont remains true
			//Main menu of program
		
			//System.out.println();
			
			System.out.print("[dsosa@CSE214Net]: $ ");
			
			selection = in.nextLine();
			
			//Goes on to the 'switch' statement in accordance to user-input
			
			if(selection.equals("pwd"))
				System.out.println(root.presentWorkingDirectory());
			
			else if(selection.equals("ls"))
				System.out.println(root.listDirectory());
			
			else if(selection.equals("ls -R")){
				root.printDirectoryTree();
				System.out.println();
			}
			
			else if(selection.equals("cd /"))
				root.resetCursor();
			
			else if(selection.equals("cd ..")){
                            try{
                                root.moveUp();
                            } catch(NotFoundException ex){
                                System.out.println();
                            }
                        }
			
			else if(selection.contains("cd ")&&selection.contains("/")){
				String pathName = selection.substring(3, selection.length());
				try{
					root.changeToDirectoryPath(pathName);
				} catch(NotADirectoryException ex){
					System.out.println(ex.getMessage());
				}
			}
				
				
			else if(selection.contains("cd ")){
				String name = selection.substring(3, selection.length());
				
				try{
					root.changeDirectory(name);
				} catch(NotADirectoryException ex){
					System.out.println(ex.getMessage());
				} catch(NotFoundException ex){
                                    System.out.println();
                                }
			}

			else if(selection.contains("mkdir ")){
				String name = selection.substring(6,selection.length());
				
				try{
					root.makeDirectory(name);
				} catch(IllegalArgumentException ex){
					System.out.println(ex.getMessage());
				} catch(FullDirectoryException ex){
					System.out.println(ex.getMessage());
				}
			}
			
			else if(selection.contains("touch ")){
				String name = selection.substring(6,selection.length());
				
				try{
					root.makeFile(name);
				} catch(IllegalArgumentException ex){
					System.out.println(ex.getMessage());
				} catch(FullDirectoryException ex){
					System.out.println(ex.getMessage());
				}
			}
			
			else if(selection.contains("find ")){
				String name = selection.substring(5, selection.length());
				try{
                                    root.findNode(name);
                                } catch(NotFoundException ex){
                                    System.out.println();
                                }
			}
			
			else if(selection.contains("mv ")){
				String[] pathArr = selection.split("\\s+");
				
				if(pathArr.length < 3 || pathArr.length > 3)
					System.out.println("ERROR: Invalid entry, please try again.");
				
				else{
					String file = pathArr[1];				
					String destination = pathArr[2];
					root.moveFile(file, destination);
				}
			}
				
			else if(selection.equals("exit")){
				System.out.println("Program terminating normally");
				cont = false;
			}

			else
				System.out.println("Invalid entry, please try again.");
		}
			
	}

}



