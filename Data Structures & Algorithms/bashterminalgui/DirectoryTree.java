package bashterminalgui;
/**
 * Dennis Sosa
 * Assignment: #5 (Ternary Tree File System)
 * CSE 214
 * Stony Brook University
 * @author DennisSosa
 * 
 * DirectoryTree Class
 */

public class DirectoryTree{
	private DirectoryNode root;
	private DirectoryNode cursor;
	private boolean found;
	private String findDirectoryCheck;
        private String directoryTree;
        private String find;
        public static final String changeDirectoryError = 
                ("Directory could not be found, please enter a different directory name.") ;

	/**
	* Initializes a DirectoryTree object with a single DirectoryNode
	* named "root"
	* <dt><b>Preconditions:</b><dd>
	* 		none
	* <dt><b>Postconditions:</b><dd>
	* 		the tree contains a single DirectoryNode named "root", and
	* 		both cursor and root reference this node
	*/
	public DirectoryTree(){
		DirectoryNode newRoot = new DirectoryNode("root");
		root = newRoot;
		cursor = newRoot;
	}

	/**
	 * Moves the cursor to the root node of the tree
	 * <dt><b>Preconditions:</b><dd>
	 * 		none
	 * <dt><b>Postconditions:</b><dd>
	 * 		the cursor now references the root node of the tree
	 */
	public void resetCursor(){
		cursor = root;
	}

	/**
	* Moves the cursor to the directory with the name indicated by name
	* <dt><b>Preconditions:</b><dd>
	* 		'name' references a valid directory('name' cannot reference a file)
	* <dt><b>Postconditions:</b><dd>
	* 		the cursor now references the directory with the name indicated by
	* 		name. If a child could not be found with that name, then the user is 
	* 		prompted to enter a different directory name.
	* @throws NotADirectoryException
	* 		thrown if the node with the indicated name is a file, as files cannot
	* 		be selected by the cursor, or cannot be found
	*/
	public void changeDirectory(String name) throws NotADirectoryException, NotFoundException{
		
		DirectoryNode temp = cursor;
		boolean found = false;
		
		if(temp.getLeft()!=null){
			if(temp.getLeft().getName().equals(name)){
				if(temp.getLeft().getIsFile())
					throw new NotADirectoryException(temp.getLeft().getIsFile());
				else{
					temp = temp.getLeft();
					found = true;
					this.cursor = temp;
					return;
				}
			}
		}

		if(temp.getMiddle()!=null){
			if(temp.getMiddle().getName().equals(name)){
				if(temp.getMiddle().getIsFile())
					throw new NotADirectoryException(temp.getIsFile());
				else{
					temp = temp.getMiddle();
					found = true;
					this.cursor = temp;
					return;
				}
			}
		}

		if(temp.getRight()!=null){
			if(temp.getRight().getName().equals(name)){
				if(temp.getRight().getIsFile())
					throw new NotADirectoryException(temp.getIsFile());
				else{
					temp = temp.getRight();
					found=true;
					this.cursor = temp;
					return;
				}
			}
		}
		
		if(!found){
                    
			System.out.println("Directory could not be found, please enter a different directory name.");
                        throw new NotFoundException("Directory could not be found, please enter a different directory name.");
		}

	}
	

	/**
	 * Returns a String containing the path directory names from
	 * the root node of the tree to the cursor, with each name separated
	 * by a forward slash "/"
	 * <dt><b>Preconditions:</b><dd>
	 * 		none
	 * <dt><b>Postconditions:</b><dd>
	 * 		the cursor remains at the same DirectoryNode
	 * @return
	 * 		a String containing the path directory names from the root
	 * 		node of the tree to the cursor
	 */
	public String presentWorkingDirectory(){
		DirectoryNode cursorTemp = cursor;
		
		String name = cursor.getName();
		String path = "root";
		int count = 0;
		
		if(name.equals(root.getName()))
			return path;
		
		while(cursorTemp!=root){
			count++;
			cursorTemp = cursorTemp.getParent();
		}
		
		String[] pathArr = new String[count];
		
		cursorTemp = cursor;
		count = 0;
		
		while(cursorTemp!=root){
			pathArr[count] = cursorTemp.getName();
			count++;
			cursorTemp = cursorTemp.getParent();
		}
		
		for(int i=pathArr.length-1; i>=0; i--){
			path += ("/"+pathArr[i]);
		}
	
		return path;
		
	}
	

