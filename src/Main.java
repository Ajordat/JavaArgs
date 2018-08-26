import javaArgs.*;
import javaArgs.exceptions.ExistingArgumentException;
import javaArgs.exceptions.NonExistentArgumentException;
import javaArgs.exceptions.NumberParsingException;
import javaArgs.exceptions.ParsingException;

import java.util.Arrays;


public class Main {

	public static void main(String[] args) {
		JavaArgs jArgs = new JavaArgs();

		try {
			jArgs.createArgument("boolean", true)
					.addToken("-b", true)
					.addToken("-n", false);

			jArgs.createArgument("string", "pardal")
					.addToken("-s")
					.addToken("--string");

			jArgs.createArgument("integer", (Integer) null)
					.addToken("-i")
					.addToken("--integer", 5);

			jArgs.parseInput(("-n --string asdf qwer 12 18").split(" "));
		} catch (ExistingArgumentException | ParsingException | NumberParsingException e) {
			e.printStackTrace();
			System.exit(1);
		}

		for (Argument arg : jArgs.getArguments()) {
			System.out.println(arg);
		}

		try {
			BooleanArgument bArg = (BooleanArgument) jArgs.getArgument("boolean");
			StringArgument sArg = (StringArgument) jArgs.getArgument("string");
			IntegerArgument iArg = (IntegerArgument) jArgs.getArgument("integer");

			System.out.println("is set: " + bArg.isSet() + " - " + bArg.getValue());
			System.out.println("is set: " + sArg.isSet() + " - " + sArg.getValue());
			System.out.println("is set: " + iArg.isSet() + " - " + iArg.getValue());

			System.out.println("# args: " + jArgs.length());
			System.out.println(Arrays.toString(jArgs.getLoneArguments()));
			System.out.println("isEmpty: " + jArgs.isEmpty());

		} catch (NonExistentArgumentException e) {
			e.printStackTrace();
		}
	}
}
