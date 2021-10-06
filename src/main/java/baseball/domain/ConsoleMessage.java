package baseball.domain;

public enum ConsoleMessage {

    BASEBALL_GAME_START_INPUT("숫자를 입력해주세요 : "),
    BASEBALL_GAME_START_OUTPUT("3개의 숫자를 모두 맞히셨습니다! 게임 끝"),
    BASEBALL_GAME_RESTART_INPUT("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요."),
    BASEBALL_GAME_RESTART_ERROR("1 이나 2를 입력해야 합니다."),

    BASEBALL_NUMBERS_RANGE_ERROR("%d부터 %d사이의 숫자 %d개를 입력해야 합니다."),
    BASEBALL_NUMBERS_DUPLICATED_ERROR("중복되지 않은 숫자를 입력해야 합니다."),

    BASEBALL_NUMBER_VERIFY_ERROR("%d부터 %d사이의 숫자를 입력해야 합니다.");

    private final String message;

    ConsoleMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