	/**
	 * Returns a String containing a space-separated list of names of
	 * all the child directories or files of the cursor
	 * <dt><b>Preconditions:</b><dd>
	 * 		none
	 * <dt><b>Postconditions:</b><dd>
	 * 		the cursor remains at the same DirectoryNode
	 * @return
	 * 		a formatted String of DirectoryNode names
	 */
	public String listDirectory(){
		String list = "";
		DirectoryNode cursorTemp = cursor;
		
		if(cursorTemp.getLeft()!=null)
			list += (cursorTemp.getLeft().getName()+" ");
		
		if(cursorTemp.getMiddle()!=null)
			list += (cursorTemp.getMiddle().getName()+" ");
			
		if(cursorTemp.getRight()!=null)
			list += (cursorTemp.getRight().getName()+" ");
		
		return list;
	}

	/**
	 * Prints a formatted nested list of names of all the nodes
	 * in the directory tree, starting from the cursor (Helper Method)
	 * <dt><b>Preconditions:</b><dd>
	 * 		none
	 * <dt><b>Postconditions:</b><dd>
	 * 		the cursor remains at the same DirectoryNode
	 */
	public void printDirectoryTree(){
		DirectoryNode temp = cursor;
                directoryTree = "";
		int spaceCount = 0;
		printDirectoryTree(temp,spaceCount);
	}
	
	/**
	 * Prints a formatted nested list of names of all the nodes
	 * in the directory tree, starting from the cursor RECURSIVELY
	 * <dt><b>Preconditions:</b><dd>
	 * 		none
	 * <dt><b>Postconditions:</b><dd>
	 * 		the cursor remains at the same DirectoryNode
	 * @param temp
	 * 		temporary DirectoryNode with cursor's reference
	 * @param spaceCount
	 * 		space counter for the indents of files and directories
	 */
	public void printDirectoryTree(DirectoryNode temp, int spaceCount){
		for(int i=0; i<spaceCount; i++){
			System.out.print("    ");
                        directoryTree += ("    ");        
		}
		
		if(!temp.getIsFile()){
			System.out.println("|- "+temp.getName());
                        directoryTree += ("|- "+temp.getName()+"\n");
                }
                
		else if(temp.getIsFile()){
			System.out.println("- "+temp.getName());
                        directoryTree += ("- "+temp.getName()+"\n");   
                }
		
		if(temp.getLeft()!=null)
			printDirectoryTree(temp.getLeft(),spaceCount+1);
		
		if(temp.getMiddle()!=null)
			printDirectoryTree(temp.getMiddle(),spaceCount+1);

		if(temp.getRight()!=null)
			printDirectoryTree(temp.getRight(),spaceCount+1);
		
	}

	/**
	 * Creates a directory with the indicated name and adds it to the
	 * children of the cursor node. (Children are added in left-to-right order)
	 * @param name
	 * 		the name of the directory to add
	 * <dt><b>Preconditions:</b><dd>
	 * 		'name' is a legal argument
	 * <dt><b>Postconditions:</b><dd>
	 * 		a new DirectoryNode has been added to the children of the cursor,
	 * 		or an exception has been thrown
	 * @throws IllegalArgumentException
	 * 		thrown if the 'name' argument is invalid
	 * @throws FullDirectoryException
	 * 		thrown if all child references of this directory are occupied
	 */
	public void makeDirectory(String name) 
		throws IllegalArgumentException, FullDirectoryException{
		
		if(name==null || name.contains(" ") || name.contains("/"))
			throw new IllegalArgumentException("Invalid argument for \'name\'");
		
		DirectoryNode newNode = new DirectoryNode(name);
		
		try{
			newNode.setParent(cursor);
			cursor.addChild(newNode);
			
		} catch(FullDirectoryException ex){
			System.out.println(ex.getMessage());
                        throw new FullDirectoryException();
		} catch(NotADirectoryException ex){
			System.out.println(ex.getMessage());
		}
		
	}

