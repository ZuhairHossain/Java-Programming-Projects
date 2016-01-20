package cse214hw2.gui;
import java.util.Scanner;

/**
 * Dennis Sosa
 * Assignment: #2 (Train Car Manager)
 * CSE 214
 * Stony Brook University
 * @author DennisSosa
 * 
 * TrainManager Class
 */

public class TrainManager {

	/**
	 * The main method runs a menu driver application which first creates an empty TrainLinkedList object;
	 * the program prompts the user for a command to execute an operation
	 * in accordance to the menu
	 * @param args
	 * 	empty command-line argument
	 * @throws IllegalArgumentException
	 * 	indicates invalid entries within the TrainLinkedList & ProductLoad class 
	 * 	& within the main program
	 * @throws Exception
	 * 	indicates that the cursor is null within the TrainLinkedList class method
	 */
	public static void main(String[] args) throws IllegalArgumentException,Exception{
		TrainLinkedList train = new TrainLinkedList();
		
		Scanner in = new Scanner(System.in); //Scanner for user-input
		
		boolean cont = true;

		String selection = "";
		char selChar = ' ';
		
		System.out.println("Welcome to Dennis Sosa's Train Manager.\n");
		
		while(cont==true){ //program will continue as long as cont remains true
			//Main menu of program
			System.out.println();
			System.out.println("(F) Cursor Forward");
			System.out.println("(B) Cursor Backward");
			System.out.println("(I) Insert Car After Cursor");
			System.out.println("(R) Remove Car At Cursor");
			System.out.println("(L) Set Product Load");
			System.out.println("(S) Search For Product");
			System.out.println("(T) Display Train");
			System.out.println("(M) Display Manifest");
			System.out.println("(D) Remove Dangerous Cars");
			System.out.println("(Q) Quit");
			System.out.println();
			
			System.out.print("Enter a selection: ");
			
			selection = in.nextLine().toUpperCase();
			selChar = selection.charAt(0); //reads char at position 0 of String, which should be expected
                                                       //to be only one character

			if(selection.length() > 1) 
				System.out.println("\nInvalid Entry, please try again.");
			
			//Goes on to the switch statement in accordance to user-input char
			else {
				switch(selChar){
					case 'F':
						try{
							train.cursorForward();
							System.out.println("\nCursor moved forward.");
						} catch(Exception ex){
							System.out.println(ex.getMessage());
						}
						break;

					case 'B':
						try{
							train.cursorBackward();
							System.out.println("\nCursor moved backward.");
						} catch(Exception ex){
							System.out.println(ex.getMessage());
						}
						break;

					case 'I':
						System.out.println();

						System.out.print("Enter car length in meters: ");
						double carLen = in.nextDouble();
						in.nextLine();
						
						System.out.print("Enter car weight in tons: ");
						double carWeight = in.nextDouble();
						in.nextLine();
						
						try{
							TrainCar insert = new TrainCar(carLen,carWeight);
						
							train.insertAfterCursor(insert);
							System.out.printf("\nNew train car %.1f meters %.1f tons "
									+ "inserted into train.\n",carLen,carWeight);
						} catch(IllegalArgumentException ex){
							System.out.println(ex.getMessage());
						}
						break;

					case 'R':
						try{
							TrainCar removedCar = train.removeCursor();

							System.out.println("Car successfully unlinked. The following load has been removed from the train: ");
							System.out.println();
							System.out.println(" Name\t\tWeight (t)\tValue ($)\tDangerous");
							System.out.println("=============================================================");
							String danger = (removedCar.getProductLoad()!=null && removedCar.getProductLoad().getDangerous())?"YES":"NO";
							
							System.out.printf(" %-15s%-16.1f%,-16.2f%-7s\n",(removedCar.getProductLoad()!=null)?removedCar.getProductLoad().getName():"Empty",
								(removedCar.getProductLoad()!=null)?removedCar.getProductLoad().getWeight():0,
                        		(removedCar.getProductLoad()!=null)?removedCar.getProductLoad().getValue():0,danger);
						} catch(Exception ex){
							System.out.println(ex.getMessage());
						}

						break;

					case 'L':
						System.out.println();
						System.out.print("Enter product name: ");
						String prodName = in.nextLine();

						System.out.print("Enter product weight in tons: ");
						double prodWeight = in.nextDouble();
						in.nextLine();

						System.out.print("Enter product value in dollars: ");
						double prodValue = in.nextDouble();
						in.nextLine();

						System.out.print("Enter is product dangerous? (y/n): ");
						String dangerousInput = in.nextLine();

						char dangerousCheck = dangerousInput.charAt(0);

						switch(dangerousCheck){
							case 'y':
								try{
									if(train.getCursorData().getProductLoad() != null){
                                		train.decProdWeight(train.getCursorData().getProductLoad().getWeight());
                                		train.decProdValue(train.getCursorData().getProductLoad().getValue());
                                			if(train.getCursorData().getProductLoad().getDangerous())
                                    			train.decDanger();
                            		}
									ProductLoad tempLoadDangY = new ProductLoad(prodName,
										prodWeight,prodValue,true);
									train.getCursorData().setProductLoad(tempLoadDangY);

									train.addProdWeight(prodWeight);
									train.addProdValue(prodValue);
									train.addDanger();

									System.out.println();
									System.out.println(prodWeight+" tons of "+prodName+" added to the current car.");
								} catch(IllegalArgumentException ex){
									System.out.println(ex.getMessage());
								} catch(Exception ex){
									System.out.println(ex.getMessage());
								}
								break;

							case 'n':
								try{
									ProductLoad tempLoadDangN = new ProductLoad(prodName,
										prodWeight,prodValue,false);
									train.addProdWeight(prodWeight);
									train.addProdValue(prodValue);
								
									train.getCursorData().setProductLoad(tempLoadDangN);
									System.out.println();
									System.out.println(prodWeight+" tons of "+prodName+" added to the current car.");
								} catch(IllegalArgumentException ex){
									System.out.println(ex.getMessage());
								} catch(Exception ex){
									System.out.println(ex.getMessage());
								}
								break;

							default:
								System.out.println("Invalid input for product danger input.");
								break;
						}

						break;

					case 'S':
						System.out.println();
						System.out.print("Enter product name: ");
						String productName = in.nextLine();
						
						train.findProduct(productName);
						break;
						
					case 'T':
						System.out.println(train.toString());
						break;

					case 'M':
						train.printManifest();
						break;

					case 'D':
						try{
							train.removeDangerousCars();
						} catch(Exception ex){
							System.out.println(ex.getMessage());
						}
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
			//System.out.println(train.toString());
		}
	}

}

