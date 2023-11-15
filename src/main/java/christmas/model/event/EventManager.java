package christmas.model.event;

import christmas.model.DecemberEventBadge;
import christmas.model.menu.MenuItem;
import christmas.util.EventCalculator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EventManager {
    private static List<EventBenefitDetails> eventPlanBenefitResult = new ArrayList<>();
    private DecemberEventBadge decemberEventBadge;

    public void createEventBadge() {
        int totalBenefitAmount = EventCalculator.calculateTotalBenefitAmount(eventPlanBenefitResult);
        this.decemberEventBadge = EventCalculator.calculateEventBadge(totalBenefitAmount);
    }

    public boolean isAssignedBadge(DecemberEventBadge badge) {
        return decemberEventBadge.equals(badge);
    }

    public List<EventBenefitDetails> getEventPlanBenefitResult(
            int totalOrderAmountBeforeDiscount, LocalDate visitDate, Map<String, Integer> menuItems) {

        if (!isSatisfied(totalOrderAmountBeforeDiscount)) {
            for (DecemberEvents event : DecemberEvents.values()) {
                eventPlanBenefitResult.add(new EventBenefitDetails(event.getName(), 0));
            }
            return eventPlanBenefitResult;
        }
        return calculateEventBenefitDetails(totalOrderAmountBeforeDiscount, visitDate, menuItems);
    }

    public int getChampagneGiftedCount() {
        int count = 0;
        for (EventBenefitDetails details : eventPlanBenefitResult) {
            if (details.getName().equals(DecemberEvents.GIFT.getName())) {
                count = details.getBenefitAmount() / MenuItem.CHAMPAGNE.getPrice();
            }
        }
        return count;
    }

    public int getExpectedPaymentAfterDiscount(int totalOrderAmountBeforeDiscount) {
        return EventCalculator.calculateExpectedPaymentAfterDiscount(
                totalOrderAmountBeforeDiscount, eventPlanBenefitResult);
    }

    private List<EventBenefitDetails> calculateEventBenefitDetails(
            int totalOrderAmountBeforeDiscount, LocalDate visitDate, Map<String, Integer> menuItems) {
        eventPlanBenefitResult = new ArrayList<>();
        checkWeekdayDiscountPolicy(visitDate, menuItems);
        checkWeekendDiscountPolicy(visitDate, menuItems);
        checkSpecialDiscountPolicy(visitDate);
        checkChristmasDDayDiscountPolicy(visitDate);
        checkGiftPolicy(totalOrderAmountBeforeDiscount, menuItems);
        return eventPlanBenefitResult;
    }

    private boolean isSatisfied(int totalOrderAmountBeforeDiscount) {
        final int MIN_ORDER_AMOUNT = 10_000;
        return totalOrderAmountBeforeDiscount > MIN_ORDER_AMOUNT;
    }

    private void checkWeekdayDiscountPolicy(LocalDate visitDate, Map<String, Integer> menuItems) {
        WeekdayDiscountPolicy weekdayDiscountPolicy = new WeekdayDiscountPolicy();
        eventPlanBenefitResult.add(new EventBenefitDetails(
                DecemberEvents.WEEKDAY_DISCOUNT.getName(),
                weekdayDiscountPolicy.calculateBenefitAmount(visitDate, menuItems)));
    }

    private void checkWeekendDiscountPolicy(LocalDate visitDate, Map<String, Integer> menuItems) {
        WeekendDiscountPolicy weekendDiscountPolicy = new WeekendDiscountPolicy();
        eventPlanBenefitResult.add(new EventBenefitDetails(
                DecemberEvents.WEEKEND_DISCOUNT.getName(),
                weekendDiscountPolicy.calculateBenefitAmount(visitDate, menuItems)));
    }

    private void checkSpecialDiscountPolicy(LocalDate visitDate) {
        SpecialDiscountPolicy specialDiscountPolicy = new SpecialDiscountPolicy();
        eventPlanBenefitResult.add(new EventBenefitDetails(
                DecemberEvents.SPECIAL_DISCOUNT.getName(),
                specialDiscountPolicy.calculateBenefitAmount(visitDate)));
    }

    private void checkChristmasDDayDiscountPolicy(LocalDate visitDate) {
        ChristmasDDayDiscountPolicy christmasDDayDiscountPolicy = new ChristmasDDayDiscountPolicy();
        eventPlanBenefitResult.add(new EventBenefitDetails(
                DecemberEvents.CHRISTMAS_D_DAY.getName(),
                christmasDDayDiscountPolicy.calculateBenefitAmount(visitDate)));
    }

    private void checkGiftPolicy(int totalOrderAmountBeforeDiscount, Map<String, Integer> menuItems) {
        GiftPolicy giftPolicy = new GiftPolicy();
        if (giftPolicy.isSatisfied(totalOrderAmountBeforeDiscount)) {
            eventPlanBenefitResult.add(new EventBenefitDetails(
                    DecemberEvents.GIFT.getName(),
                    MenuItem.CHAMPAGNE.getPrice()));
        }
    }
}
