package baseball.domain;

import nextstep.utils.Console;

import static baseball.domain.ConsoleMessage.*;

public class ConsoleInOut {

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

    public static void printErrorMessageByGameStatus(GameStatus gameStatus) {
        if (gameStatus == GameStatus.ERROR) {
            printErrorMessage(BASEBALL_GAME_RESTART_ERROR);
        }
    }
}
