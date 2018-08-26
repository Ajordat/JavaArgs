package javaArgs.exceptions;

/**
 * Exception made to register the error of receiving a non-Integer value from the user, to a compound integer token.
 * e.g.
 *      --integer hi
 * instead of:
 *      --integer 3
 *
 * @author Ajordat
 * @version 1.0
 */
public class NumberParsingException extends ArgumentException {

    /**
     * Constructor receiving two Strings being the name of the token and the non-integer value read.
     *
     * @param tokenName Name of the token.
     * @param value Value read from input. Obviously, not an Integer.
     */
    public NumberParsingException(String tokenName, String value) {
        super("Wrong " + OnType.INT + " format on compound argument \"" + tokenName + "\" for input value \"" + value + "\".");
    }

}
