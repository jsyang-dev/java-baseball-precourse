package baseball.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("야구 게임 테스트")
public class BaseballGameTest {

    @Test
    @DisplayName("야구 게임을 생성한다.")
    void init() {
        // when
        BaseballGame baseballGame = BaseballGame.newInstance();

        // then
        assertAll(
                () -> assertThat(baseballGame.getBaseballNumbersOfComputer()).isNotNull(),
                () -> assertThat(baseballGame.getBaseballNumbersOfComputer().getBaseballNumbers()[0]).isNotNull(),
                () -> assertThat(baseballGame.getBaseballNumbersOfComputer().getBaseballNumbers()[1]).isNotNull(),
                () -> assertThat(baseballGame.getBaseballNumbersOfComputer().getBaseballNumbers()[2]).isNotNull()
        );
    }
}
