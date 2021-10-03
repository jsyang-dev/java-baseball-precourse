package baseball.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

@DisplayName("야구 점수 테스트")
class BaseballScoreTest {

    @ParameterizedTest(name = "{displayName} - {arguments}")
    @CsvSource(value = {"1:1:false", "3:0:true", "0:3:false", "0:0:false"}, delimiter = ':')
    @DisplayName("승리 여부를 조회한다.")
    void isWin(int strikeCount, int ballCount, boolean expected) {
        // given
        BaseballScore baseballScore = new BaseballScore(strikeCount, ballCount);

        // when
        boolean win = baseballScore.isWin();

        // then
        assertThat(win).isEqualTo(expected);
    }

    @ParameterizedTest(name = "{displayName} - {arguments}")
    @CsvSource(value = {
            "1:1:1" + BaseballScore.STRIKE + " " + "1" + BaseballScore.BALL,
            "3:0:3" + BaseballScore.STRIKE,
            "0:3:3" + BaseballScore.BALL,
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
