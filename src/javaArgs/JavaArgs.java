package javaArgs;

import javaArgs.exceptions.NumberParsingException;
import javaArgs.exceptions.ParsingException;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import static javaArgs.exceptions.ArgumentException.OnType.INT;
import static javaArgs.exceptions.ArgumentException.OnType.STRING;


public class JavaArgs {
    private LinkedHashMap<String, Argument> args;
    private LinkedList<String> loneStrings;

    public JavaArgs() {
        args = new LinkedHashMap<>();
        loneStrings = new LinkedList<>();
    }

    public BooleanArgument createArgument(String key, boolean defaultsTo) {
        BooleanArgument arg = new BooleanArgument(defaultsTo);
        args.put(key, arg);
        return arg;
    }

    public StringArgument createArgument(String key, String defaultsTo) {
        StringArgument arg = new StringArgument(defaultsTo);
        args.put(key, arg);
        return arg;
    }

    public IntegerArgument createArgument(String key, int defaultsTo) {
        IntegerArgument arg = new IntegerArgument(defaultsTo);
        args.put(key, arg);
        return arg;
    }

    public Collection<Argument> getArguments() {
        return args.values();
    }

    public Argument getArg(String key) {
        return args.get(key);
    }

    public String[] getLoneStrings() {
        return (String[]) this.loneStrings.toArray();
    }

    public void parseInput(String[] input) throws ParsingException, NumberParsingException {
        for (int i = 0; i < input.length; i++) {
            for (Argument argument : this.args.values()) {
                if (argument.isArgument(input[i])) {
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
                    break;
                } else {
                    loneStrings.add(input[i]);
                }
            }
        }
    }
}
