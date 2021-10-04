package baseball.ui;

import nextstep.utils.Console;

public class ConsoleView {

    public static final String ERROR_PREFIX = "[ERROR]";

    public static String input(String message) {
        System.out.print(message);
        return Console.readLine();
    }

    public static String inputWithNewLine(String message) {
        System.out.println(message);
        return Console.readLine();
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void printErrorMessage(String message) {
        System.out.printf("%s %s%n", ERROR_PREFIX, message);
    }
}
