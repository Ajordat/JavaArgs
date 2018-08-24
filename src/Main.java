import javaArgs.Argument;
import javaArgs.JavaArgs;
import javaArgs.exceptions.NumberParsingException;
import javaArgs.exceptions.ParsingException;

import java.util.Arrays;


public class Main {

    public static void main(String[] args) {
        JavaArgs jArgs = new JavaArgs();

        jArgs.createArgument("boolean", true)
                .addToken("-b", true)
                .addToken("-n", false);

        jArgs.createArgument("string", "pardal")
                .addToken("-s")
                .addToken("--string");

        jArgs.createArgument("integer", 3)
                .addToken("-i")
                .addToken("--integer", 5);

        try {
            jArgs.parseInput(("-n --string asdf qwer --integer 12 -i 18").split(" "));
        } catch (ParsingException | NumberParsingException e) {
            e.printStackTrace();
            System.exit(1);
        }

        for (Argument arg : jArgs.getArguments()) {
            System.out.println(arg);
        }

        System.out.println(jArgs.getArg("boolean").getValue());
        System.out.println(jArgs.getArg("string").getValue());
        System.out.println(jArgs.getArg("integer").getValue());
        System.out.println("# args: " + jArgs.length());
        System.out.println(Arrays.toString(jArgs.getLoneArguments()));
        System.out.println("isEmpty: " + jArgs.isEmpty());
    }
}
