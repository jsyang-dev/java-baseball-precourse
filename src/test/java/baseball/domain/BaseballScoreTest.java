package baseball.domain;

import baseball.CustomParameterizedTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

@DisplayName("야구 점수 도메인 테스트")
class BaseballScoreTest {

    @CustomParameterizedTest
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

    @CustomParameterizedTest
    @CsvSource(value = {"1:1:1스트라이크 1볼", "3:0:3스트라이크", "0:3:3볼", "0:0:낫싱"}, delimiter = ':')
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
