package cse214hw2.gui;
/**
 * Dennis Sosa
 * Assignment: #2 (Train Car Manager)
 * CSE 214
 * Stony Brook University
 * @author DennisSosa
 * 
 * TrainCarNode Class
 */

public class TrainCarNode {
	private TrainCarNode prev;
	private TrainCarNode next;
	private TrainCar car;

	/**
	 * Creates a new TrainCarNode object with uninstantiated parameters
	 */
	public TrainCarNode(){

	}

	/**
	 * Creates a new TrainCarNode using given values.
	 * @param car
	 * 		new TrainCar of the TrainCarNode
	 */
	public TrainCarNode(TrainCar car){
            this.prev = null;
            this.next = null;
            this.car = car;
	}

	/**
	 * Changes a particular TrainCarNode's previous TrainCarNode to
	 * a new specified TrainCarNode
	 * @param prev
	 * 	new previous TrainCarNode of the current TrainCarNode
	 */
	public void setPrev(TrainCarNode prev){
            this.prev = prev;
	}

	/**
	 * Receives the previous TrainCarNode of a particular TrainCarNode
	 * @return
	 * 	the previous TrainCarNode of a particular TrainCarNode
	 */
	public TrainCarNode getPrev(){
            return prev;
	}

	/**
	 * Changes a particular TrainCarNode's next TrainCarNode to
	 * a new specified TrainCarNode
	 * @param next
	 * 	new next TrainCarNode of the current TrainCarNode
	 */
	public void setNext(TrainCarNode next){
            this.next = next;
	}

	/**
	 * Receives the next TrainCarNode of a particular TrainCarNode
	 * @return
	 * 	the next TrainCarNode of a particular TrainCarNode
	 */
	public TrainCarNode getNext(){
            return next;
	}

	/**
	 * Changes a particular TrainCarNode's TrainCar
	 * @param car
	 * 	new TrainCar of the TrainCarNode
	 */
	public void setCar(TrainCar car){
            this.car = car;
	}

	/**
	 * Receives the TrainCar of a particular TrainCarNode
	 * @return
	 * 	the car of a TrainCarNode as a TrainCar object
	 */
	public TrainCar getCar(){
            return car;
	}

	/**
	 * Obtains the String representation of this TrainCarNode object, which is
	 * a neatly formatted output of the content of a particular TrainCarNode
	 * @return
	 * 	the representation of this TrainCarNode object as a String
	 */
	public String toString(){
		//String a = "Previous TrainCarNode: ";
		//String b = "Next TrainCarNode: ";
		String c = "TrainCar: ";
		String d = "  Length(m): ";
		String e = "  Weight (t): ";
		String f = "  Load: ";
		String g = "	Name: ";
		String h = "	Weight: ";
		String i = "	Value: ";
		String j = "	Dangerous: ";

		 /*a+getPrev()+"\n"+
			b+getNext()+*/
		return ("\n"+c+"\n"+
			d+car.getCarLength()+"\n"+
			e+car.getCarWeight()+"\n"+
			f+"\n"+g+car.getProductLoad().getName()+"\n"+
			h+car.getProductLoad().getWeight()+"\n"+i+
			car.getProductLoad().getValue()+"\n"+j+
			car.getProductLoad().getDangerous()+"\n" );
	}
}

