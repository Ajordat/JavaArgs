package javaArgs;

import java.util.ArrayList;

public class JavaArgs {
    private ArrayList<Argument> args;

    public JavaArgs() {
        args = new ArrayList<Argument>();
    }

    public void createArg(String name, boolean defaultsTo) {
        BooleanArgument arg = new BooleanArgument(name, defaultsTo);
        args.add(arg);
    }

    public void createArg(String shortName, String longName, boolean defaultsTo) {
        BooleanArgument arg = new BooleanArgument(shortName, longName, defaultsTo);
        args.add(arg);
    }

    public void createArg(String name, String defaultsTo) {
        StringArgument arg = new StringArgument(name, defaultsTo);
        args.add(arg);
    }

    public void createArg(String shortName, String longName, String defaultsTo) {
        StringArgument arg = new StringArgument(shortName, longName, defaultsTo);
        args.add(arg);
    }

    public ArrayList<Argument> getArguments() {
        return args;
    }

    public Argument getArg(String name) {
        for (Argument arg : args) {
            if (arg.getShortName().equals(name) || arg.getLongName().equals(name))
                return arg;
        }
        return null;
    }

    public void parseInput(String[] input) {

        for (int i = 0; i < input.length; i++) {
            for (Argument argument : this.args) {
                if (argument.getShortName().equals(input[i]) || argument.getLongName().equals(input[i])) {
                    if (argument instanceof BooleanArgument) {
                        ((BooleanArgument)argument).turnValue();
                    } else if (argument instanceof StringArgument) {
                        if (++i < input.length)
                            argument.setValue(input[i]);
                        else
                            System.out.println("Error");
                    }
                    break;
                }
            }
        }
    }
}
