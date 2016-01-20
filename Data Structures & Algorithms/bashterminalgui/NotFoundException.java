package bashterminalgui;
/**
 * Dennis Sosa
 * Assignment: #5 (Ternary Tree File System)
 * CSE 214
 * Stony Brook University
 * @author DennisSosa
 * 
 * NotFoundException Custom Exception Class
 */

public class NotFoundException extends Exception {

	/**
	 * Throws an exception, and displays a message to the user
	 * indicating that desired Directory/File was not found
	 */
	public NotFoundException(String message) {
		super(message);
	}
	
}