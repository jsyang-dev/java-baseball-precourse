package baseball.domain;

public enum JudgmentType {

    STRIKE("스트라이크"), BALL("볼"), NOTHING("낫싱");

    private final String name;

    JudgmentType(String name) {
        this.name = name;
    }

    public String toString(int count) {
        return count + name;
    }

    @Override
    public String toString() {
        return name;
    }
}
