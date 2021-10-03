package baseball.domain;

import java.util.Objects;

public enum GameStatus {

    ONGOING("1"), ENDED("2"), ERROR("");

    private final String code;

    GameStatus(String code) {
        this.code = code;
    }

    public static GameStatus fromCode(String code) {
        for (GameStatus gameStatus : values()) {
            if (Objects.equals(code, gameStatus.code)) {
                return gameStatus;
            }
        }
        return ERROR;
    }
}
