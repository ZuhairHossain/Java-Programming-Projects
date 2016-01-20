package cse214hw2.gui;
/**
 * Dennis Sosa
 * Assignment: #2 (Train Car Manager)
 * CSE 214
 * Stony Brook University
 * @author DennisSosa
 * 
 * TrainLinkedList Class
 */

/**
 * @author DennisSosa
 *
 */
public class TrainLinkedList {
	private TrainCarNode head;
	private TrainCarNode tail;
	private TrainCarNode cursor;

	private int size;
	private double totalLength;
	private double totalWeight;
	private double totalValue;
	private int isDangerous;
        private String search;


	/**
	 * Constructs an instance of the TrainLinkedList with no TrainCar
	 * objects in it
	 * <dt><b>Postconditions:</b><dd>
	 * 	This TrainLinkedList has been initialized to an empty 
	 * 	linked list; head,tail, & cursor are all set to null.
	 */
	public TrainLinkedList(){
		size = 0;
		totalLength = 0;
		totalWeight = 0;
		isDangerous = 0;
                search = "";

		head = null;
		tail = null;
		cursor = null;
	}

	/**
	 * Returns a reference to the TrainCar at the node currently referenced
	 * by the cursor
	 * <dt><b>Preconditions:</b><dd>
	 * 	the list is not empty (cursor is not null)
	 * @return
	 * 	the reference to the TrainCar at the node currently referenced
	 * 	by the cursor
	 * @throws Exception
	 * 	indicates that the cursor is null
	 */
	public TrainCar getCursorData() throws Exception{
		if(cursor==null)
			throw new Exception("Cursor is null.");
		
		return cursor.getCar();
	}

	/**
	 * Places car in the node currently referenced by the cursor
	 * <dt><b>Preconditions:</b><dd>
	 * 	the list is not empty (cursor is not null)
	 * <dt><b>Postconditions:</b><dd>
	 * 	the cursor node now contains a reference to car as its data
	 * @param car
	 * 	new TrainCar to be set into the cursor's TrainCarNode 
	 * @throws Exception
	 * 	indicates that the cursor is null
	 */
	public void setCursorData(TrainCar car) throws Exception{
		if(cursor!=null)
			cursor.setCar(car);
		else
			throw new Exception("The list is empty; cursor is null.");
	}

	/**
	 * Moves the cursor to point at the previous TrainCarNode
	 * <dt><b>Preconditions:</b><dd>
	 * 	the list is not empty (cursor is not null) and
	 * 	the cursor does not currently reference the head
	 * 	of the list
	 * <dt><b>Postconditions:</b><dd>
	 * 	the cursor has been moved back to the previous TrainCarNode,
	 * 	or has remained at the head of the list
	 * @throws Exception
	 * 	indicates that the cursor is null
	 */
	public void cursorBackward() throws Exception{
		if(cursor!=null && cursor!=head){
			cursor = cursor.getPrev();
		}

		else
			throw new Exception("\nNo previous car, cannot move cursor backward.");
	}

	/**
	 * Moves the cursor to point at the next TrainCarNode
	 * <dt><b>Preconditions:</b><dd>
	 * 	the list is not empty (cursor is not null) and
	 * 	the cursor does not currently reference the tail
	 * 	of the list
	 * <dt><b>Postconditions:</b><dd>
	 * 	the cursor has been advanced to the next TrainCarNode,
	 * 	or has remained at the tail of the list
	 * @throws Exception
	 * 	indicates that the cursor is null
	 */
	public void cursorForward() throws Exception{
		if(cursor!=null && cursor!=tail){
			cursor = cursor.getNext();
		}

		else
			throw new Exception("\nNo next car, cannot move cursor forward.");
	}

