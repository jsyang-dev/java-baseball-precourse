package baseball.domain;

import nextstep.utils.Randoms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static baseball.domain.BaseballNumber.*;

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
        int number1 = generateRandomNumber();
        int number2 = generateRandomNumber(number1);
        int number3 = generateRandomNumber(number1, number2);
        return new BaseballNumbers(number1, number2, number3);
    }

    public BaseballNumber[] getBaseballNumbers() {
        return baseballNumbers;
    }

    public BaseballScore calculateScore(BaseballNumbers baseballNumbersOfPlayer) {
        int strikeCount = 0;
        int ballCount = 0;

        for (int i = START_OF_POSSIBLE_RANGE; i <= END_OF_POSSIBLE_RANGE; i++) {
            strikeCount += getAddStrikeCount(baseballNumbersOfPlayer, i);
            ballCount += getAddBallCount(baseballNumbersOfPlayer, i);
        }

        return new BaseballScore(strikeCount, ballCount);
    }

    public void generateNewNumber() {
        baseballNumbers[0] = new BaseballNumber(generateRandomNumber());
        baseballNumbers[1] = new BaseballNumber(generateRandomNumber(baseballNumbers[0].getValue()));
        baseballNumbers[2] = new BaseballNumber(
                generateRandomNumber(baseballNumbers[0].getValue(), baseballNumbers[1].getValue()));
    }

    private static void verifyValue(String value) {
        checkNumberRange(value);
        checkNumberDuplicated(value);
    }

    private static void checkNumberRange(String value) {
        String pattern = String.format("[%d-%d]{%d}", START_OF_POSSIBLE_RANGE, END_OF_POSSIBLE_RANGE, MAX_SIZE);
        if (!value.matches(pattern)) {
            throw new RuntimeException(String.format("[ERROR] %d부터 %d사이의 숫자 %d개를 입력해야 합니다.",
                    START_OF_POSSIBLE_RANGE, END_OF_POSSIBLE_RANGE, MAX_SIZE));
        }
    }

    private static void checkNumberDuplicated(String value) {
        Set<String> uniqueValues = new HashSet<>(Arrays.asList(value.split("")));
        if (uniqueValues.size() != MAX_SIZE) {
            throw new RuntimeException("[ERROR] 야구 숫자가 중복될 수 없습니다.");
        }
    }

    private static int generateRandomNumber(int... except) {
        List<Integer> exceptNumbers = makeExceptNumbers(except);
        return pickUniqueNumber(exceptNumbers);
    }

    private static List<Integer> makeExceptNumbers(int[] except) {
        List<Integer> exceptNumbers = new ArrayList<>(except.length);
        for (int exceptNumber : except) {
            exceptNumbers.add(exceptNumber);
        }

        return exceptNumbers;
    }

    private static int pickUniqueNumber(List<Integer> exceptNumbers) {
        int number;
        do {
            number = Randoms.pickNumberInRange(START_OF_POSSIBLE_RANGE, END_OF_POSSIBLE_RANGE);
        } while (exceptNumbers.contains(number));
        return number;
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

    private int findPosition(int baseballNumberValue) {
        for (int i = 0; i < MAX_SIZE; i++) {
            if (isSamePosition(baseballNumbers[i].getValue(), baseballNumberValue)) {
                return i;
            }
        }

        return NOT_FOUND;
    }

    private boolean isNotFound(int playerPosition, int computerPosition) {
        return isSamePosition(playerPosition, NOT_FOUND) || isSamePosition(computerPosition, NOT_FOUND);
    }

    private boolean isSamePosition(int playerPosition, int computerPosition) {
        return playerPosition == computerPosition;
    }
}
