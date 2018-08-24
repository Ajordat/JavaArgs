package javaArgs;

public class BooleanArgument extends Argument<Boolean> {

    BooleanArgument(Boolean defaultsTo) {
        super(defaultsTo);
    }

    @Override
    public void useToken(String token) {
        for (ArgumentToken argToken : args) {
            if (argToken.getToken().equals(token)) {
                this.value = (Boolean) argToken.getValue();
            }
        }
    }

    @Override
    void setValue(Boolean value) {
        this.value = value;
    }
}