	/**
	 * Inserts a car into the train after the cursor position
	 * @param newCar
	 * 	the new TrainCar to be inserted into the train
	 * <dt><b>Preconditions:</b><dd>
	 * 	this TrainCar object has been instantiated
	 * <dt><b>Postconditions:</b><dd>
	 * 	the new TrainCar has been inserted into the train after
	 * 	the position of the cursor; All TrainCar objects previously
	 * 	on the train, and their order has been preserved; The cursor
	 * 	now points to the inserted car
	 * @throws IllegalArgumentException
	 * 	indicates the newCar is null
	 */
	public void insertAfterCursor(TrainCar newCar) throws IllegalArgumentException{
		if(newCar!=null){
			TrainCarNode newNode = new TrainCarNode(newCar);
			if(cursor==null){
				size++;
				totalLength += newCar.getCarLength();
				totalWeight += newCar.getCarWeight();
				if(newCar.getProductLoad()!=null){
					totalWeight += newCar.getProductLoad().getWeight();
					totalValue += newCar.getProductLoad().getValue();
				

					if(newCar.getProductLoad().getDangerous())
						isDangerous++;
				}
				
				newNode.setNext(null);
				newNode.setPrev(null);
				cursor = newNode;
				head = newNode;
				tail = newNode;
			}

			else if(cursor==tail){
				size++;
				totalLength += newCar.getCarLength();
				totalWeight += newCar.getCarWeight();
				
				if(newCar.getProductLoad()!=null){
					totalWeight += newCar.getProductLoad().getWeight();
					totalValue += newCar.getProductLoad().getValue();
				

					if(newCar.getProductLoad().getDangerous())
						isDangerous++;
				}
				
				newNode.setNext(null);
				newNode.setPrev(cursor);
				cursor.setNext(newNode);
				tail = newNode;
				cursor = tail;
			}

			else {
				size++;
				totalLength += newCar.getCarLength();
				totalWeight += newCar.getCarWeight();
				if(newCar.getProductLoad()!=null){
					totalWeight += newCar.getProductLoad().getWeight();
					totalValue += newCar.getProductLoad().getValue();
				

					if(newCar.getProductLoad().getDangerous())
						isDangerous++;
				}

				newNode.setNext(cursor.getNext());
				newNode.setPrev(cursor);
				cursor.setNext(newNode);
				newNode.getNext().setPrev(newNode);
				cursor = newNode;
			}
		}

		else
			throw new IllegalArgumentException("newCar is null.");
	}

	/**
	 * Removes the TrainCarNode referenced by the cursor and returns
	 * the TrainCar contained within the node
	 * <dt><b>Preconditions:</b><dd>
	 * 	the cursor is not null
	 * <dt><b>Postconditions:</b><dd>
	 * 	the TrainCarNode referenced by the cursor has been 
	 * 	removed from the train; the cursor now references
	 * 	the next node, or the previous node if no next node exists
	 * @return
	 * 	the TrainCar object being removed
	 * @throws Exception
	 * 	indicates that the cursor is null
	 */
	public TrainCar removeCursor() throws Exception{
		TrainCar removedNode = null;

		if(cursor!=null){
			removedNode = cursor.getCar();
			
			if(cursor==head){
				if(cursor==tail){
					cursor = null;
                                        tail = null;
                                        head = null;
					size = 0;
					totalLength = 0;
					totalWeight = 0;
					totalWeight = 0;
					totalValue = 0;
					isDangerous = 0;
				}

				else{
					size--;
					totalLength -= cursor.getCar().getCarLength();
					totalWeight -= cursor.getCar().getCarWeight();
					if(cursor.getCar().getProductLoad()!=null){
						totalWeight -= cursor.getCar().getProductLoad().getWeight();
						totalValue -= cursor.getCar().getProductLoad().getValue();
				
						if(cursor.getCar().getProductLoad().getDangerous())
							isDangerous--;
					}

					cursorForward();
					cursor.setPrev(null);
					head = cursor;
				}
			}

			else if(cursor==tail){
				size--;
				totalLength -= cursor.getCar().getCarLength();
				totalWeight -= cursor.getCar().getCarWeight();
				if(cursor.getCar().getProductLoad()!=null){
					totalWeight -= cursor.getCar().getProductLoad().getWeight();
					totalValue -= cursor.getCar().getProductLoad().getValue();
			
					if(cursor.getCar().getProductLoad().getDangerous())
						isDangerous--;
				}

				cursorBackward();
				//cursor.getNext().setPrev(null);
				cursor.setNext(null);
				tail = cursor;
			}

			else{
				size--;
				totalLength -= cursor.getCar().getCarLength();
				totalWeight -= cursor.getCar().getCarWeight();
				
				if(cursor.getCar().getProductLoad()!=null){
					totalWeight -= cursor.getCar().getProductLoad().getWeight();
					totalValue -= cursor.getCar().getProductLoad().getValue();
			
					if(cursor.getCar().getProductLoad().getDangerous())
						isDangerous--;
				}

				cursorForward();
				cursor.getPrev().getPrev().setNext(cursor);
				cursor.setPrev(cursor.getPrev().getPrev());
			}
		}

		else
                    throw new Exception("Empty List.");

		return removedNode;
	}

