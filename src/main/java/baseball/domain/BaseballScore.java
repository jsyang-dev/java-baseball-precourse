package baseball.domain;

public class BaseballScore {

    public static final String STRIKE = "스트라이크";
    public static final String BALL = "볼";
    public static final String NOTHING = "낫싱";

    private final int strikeCount;
    private final int ballCount;

    public BaseballScore(int strikeCount, int ballCount) {
        this.strikeCount = strikeCount;
        this.ballCount = ballCount;
    }

    public int getStrikeCount() {
        return strikeCount;
    }

    public int getBallCount() {
        return ballCount;
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

        return getStrikeText()+ " " + getBallText();
    }

    private String getStrikeText() {
        return strikeCount + STRIKE;
    }

    private String getBallText() {
        return ballCount + BALL;
    }
}
