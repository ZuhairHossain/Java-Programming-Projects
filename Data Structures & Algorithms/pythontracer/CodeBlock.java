
/**
 * Dennis Sosa
 * Assignment: #3 (Python Code Tracer)
 * CSE 214
 * Stony Brook University
 * @author DennisSosa
 * 
 * CodeBlock Class
 */

public class CodeBlock {

	public static final String[] BLOCK_TYPES = {"def ","for", "while", "if", "else", "elif"};
	public static final int DEF = 0;
	public static final int FOR = 1;
	public static final int WHILE = 2;
	public static final int IF = 3;
	public static final int ELSE = 4;
	public static final int ELIF = 5;

	private Complexity blockComplexity;
	private Complexity highestSubComplexity;
	private String name;
	private String loopVariable;

	/**
	 * Creates a new CodeBlock object with uninstantiated parameters
	 */
	public CodeBlock(){
		loopVariable = null;
	}

	/**
	 * Changes a particular CodeBlock's blockCompelexity Complexity object
	 * to a new specified Complexity
	 * @param blockComplexity
	 * 		new blockComplexity Complexity of the current CodeBlock
	 */
	public void setBlockComplexity(Complexity blockComplexity){
		this.blockComplexity = blockComplexity;
	}

	/**
	 * Receives the blockComplexity Complexity of a particular CodeBlock
	 * @return
	 * 		the blockComplexity Complexity of a particular CodeBlock
	 */
	public Complexity getBlockComplexity(){
		return blockComplexity;
	}
	
	
	/**
	 * Changes a particular CodeBlock's highestSubComplexity Complexity object
	 * to a new specified Complexity
	 * @param highestSubComplexity
	 * 		new highestSubComplexity of the current CodeBlock
	 */
	public void setHighestSubComplexity(Complexity highestSubComplexity){
		this.highestSubComplexity = highestSubComplexity;
	}

	/**
	 * Receives the highestSubComplexity of a particular CodeBlock
	 * @return
	 * 		the highestSubComplexity of a particular CodeBlock
	 */
	public Complexity getHighestSubComplexity(){
		return highestSubComplexity;
	}

	/**
	 * Changes a particular CodeBlock's name to a new specified name
	 * @param name
	 * 		new name of the CodeBlock
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * Receives the name of a particular CodeBlock
	 * @return
	 * 		the name of a CodeBlock as a String
	 */
	public String getName(){
		return name;
	}

	/**
	 * Changes a particular CodeBlock's loopVariable to a new specified
	 * loopVariable
	 * @param loopVariable
	 * 		new loopVariable of the CodeBlock
	 */
	public void setLoopVariable(String loopVariable){
		this.loopVariable = loopVariable;
	}

	/**
	 * Receives the loopVariable of a particular CodeBlock
	 * @return
	 * 		the loopVariable of a CodeBlock as a String
	 */
	public String getLoopVariable(){
		return loopVariable;
	}
	
	public String toString(){
		String a = "    BLOCK "+name+":";
		String b = "block complexity = "+blockComplexity.toString();
		String c = "highest sub-complexity = "+highestSubComplexity.toString();
		
		
		return String.format("%-30s%-35s%s",a,b,c);

	}

}