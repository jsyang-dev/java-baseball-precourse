package baseball.domain;

import nextstep.utils.Console;

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
            BaseballScore baseballScore;
            do {
                System.out.print("숫자를 입력해주세요 : ");
                String input = Console.readLine();
                BaseballNumbers baseballNumbersOfPlayer = BaseballNumbers.from(input);
                baseballScore = baseballNumbersOfComputer.calculateScore(baseballNumbersOfPlayer);
                System.out.println(baseballScore);
            } while (!baseballScore.isWin());

            System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 끝");

            restart();
        }
    }

    private void restart() {
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        String input = Console.readLine();

        gameStatus = GameStatus.fromCode(input);
        baseballNumbersOfComputer.generateNewNumber();
    }
}
