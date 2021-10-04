package baseball.domain;

import java.util.Objects;

public class BaseballScore {

    public static final String STRIKE = "스트라이크";
    public static final String BALL = "볼";
    public static final String NOTHING = "낫싱";
    private static final int WIN_STRIKE_COUNT = 3;

    private static BaseballScore NOTHING_INSTANCE;

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
        if (strikeCount <= 0 && ballCount <= 0) {
            return NOTHING;
        }

        if (strikeCount > 0 && ballCount <= 0) {
            return getStrikeText();
        }

        if (strikeCount <= 0) {
            return getBallText();
        }

        return String.format("%s %s", getStrikeText(), getBallText());
    }

    private String getStrikeText() {
        return strikeCount + STRIKE;
    }

    private String getBallText() {
        return ballCount + BALL;
    }
}
