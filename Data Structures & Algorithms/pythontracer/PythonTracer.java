import java.util.Scanner;
import java.io.File;

/**
 * Dennis Sosa
 * Assignment: #3 (Python Code Tracer)
 * CSE 214 
 * Stony Brook University
 * @author DennisSosa
 * 
 * PythonTracer Class
 */

public class PythonTracer {
	public static final int SPACE_COUNT = 4;

	/**
	 * Opens the indicated file and traces through the code of the Python function
	 * contained within the file, returning the Big-Oh order of complexity of the
	 * function. During operation, the stack trace should be printed to the console
	 * as code blocks are pushed to/popped from the stack
	 * <dt><b>Preconditions:</b><dd>
	 * 		fileName is not null and the file it names contains a single 
	 * 		Python function with valid syntax (Reminder: Students do NOT
	 * 		to check for invalid syntax).
	 * @param fileName
	 * 		Python file name obtained from user-input
	 * @retur
	 * 		a Complexity object representing the total order of complexity 
	 * 		of the Python code contained within the file.
	 * @throws Exception
	 * 		lets the user know fileName is null
	 */
	public static Complexity traceFile(String fileName) throws Exception{
		if(fileName==null)
			throw new Exception("fileName is null.");

		BlockStack stack = new BlockStack();
		Complexity highest = new Complexity();
		
		try{
			File myFile = new File(fileName);
			Scanner fileData = new Scanner(myFile);

			String data = "";
			int lineSpaces=0;
			int indents=0;
			
			int name=1;
			
			boolean newBlock = false;
			boolean first = true;
			
			while(fileData.hasNextLine()){
				data = fileData.nextLine();
				
				if(data.equals("") || data.length()==0)
					continue;
				
				if(data.trim().contains("#")||data.trim().contains("\""))
					continue;
				
				else {
					lineSpaces = 0;
					for(int i=0;i<data.length();i++){
						if(data.charAt(i) == ' ')
							lineSpaces++;
						else
							break;
					}
					
					indents = lineSpaces/SPACE_COUNT;
					
					if(indents==1)
						newBlock = true;
					else
						newBlock = false;
					
					if(data.trim().contains("def ")){
						CodeBlock block = new CodeBlock();
						Complexity blockComp = new Complexity(0,0);
						Complexity highestSub = new Complexity(0,0);
						
						block.setBlockComplexity(blockComp);
						block.setHighestSubComplexity(highestSub);

						block.setName("1");
						stack.push(block);
						System.out.println("\nEntering block "+block.getName()
								+" 'def' :"+"\n"+block.toString());
					}
					
					else if(data.trim().contains("for ")){
						CodeBlock block = new CodeBlock();
						Complexity blockComp = new Complexity(0,0);
						Complexity highestSub = new Complexity(0,0);
						
						block.setBlockComplexity(blockComp);
						block.setHighestSubComplexity(highestSub);
						
						if(first){
							block.setName("1."+name);
							first = false;
						}
						
						else if(newBlock){
							name++;
							block.setName("1."+name);
							
							
							while(!stack.peek().getName().equals("1")){
								CodeBlock oldTop = stack.pop();
								Complexity oldTopComplexity = oldTop.getBlockComplexity();
								Complexity oldHighSubTop = oldTop.getHighestSubComplexity();
								
								Complexity currentTop = stack.peek().getHighestSubComplexity();
								
								boolean changes = false;
								
								if(oldTopComplexity.getNPower() > currentTop.getNPower()){
									currentTop.setNPower(currentTop.getNPower()+oldTopComplexity.getNPower());
									highest = currentTop;
									changes = true;
								}
								
								if(oldHighSubTop.getNPower() >= currentTop.getNPower()){
									currentTop.setNPower(currentTop.getNPower()+oldHighSubTop.getNPower());
									highest = currentTop;
									changes = true;
								}
								
								if(oldTopComplexity.getLogPower() > currentTop.getLogPower()){
									currentTop.setLogPower(currentTop.getLogPower()+oldTopComplexity.getLogPower());
									highest = currentTop;
									changes = true;
								}
								
								if(oldHighSubTop.getLogPower() >= currentTop.getLogPower()){
									currentTop.setLogPower(currentTop.getLogPower()+oldHighSubTop.getLogPower());
									highest = currentTop;
									changes = true;
								}
								
								if(changes){
									stack.peek().setHighestSubComplexity(currentTop);
								
									System.out.println("\nLeaving block "+oldTop.getName()+", updating block "+stack.peek().getName()+":");
									System.out.println(stack.peek().toString());
								}
								
								else{
									System.out.println("\nLeaving block "+oldTop.getName()+", nothing to update.");
									System.out.println(stack.peek().toString());
								}
							}
							
						}
						
						else
							block.setName(stack.peek().getName()+"."+name);
						
						if(data.contains("log_N")){
							block.getBlockComplexity().setLogPower(1);
						}
						
						if(data.contains(" N")){
							block.getBlockComplexity().setNPower(1);
						}
						
						stack.push(block);
						
						System.out.println("\nEntering block "+block.getName()
								+" 'for' :"+"\n"+block.toString());
						
					}
					
					else if(data.trim().contains("while ")){
						CodeBlock block = new CodeBlock();
						Complexity blockComp = new Complexity(0,0);
						Complexity highestSub = new Complexity(0,0);
						
						block.setBlockComplexity(blockComp);
						block.setHighestSubComplexity(highestSub);
						
						if(first){
							block.setName("1."+name);
							first = false;
						}
						
						else if(newBlock){
							name++;
							block.setName("1."+name);
							
							while(!stack.peek().getName().equals("1")){
								CodeBlock oldTop = stack.pop();
								Complexity oldTopComplexity = oldTop.getBlockComplexity();
								Complexity oldHighSubTop = oldTop.getHighestSubComplexity();
									
								Complexity currentTop = stack.peek().getHighestSubComplexity();
								
								boolean changes = false;
								
								if(oldTopComplexity.getNPower() > currentTop.getNPower()){
									currentTop.setNPower(currentTop.getNPower()+oldTopComplexity.getNPower());
									highest = currentTop;
									changes = true;
								}
								
								if(oldHighSubTop.getNPower() >= currentTop.getNPower()){
									currentTop.setNPower(currentTop.getNPower()+oldHighSubTop.getNPower());
									highest = currentTop;
									changes = true;
								}
								
								if(oldTopComplexity.getLogPower() > currentTop.getLogPower()){
									currentTop.setLogPower(currentTop.getLogPower()+oldTopComplexity.getLogPower());
									highest = currentTop;
									changes = true;
								}
								
								if(oldHighSubTop.getLogPower() >= currentTop.getLogPower()){
									currentTop.setLogPower(currentTop.getLogPower()+oldHighSubTop.getLogPower());
									highest = currentTop;
									changes = true;
								}
								
								if(changes){
									stack.peek().setHighestSubComplexity(currentTop);
								
									System.out.println("\nLeaving block "+oldTop.getName()+", updating block "+stack.peek().getName()+":");
									System.out.println(stack.peek().toString());
								}
								
								else{
									System.out.println("\nLeaving block "+oldTop.getName()+", nothing to update.");
									System.out.println(stack.peek().toString());
								}
							}
						}
						
						else
							block.setName(stack.peek().getName()+"."+name);
						
						block.setLoopVariable(Character.toString(data.trim().charAt(6)));
						
						if(data.contains("log_N")){
							block.getBlockComplexity().setLogPower(1);
						}
							
						else if(data.contains(" N")){
							block.getBlockComplexity().setNPower(1);
						}
						
						stack.push(block);
						
						System.out.println("\nEntering block "+block.getName()
								+" 'while' :"+"\n"+block.toString());
						
						
					}
					
					else if(data.trim().contains("elif ")){
						CodeBlock block = new CodeBlock();
						Complexity blockComp = new Complexity(0,0);
						Complexity highestSub = new Complexity(0,0);
						
						block.setBlockComplexity(blockComp);
						block.setHighestSubComplexity(highestSub);
						
						if(first){
							block.setName("1."+name);
							first = false;
						}
						
						else if(newBlock){
							name++;
							block.setName("1."+name);
							
							while(!stack.peek().getName().equals("1")){
								CodeBlock oldTop = stack.pop();
								Complexity oldTopComplexity = oldTop.getBlockComplexity();	
								Complexity oldHighSubTop = oldTop.getHighestSubComplexity();
								
								Complexity currentTop = stack.peek().getHighestSubComplexity();
								
								boolean changes = false;
								
								if(oldTopComplexity.getNPower() > currentTop.getNPower()){
									currentTop.setNPower(currentTop.getNPower()+oldTopComplexity.getNPower());
									highest = currentTop;
									changes = true;
								}
								
								if(oldHighSubTop.getNPower() >= currentTop.getNPower()){
									currentTop.setNPower(currentTop.getNPower()+oldHighSubTop.getNPower());
									highest = currentTop;
									changes = true;
								}
								
								if(oldTopComplexity.getLogPower() > currentTop.getLogPower()){
									currentTop.setLogPower(currentTop.getLogPower()+oldTopComplexity.getLogPower());
									highest = currentTop;
									changes = true;
								}
								
								if(oldHighSubTop.getLogPower() >= currentTop.getLogPower()){
									currentTop.setLogPower(currentTop.getLogPower()+oldHighSubTop.getLogPower());
									highest = currentTop;
									changes = true;
								}
								
								if(changes){
									stack.peek().setHighestSubComplexity(currentTop);
								
									System.out.println("\nLeaving block "+oldTop.getName()+", updating block "+stack.peek().getName()+":");
									System.out.println(stack.peek().toString());
								}
								
								else{
									System.out.println("\nLeaving block "+oldTop.getName()+", nothing to update.");
									System.out.println(stack.peek().toString());
								}
							}
							
						}
						
						else
							block.setName(stack.peek().getName()+"."+name);
						
						stack.push(block);
						System.out.println("\nEntering block "+block.getName()
								+" 'elif' :"+"\n"+block.toString());
					}
					
					else if(data.trim().contains("if ")){
						CodeBlock block = new CodeBlock();
						Complexity blockComp = new Complexity(0,0);
						Complexity highestSub = new Complexity(0,0);
						
						block.setBlockComplexity(blockComp);
						block.setHighestSubComplexity(highestSub);
						
						if(first){
							block.setName("1."+name);
							first = false;
						}
						
						else if(newBlock){
							name++;
							block.setName("1."+name);
							
							while(!stack.peek().getName().equals("1")){
								CodeBlock oldTop = stack.pop();
								Complexity oldTopComplexity = oldTop.getBlockComplexity();	
								Complexity oldHighSubTop = oldTop.getHighestSubComplexity();
								
								Complexity currentTop = stack.peek().getHighestSubComplexity();
								
								boolean changes = false;
								
								if(oldTopComplexity.getNPower() > currentTop.getNPower()){
									currentTop.setNPower(currentTop.getNPower()+oldTopComplexity.getNPower());
									highest = currentTop;
									changes = true;
								}
								
								if(oldHighSubTop.getNPower() >= currentTop.getNPower()){
									currentTop.setNPower(currentTop.getNPower()+oldHighSubTop.getNPower());
									highest = currentTop;
									changes = true;
								}
								
								if(oldTopComplexity.getLogPower() > currentTop.getLogPower()){
									currentTop.setLogPower(currentTop.getLogPower()+oldTopComplexity.getLogPower());
									highest = currentTop;
									changes = true;
								}
								
								if(oldHighSubTop.getLogPower() >= currentTop.getLogPower()){
									currentTop.setLogPower(currentTop.getLogPower()+oldHighSubTop.getLogPower());
									highest = currentTop;
									changes = true;
								}
								
								if(changes){
									stack.peek().setHighestSubComplexity(currentTop);
								
									System.out.println("\nLeaving block "+oldTop.getName()+", updating block "+stack.peek().getName()+":");
									System.out.println(stack.peek().toString());
								}
								
								else{
									System.out.println("\nLeaving block "+oldTop.getName()+", nothing to update.");
									System.out.println(stack.peek().toString());
								}
							}
							
						}
						
						else
							block.setName(stack.peek().getName()+"."+name);
						
						stack.push(block);
						System.out.println("\nEntering block "+block.getName()
								+" 'if' :"+"\n"+block.toString());
					}
					
					else if(data.trim().contains("else")){
						CodeBlock block = new CodeBlock();
						Complexity blockComp = new Complexity(0,0);
						Complexity highestSub = new Complexity(0,0);
						
						block.setBlockComplexity(blockComp);
						block.setHighestSubComplexity(highestSub);
						
						if(first){
							block.setName("1."+name);
							first = false;
						}
						
						if(newBlock){
							name++;
							block.setName("1."+name);
							
							while(!stack.peek().getName().equals("1")){
								CodeBlock oldTop = stack.pop();
								Complexity oldTopComplexity = oldTop.getBlockComplexity();
								Complexity oldHighSubTop = oldTop.getHighestSubComplexity();
								
								Complexity currentTop = stack.peek().getHighestSubComplexity();
								
								boolean changes = false;
								
								if(oldTopComplexity.getNPower() > currentTop.getNPower()){
									currentTop.setNPower(currentTop.getNPower()+oldTopComplexity.getNPower());
									highest = currentTop;
									changes = true;
								}
								
								if(oldHighSubTop.getNPower() >= currentTop.getNPower()){
									currentTop.setNPower(currentTop.getNPower()+oldHighSubTop.getNPower());
									highest = currentTop;
									changes = true;
								}
								
								if(oldTopComplexity.getLogPower() > currentTop.getLogPower()){
									currentTop.setLogPower(currentTop.getLogPower()+oldTopComplexity.getLogPower());
									highest = currentTop;
									changes = true;
								}
								
								if(oldHighSubTop.getLogPower() >= currentTop.getLogPower()){
									currentTop.setLogPower(currentTop.getLogPower()+oldHighSubTop.getLogPower());
									highest = currentTop;
									changes = true;
								}
								
								if(changes){
									stack.peek().setHighestSubComplexity(currentTop);
								
									System.out.println("\nLeaving block "+oldTop.getName()+", updating block "+stack.peek().getName()+":");
									System.out.println(stack.peek().toString());
								}
								
								else{
									System.out.println("\nLeaving block "+oldTop.getName()+", nothing to update.");
									System.out.println(stack.peek().toString());
								}
							}
							
						}
						else
							block.setName(stack.peek().getName()+"."+name);
						
						stack.push(block);
						System.out.println("\nEntering block "+block.getName()
								+" 'else' :"+"\n"+block.toString());
					}
					
					else if(data.trim().contains(stack.peek().getLoopVariable()+" /=")||
							data.trim().contains(stack.peek().getLoopVariable()+" *=")){
						stack.peek().getBlockComplexity().setLogPower(stack.peek().getBlockComplexity().getLogPower()+1);
					
						System.out.println("\nFound update statement, updating block "+stack.peek().getName()+":\n"+
							stack.peek().toString());
					
					}
				
					else if(data.trim().contains(stack.peek().getLoopVariable()+ " -=") ||
						data.trim().contains(stack.peek().getLoopVariable()+ " +=")){
						stack.peek().getBlockComplexity().setNPower(stack.peek().getBlockComplexity().getNPower()+1);
					
						System.out.println("\nFound update statement, updating block "+stack.peek().getName()+":\n"+
							stack.peek().toString());
					}
				
				}
				
			}
			fileData.close();
		} catch(Exception ex){
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		}
		
		//Empty the rest of the stack
		if(!stack.peek().equals("1")){
			while(!stack.peek().getName().equals("1")){
				CodeBlock oldTop = stack.pop();
				Complexity oldTopComplexity = oldTop.getBlockComplexity();
				Complexity oldHighSubTop = oldTop.getHighestSubComplexity();
				
				Complexity currentTop = stack.peek().getHighestSubComplexity();
				
				boolean changes = false;
				
				if(oldTopComplexity.getNPower() > currentTop.getNPower()){
					currentTop.setNPower(currentTop.getNPower()+oldTopComplexity.getNPower());
					highest = currentTop;
					changes = true;
				}
				
				if(oldHighSubTop.getNPower() >= currentTop.getNPower()){
					currentTop.setNPower(currentTop.getNPower()+oldHighSubTop.getNPower());
					highest = currentTop;
					changes = true;
				}
				
				if(oldTopComplexity.getLogPower() > currentTop.getLogPower()){
					currentTop.setLogPower(currentTop.getLogPower()+oldTopComplexity.getLogPower());
					highest = currentTop;
					changes = true;
				}
				
				if(oldHighSubTop.getLogPower() >= currentTop.getLogPower()){
					currentTop.setLogPower(currentTop.getLogPower()+oldHighSubTop.getLogPower());
					highest = currentTop;
					changes = true;
				}
				
				if(changes){
					stack.peek().setHighestSubComplexity(currentTop);
				
					System.out.println("\nLeaving block "+oldTop.getName()+", updating block "+stack.peek().getName()+":");
					System.out.println(stack.peek().toString());
				}
				
				else{
					System.out.println("\nLeaving block "+oldTop.getName()+", nothing to update.");
					System.out.println(stack.peek().toString());
				}
			}
		}
		
		//System.out.println("\nLeaving block "+stack.pop().getName()+", nothing to update.");
		//System.out.println(stack.peek().toString());
		
		stack.peek().setHighestSubComplexity(highest);
		System.out.println("\nLeaving block "+stack.peek().getName()+".");
		return stack.pop().getHighestSubComplexity();

	}

	/**
	 * The main method runs a menu driver application which prompts the user
	 * for a Python file name to trace to find its order of Complexity or to quit
	 * @param args
	 * 		empty command-line argument
	 */
	public static void main(String[] args){
		//for user-input
		Scanner in = new Scanner(System.in);
		
		while(true){
			try{
				System.out.println();
				System.out.print("Please enter a file name (or 'quit' to quit): ");
				String input = in.nextLine();

				if(input.equals("quit")){
					System.out.println("\nProgram terminating successfully...");
					break;
				}

				else{
					Complexity result = traceFile(input);

					System.out.println("\nOverall Complexity of "+
							input.substring(0,input.length()-3)+": "+
							result.toString());
				}
			} catch(Exception ex){
				System.out.println(ex.getMessage());
			}
		}
	}

}