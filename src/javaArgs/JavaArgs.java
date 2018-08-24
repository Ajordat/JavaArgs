package javaArgs;

import java.util.Collection;
import java.util.LinkedHashMap;

public class JavaArgs {
    private LinkedHashMap<String, Argument> args;

    public JavaArgs() {
        args = new LinkedHashMap<>();
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

    public void parseInput(String[] input) {
        for (int i = 0; i < input.length; i++) {
            for (Argument argument : this.args.values()) {
                if (argument.isArgument(input[i])) {
                    if (argument instanceof BooleanArgument) {
                        argument.useToken(input[i]);
                    } else if (argument instanceof StringArgument) {
                        if (++i < input.length) {
                            argument.setValue(input[i]);
                        } else
                            System.out.println("Error");
                    } else if (argument instanceof IntegerArgument) {
                        if (++i < input.length) {
                            argument.setValue(Integer.valueOf(input[i]));
                        } else
                            System.out.println("Error");
                    }
                    break;
                }
            }
        }
    }
}