	/**
	 * Determines the number of TrainCar objects 
	 * currently on the train
	 * <p>
	 * Notes: This function should complete in O(1) time
	 * </p>
	 * @return
	 * 	the number of TrainCar objects on this train
	 */
	public int size(){
		return size;
	}

	/**
	 * Returns the total length of the train in meters
	 * <p>
	 * Notes: This function should complete in O(1) time
	 * </p>
	 * @return
	 * 	the sum of the lengths of each TrainCar in the train
	 */
	public double getLength(){
		return totalLength;
	}

	/**
	 * Returns the total value of product carried by the train
	 * <p>
	 * Notes: This function should complete in O(1) time
	 * </p>
	 * @return
	 * 	the sum of the values of each TrainCar in the train
	 */
	public double getValue(){
		return totalValue;
	}

	/**
	 * Returns the total weight in tons of the train
	 * <p>
	 * Notes: This function should complete in O(1) time
	 * </p>
	 * @return
	 * 	the sum of the weight of each TrainCar plus the sum of the 
	 * 	ProductLoad carried by that car
	 */
	public double getWeight(){
		return totalWeight;
	}

	/**
	 * Whether or not there is a dangerous product on one of the
	 * TrainCar objects on the train
	 * <p>
	 * Notes: This function should complete in O(1) time
	 * </p>
	 * @return
	 * 	returns true if the train contains at least one TrainCar
	 * 	carrying a dangerous ProductLoad, false otherwise
	 */
	public boolean isDangerous(){
		if(isDangerous > 0)
                    return true;
		return false;
	}

