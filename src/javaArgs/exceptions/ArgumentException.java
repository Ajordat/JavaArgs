package javaArgs.exceptions;

/**
 * Class from which the JavaArgs own exceptions inherit.
 * It's easy for a programmer to catch all exceptions thrown from this library by just catching
 * this specific exception.
 *
 * Moreover, it has an enum essential for specific exceptions.
 *
 * @author Ajordat
 * @version 1.0
 */
public abstract class ArgumentException extends Exception {

    /**
     * Enum with the different types of arguments. It's needed to log the errors.
     */
    public enum OnType {
        STRING,
        INT,
        BOOLEAN
    }

    /**
     * Constructor with a single String.
     *
     * @param msg Message to be sent to the program.
     */
    ArgumentException(String msg) {
        super(msg);
    }
}
