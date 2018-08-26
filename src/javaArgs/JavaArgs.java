package javaArgs;

import javaArgs.exceptions.ExistingArgumentException;
import javaArgs.exceptions.NonExistentArgumentException;
import javaArgs.exceptions.NumberParsingException;
import javaArgs.exceptions.ParsingException;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import static javaArgs.exceptions.ArgumentException.OnType.INT;
import static javaArgs.exceptions.ArgumentException.OnType.STRING;

/**
 * Class used to facilitate the use of command line arguments in a Java program.
 * Each argument has an associated key (to retrieve the argument) and tokens (used from command line).
 * The steps to use this tool are the following:
 * <p>
 * 1) Get an instance through the constructor.
 * 2) Create the arguments and associate tokens to them.
 * 3) Parse the command line arguments.
 * 4) Retrieve the desired values.
 *
 * @author Ajordat
 * @version 1.0
 */
public class JavaArgs {

	/**
	 * LinkedHasMap to store the arguments created.
	 */
	private LinkedHashMap<String, Argument> args;

	/**
	 * LinkedList of strings to store the lone arguments.
	 */
	private LinkedList<String> loneArguments;

	/**
	 * Integer to store the number of arguments parsed.
	 */
	private int length;

	/**
	 * JavaArgs constructor.
	 */
	public JavaArgs() {
		this.args = new LinkedHashMap<>();
		this.loneArguments = new LinkedList<>();
		this.length = 0;
	}

	/**
	 * Method to create a boolean argument.
	 * Each argument requires a key and a default value (nullable).
	 * <p>
	 * This method returns the argument created in order to chain token additions.
	 *
	 * @param key        Key associated to the argument.
	 * @param defaultsTo Default value of the argument. If the value is a null Boolean, the argument is mandatory.
	 * @return The created argument.
	 * @throws ExistingArgumentException The argument already exists.
	 */
	public BooleanArgument createArgument(String key, Boolean defaultsTo) throws ExistingArgumentException {

		if (args.containsKey(key))
			throw new ExistingArgumentException(key);

		BooleanArgument arg = new BooleanArgument(defaultsTo);
		args.put(key, arg);
		return arg;
	}

	/**
	 * Method to create a string argument.
	 * Each argument requires a key and a default value (nullable).
	 * <p>
	 * This method returns the argument created in order to chain token additions.
	 *
	 * @param key        Key associated to the argument.
	 * @param defaultsTo Default value of the argument. If the value is a null String, the argument is mandatory.
	 * @return The created argument.
	 * @throws ExistingArgumentException The argument already exists.
	 */
	public StringArgument createArgument(String key, String defaultsTo) throws ExistingArgumentException {

		if (args.containsKey(key))
			throw new ExistingArgumentException(key);

		StringArgument arg = new StringArgument(defaultsTo);
		args.put(key, arg);
		return arg;
	}

	/**
	 * Method to create an integer argument.
	 * Each argument requires a key and a default value (nullable).
	 * <p>
	 * This method returns the argument created in order to chain token additions.
	 *
	 * @param key        Key associated to the argument.
	 * @param defaultsTo Default value of the argument. If the value is a null Integer, the argument is mandatory.
	 * @return The created argument.
	 * @throws ExistingArgumentException The argument already exists.
	 */
	public IntegerArgument createArgument(String key, Integer defaultsTo) throws ExistingArgumentException {

		if (args.containsKey(key))
			throw new ExistingArgumentException(key);

		IntegerArgument arg = new IntegerArgument(defaultsTo);
		args.put(key, arg);
		return arg;
	}

	/**
	 * Getter of all created arguments, ignoring type.
	 *
	 * @return All created arguments.
	 */
	public Collection<Argument> getArguments() {
		return args.values();
	}

	/**
	 * Method to retrieve an specific Argument from its key.
	 *
	 * @param key Key of the argument to retrieve.
	 * @return The argument requested.
	 * @throws NonExistentArgumentException The argument doesn't exist.
	 */
	public Argument getArgument(String key) throws NonExistentArgumentException {

		if (!args.containsKey(key))
			throw new NonExistentArgumentException(key);

		return args.get(key);
	}

	/**
	 * Method to retrieve all parsed input arguments that weren't associated to an argument of JavaArgs.
	 *
	 * @return Array of strings with all unassociated input arguments.
	 */
	public String[] getLoneArguments() {
		return this.loneArguments.toArray(new String[0]);
	}

	/**
	 * Method to parse the input arguments of the program.
	 * <p>
	 * After this call all configured parameters have been modified according to the user input. The developer can now
	 * retrieve all values from the arguments using their keys.
	 *
	 * @param input Array of Strings received from command line to the variable 'args' from the main method.
	 * @throws ParsingException       Exception thrown if a compound argument can't get its value.
	 * @throws NumberParsingException Exception thrown if the value of a compound integer argument is not formatted as an Integer.
	 */
	@SuppressWarnings("unchecked")
	public void parseInput(String[] input) throws ParsingException, NumberParsingException {
		boolean found;

		if (input == null)
			return;

		this.length = 0;
		for (int i = 0; i < input.length; i++) {
			found = false;
			for (Argument argument : this.args.values()) {
				if (argument.isToken(input[i])) {
					ArgumentToken token = argument.getToken(input[i]);

					if (argument instanceof BooleanArgument) {
						argument.useToken(token);

					} else if (argument instanceof StringArgument) {
						if (token.isCompound()) {
							if (++i < input.length) {
								argument.setValue(input[i]);
							} else
								throw new ParsingException(input[i - 1], STRING);
						} else {
							argument.useToken(token);
						}

					} else if (argument instanceof IntegerArgument) {
						if (token.isCompound()) {
							if (++i < input.length) {
								try {
									argument.setValue(Integer.valueOf(input[i]));
								} catch (NumberFormatException e) {
									throw new NumberParsingException(input[i - 1], input[i]);
								}
							} else
								throw new ParsingException(input[i - 1], INT);
						} else {
							argument.useToken(token);
						}
					}
					found = true;
					break;
				}
			}
			if (!found) {
				this.loneArguments.add(input[i]);
			}
			this.length++;
		}
	}

	/**
	 * Method to get the number of arguments read.
	 *
	 * @return The number of  arguments read.
	 */
	public int length() {
		return this.length;
	}

	/**
	 * Method to know if it wasn't possible to read any arguments.
	 *
	 * @return If it wasn't possible to read any arguments.
	 */
	public boolean isEmpty() {
		return this.length == 0;
	}
}
