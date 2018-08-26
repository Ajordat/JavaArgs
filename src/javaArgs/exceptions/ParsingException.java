package javaArgs.exceptions;

/**
 * Exception made to register the error of not being able to read the second part of a compound argument.
 *
 * @author Ajordat
 * @version 1.0
 */
public class ParsingException extends ArgumentException {

    /**
     * Constructor receiving the name of the compound token and its type.
     *
     * @param tokenName Name of the token.
     * @param type Type of the token.
     */
    public ParsingException(String tokenName, OnType type) {
        super("Missing following argument of \"" + tokenName + "\" declared as \"" + type + "\".");
    }

}
