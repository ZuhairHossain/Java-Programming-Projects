package cse214hw2.gui;

/**
 * Dennis Sosa
 * Assignment: #2 (Train Car Manager)
 * CSE 214 
 * Stony Brook University
 * @author DennisSosa
 * 
 * ProductLoad Class
 */

public class ProductLoad {
	private String name;
	private double weight;
	private double value;
	private boolean dangerous;

	/**
	 * Creates a new ProductLoad object with uninstantiated parameters
	 */
	public ProductLoad() {

	}

	/**
	 * Creates a new ProductLoad object using given values.
	 * Overloaded constructor
	 * @param name
	 * 	full name of the ProductLoad
	 * @param weight
	 * 	weight value of the ProductLoad in decimal
	 * @param value
	 * 	value of the ProductLoad in decimal
	 * @param dangerous
	 * 	indicates whether or not the ProductLoad is dangerous
	 */
	public ProductLoad(String name, double weight,
		double value, boolean dangerous) throws IllegalArgumentException {
                
                if(weight < 0)
                    throw new IllegalArgumentException("Invalid Entry for weight (negative).");
                if(value < 0)
                    throw new IllegalArgumentException("Invalid Entry for value (negative).");
		this.name = name;
		this.weight = weight;
		this.value = value;
		this.dangerous = dangerous;
	}

	/**
	 * Changes a particular ProductLoad's name to a new specified name
	 * @param name
	 * 	new name of the ProductLoad
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * Receives the name of a particular ProductLoad
	 * @return
	 * 	the name of a ProductLoad as a String
	 */
	public String getName(){
		return name;
	}

	/**
	 * Changes a particular ProductLoad's weight value to a new 
	 * specified weight value
	 * @param weight
	 * 	new weight value of the ProductLoad
	 * @throws IllegalArgumentException
	 * 	indicates that the weight is an invalid entry (negative value).
	 */
	public void setWeight(double weight) throws IllegalArgumentException 
	{
		if(weight < 0)
			throw new IllegalArgumentException("Invalid entry for weight (negative).");
		this.weight = weight;
	}

	/**
	 * Receives the weight value of a particular ProductLoad
	 * @return
	 * 	the weight value of a ProductLoad as a double
	 */		
	public double getWeight(){
		return weight;
	}

	/**
	 * Changes a particular ProductLoad's value to a new
	 * specified value
	 * @param value
	 * 	new value of the ProductLoad
	 * @throws IllegalArgumentException
	 * 	indicates that value is an invalid entry (negative value)
	 */
	public void setValue(double value) throws IllegalArgumentException 
	{
		if(value < 0)
			throw new IllegalArgumentException("Invalid entry for value (negative).");
		this.value = value;
	}

	/**
	 * Receives the value of a particular ProductLoad
	 * @return
	 * 	the value of a ProductLoad as a double
	 */
	public double getValue(){
		return value;
	}

	/**
	 * Changes a particular ProductLoad's indication of danger
	 * @param dangerous
	 * 	new indication of whether or not the product is dangerous
	 */
	public void setDangerous(boolean dangerous){
		this.dangerous = dangerous;
	}

	/**
	 * Receives the danger status of a particular ProductLoad
	 * @return
	 * 	the indication of a dangerous ProductLoad as a boolean
	 */
	public boolean getDangerous(){
		return dangerous;
	}

}
