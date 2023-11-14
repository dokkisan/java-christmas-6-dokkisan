package christmas.model.event;

public enum DecemberEvents {
    CHRISTMAS_D_DAY("<크리스마스 디데이 할인>"),
    WEEKDAY_DISCOUNT("<평일 할인>"),
    WEEKEND_DISCOUNT("<주말 할인>"),
    SPECIAL_DISCOUNT("<특별 할인>"),
    GIFT("<증정 이벤트>");

    private final String name;

    DecemberEvents(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
