# JavaArgs

This project aims to implement a library that facilitates the processing of command line arguments in a Java program.

The system works by associating **keys** to arguments and using them to retrieve their value after parsing the input. 

## Creating the object 

It's done as follows:

```java
JavaArgs jArgs = new JavaArgs();
```
This was the easy part.

##  Setting the arguments

As said, now you need to create the arguments using *keys*. In this example, "boolean_arg", "string_arg" and "integer_arg" are the keys of each argument.
```java
jArgs.createArgument("boolean_arg", true);
jArgs.createArgument("string_arg", "hello world!");
jArgs.createArgument("integer_arg", 0);
```

This means that the three arguments get created and they all have the default value specified, indicating the type of each argument. 
The default value is returned when requesting for the value of an argument that hasn't been specified from command line (by the user).

The *key* is transparent for the user. In order for him to interact, you need to create the argument's tokens.

### Mandatory arguments

If you want to force the user to set an argument, just set the default value to `null`:

```java
jArgs.createArgument("speed", (Integer) null);
```
If you set the default value to `null`, you'll have to cast it to either `Boolean`, `String` or `Integer` in order to set the type of the argument.


## Adding the *tokens*
After creating the argument, it is needed to add the **tokens**, which are the words the user will use to pass the values to 
the program. This *tokens* can be either `Boolean`, `String` or `Integer`; meaning these three are the type of argument accepted.

The addition of a `String` argument  is done as follows:

```java
jArgs.createArgument("path", ".")
        .addToken("--path")
        .addToken("--prev-dir", "..");
```

In the lines above, the argument "path" has been created with the default value ".". Later, we add the following *tokens*:
* `--path`: This argument doesn't have a default value so it requires a second argument when calling the program used, 
e.g.: `./foo --path /home/foo`.
* `--prev-dir`: This other argument has an specified default value, so when it appears on the command line, 
the argument "path" takes the written value. It is used as: `./foo --prev-dir`.

As said, we could also have `Boolean` arguments such as:

```java
jArgs.createArgument("debug_mode", false)
        .addToken("-d", true)
        .addToken("--debug", true)
        .addToken("--no-debug", false);
```

In this case, if none of the previous tokens is written as an argument, the value of argument "debug_mode" is *false*.
If the tokens `-d` or `--debug` are written, "debug_mode" is *true*.

To create an `Integer` argument just set the default value as an `int` (or a casted `null` for a mandatory argument):

```java
jArgs.createArgument("speed", (Integer) null)
        .addToken("--speed")
        .addToken("--slow", 1)
        .addToken("--medium", 3)
        .addToken("--fast", 5);
```

## Parsing the arguments
After setting up the JavaArgs object, it needs to read the `String[] args` parameter of the `main` executable in order to parse 
the arguments.
```java
jArgs.parseInput(args);
```
This call may throw **exceptions** (see Documentation for details), which all inherit from the abstract class `ArgumentException`.

## Retrieving argument values

After parsing the program arguments, we can now retrieve the desired arguments:

```java
int speed = (int) jArgs.getArgument("speed").getValue();
```

Note the cast to `int`. The call `getArgument()` returns an instance of `Argument`, which only deals with `Object`s.
In order to work more easily if there's need to perform multiple calls to said argument, it's easier to work with
instances of `BooleanArgument`, `StringArgument` and `IntegerArgument`.

```java
IntegerArgument speedArg = (IntegerArgument) jArgs.getArgument("speed");
```

### Retrieving from mandatory arguments

For mandatory arguments, it's better to check before hand if the value was set (we want to avoid NullPointerException's, right?):

```java
if (speedArg.isSet()) {
        speed = speedArg.getValue();    // There's no need for cast, we're already treating with integers
} else {
        logError();
}
```

In this last example we can also see how it's easier to work with the classes that inherit from `Argument`.



