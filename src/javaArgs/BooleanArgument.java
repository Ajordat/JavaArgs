package javaArgs;

class BooleanArgument extends Argument<Boolean> {

    BooleanArgument(String name, Boolean value) {
        super(name, value);
    }

    BooleanArgument(String shortName, String longName, Boolean value) {
        super(shortName, longName, value);
    }

    void turnValue() {
        this.value = !this.value;
    }
}
