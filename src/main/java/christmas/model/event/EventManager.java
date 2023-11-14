package christmas.model.event;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class EventManager {
    public List<EventBenefitDetails> benefits;

    private List<EventBenefitDetails> getEventBenefitDetails(LocalDate visitDate, Map<String, Integer> menuItems) {
        checkWeekdayDiscountPolicy(visitDate, menuItems);
        checkWeekendDiscountPolicy(visitDate, menuItems);
        checkSpecialDiscountPolicy(visitDate);
        checkChristmasDDayDiscountPolicy(visitDate);
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
                DecemberEvents.WEEKEND_DISCOUNT.getName(),
                weekendDiscountPolicy.calculateBenefitAmount(visitDate, menuItems)));
    }

    private void checkSpecialDiscountPolicy(LocalDate visitDate) {
        SpecialDiscountPolicy specialDiscountPolicy = new SpecialDiscountPolicy();
        benefits.add(new EventBenefitDetails(
                DecemberEvents.SPECIAL_DISCOUNT.getName(),
                specialDiscountPolicy.calculateBenefitAmount(visitDate)));
    }

    private void checkChristmasDDayDiscountPolicy(LocalDate visitDate) {
        ChristmasDDayDiscountPolicy christmasDDayDiscountPolicy = new ChristmasDDayDiscountPolicy();
        benefits.add(new EventBenefitDetails(
                DecemberEvents.CHRISTMAS_D_DAY.getName(),
                christmasDDayDiscountPolicy.calculateBenefitAmount(visitDate)));
    }
}
