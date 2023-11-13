package christmas.model;

public enum ErrorMessage {
    INVALID_DAYS_IN_DECEMBER("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_MENU_FORMAT("유효하지 않은 주문입니다. 다시 입력해 주세요.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        final String PREFIX = "[ERROR] ";
        return PREFIX + message;
    }
}
