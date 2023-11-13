package christmas.model;

public enum ErrorMessage {
    INVALID_NUMBER_FORMAT("숫자만 입력해 주세요. (문자, 기호, 공백은 입력 불가합니다.)"),
    INVALID_DAYS_IN_DECEMBER("이벤트 혜택 기간은 12월 1일~12월 31일까지 입니다. 이벤트 기간 내 일자를 입력해 주세요.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        final String PREFIX = "[ERROR] ";
        return PREFIX + message;
    }
}
