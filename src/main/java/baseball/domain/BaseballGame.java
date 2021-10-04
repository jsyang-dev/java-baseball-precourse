package baseball.domain;

import static baseball.domain.ConsoleMessage.*;

public class BaseballGame {

    private final BaseballNumbers baseballNumbers;
    private GameStatus gameStatus = GameStatus.ONGOING;

    private BaseballGame(BaseballNumbers baseballNumbers) {
        this.baseballNumbers = baseballNumbers;
    }

    public static BaseballGame create() {
        return new BaseballGame(BaseballNumbers.createRandomInstance());
    }

    public BaseballNumbers getBaseballNumbers() {
        return baseballNumbers;
    }

    public void start() {
        while (gameStatus == GameStatus.ONGOING) {
            inputNumbersOfPlayer();
            ConsoleInOut.printMessage(BASEBALL_GAME_START_OUTPUT);
            restart();
        }
    }

    private void inputNumbersOfPlayer() {
        BaseballScore baseballScore = BaseballScore.getNothingInstance();
        do {
            String input = ConsoleInOut.input(BASEBALL_GAME_START_INPUT);
            baseballScore = calculateScore(baseballScore, input);
        } while (!baseballScore.isWin());
    }

    private BaseballScore calculateScore(BaseballScore baseballScore, String input) {
        try {
            BaseballNumbers baseballNumbersOfPlayer = BaseballNumbers.from(input);
            baseballScore = baseballNumbers.calculateScore(baseballNumbersOfPlayer);
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

        generateNewBaseballNumbers();
    }

    private void generateNewBaseballNumbers() {
        if (gameStatus == GameStatus.ONGOING) {
            baseballNumbers.generateNewBaseballNumbers();
        }
    }
}
