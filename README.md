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

As said, now you need to create the arguments using a *key*. This time the *key* will be the word "string".
```java
jArgs.createArgument("string", "hello world!");
```

This means that an argument called "string" is created and has the default value "hello world!". This default value is returned 
when requesting for an argument that hasn't been specified from command line (user).
It can be *null*, making the parameter mandatory.

This *key* is transparent for the user. In order for him to interact, you need to create the argument's tokens.


## Adding the *tokens*
After creating the argument, it is needed to add the **tokens**, which are the words the user will use to pass the values to 
the program. This *tokens* can be either **String**, **int** or **boolean**; meaning these three are the type of argument accepted.
This can be done either on creation or afterwards, as shown:

```java
jArgs.createArgument("path", ".")
        .addToken("--path")
        .addToken("--prev-dir", "..");
```

In the lines above the argument "path" has been created with the default value ".". Later, we add the following *tokens*:
* `--path`: This argument doesn't have a default value so it requires a second argument when calling the program used, 
e.g.: `./foo --path /home/foo`.
* `--prev-dir`: This other argument has an specified default value, so when it appears on the command line, 
the argument "path" takes the written value. It is used as: `./foo --prev-dir`.

As said, we could also have *boolean* arguments such as:

```java
jArgs.createArgument("debug_mode", false)
        .addToken("-d", true)
        .addToken("--debug", true)
        .addToken("--no-debug", false);
```

In this case, if none of the previous tokens is written as an argument, the value of argument "debug_mode" is *false*.
If the tokens "-d" or "--debug" are written, "debug_mode" is *true*.

It can also be done with *integers* and without an argument default value, making this parameter **mandatory** for the user:
```java
jArgs.createArgument("speed", null)
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
This call may throw **exceptions** (see JavaDoc for details), which all inherit from the abstract class `ArgumentException`.

## Retrieving the values

After parsing the program arguments, we can now retrieve the desired arguments:
