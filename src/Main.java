import javaArgs.JavaArgs;
import javaArgs.Argument;


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

        jArgs.parseInput(args);

        for (Argument arg : jArgs.getArguments()) {
            System.out.println(arg);
        }

        System.out.println(jArgs.getArg("boolean").getValue());
        System.out.println(jArgs.getArg("string").getValue());
        System.out.println(jArgs.getArg("integer").getValue());
    }
}
