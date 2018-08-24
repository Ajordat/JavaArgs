package javaArgs;

public class IntegerArgument extends Argument<Integer> {

    IntegerArgument(Integer defaultsTo) {
        super(defaultsTo);
    }

    @Override
    public void useToken(String token) {
        for (ArgumentToken argToken : args) {
            if (argToken.getToken().equals(token)) {
                this.value = (Integer) argToken.getValue();
            }
        }
    }

    @Override
    public void useToken(ArgumentToken token) {
        this.value = (Integer) token.getValue();
    }

    @Override
    void setValue(Integer value) {
        this.value = value;
    }
}