	/**
	 * Creates a file with the indicated name and adds it to the children
	 * of the cursor node. (Children are added left-to-right)
	 * @param name
	 * 		the name of the file to add
	 * <dt><b>Preconditions:</b><dd>
	 * 		'name' is a legal argument (does not contain spaces " " or forward
	 * 		slashes "/")
	 * <dt><b>Postconditions:</b><dd>
	 * 		a new DirectoryNode has been added to the children of the cursor,
	 * 		or an exception has been thrown
	 * @throws IllegalArgumentException
	 * 		thrown if the 'name' argument is invalid
	 * @throws FullDirectoryException
	 * 		thrown if all child references of this directory are occupied
	 */
	public void makeFile(String name) throws
		IllegalArgumentException, FullDirectoryException{
		
		if(name==null || name.contains(" ") || name.contains("/"))
			throw new IllegalArgumentException("Invalid argument for \'name\'");
		
		DirectoryNode newNode = new DirectoryNode(name);
		newNode.setIsFile(true);
		
		
		try{
			newNode.setParent(cursor);
			cursor.addChild(newNode);
			
		} catch(FullDirectoryException ex){
			System.out.println(ex.getMessage());
                        throw new FullDirectoryException();
		} catch(NotADirectoryException ex){
			System.out.println(ex.getMessage());
		}
	}
	
	
	/**
	 * EXTRA CREDIT: "cd .." command
	 * Moves the cursor up to its parent directory(does nothing at root)
	 * Prints error message if cannot move to a parent
	 */
	public void moveUp() throws NotFoundException{
		if(cursor==root){
			System.out.println("ERROR: Already at root directory; no previous directory.");
                        throw new NotFoundException("ERROR: Already at root directory; no previous directory.");
                }
		
		else
			cursor = cursor.getParent();
	}
	
	
	/**
	 * EXTRA CREDIT: "find {name}" command
	 * Finds the node in the tree with the indicated name and prints the path
	 * @param name
	 * 		user-input name of the desired node
	 */
	public void findNode(String name) throws NotFoundException{
		found = false;
                find = "";
		DirectoryNode temp = root;
		findNode(name,temp);
		if(!found){
			System.out.println("ERROR: No such file exists.");
                        throw new NotFoundException("ERROR: No such file exists.");
                }
	}
	
	/**
	 * Continuation of EXTRA CREDIT: "find {name}" command
	 * Recursive method to traverse the ternary tree
	 * @param name
	 * 		user-input name of the desired node
	 * @param temp
	 * 		temporary DirectoryNode with root reference
	 */
	public void findNode(String name, DirectoryNode temp){
		if(temp.getName().equals(name)){
			System.out.println(findNodePresentWorkingDirectory(temp));
			found = true;
		}
		
		if(temp.getLeft()!=null)
			findNode(name,temp.getLeft());
		
		if(temp.getMiddle()!=null)
			findNode(name,temp.getMiddle());

		if(temp.getRight()!=null)
			findNode(name,temp.getRight());
		
	}
		
