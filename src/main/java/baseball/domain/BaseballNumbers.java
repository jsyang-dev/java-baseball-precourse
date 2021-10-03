package baseball.domain;

import nextstep.utils.Randoms;

import java.util.regex.Pattern;

public class BaseballNumbers {

    private static final int MAX_SIZE = 3;
    private static final int NOT_FOUND = -1;

    private final BaseballNumber[] baseballNumbers;

    private BaseballNumbers(int number1, int number2, int number3) {
        this.baseballNumbers = new BaseballNumber[MAX_SIZE];
        this.baseballNumbers[0] = new BaseballNumber(number1);
        this.baseballNumbers[1] = new BaseballNumber(number2);
        this.baseballNumbers[2] = new BaseballNumber(number3);
    }

    public static BaseballNumbers from(String value) {
        verifyValue(value);
        return new BaseballNumbers(
                Character.getNumericValue(value.charAt(0)),
                Character.getNumericValue(value.charAt(1)),
                Character.getNumericValue(value.charAt(2))
        );
    }

    public static BaseballNumbers getRandomInstance() {
        return new BaseballNumbers(getRandomNumber(), getRandomNumber(), getRandomNumber());
    }

    public BaseballNumber[] getBaseballNumbers() {
        return baseballNumbers;
    }

    public BaseballScore calculateScore(BaseballNumbers baseballNumbersOfPlayer) {
        int strikeCount = 0;
        int ballCount = 0;

        for (int i = BaseballNumber.START_OF_POSSIBLE_RANGE; i <= BaseballNumber.END_OF_POSSIBLE_RANGE; i++) {
            strikeCount += getAddStrikeCount(baseballNumbersOfPlayer, i);
            ballCount += getAddBallCount(baseballNumbersOfPlayer, i);
        }

        return new BaseballScore(strikeCount, ballCount);
    }

    private static void verifyValue(String value) {
        String pattern = String.format("[%d-%d]{%d}",
                BaseballNumber.START_OF_POSSIBLE_RANGE,
                BaseballNumber.END_OF_POSSIBLE_RANGE,
                MAX_SIZE);
        if (!Pattern.matches(pattern, value)) {
            throw new RuntimeException(String.format("[ERROR] %d부터 %d사이의 숫자 %d개를 입력해야 합니다.",
                    BaseballNumber.START_OF_POSSIBLE_RANGE, BaseballNumber.END_OF_POSSIBLE_RANGE, MAX_SIZE));
        }
    }

    private static int getRandomNumber() {
        return Randoms.pickNumberInRange(BaseballNumber.START_OF_POSSIBLE_RANGE, BaseballNumber.END_OF_POSSIBLE_RANGE);
    }

    private int getAddStrikeCount(BaseballNumbers baseballNumbersOfPlayer, int i) {
        int playerPosition = baseballNumbersOfPlayer.findPosition(i);
        int computerPosition = findPosition(i);

        if (isNotFound(playerPosition, computerPosition)) {
            return 0;
        }

        if (isSamePosition(playerPosition, computerPosition)) {
            return 1;
        }

        return 0;
    }

    private int getAddBallCount(BaseballNumbers baseballNumbersOfPlayer, int i) {
        int playerPosition = baseballNumbersOfPlayer.findPosition(i);
        int computerPosition = findPosition(i);

        if (isNotFound(playerPosition, computerPosition)) {
            return 0;
        }

        if (!isSamePosition(playerPosition, computerPosition)) {
            return 1;
        }

        return 0;
    }

    private boolean isNotFound(int playerPosition, int computerPosition) {
        return isSamePosition(playerPosition, NOT_FOUND) || isSamePosition(computerPosition, NOT_FOUND);
    }

    private boolean isSamePosition(int playerPosition, int computerPosition) {
        return playerPosition == computerPosition;
    }

    private int findPosition(int baseballNumberValue) {
        for (int i = 0; i < MAX_SIZE; i++) {
            if (isSamePosition(baseballNumbers[i].getValue(), baseballNumberValue)) {
                return i;
            }
        }

        return NOT_FOUND;
    }
}
