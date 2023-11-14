package christmas.model.event;

import christmas.model.menu.MenuItem;
import christmas.util.DataConverter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WeekdayDiscount implements EventPolicyByVisitDate {
    private final List<DayOfWeek> satisfiedDaysOfWeek = new ArrayList<>(List.of(DayOfWeek.SUNDAY, DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY));


    public int calculateBenefitAmount(LocalDate visitDate, Map<String, Integer> menuItemsOrdered) {
        if (isWeekday(visitDate)) {
            int quantity = checkDessertOrderQuantity(menuItemsOrdered);
            if (quantity > 0) {
                return calculateTotalBenefitAmount(quantity);
            }
        }
        return 0;
    }

    private boolean isWeekday(LocalDate visitDate) {
        DayOfWeek visitDayOfWeek = DataConverter.convertToDayOfWeek(visitDate);
        return satisfiedDaysOfWeek.stream().anyMatch(day -> day.equals(visitDayOfWeek));
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

    private int calculateTotalBenefitAmount(int orderQuantity) {
        final int DISCOUNT_AMOUNT_PER_DESSERT_ORDER = 2_023;
        return orderQuantity * DISCOUNT_AMOUNT_PER_DESSERT_ORDER;
    }
}
