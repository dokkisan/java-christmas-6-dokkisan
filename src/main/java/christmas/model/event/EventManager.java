package christmas.model.event;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class EventManager {
    public List<EventBenefitDetails> benefits;

    private List<EventBenefitDetails> getEventBenefitDetails(LocalDate visitDate, Map<String, Integer> menuItems) {
        checkWeekdayDiscount(visitDate, menuItems);
        return benefits;
    }

    private void checkWeekdayDiscount(LocalDate visitDate, Map<String, Integer> menuItems) {
        WeekdayDiscount weekdayDiscount = new WeekdayDiscount();
        benefits.add(new EventBenefitDetails(
                DecemberEvents.WEEKDAY_DISCOUNT.getName(),
                weekdayDiscount.calculateBenefitAmount(visitDate, menuItems)));
    }
}
