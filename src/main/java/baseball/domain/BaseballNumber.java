package baseball.domain;

public class BaseballNumber {

    public static final int START_OF_POSSIBLE_RANGE = 1;
    public static final int END_OF_POSSIBLE_RANGE = 9;
    private final int value;

    public BaseballNumber(int value) {
        verifyValue(value);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    private void verifyValue(int value) {
        if (value < START_OF_POSSIBLE_RANGE || value > END_OF_POSSIBLE_RANGE) {
            throw new RuntimeException(String.format(
                    "[ERROR] 야구 숫자는 %d부터 %d사이의 숫자여야 합니다.", START_OF_POSSIBLE_RANGE, END_OF_POSSIBLE_RANGE));
        }
    }
}
