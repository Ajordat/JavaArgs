package javaArgs;

import java.util.Iterator;
import java.util.LinkedList;

public abstract class Argument<Object> {
    LinkedList<ArgumentToken> args;
    private Object defaultsTo;
    Object value;


    Argument(Object defaultsTo) {
        this.args = new LinkedList<>();
        this.defaultsTo = defaultsTo;
        this.value = defaultsTo;
    }

    public Argument<Object> addToken(String name, Object value) {
        args.add(new ArgumentToken<>(name, value));
        return this;
    }

    public Argument<Object> addToken(String name) {
        args.add(new ArgumentToken<>(name));
        return this;
    }

    public abstract void useToken(String token);
    abstract void setValue(Object value);

    boolean isArgument(String token) {
        for (ArgumentToken argToken : this.args) {
            if (token.equals(argToken.getToken()))
                return true;
        }
        return false;
    }

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

    public Object getValue() {
        return this.value;
    }


    Object getDefault() {
        return defaultsTo;
    }

    String[] getNames() {
        return (String[]) this.args.toArray();
    }
}
