package javaArgs.exceptions;

/**
 * Exception made to register the error of requesting a non existent argument.
 *
 * @author Ajordat
 * @version 1.0
 */
public class NonExistentArgumentException extends ArgumentException {
	/**
	 * Constructor with a single String.
	 *
	 * @param key Message to be sent to the program.
	 */
	public NonExistentArgumentException(String key) {
		super("The key \"" + key + "\" doesn't exist as an argument.");
	}
}
