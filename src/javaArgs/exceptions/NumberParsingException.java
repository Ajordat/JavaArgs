package javaArgs.exceptions;


public class NumberParsingException extends ArgumentException {

    public NumberParsingException(String argument, String value) {
        super("Wrong " + OnType.INT + " format on compound argument \"" + argument + "\" for input value \"" + value + "\".");
    }

}
