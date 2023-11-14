package christmas.model.event;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class EventManager {
    public List<EventBenefitDetails> benefits;

    private List<EventBenefitDetails> getEventBenefitDetails(LocalDate visitDate, Map<String, Integer> menuItems) {
        checkWeekdayDiscountPolicy(visitDate, menuItems);
        checkWeekendDiscountPolicy(visitDate, menuItems);
        return benefits;
    }

    private void checkWeekdayDiscountPolicy(LocalDate visitDate, Map<String, Integer> menuItems) {
        WeekdayDiscountPolicy weekdayDiscountPolicy = new WeekdayDiscountPolicy();
        benefits.add(new EventBenefitDetails(
                DecemberEvents.WEEKDAY_DISCOUNT.getName(),
                weekdayDiscountPolicy.calculateBenefitAmount(visitDate, menuItems)));
    }

    private void checkWeekendDiscountPolicy(LocalDate visitDate, Map<String, Integer> menuItems) {
        WeekendDiscountPolicy weekendDiscountPolicy = new WeekendDiscountPolicy();
        benefits.add(new EventBenefitDetails(
                DecemberEvents.WEEKDAY_DISCOUNT.getName(),
                weekendDiscountPolicy.calculateBenefitAmount(visitDate, menuItems)));
    }
}
