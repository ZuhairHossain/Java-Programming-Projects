import java.util.Stack;

/**
 * Dennis Sosa
 * Assignment: #3 (Python Code Tracer)
 * CSE 214
 * Stony Brook University
 * @author DennisSosa
 * 
 * BlockStack Class
 */

public class BlockStack extends Stack{
	private Stack<CodeBlock> stack;

	/**
	 * Creates a new BlockStack object with uninstantiated parameters
	 */
	public BlockStack(){
		stack = new Stack<CodeBlock>();
	}

	/**
	 * Pushes a CodeBlock object onto the top of the BlockStack
	 * @param block
	 * 		CodeBlock object being added to the stack
	 */
	public void push(CodeBlock block){
		stack.push(block);
	}

	/**
	 * Removes the top CodeBlock from the top of the BlockStack
	 * @return
	 * 		the CodeBlock object removed from the BlockStack
	 */
	public CodeBlock pop(){
		return stack.pop();
	}

	/**
	 * Returns the CodeBlock from the top of the BlockStack for inspection
	 * @return
	 * 		the CodeBlock object at the top of the BlockStack
	 */
	public CodeBlock peek(){
		return stack.peek();
	}

	/**
	 * Receives the size of the BlockStack
	 * @return
	 * 		the size of the BlockStack as an int
	 */
	public int size(){
		return stack.size();
	}

	/**
	 * Checks whether the BlockStack is empty or not
	 * @return
	 * 		the empty indication of the BlockStack as a boolean 
	 */
	public boolean isEmpty(){
		return stack.empty();
	}
}

