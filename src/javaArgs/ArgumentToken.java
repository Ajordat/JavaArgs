package javaArgs;


public class ArgumentToken<Object> {

    private String token;
    private Object value;
    private boolean compound;

    ArgumentToken(String name) {
        this.token = name;
        this.value = null;
        this.compound = true;
    }

    ArgumentToken(String name, Object value) {
        this.token = name;
        this.value = value;
        this.compound = false;
    }

    public String toString() {
        return this.token + (this.value != null ? "(" + this.value + ")" : "");
    }

    void setValue(Object value) {
        this.value = value;
    }

    Object getValue() {
        return value;
    }

    String getToken() {
        return this.token;
    }

    boolean isCompound() {
        return this.compound;
    }
}
