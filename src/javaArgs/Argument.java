package javaArgs;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Abstract class to define the desired behaviour of arguments.
 *
 * As this class cannot be instantiated, it's usage is bound to the classes that inherit from this one.
 * These classes are BooleanArgument, StringArgument and IntegerArgument and represent the different types
 * of argument available.
 *
 * Each of the said classes' Object parameter is the type of the argument. Can be Boolean, String or Integer.
 *
 * @param <Object> Type of the argument. In this abstract class, Object.
 *
 * @author Ajordat
 * @version 1.0
 */
@SuppressWarnings("unused")
public abstract class Argument<Object> {

    /**
     * LinkedList of ArgumentToken to store all the tokens of an argument.
     */
    LinkedList<ArgumentToken> args;

    /**
     * Default value of the Argument.
     * In the classes that inherit, this Object is the same class as the their constructor.
     */
    private Object defaultsTo;

    /**
     * Current value of the Argument.
     * In the classes that inherit, this Object is the same class as the their constructor.
     */
    Object value;

    /**
     * Argument constructor. Sets the default value and the value from the received parameter.
     *
     * @param defaultsTo Default value and current value of the Argument.
     */
    Argument(Object defaultsTo) {
        this.args = new LinkedList<>();
        this.defaultsTo = defaultsTo;
        this.value = defaultsTo;
    }

    /**
     * Method to add a token to the argument.
     * Receives the token name and the value it's associated to.
     *
     * This method returns the Argument itself in order to chain token additions.
     *
     * @param name Name of the token
     * @param value Value of the token
     * @return Argument
     */
    public Argument<Object> addToken(String name, Object value) {
        args.add(new ArgumentToken<>(name, value));
        return this;
    }

    /**
     * Method to add a compound token to the argument.
     * Receives only the token name, so when the input is parsed, JavaArgs will assume that the value of this token
     * is specified following the token.
     *
     * This method returns the Argument itself in order to chain token additions.
     *
     * @param name Name of the token
     * @return Argument
     */
    public Argument<Object> addToken(String name) {
        args.add(new ArgumentToken<>(name));
        return this;
    }

    /**
     * Set the Argument's value to the value of the indicated token, retrieved from the token name.
     *
     * @param tokenName Name of the token the argument's value will be set from.
     */
    public abstract void useToken(String tokenName);

    /**
     * Set the Argument's value to the value of the received token.
     *
     * @param token Token from which to extract the value.
     */
    public abstract void useToken(ArgumentToken token);

    /**
     * Set the Argument's value to the value received.
     *
     * @param value Value to set.
     */
    abstract void setValue(Object value);

    /**
     * Method to check whether a token exists in the argument.
     *
     * @param tokenName The name of the token.
     * @return If the token exists for this argument.
     */
    boolean isToken(String tokenName) {
        for (ArgumentToken argToken : this.args) {
            if (tokenName.equals(argToken.getTokenName()))
                return true;
        }
        return false;
    }

    /**
     * Retrieve a token from its name.
     *
     * @param tokenName The name of the token.
     * @return The token retrieved. Null if it doesn't exist.
     */
    ArgumentToken getToken(String tokenName) {
        for (ArgumentToken argToken : this.args) {
            if (tokenName.equals(argToken.getTokenName()))
                return argToken;
        }
        return null;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return String representation of the object.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();

        Iterator i = this.args.iterator();
        while (i.hasNext()) {
            s.append(i.next());
            if (i.hasNext())
                s.append(", ");
        }

        return s + ": " + this.value + (this.defaultsTo != null ? "(" + this.defaultsTo + ")" : "");
    }

    /**
     * Get the value of the argument.
     *
     * @return The value of the argument.
     */
    public Object getValue() {
        return this.value;
    }

    /**
     * Get if the argument is set.
     * The only case it returns false is when it's a mandatory argument that hasn't been set by the user.
     *
     * @return If the argument's value is set.
     */
    public boolean isSet() {
        return this.value != null;
    }

    Object getDefault() {
        return defaultsTo;
    }

    String[] getNames() {
        return (String[]) this.args.toArray();
    }
}
