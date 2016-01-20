
/**
 * Dennis Sosa
 * Assignment: #3 (Python Code Tracer)
 * CSE 214
 * Stony Brook University
 * @author DennisSosa
 * 
 * Complexity Class
 */

public class Complexity{
	private int nPower;
	private int logPower;

	/**
	 * Creates a new Complexity object with uninstantiated parameters
	 */
	public Complexity(){

	}
	
	
	/**
	 * Creates a new Complexity object using given values.
	 * Overloaded constructor
	 * @param nPower
	 * 		n Power value for Big-Oh O(n) notation of Complexity
	 * 		for some block of code
	 * @param logPower
	 * 		log Power value for Big-Oh O(log(n)) notation of Complexity
	 * 		for some block of code
	 */
	public Complexity(int nPower, int logPower){
		this.nPower = nPower;
		this.logPower = logPower;
	}
	
	
	/**
	 * Changes a particular Complexity's Big-Oh nPower notation to
	 * a new specified nPower
	 * @param nPower
	 * 		new nPower of the Complexity block of code
	 */
	public void setNPower(int nPower){
		this.nPower = nPower;
	}

	/**
	 * Receives the nPower of a particular Complexity
	 * @return
	 * 		the nPower of a Complexity as an int
	 */
	public int getNPower(){
		return nPower;
	}

	/**
	 * Changes a particular Complexity's Big-Oh logPower notation
	 * to a new specified logPower
	 * @param logPower
	 * 		new logPower of the Complexity block of code
	 */
	public void setLogPower(int logPower){
		this.logPower = logPower;
	}

	/**
	 * Receives the logPower of a particular Complexity
	 * @return
	 * 		the logPower of a Complexity as an int
	 */
	public int getLogPower(){
		return logPower;
	}

	/**
	 * Obtains the String representation of this Complexity object, which
	 * is a neatly formatted output of the content of the Big-Oh notation
	 * of some block of code
	 * @return
	 * 		the representation of this Complexity object as a String
	 * 		
	 */
	public String toString(){
		String a = "";
		if(nPower == 0 && logPower == 0)
			a+="O(1)";
		else if(nPower == 1 && logPower==1)
			a += "O(n * log(n))";
		else if(nPower == 1)
			a += "O(n)";
		else if(logPower == 1)
			a += "O(log(n))";
		
		else if(logPower == 0 & nPower!=0)
			a+="O(n^"+nPower+")";
		else
			a+="O(n^"+nPower+" * log(n)^"+logPower+")";
		
		return a;
	}
}