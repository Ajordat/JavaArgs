import javaArgs.JavaArgs;
import javaArgs.Argument;


public class Main {

    public static void main(String[] args) {
        JavaArgs jArgs = new JavaArgs();

        jArgs.createArg("-b", true);
        jArgs.createArg("-s", "--string", "pardal");

        jArgs.parseInput(args);

        for (Argument arg : jArgs.getArguments()) {
            System.out.println(arg);
        }
    }
}
