package baseball.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("야구 게임 도메인 테스트")
public class BaseballGameTest {

    @Test
    @DisplayName("야구 게임을 생성한다.")
    void create() {
        // when
        BaseballGame baseballGame = BaseballGame.create();

        // then
        assertAll(
                () -> assertThat(baseballGame.getBaseballNumbers()).isNotNull(),
                () -> assertThat(baseballGame.getBaseballNumbers().getBaseballNumbers()[0]).isNotNull(),
                () -> assertThat(baseballGame.getBaseballNumbers().getBaseballNumbers()[1]).isNotNull(),
                () -> assertThat(baseballGame.getBaseballNumbers().getBaseballNumbers()[2]).isNotNull()
        );
    }
}
