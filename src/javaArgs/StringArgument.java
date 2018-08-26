package javaArgs;

/**
 * Class to define the desired behaviour of String arguments.
 * <p>
 * It should be very similar to classes {@link BooleanArgument} and {@link IntegerArgument}.
 *
 * @author Ajordat
 * @version 1.0
 */
public class StringArgument extends Argument<String> {

	StringArgument(String defaultsTo) {
		super(defaultsTo);
	}

	@Override
	public void useToken(String tokenName) {
		for (ArgumentToken argToken : args) {
			if (argToken.getTokenName().equals(tokenName)) {
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
