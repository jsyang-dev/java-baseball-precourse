package baseball.ui;

import baseball.domain.ConsoleMessage;
import nextstep.utils.Console;

public class ConsoleView {

    public static final String ERROR_PREFIX = "[ERROR]";

    public static String input(ConsoleMessage consoleMessage) {
        System.out.print(consoleMessage.getMessage());
        return Console.readLine();
    }

    public static String inputWithNewLine(ConsoleMessage consoleMessage) {
        System.out.println(consoleMessage.getMessage());
        return Console.readLine();
    }

    public static void printMessage(ConsoleMessage consoleMessage) {
        System.out.println(consoleMessage.getMessage());
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void printErrorMessage(ConsoleMessage consoleMessage) {
        System.out.printf("%s %s%n", ERROR_PREFIX, consoleMessage.getMessage());
    }

    public static void printErrorMessage(Exception e) {
        System.out.printf("%s %s%n", ERROR_PREFIX, e.getMessage());
    }
}
