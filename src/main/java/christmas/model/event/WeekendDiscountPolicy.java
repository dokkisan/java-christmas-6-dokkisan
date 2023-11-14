package christmas.model.event;

import christmas.model.menu.MenuItem;
import christmas.model.menu.MenuManager;
import christmas.util.DataConverter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WeekendDiscountPolicy {
    private final List<DayOfWeek> WEEKEND = new ArrayList<>(List.of(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY));

    public int calculateBenefitAmount(LocalDate visitDate, Map<String, Integer> menuItemsOrdered) {
        if (isWeekend(visitDate)) {
            int quantity = checkMainDishOrderQuantity(menuItemsOrdered);
            if (quantity > 0) {
                return calculateTotalBenefitAmount(quantity);
            }
        }
        return 0;
    }

    private boolean isWeekend(LocalDate visitDate) {
        return WEEKEND.stream().anyMatch(day -> day.equals(DataConverter.convertToDayOfWeek(visitDate)));
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

    private int calculateTotalBenefitAmount(int orderQuantity) {
        final int DISCOUNT_AMOUNT_PER_MAIN_DISH_ORDER = 2_023;
        return orderQuantity * DISCOUNT_AMOUNT_PER_MAIN_DISH_ORDER;
    }
}
