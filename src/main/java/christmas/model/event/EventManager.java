package christmas.model.event;

import christmas.model.menu.MenuItem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EventManager {
    private static List<EventBenefitDetails> benefits;

    public List<EventBenefitDetails> getBenefits(
            int totalOrderAmountBeforeDiscount, LocalDate visitDate, Map<String, Integer> menuItems) {

        if (!isSatisfied(totalOrderAmountBeforeDiscount)) {
            List<EventBenefitDetails> unsatisfied = new ArrayList<>();
            for (DecemberEvents event : DecemberEvents.values()) {
                unsatisfied.add(new EventBenefitDetails(event.getName(), 0));
            }
            return unsatisfied;
        }
        return calculateEventBenefitDetails(totalOrderAmountBeforeDiscount, visitDate, menuItems);
    }

    private List<EventBenefitDetails> calculateEventBenefitDetails(
            int totalOrderAmountBeforeDiscount, LocalDate visitDate, Map<String, Integer> menuItems) {
        benefits = new ArrayList<>();
        checkWeekdayDiscountPolicy(visitDate, menuItems);
        checkWeekendDiscountPolicy(visitDate, menuItems);
        checkSpecialDiscountPolicy(visitDate);
        checkChristmasDDayDiscountPolicy(visitDate);
        checkGiftPolicy(totalOrderAmountBeforeDiscount, menuItems);
        return benefits;
    }

    private boolean isSatisfied(int totalOrderAmountBeforeDiscount) {
        final int MIN_ORDER_AMOUNT = 10_000;
        return totalOrderAmountBeforeDiscount > MIN_ORDER_AMOUNT;
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

    private void checkGiftPolicy(int totalOrderAmountBeforeDiscount, Map<String, Integer> menuItems) {
        GiftPolicy giftPolicy = new GiftPolicy();
        if (giftPolicy.isSatisfied(totalOrderAmountBeforeDiscount)) {
            benefits.add(new EventBenefitDetails(
                    DecemberEvents.GIFT.getName(),
                    MenuItem.CHAMPAGNE.getPrice()));
        }
    }
}
