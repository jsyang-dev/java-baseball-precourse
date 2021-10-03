package baseball.domain;

import nextstep.utils.Console;

public class BaseballGame {

    private final BaseballNumbers baseballNumbersOfComputer;

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
        BaseballScore baseballScore;
        do {
            System.out.print("숫자를 입력해주세요 : ");
            String input = Console.readLine();
            BaseballNumbers baseballNumbersOfPlayer = BaseballNumbers.from(input);
            baseballScore = baseballNumbersOfComputer.calculateScore(baseballNumbersOfPlayer);
            System.out.println(baseballScore);
        } while (!baseballScore.isWin());
    }
}
