package cse214hw2.gui;

/**
 * Dennis Sosa
 * Assignment: #2 (Train Car Manager)
 * CSE 214
 * Stony Brook University
 * @author DennisSosa
 * 
 * TrainCar Class
 */

public class TrainCar {
	private double carLength;
	private double carWeight;
	private ProductLoad load;

	/**
	 * Creates a new TrainCar object with uninstantiated parameters
	 */
	public TrainCar(){

	}

	/**
	 * Creates a new TrainCar object using given values.
	 * @param carLength
	 * 	length value of the car
	 * @param carWeight
	 * 	weight value of the car
	 */
	public TrainCar(double carLength, double carWeight) throws IllegalArgumentException{
            if(carLength<0)
                throw new IllegalArgumentException("Invalid entry for carLength; negative value.");
            if(carWeight<0)
                throw new IllegalArgumentException("Invalid entry for carWeight; negative value.");
            this.carLength = carLength;
            this.carWeight = carWeight;
	}

	/**
	 * Creates a new TrainCar object using given values.
	 * Overload Constructor
	 * @param carLength
	 * 	length value of the car
	 * @param carWeight
	 * 	weight value of the car
	 * @param load
	 * 	the ProductLoad object being carried by the car
	 */
	public TrainCar(double carLength, double carWeight, ProductLoad load){
            this.carLength = carLength;
            this.carWeight = carWeight;
            this.load = load;
	}

	/**
	 * Receives the car length of a particular TrainCar
	 * @return
	 * 	the car length value of a TrainCar as a double
	 */
	public double getCarLength(){
            return carLength;
	}

	/**
	 * Receives the car weight value of a particular TrainCar
	 * @return
	 * 	the car weight of a TrainCar as a double
	 */
	public double getCarWeight(){
            return carWeight;
	}

	/**
	 * Receives the ProductLoad object of a particular TrainCar
	 * @return
	 * 	the ProductLoad object of a TrainCar
	 */
	public ProductLoad getProductLoad(){
            return load;
	}

	/**
	 * Changes a particular TrainCar's ProductLoad
	 * to a new specified ProductLoad
	 * @param load
	 * 	new load of the TrainCar
	 */
	public void setProductLoad(ProductLoad load){
            this.load = load;
	}

	/**
	 * Determines whether the car is empty or not
	 * @return
	 * 	the indication of an empty TrainCar as a boolean
	 */
	public boolean isEmpty(){
            if(load==null)
		return true;
            else
		return false;
	}


}
