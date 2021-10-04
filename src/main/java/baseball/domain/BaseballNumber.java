package baseball.domain;

import java.util.Objects;

import static baseball.domain.ConsoleMessage.*;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseballNumber that = (BaseballNumber) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    private void verifyValue(int value) {
        if (isImpossibleRange(value)) {
            throw new IllegalArgumentException(String.format(
                    BASEBALL_NUMBER_VERIFY_ERROR, START_OF_POSSIBLE_RANGE, END_OF_POSSIBLE_RANGE));
        }
    }

    private boolean isImpossibleRange(int value) {
        return value < START_OF_POSSIBLE_RANGE || value > END_OF_POSSIBLE_RANGE;
    }
}
