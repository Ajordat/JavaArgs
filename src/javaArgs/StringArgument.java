package javaArgs;

public class StringArgument extends Argument<String> {

    StringArgument(String name, String value) {
        super(name, value);
    }

    StringArgument(String shortName, String longName, String value) {
        super(shortName, longName, value);
    }

}
