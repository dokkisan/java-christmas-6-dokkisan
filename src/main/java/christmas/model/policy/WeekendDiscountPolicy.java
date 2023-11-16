package christmas.model.policy;

import christmas.model.menu.MenuItem;
import christmas.model.menu.MenuManager;
import christmas.util.DateConverter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WeekendDiscountPolicy {
    private final int BENEFIT_AMOUNT_UNIT = 2_023;
    private final List<DayOfWeek> WEEKEND = new ArrayList<>(List.of(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY));

    public int calculateBenefitAmount(LocalDate visitDate, Map<String, Integer> menuItemsOrdered) {
        int benefitAmountByVisitOnWeekend = calculateBenefitAmountByVisitOnWeekend(visitDate);
        if (benefitAmountByVisitOnWeekend > 0) {
            return benefitAmountByVisitOnWeekend + calculateDiscountAmountByMainDishOrder(menuItemsOrdered);
        }
        return benefitAmountByVisitOnWeekend;
    }

    private int calculateBenefitAmountByVisitOnWeekend(LocalDate visitDate) {
        if (WEEKEND.stream().anyMatch(day -> day.equals(DateConverter.convertToDayOfWeek(visitDate)))) {
            return BENEFIT_AMOUNT_UNIT;
        }
        return 0;
    }

    private int calculateDiscountAmountByMainDishOrder(Map<String, Integer> orderedMenuItems) {
        return calculateBenefitAmountBy(checkMainDishOrderQuantity(orderedMenuItems));
    }

    private int checkMainDishOrderQuantity(Map<String, Integer> orderedMenuItems) {
        List<MenuItem> mainDishes = MenuManager.getMainDishes();
        int quantity = 0;
        for (Map.Entry<String, Integer> item : orderedMenuItems.entrySet()) {
            if (mainDishes.stream()
                    .anyMatch(mainDishItem -> mainDishItem.getName().equals(item.getKey()))) {
                ++quantity;
            }
        }
        return quantity;
    }

    private int calculateBenefitAmountBy(int orderQuantity) {
        return orderQuantity * BENEFIT_AMOUNT_UNIT;
    }
}