	/**
	 * Part of the EXTRA CREDIT: "find {name}" command
	 * Obtains the position of the 'temp' DirectoryNode
	 * and traverses backwards to obtain the Path
	 * @param temp
	 * 		temporary DirectoryNode passed from 'findNode' method
	 * @return
	 * 		path directory of the temporary DirectoryNode's location
	 */
	public String findNodePresentWorkingDirectory(DirectoryNode temp){
		DirectoryNode cursorTemp = temp;
			
		String name = temp.getName();
		String path = "root";
		int count = 0;
			
		if(name.equals(root.getName())){
                    find += path +"\n";
                    return path;
                }
			
		while(cursorTemp!=root){
			count++;
			cursorTemp = cursorTemp.getParent();
		}
			
		String[] pathArr = new String[count];
			
		cursorTemp = temp;
		count = 0;
			
		while(cursorTemp!=root){
			pathArr[count] = cursorTemp.getName();
			count++;
			cursorTemp = cursorTemp.getParent();
		}
			
		for(int i=pathArr.length-1; i>=0; i--){
			path += ("/"+pathArr[i]);
		}
		find += path + "\n";
		return path;
			
	}
	
	/**
	 * EXTRA CREDIT: "cd {path}" command
	 * Moves the cursor to the directory with the indicated path from
	 * user-input
	 * @param pathName
	 * 		user-input path
	 * @throws NotADirectoryException
	 * 		indicates final destination of user-input path is not a Directory
	 */
	public void changeToDirectoryPath(String pathName) throws NotADirectoryException{
		String findNode = "";
		String findNodeRev = "";
		
		for(int i=pathName.length()-1; i>=0; i--){
			if(pathName.charAt(i)=='/')
				break;
			findNode += pathName.charAt(i);
		}
		
		for(int i=findNode.length()-1; i>=0; i--){
			findNodeRev += findNode.charAt(i);
		}
		try{
                    findNodePath(findNodeRev,pathName);
                } catch(NotFoundException ex){
                    System.out.println();
                }
		

	}
	
	/**
	 * Part of the EXTRA CREDIT: "cd {path}" command
	 * Helper method for the traversing method
	 * @param name
	 * 		name of final destination in desired directory
	 * @param path
	 * 		path name from user-input
	 * 		
	 */
	public void findNodePath(String name, String path) throws NotFoundException{
		findDirectoryCheck = "";
		found = false;
		DirectoryNode temp = root;
		
		try{
			findNodePath(name,temp,path);
		} catch(NotADirectoryException ex){
			System.out.println(ex.getMessage());
		}
		
		if(!found){
			System.out.println("Directory could not be found, please enter a different directory name.");
                        throw new NotFoundException("Directory could not be found, please enter a different directory name.");
                }
	}
	
	/**
	 * Part of EXTRA CREDIT: "cd {path}" command
	 * Recursive method to traverse the ternary tree
	 * and move the cursor to the directory with the initially indicated path
	 * @param name
	 * 		name of the final destination of directory passed from helper method
	 * @param temp
	 * 		temporary DirectoryNode with root reference passed from helper method
	 * @param path
	 * 		path name from user-input passed from helper method
	 * @throws NotADirectoryException
	 * 		indicates desired directory path ends up into a file, not a Directory
	 */
	public void findNodePath(String name, DirectoryNode temp, String path) throws NotADirectoryException{
		if(temp.getName().equals(name)){
			if(temp.getIsFile())
				throw new NotADirectoryException(temp.getIsFile());
			else{
				findDirectoryCheck = findNodePresentWorkingDirectory(temp);
				//System.out.println(findDirectoryCheck);
				if(findDirectoryCheck.equals("root/"+path)){
					found = true;
					cursor = temp;
				}
				return;
			}
		}
		
		if(temp.getLeft()!=null)
			findNodePath(name,temp.getLeft(),path);
		
		if(temp.getMiddle()!=null)
			findNodePath(name,temp.getMiddle(),path);

		if(temp.getRight()!=null)
			findNodePath(name,temp.getRight(),path);
		
	}
	
	
	/**
	 * EXTRA CREDIT: "mv {src} {dst}" command
	 * Moves a file or directory specified by src to dst, including all children
	 * (src and dst are absolute paths)
	 * @param pathName
	 * 		user-input indicated and desired path name
	 * @param destination
	 * 		user-input indicated and desired destination (path name)
	 */
	public void moveFile(String pathName, String destination) {
		String findNode = "";
		String findNodeRev = "";
		
		for(int i=pathName.length()-1; i>=0; i--){
			if(pathName.charAt(i)=='/')
				break;
			findNode += pathName.charAt(i);
		}
		
		for(int i=findNode.length()-1; i>=0; i--){
			findNodeRev += findNode.charAt(i);
		}
		
		findPath(findNodeRev, pathName, destination);

	}
	
