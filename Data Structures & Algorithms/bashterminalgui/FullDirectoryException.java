package bashterminalgui;
/**
 * Dennis Sosa
 * Assignment: #5 (Ternary Tree File System)
 * CSE 214 
 * Stony Brook University
 * @author DennisSosa
 * 
 * FullDirectoryException Custom Exception Class
 */

public class FullDirectoryException extends Exception {

	/**
	 * Throws an exception, and displays a message to the user
	 * indicating that all child references in the current DirectoryNode
	 * are occupied
	 */
	public FullDirectoryException() {
		super("ERROR: All Child references of this directory are occupied.");
	}
	
}