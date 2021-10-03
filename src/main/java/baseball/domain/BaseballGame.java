package baseball.domain;

import nextstep.utils.Console;

import java.util.Objects;

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
            BaseballScore baseballScore = null;

            do {
                System.out.print("숫자를 입력해주세요 : ");
                String input = Console.readLine();
                baseballScore = calculateScore(baseballScore, input);
            } while (!Objects.requireNonNull(baseballScore).isWin());

            System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 끝");
            restart();
        }
    }

    private BaseballScore calculateScore(BaseballScore baseballScore, String input) {
        try {
            BaseballNumbers baseballNumbersOfPlayer = BaseballNumbers.from(input);
            baseballScore = baseballNumbersOfComputer.calculateScore(baseballNumbersOfPlayer);
            System.out.println(baseballScore);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        return baseballScore;
    }

    private void restart() {
        do {
            System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
            String input = Console.readLine();

            gameStatus = GameStatus.fromCode(input);

            if (gameStatus == GameStatus.ERROR) {
                System.out.println("[ERROR] 1 이나 2를 입력해야 합니다.");
            }
        } while (gameStatus == GameStatus.ERROR);

        if (gameStatus == GameStatus.ONGOING) {
            baseballNumbersOfComputer.generateNewNumber();
        }
    }
}
