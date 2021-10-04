package baseball.domain;

import static baseball.domain.ConsoleMessage.*;

public class BaseballGame {

    private final BaseballNumbers baseballNumbersOfComputer;
    private GameStatus gameStatus = GameStatus.ONGOING;

    private BaseballGame(BaseballNumbers baseballNumbersOfComputer) {
        this.baseballNumbersOfComputer = baseballNumbersOfComputer;
    }

    public static BaseballGame newInstance() {
        return new BaseballGame(BaseballNumbers.getRandomInstance());
    }

    public BaseballNumbers getBaseballNumbersOfComputer() {
        return baseballNumbersOfComputer;
    }

    public void start() {
        while (gameStatus == GameStatus.ONGOING) {
            BaseballScore baseballScore = BaseballScore.getNothingInstance();

            do {
                String input = ConsoleInOut.input(BASEBALL_GAME_START_INPUT);
                baseballScore = calculateScore(baseballScore, input);
            } while (!baseballScore.isWin());

            ConsoleInOut.printMessage(BASEBALL_GAME_START_OUTPUT);
            restart();
        }
    }

    private BaseballScore calculateScore(BaseballScore baseballScore, String input) {
        try {
            BaseballNumbers baseballNumbersOfPlayer = BaseballNumbers.from(input);
            baseballScore = baseballNumbersOfComputer.calculateScore(baseballNumbersOfPlayer);
            ConsoleInOut.printMessage(baseballScore.toString());
        } catch (IllegalArgumentException e) {
            ConsoleInOut.printErrorMessage(e.getMessage());
        }

        return baseballScore;
    }

    private void restart() {
        do {
            String input = ConsoleInOut.inputWithNewLine(BASEBALL_GAME_RESTART_INPUT);
            gameStatus = GameStatus.fromCode(input);
            ConsoleInOut.printErrorMessageByGameStatus(gameStatus);
        } while (gameStatus == GameStatus.ERROR);

        if (gameStatus == GameStatus.ONGOING) {
            baseballNumbersOfComputer.generateNewNumber();
        }
    }
}
