package javaArgs.exceptions;

public abstract class ArgumentException extends Exception {

    public enum OnType {
        STRING,
        INT
    }

    ArgumentException(String msg) {
        super(msg);
    }
}
