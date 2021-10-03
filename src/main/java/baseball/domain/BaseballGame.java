package baseball.domain;

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
}
