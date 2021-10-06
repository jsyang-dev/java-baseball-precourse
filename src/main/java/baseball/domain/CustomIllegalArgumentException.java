package baseball.domain;

public class CustomIllegalArgumentException extends IllegalArgumentException {

    public CustomIllegalArgumentException(ConsoleMessage consoleMessage) {
        super(consoleMessage.getMessage());
    }

    public CustomIllegalArgumentException(ConsoleMessage consoleMessage, Object... args) {
        super(String.format(consoleMessage.getMessage(), args));
    }
}
