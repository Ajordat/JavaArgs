package javaArgs;

/**
 * Class to define the desired behaviour of Boolean arguments.
 * <p>
 * It should be very similar to classes {@link StringArgument} and {@link IntegerArgument}.
 *
 * @author Ajordat
 * @version 1.0
 */
public class BooleanArgument extends Argument<Boolean> {

	BooleanArgument(Boolean defaultsTo) {
		super(defaultsTo);
	}

	@Override
	public void useToken(String tokenName) {
		for (ArgumentToken argToken : args) {
			if (argToken.getTokenName().equals(tokenName)) {
				this.value = (Boolean) argToken.getValue();
			}
		}
	}

	@Override
	public void useToken(ArgumentToken token) {
		this.value = (Boolean) token.getValue();
	}

	@Override
	void setValue(Boolean value) {
		this.value = value;
	}
}