	/**
	 * Searches the list for all ProductLoad objects with the indicated name,
	 * sums together their weight and value, obtains its danger status, and prints
	 * a single ProductLoad record to the console
	 * @param name
	 * 	the name of the ProductLoad to find on the train
	 */
	public void findProduct(String name){
		double cursorWeight = 0;
		double cursorValue = 0;
		boolean cursorDanger = false;
		boolean firstMatch = false;
		TrainCarNode tempCursor = new TrainCarNode();
		tempCursor = head;
		int count = 0;
                search = "";

		while(tempCursor!=null){
                        if(tempCursor.getCar().getProductLoad()==null)
                            break;
			if((tempCursor.getCar().getProductLoad().getName()).equals(name)) {
				if(firstMatch==false){
					cursorDanger = tempCursor.getCar().getProductLoad().getDangerous();
					firstMatch = true;
				}

				cursorWeight += tempCursor.getCar().getProductLoad().getWeight();
				cursorValue += tempCursor.getCar().getProductLoad().getValue();
				count++;
			}

			tempCursor = tempCursor.getNext();
		}

		if(firstMatch==false){
			System.out.println("\nThere are no ProductLoad objects of the indicated name on board the train.");
			System.out.println("No record of "+name+" on board train.");
                        search = ("\nThere are no ProductLoad objects of the indicated name on board the train.\n")+
                            ("No record of "+name+" on board train.\n");
		}

		else{
			System.out.println("\nThe following products were found on "+count+" cars: ");
                        search = ("\nThe following products were found on "+count+" cars: \n\n");
			System.out.println();
			System.out.println(" Name\t\tWeight (t)\tValue ($)\tDangerous");
                        search += (" Name\t\tWeight (t)\tValue ($)\tDangerous\n");
			System.out.println("=============================================================");
                        search += ("=============================================================\n");
			String danger = (cursorDanger)?"YES":"NO";
			
			System.out.printf(" %-15s%-16.1f%,-16.2f%-7s\n",name,cursorWeight,cursorValue,danger);
                        search += String.format(" %-15s%-16.1f%,-16.2f%-7s\n",name,cursorWeight,cursorValue,danger);
		}
	}
	
	
	/**
	 * Prints a neatly formatted table of the car number, car length, car weight,
	 * load name, load weight, load value, and load dangerousness for all of the
	 * car on the train.
	 */
	public void printManifest(){
		TrainCarNode temp = head;
		int num = 1;
		System.out.println();
		System.out.println("    CAR:\t\t\t     LOAD:");
		System.out.println("     Num    Length (m)   Weight (t) | Name\t\tWeight (t)\tValue ($)\tDangerous");
		System.out.println("====================================+===============================================================");
		
		while(temp!=null){
			if(temp==cursor)
				System.out.printf("  -> %-7d%-13.1f%-11.1f| %-18s%-16.1f%,-16.2f%s\n",num,temp.getCar().getCarLength(),
						temp.getCar().getCarWeight(),(temp.getCar().getProductLoad()!=null)?temp.getCar().getProductLoad().getName():"Empty",
						(temp.getCar().getProductLoad()!=null)?temp.getCar().getProductLoad().getWeight():0,
						(temp.getCar().getProductLoad()!=null)?temp.getCar().getProductLoad().getValue():0,
						(temp.getCar().getProductLoad()!=null && temp.getCar().getProductLoad().getDangerous())?"YES":"NO");
			else
				System.out.printf("     %-7d%-13.1f%-11.1f| %-18s%-16.1f%,-16.2f%s\n",num,temp.getCar().getCarLength(),
						temp.getCar().getCarWeight(),(temp.getCar().getProductLoad()!=null)?temp.getCar().getProductLoad().getName():"Empty",
						(temp.getCar().getProductLoad()!=null)?temp.getCar().getProductLoad().getWeight():0,
						(temp.getCar().getProductLoad()!=null)?temp.getCar().getProductLoad().getValue():0,
						(temp.getCar().getProductLoad()!=null && temp.getCar().getProductLoad().getDangerous())?"YES":"NO");
			
			temp = temp.getNext();
			num++;
		}
	}

	/**
	 * Removes all dangerous cars from the train, maintaining the order of
	 * the cars in the train
	 * <dt><b>Postconditions:</b><dd>
	 * 	all dangerous cars have been removed from this train; the order
	 * 	of all non-dangerous cars must be maintained upon the completion
	 * 	of this method
	 * @throws Exception
	 * 	indicates that cursor removal failed
	 */
	public void removeDangerousCars() throws Exception{
		cursor = head;
		TrainCar removed = null;

		while(cursor!=null){
                        if(cursor.getCar().getProductLoad()!=null){
                            if(cursor.getCar().getProductLoad().getDangerous())
				removed = removeCursor();
                        }
			if(cursor==tail)
				break;

			cursorForward();
		}

		if(removed==null)
			System.out.println("\nThe train contains no dangerous cars.");
		else
			System.out.println("\nDangerous cars successfully removed from the train.");

	}