	public void findPath(String name, String path, String destination){
		findDirectoryCheck = "";
		found = false;
		DirectoryNode temp = root;
		
		findPath(name,temp,path,destination);

		if(!found)
			System.out.println("Directory could not be found, please enter a different directory name.");
		
	}
	
	public void findPath(String name, DirectoryNode temp, String path, String destination) {
		if(temp.getName().equals(name)){
			findDirectoryCheck = findNodePresentWorkingDirectory(temp);
			//System.out.println(findDirectoryCheck);
			if(findDirectoryCheck.equals(path)){
				found = true;
				DirectoryNode newTemp = temp;
				temp = temp.getParent();
				cursor = temp;
				
				if(cursor.getLeft()!=null){
					if(cursor.getLeft().equals(name))
						cursor.setLeft(null);
				}
				
				if(cursor.getMiddle()!=null){
					if(cursor.getMiddle().equals(name))
						cursor.setMiddle(null);
				}
				
				if(cursor.getRight()!=null){
					if(cursor.getRight().equals(name))
						cursor.setRight(null);
				}
				
				moveToDest(destination, newTemp);
			}
			return;
		}
		
		if(temp.getLeft()!=null)
			findPath(name,temp.getLeft(),path,destination);
		
		if(temp.getMiddle()!=null)
			findPath(name,temp.getMiddle(),path,destination);

		if(temp.getRight()!=null)
			findPath(name,temp.getRight(),path,destination);
		
	}
	
	public void moveToDest(String destination, DirectoryNode newTemp) {
		String findNode = "";
		String findNodeRev = "";
		
		for(int i=destination.length()-1; i>=0; i--){
			if(destination.charAt(i)=='/')
				break;
			findNode += destination.charAt(i);
		}
		
		for(int i=findNode.length()-1; i>=0; i--){
			findNodeRev += findNode.charAt(i);
		}
		
		findPathDest(findNodeRev, destination, newTemp);

	}
	
	public void findPathDest(String name, String path, DirectoryNode newTemp){
		findDirectoryCheck = "";
		found = false;
		DirectoryNode temp = root;
		
		findPathDest(name,path,newTemp,temp);

		if(!found)
			System.out.println("Directory could not be found, please enter a different directory name.");
		
	}
	
	public void findPathDest(String name, String path, DirectoryNode newTemp, DirectoryNode temp) {
		if(temp.getName().equals(name)){
			findDirectoryCheck = findNodePresentWorkingDirectory(temp);
			//System.out.println(findDirectoryCheck);
			if(findDirectoryCheck.equals(path)){
				found = true;
				cursor = temp;
				
				try{
					cursor.addChild(newTemp);
				} catch(NotADirectoryException ex){
					System.out.println(ex.getMessage());
				} catch(FullDirectoryException ex){
					System.out.println(ex.getMessage());
				}
				resetCursor();
				return;
			}
		}
		
		if(temp.getLeft()!=null)
			findPathDest(name, path, newTemp, temp.getLeft());
		
		if(temp.getMiddle()!=null)
			findPathDest(name, path, newTemp, temp.getMiddle());

		if(temp.getRight()!=null)
			findPathDest(name, path, newTemp, temp.getRight());
		
	}
        
        public String getDirectoryTree(){
            return directoryTree;
        }
        
        public String getChangeDirectoryError(){
            return changeDirectoryError;
        }
	
        public String getFind(){
            return find;
        }
	
}