package javaArgs;

/**
 * Class representation of an argument's tokenName.
 *
 * @param <Object> The type of the argument. It can be either Boolean, String or Integer.
 *
 * @author Ajordat
 * @version 1.0
 */
public class ArgumentToken<Object> {

    /**
     * Name of the token.
     */
    private String tokenName;

    /**
     * Value of the token. It can be either Boolean, String or Integer.
     */
    private Object value;

    /**
     * Boolean indicating if the token is compound or not. If it is compound, the field value should be null.
     */
    private boolean compound;

    /**
     * Token constructor for a compound token. It requires a second string to be parsed on input to get its value.
     *
     * @param name Name of the token.
     */
    ArgumentToken(String name) {
        this.tokenName = name;
        this.value = null;
        this.compound = true;
    }

    /**
     * Token constructor for a non-compound token. When this token appears as a command line argument, the argument
     * takes the value of this token.
     *
     * @param name Name of the token.
     * @param value Value of the token.
     */
    ArgumentToken(String name, Object value) {
        this.tokenName = name;
        this.value = value;
        this.compound = false;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return String representation of the object.
     */
    public String toString() {
        return this.tokenName + (this.value != null ? "(" + this.value + ")" : "");
    }

    void setValue(Object value) {
        this.value = value;
    }

    /**
     * Get the value of the token.
     *
     * @return The value of the token.
     */
    Object getValue() {
        return value;
    }

    /**
     * Get the name of the token.
     *
     * @return The name of the token.
     */
    String getTokenName() {
        return this.tokenName;
    }

    /**
     * Returns if the token is compound.
     *
     * @return If the token is compound.
     */
    boolean isCompound() {
        return this.compound;
    }
}
