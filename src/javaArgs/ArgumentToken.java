package javaArgs;


public class ArgumentToken<Object> {

    private String token;
    private Object value;

    ArgumentToken(String name) {
        this.token = name;
        this.value = null;
    }

    ArgumentToken(String name, Object value) {
        this.token = name;
        this.value = value;
    }

    public String toString() {
        return this.token + (this.value != null ? "(" + this.value + ")": "");
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

}
