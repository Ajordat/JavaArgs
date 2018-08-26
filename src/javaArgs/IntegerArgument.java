package javaArgs;

/**
 * Class to define the desired behaviour of Integer arguments.
 * <p>
 * It should be very similar to classes {@link BooleanArgument} and {@link StringArgument}.
 *
 * @author Ajordat
 * @version 1.0
 */
public class IntegerArgument extends Argument<Integer> {

	IntegerArgument(Integer defaultsTo) {
		super(defaultsTo);
	}

	@Override
	public void useToken(String tokenName) {
		for (ArgumentToken argToken : args) {
			if (argToken.getTokenName().equals(tokenName)) {
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