	/**
	 * Returns a neatly formatted String representation of the train
	 * @return
	 * 	a neatly formatted string containing information about the
	 * 	train, including it's size (number of cars), length in meters,
	 * 	weight in tons, value in dollars, and whether it is dangerous
	 * 	or not
	 */
	public String toString(){
		return (String.format("\nTrain: %d cars, %.1f meters, %.1f tons, $%,.2f value, %s.",
				size,totalLength,totalWeight,totalValue,(isDangerous>0)?"DANGEROUS":"not dangerous"));
	}
        
	/**
	 * Increments totalWeight variable from
	 * added ProductLoad weight
	 * @param prodWeight
	 * 	added ProductLoad weight
	 */
	public void addProdWeight(double prodWeight){
		totalWeight+=prodWeight;
	}
        
        /**
         * Decrements totalWeight variable from
         * replaced ProductLoad weight
         * @param prodWeight
         *      subtracted ProductLoad weight
         */
        public void decProdWeight(double prodWeight){
            totalWeight-=prodWeight;
        }
	
	
	/**
	 * Increments totalValue variable from 
	 * added ProductLoad value
	 * @param prodValue
	 * 	added ProductLoad value
	 */
	public void addProdValue(double prodValue){
		totalValue += prodValue;
	}
        
        /**
         * Decrements totalValue variable from 
         * replaced/removed ProductLoad value
         * @param prodValue
         *      subtracted ProductLoad value
         */
        public void decProdValue(double prodValue){
            totalValue -= prodValue;
        }
        
	/**
	 * Increments private variable isDangerous
	 * to indicate product danger
	 */
	public void addDanger(){
            isDangerous++;
	}
        
        /**
         * Decrements private variable isDangerous
         * to indicate that a product which 
         * was dangerous was removed.
         */
        public void decDanger(){
            isDangerous--;
        }
        
        /**
         * Returns the the Manifest Display from
         * the printManifest() method
         * @return 
         *      a neatly formatted table displayed in printManifest() as a String
         */
        public String manifestString(){
            TrainCarNode temp = head;
            int num = 1;
            String a = "\n";
            String b = ("    CAR:\t\t\t     LOAD:\n");
            String c = ("     Num    Length (m)   Weight (t) | Name\t\tWeight (t)\tValue ($)\tDangerous\n");
            String d = ("====================================+===============================================================\n");
            String e = "";
		
            while(temp!=null){
		if(temp==cursor)
                    e += String.format("  -> %-7d%-13.1f%-11.1f| %-18s%-16.1f%,-16.2f%s\n",num,temp.getCar().getCarLength(),
                            temp.getCar().getCarWeight(),(temp.getCar().getProductLoad()!=null)?temp.getCar().getProductLoad().getName():"Empty",
                            (temp.getCar().getProductLoad()!=null)?temp.getCar().getProductLoad().getWeight():0,
                            (temp.getCar().getProductLoad()!=null)?temp.getCar().getProductLoad().getValue():0,
                            (temp.getCar().getProductLoad()!=null && temp.getCar().getProductLoad().getDangerous())?"YES":"NO");
		else
                    e += String.format("     %-7d%-13.1f%-11.1f| %-18s%-16.1f%,-16.2f%s\n",num,temp.getCar().getCarLength(),
                            temp.getCar().getCarWeight(),(temp.getCar().getProductLoad()!=null)?temp.getCar().getProductLoad().getName():"Empty",
                            (temp.getCar().getProductLoad()!=null)?temp.getCar().getProductLoad().getWeight():0,
                            (temp.getCar().getProductLoad()!=null)?temp.getCar().getProductLoad().getValue():0,
                            (temp.getCar().getProductLoad()!=null && temp.getCar().getProductLoad().getDangerous())?"YES":"NO");
			
		temp = temp.getNext();
		num++;
            }
            return a+b+c+d+e;
        }
        
        /**
         * Returns a String version of the table 
         * output for findProduct(String name) method
         * @return 
         *      returns a neatly formatted table displayed in 
         *      the findProduct(String name) method as a String
         */     
        public String searchProdString(){
            return search;
        }

}
