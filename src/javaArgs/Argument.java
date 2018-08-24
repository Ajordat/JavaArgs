package javaArgs;

public abstract class Argument<Object> {

    private String shortName;
    private String longName;
    Object value;

    Argument(String name, Object value) {
        this(name, name, value);
    }

    Argument(String shortName, String longName, Object value) {
        this.shortName = shortName;
        this.longName = longName;
        this.value = value;
    }

    public String toString() {
        String s = this.shortName;

        if (!this.shortName.equals(this.longName))
            s += ", " + this.longName;

        return s + ": " + this.value;
    }

    void setValue(Object value) {
        this.value = value;
    }

    Object getValue() {
        return value;
    }

    String getShortName() {
        return this.shortName;
    }

    String getLongName() {
        return this.longName;
    }

}
