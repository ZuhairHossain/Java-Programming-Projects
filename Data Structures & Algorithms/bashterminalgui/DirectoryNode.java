package bashterminalgui;
/**
 * Dennis Sosa
 * Assignment: #5 (Ternary Tree File System)
 * CSE 214 
 * Stony Brook University
 * @author DennisSosa
 * 
 * DirectoryNode Class
 */


public class DirectoryNode{
	private String name;
	private String path;
	private DirectoryNode left;
	private DirectoryNode middle;
	private DirectoryNode right;
	private DirectoryNode parent;
	private boolean isFile;

	/**
	 * Creates a new DirectoryNode object with uninstantiated parameters
	 * Represents a node in a file tree
	 */
	public DirectoryNode(){
		left = null;
		middle = null;
		right = null;
		parent = null;
	}

	/**
	 * Creates a new DirectoryNode object using given values
	 * Overloaded constructor
	 * @param name
	 * 		the name of the node in the tree
	 * @param left
	 * 		left leaf/node of the DirectoryNode object
	 * @param middle
	 * 		middle leaf/node of the DirectoryNode object
	 * @param right
	 * 		right leaf/node of the DirectoryNode object
	 */
	public DirectoryNode(String name, DirectoryNode left,
		DirectoryNode middle, DirectoryNode right){
		this.name = name;
		this.left = left;
		this.middle = middle;
		this.right = right;
		parent = null;
	}

	/**
	 * Creates a new DirectoryNode object with a given name
	 * @param name
	 * 		the name of the node in the tree as a String
	 */
	public DirectoryNode(String name){
		this.name = name;
		left = null;
		middle = null;
		right = null;
		parent = null;
	}


	/**
	 * Receives the name of a particular DirectorNode
	 * @return
	 * 		the name of a DirectoryNode as a String
	 */
	public String getName(){
		return name;
	}

	/**
	 * Changes a particular DirectoryNode's name to a new specified name
	 * @param name
	 * 		new name of the DirectoryNode
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * Receives the indication of whether a particular DirectoryNode is
	 * a file or a directory
	 * @return
	 * 		true if the current DirectoryNode is a file, else false
	 */
	public boolean getIsFile(){
		return isFile;
	}

	/**
	 * Changes a particular DirectoryNode's indication of whether it
	 * is a file or a directory
	 * @param isFile
	 * 		new boolean value of whether current node is a file
	 */
	public void setIsFile(boolean isFile){
		this.isFile = isFile;
	}

	/**
	 * Receives the left child of a particular ternary tree DirectoryNode's leaf/node
	 * @return
	 * 		child at left of current DirectoryNode
	 */
	public DirectoryNode getLeft(){
		return left;
	}

	/**
	 * Changes a particular DirectoryNodes's left child to a new specified left child
	 * @param left
	 * 		new left child of the current DirectoryNode
	 */
	public void setLeft(DirectoryNode left){
		this.left = left;
	}

	/**
	 * Receives the middle child of a particular ternary tree DirectoryNode's leaf/node
	 * @return
	 * 		child at middle of current DirectoryNode
	 */
	public DirectoryNode getMiddle(){
		return middle;
	}

	/**
	 * Changes a particular DirectoryNode's middle child to a new specified middle child
	 * @param middle
	 * 		new middle child of the current DirectoryNode
	 */
	public void setMiddle(DirectoryNode middle){
		this.middle = middle;
	}
	/**
	 * Receives the right child of a particular ternary tree DirectoryNode's leaf/node
	 * @return
	 * 		child at right of current DirectoryNode
	 */
	public DirectoryNode getRight(){
		return right;
	}

	/**
	 * Changes a particular DirectoryNode's right child to a new specified right child
	 * @param right
	 * 		new right child of the current DirectoryNode
	 */
	public void setRight(DirectoryNode right){
		this.right = right;
	}
	
	/**
	 * Adds newChild to any of the open child positions of this node
	 * (left, middle, or right) in left-to-right order
	 * @param newChild
	 * 		new child element being added to the current DirectoryNode
	 * <dt><b>Preconditions:</b><dd>
	 * 		This node is not a file, & There is at least one empty position
	 * 		in the children of this node (left, middle, or right)
	 * <dt><b>Postconditions:</b><dd>
	 * 		newChild has been added as a child of this node. If there is no
	 * 		room for a new node, throw FullDirectoryException
	 * @throws FullDirectoryException
	 * 		indicates that all child references of this directory are occupied
	 * @throws NotADirectoryException
	 * 		indicates the current node is a file, as files cannot contain 
	 * 		DirectoryNode references
	 */
	public void addChild(DirectoryNode newChild) 
		throws FullDirectoryException, NotADirectoryException {
		if(!isFile && (this.getLeft()==null || this.getMiddle()==null 
			|| this.getRight()==null)){
			
			if(this.getLeft() == null)
				this.setLeft(newChild);

			else if(this.getMiddle() == null)
				this.setMiddle(newChild);

			else if(this.getRight()==null)
				this.setRight(newChild);

		}

		else if(isFile){
			throw new NotADirectoryException(isFile);
		}

		else if(this.getLeft()!=null && this.getMiddle()!=null 
			&& this.getRight()!=null){
			throw new FullDirectoryException();
		}

		return;

	}
		
	/**
	 * Receives the parent of the current DirectoryNode (if a child/leaf)
	 * @return
	 * 		the parent of the current Ternary tree DirectoryNode
	 */
	public DirectoryNode getParent(){
		return parent;
	}
	
	/**
	 * Changes the parent of the current DirectoryNode to a new specified
	 * parent DirectoryNode
	 * @param parent
	 * 		new specified parent DirectoryNode for the current DirectoryNode
	 */
	public void setParent(DirectoryNode parent){
		this.parent = parent;
	}
	
}