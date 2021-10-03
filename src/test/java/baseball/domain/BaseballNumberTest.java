package baseball.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

@DisplayName("야구 숫자 테스트")
public class BaseballNumberTest {

    @ParameterizedTest(name = "{displayName} - {arguments}")
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    @DisplayName("야구 숫자를 생성을 성공한다.")
    void init(int input) {
        // when
        BaseballNumber baseballNumber = new BaseballNumber(input);

        // then
        assertThat(baseballNumber.getValue()).isEqualTo(input);
    }

    @ParameterizedTest(name = "{displayName} - {arguments}")
    @ValueSource(ints = {0, 10})
    @DisplayName("야구 숫자를 생성을 실패한다.")
    void initException(int input) {
        // when & then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new BaseballNumber(input))
                .withMessageMatching("\\[ERROR] 야구 숫자는 \\d+부터 \\d+사이의 숫자여야 합니다.");
    }
}
