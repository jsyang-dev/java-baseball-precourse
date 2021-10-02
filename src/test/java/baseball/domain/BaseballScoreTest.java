package baseball.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

@DisplayName("야구 점수 테스트")
class BaseballScoreTest {

    @ParameterizedTest(name = "{displayName} - {arguments}")
    @CsvSource(value = {
            "1:1:1" + BaseballScore.STRIKE + " " + "1" + BaseballScore.BALL,
            "1:0:1" + BaseballScore.STRIKE,
            "0:2:2" + BaseballScore.BALL,
            "0:0:" + BaseballScore.NOTHING
    }, delimiter = ':')
    @DisplayName("야구 점수를 출력한다.")
    void toString(int strikeCount, int ballCount, String expected) {
        // given
        BaseballScore baseballScore = new BaseballScore(strikeCount, ballCount);

        // when
        String result = baseballScore.toString();

        // then
        assertThat(result).isEqualTo(expected);
    }
}
