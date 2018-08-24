package javaArgs;

public class StringArgument extends Argument<String> {

    StringArgument(String defaultsTo) {
        super(defaultsTo);
    }

    @Override
    public void useToken(String token) {
        for (ArgumentToken argToken : args) {
            if (argToken.getToken().equals(token)) {
                this.value = (String) argToken.getValue();
            }
        }
    }

    @Override
    public void useToken(ArgumentToken token) {
        this.value = (String) token.getValue();
    }

    @Override
    void setValue(String value) {
        this.value = value;
    }
}
