package christmas.model.policy;

import christmas.model.menu.MenuItem;
import christmas.util.DateConverter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WeekdayDiscountPolicy {
    private final int BENEFIT_AMOUNT_UNIT = 2_023;
    private final List<DayOfWeek> WEEKDAYS = new ArrayList<>(List.of(DayOfWeek.SUNDAY, DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY));

    public int calculateBenefitAmount(LocalDate visitDate, Map<String, Integer> menuItemsOrdered) {
        int benefitAmount = calculateBenefitAmountByVisitOnWeekdays(visitDate);
        if (benefitAmount > 0) {
            return benefitAmount + calculateDiscountAmountByDessertOrder(menuItemsOrdered);
        }
        return benefitAmount;
    }

    private int calculateBenefitAmountByVisitOnWeekdays(LocalDate visitDate) {
        if (WEEKDAYS.stream().anyMatch(day -> day.equals(DateConverter.convertToDayOfWeek(visitDate)))) {
            return BENEFIT_AMOUNT_UNIT;
        }
        return 0;
    }

    private int calculateDiscountAmountByDessertOrder(Map<String, Integer> orderedMenuItems) {
        return calculateBenefitAmountBy(checkDessertOrderQuantity(orderedMenuItems));
    }

    private int checkDessertOrderQuantity(Map<String, Integer> orderedMenuItems) {
        int count = 0;
        if (orderedMenuItems.containsKey(MenuItem.CHOCOLATE_CAKE.getName())) {
            ++count;
        }
        if (orderedMenuItems.containsKey(MenuItem.ICE_CREAM.getName())) {
            ++count;
        }
        return count;
    }

    private int calculateBenefitAmountBy(int orderQuantity) {
        return orderQuantity * BENEFIT_AMOUNT_UNIT;
    }
}
