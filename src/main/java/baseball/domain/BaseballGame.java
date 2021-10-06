package baseball.domain;

import baseball.ui.ConsoleView;

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
            ConsoleView.printMessage(BASEBALL_GAME_START_OUTPUT);
            restart();
        }
    }

    private void inputNumbersOfPlayer() {
        BaseballScore baseballScore = BaseballScore.getNothingInstance();
        do {
            String input = ConsoleView.input(BASEBALL_GAME_START_INPUT);
            baseballScore = calculateScore(baseballScore, input);
        } while (!baseballScore.isWin());
    }

    private BaseballScore calculateScore(BaseballScore baseballScore, String input) {
        try {
            BaseballNumbers baseballNumbersOfPlayer = BaseballNumbers.from(input);
            baseballScore = baseballNumbers.calculateScore(baseballNumbersOfPlayer);
            ConsoleView.printMessage(baseballScore.toString());
        } catch (CustomIllegalArgumentException e) {
            ConsoleView.printErrorMessage(e);
        }
        return baseballScore;
    }

    private void restart() {
        do {
            String input = ConsoleView.inputWithNewLine(BASEBALL_GAME_RESTART_INPUT);
            gameStatus = GameStatus.fromCode(input);
            printErrorMessageByGameStatus();
        } while (gameStatus == GameStatus.ERROR);

        generateNewBaseballNumbers();
    }

    private void printErrorMessageByGameStatus() {
        if (gameStatus == GameStatus.ERROR) {
            ConsoleView.printErrorMessage(BASEBALL_GAME_RESTART_ERROR);
        }
    }

    private void generateNewBaseballNumbers() {
        if (gameStatus == GameStatus.ONGOING) {
            baseballNumbers.generateNewBaseballNumbers();
        }
    }
}
