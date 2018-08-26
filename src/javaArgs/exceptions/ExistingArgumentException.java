package javaArgs.exceptions;

/**
 * Exception made to register the error of trying to create an already-existing argument.
 *
 * @author Ajordat
 * @version 1.0
 */
public class ExistingArgumentException extends ArgumentException {

	/**
	 * Constructor with a single String.
	 *
	 * @param key Message to be sent to the program.
	 */
	public ExistingArgumentException(String key) {
		super("The key \"" + key + "\" already exists.");
	}
}
