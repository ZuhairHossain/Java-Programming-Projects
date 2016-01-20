package bashterminalgui;
/**
 * Dennis Sosa
 * Assignment: #5 (Ternary Tree File System)
 * CSE 214
 * Stony Brook University
 * @author DennisSosa
 * 
 * NotADirectoryException Custom Exception Class
 */

public class NotADirectoryException extends Exception {
	private boolean isFile;

	/**
	 * Throws an exception, and displays a message to the user
	 * indicating that the current node is a file, as files cannot
	 * contain DirectoryNode references (i.e. all files are leaves)
	 * @param isFile
	 * 		indication of DirectoryNode being a file or not
	 */
	public NotADirectoryException(boolean isFile) {
		super("ERROR: Current Node is a file, files "
				+ "cannot contain DirectoryNode references.");
		this.isFile = isFile;
	}

	/**
	 * Receives the value of the isFile
	 * which caused this Custom Exception Class to be called
	 * @return
	 * 		the value of isFile as a boolean value
	 */
	public boolean getIsFile(){
		return isFile;
	}
	
}