package christmas;

public enum OperationMessage {
    GREETING_AND_INTRODUCE("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    INPUT_EXPECTED_VISIT_DATE("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    INPUT_EXPECTED_MENU_ITEMS("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    EVENT_BENEFITS_PREVIEW("12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    ORDER_MENU("<주문 메뉴>"),
    TOTAL_ORDER_AMOUNT_BEFORE_DISCOUNT("<할인 전 총주문 금액>"),
    GIFT_MENU("<증정 메뉴>"),
    BENEFITS_DETAILS("<혜택 내역>"),
    TOTAL_BENEFITS_AMOUNT("<총혜택 금액>"),
    TOTAL_AMOUNT_DUE_AFTER_DISCOUNT("<할인 후 예상 결제 금액>"),
    DECEMBER_EVENT_BADGE("<12월 이벤트 배지>");

    private final String message;

    OperationMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
