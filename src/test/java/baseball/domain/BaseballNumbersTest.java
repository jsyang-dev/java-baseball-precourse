package baseball.domain;

import org.assertj.core.api.AbstractIntegerAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("야구 숫자 세트 테스트")
public class BaseballNumbersTest {

    @Test
    @DisplayName("3개의 숫자로 야구 숫자 세트를 생성한다.")
    void from() {
        // when
        BaseballNumbers baseballNumbers = BaseballNumbers.from("123");

        // then
        assertThat(baseballNumbers.getBaseballNumbers()).containsExactly(
                new BaseballNumber(1),
                new BaseballNumber(2),
                new BaseballNumber(3)
        );
    }

    @ParameterizedTest(name = "{displayName} - {arguments}")
    @CsvSource(value = {"023", "12", "1234", "12a"}, delimiter = ':')
    @DisplayName("잘못된 문자열로 숫자로 야구 숫자 세트를 생성하면 예외가 발생한다.")
    void fromException1(String input) {
        // when & then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> BaseballNumbers.from(input))
                .withMessageMatching("\\d+부터 \\d+사이의 숫자 \\d+개를 입력해야 합니다.");

    }

    @ParameterizedTest(name = "{displayName} - {arguments}")
    @ValueSource(strings = {"112", "233", "343", "555"})
    @DisplayName("중복된 숫자로 숫자로 야구 숫자 세트를 생성하면 예외가 발생한다.")
    void fromException2(String input) {
        // when & then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> BaseballNumbers.from(input))
                .withMessageMatching("야구 숫자가 중복될 수 없습니다.");

    }

    @Test
    @DisplayName("임의의 숫자로 이루어진 야구 숫자 세트를 생성한다.")
    void createRandomInstance() {
        // when
        BaseballNumbers baseballNumbers = BaseballNumbers.createRandomInstance();

        // then
        assertAll(
                () -> assertBaseballNumberValue(baseballNumbers.getBaseballNumbers()[0]),
                () -> assertBaseballNumberValue(baseballNumbers.getBaseballNumbers()[1]),
                () -> assertBaseballNumberValue(baseballNumbers.getBaseballNumbers()[2])
        );
    }

    @ParameterizedTest(name = "{displayName} - {arguments}")
    @CsvSource(value = {"425:123:1:0", "425:456:1:1", "425:567:0:1", "425:789:0:0"}, delimiter = ':')
    @DisplayName("야구 점수를 계산한다.")
    void calculateScore(String computer, String player, int expectedStrikeCount, int expectedBallCount) {
        // given
        BaseballNumbers baseballNumbersOfComputer = BaseballNumbers.from(computer);
        BaseballNumbers baseballNumbersOfPlayer = BaseballNumbers.from(player);

        // when
        BaseballScore baseballScore = baseballNumbersOfComputer.calculateScore(baseballNumbersOfPlayer);

        // then
        assertAll(
                () -> assertThat(baseballScore.getStrikeCount()).isEqualTo(expectedStrikeCount),
                () -> assertThat(baseballScore.getBallCount()).isEqualTo(expectedBallCount)
        );
    }

    private AbstractIntegerAssert<?> assertBaseballNumberValue(BaseballNumber baseballNumber) {
        return assertThat(baseballNumber.getValue())
                .isBetween(BaseballNumber.START_OF_POSSIBLE_RANGE, BaseballNumber.END_OF_POSSIBLE_RANGE);
    }
}
