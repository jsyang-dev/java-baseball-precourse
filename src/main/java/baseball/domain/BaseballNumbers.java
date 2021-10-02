package baseball.domain;

import nextstep.utils.Randoms;

public class BaseballNumbers {

    private final BaseballNumber[] baseballNumbers;

    private BaseballNumbers(int number1, int number2, int number3) {
        this.baseballNumbers = new BaseballNumber[3];
        this.baseballNumbers[0] = new BaseballNumber(number1);
        this.baseballNumbers[1] = new BaseballNumber(number2);
        this.baseballNumbers[2] = new BaseballNumber(number3);
    }

    public static BaseballNumbers of(int number1, int number2, int number3) {
        return new BaseballNumbers(number1, number2, number3);
    }

    public static BaseballNumbers getRandomInstance() {
        return new BaseballNumbers(getRandomNumber(), getRandomNumber(), getRandomNumber());
    }

    public BaseballNumber[] getBaseballNumbers() {
        return baseballNumbers;
    }

    private static int getRandomNumber() {
        return Randoms.pickNumberInRange(BaseballNumber.START_OF_POSSIBLE_RANGE, BaseballNumber.END_OF_POSSIBLE_RANGE);
    }
}
