package baseball.domain;

import baseball.CustomParameterizedTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.provider.ValueSource;

import static baseball.domain.ConsoleMessage.*;
import static org.assertj.core.api.Assertions.*;

@DisplayName("야구 숫자 도메인 테스트")
public class BaseballNumberTest {

    @CustomParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    @DisplayName("야구 숫자를 생성을 성공한다.")
    void init(int input) {
        // when
        BaseballNumber baseballNumber = new BaseballNumber(input);

        // then
        assertThat(baseballNumber.getValue()).isEqualTo(input);
    }

    @CustomParameterizedTest
    @ValueSource(ints = {0, 10})
    @DisplayName("야구 숫자를 생성을 실패한다.")
    void initException(int input) {
        // when & then
        assertThatExceptionOfType(CustomIllegalArgumentException.class)
                .isThrownBy(() -> new BaseballNumber(input))
                .withMessageMatching(BASEBALL_NUMBER_VERIFY_ERROR.getMessage().replace("%d", "\\d+"));
    }
}
