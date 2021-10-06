package baseball.domain;

import java.util.Objects;

import static baseball.domain.JudgmentType.*;

public class BaseballScore {

    private static BaseballScore NOTHING_INSTANCE;
    private static final int WIN_STRIKE_COUNT = 3;

    private final int strikeCount;
    private final int ballCount;

    public BaseballScore(int strikeCount, int ballCount) {
        this.strikeCount = strikeCount;
        this.ballCount = ballCount;
    }

    public static BaseballScore getNothingInstance() {
        if (Objects.isNull(NOTHING_INSTANCE)) {
            NOTHING_INSTANCE = new BaseballScore(0, 0);
        }
        return NOTHING_INSTANCE;
    }

    public int getStrikeCount() {
        return strikeCount;
    }

    public int getBallCount() {
        return ballCount;
    }

    public boolean isWin() {
        return strikeCount == WIN_STRIKE_COUNT;
    }

    @Override
    public String toString() {
        if (isNothing()) {
            return NOTHING.toString();
        }
        if (isOnlyStrike()) {
            return makeStrikeText();
        }
        if (isOnlyBall()) {
            return makeBallText();
        }
        return String.format("%s %s", makeStrikeText(), makeBallText());
    }

    private boolean isNothing() {
        return strikeCount <= 0 && ballCount <= 0;
    }

    private boolean isOnlyStrike() {
        return strikeCount > 0 && ballCount <= 0;
    }

    private boolean isOnlyBall() {
        return strikeCount <= 0 && ballCount > 0;
    }

    private String makeStrikeText() {
        return STRIKE.toString(strikeCount);
    }

    private String makeBallText() {
        return BALL.toString(ballCount);
    }
}
