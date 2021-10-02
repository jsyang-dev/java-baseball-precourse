package baseball.domain;

import org.assertj.core.api.AbstractIntegerAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("야구 숫자 세트 테스트")
public class BaseballNumbersTest {

    @Test
    @DisplayName("3개의 숫자로 야구 숫자 세트를 생성한다.")
    void of() {
        // when
        BaseballNumbers baseballNumbers = BaseballNumbers.of(1, 2, 3);

        // then
        assertThat(baseballNumbers.getBaseballNumbers()).containsExactly(
                new BaseballNumber(1),
                new BaseballNumber(2),
                new BaseballNumber(3)
        );
    }

    @Test
    @DisplayName("임의의 숫자로 이루어진 야구 숫자 세트를 생성한다.")
    void getRandomInstance() {
        // when
        BaseballNumbers baseballNumbers = BaseballNumbers.getRandomInstance();

        // then
        assertAll(
                () -> assertBaseballNumberValue(baseballNumbers.getBaseballNumbers()[0]),
                () -> assertBaseballNumberValue(baseballNumbers.getBaseballNumbers()[1]),
                () -> assertBaseballNumberValue(baseballNumbers.getBaseballNumbers()[2])
        );
    }

    private AbstractIntegerAssert<?> assertBaseballNumberValue(BaseballNumber baseballNumber) {
        return assertThat(baseballNumber.getValue())
                .isBetween(BaseballNumber.START_OF_POSSIBLE_RANGE, BaseballNumber.END_OF_POSSIBLE_RANGE);
    }
}