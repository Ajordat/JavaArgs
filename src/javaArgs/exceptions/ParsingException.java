package javaArgs.exceptions;

public class ParsingException extends ArgumentException {

    public ParsingException(String argument, OnType type) {
        super("Missing following argument of \"" + argument + "\" declared as \"" + type + "\".");
    }

}
