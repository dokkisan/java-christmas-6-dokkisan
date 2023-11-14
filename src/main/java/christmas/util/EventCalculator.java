package christmas.util;

import christmas.model.menu.MenuItem;

import java.util.Arrays;
import java.util.Map;

public class EventCalculator {

    public static int calculateTotalOrderAmountBeforeDiscount(Map<String, Integer> orderedMenuItems) {
        int totalOrderAmountBeforeDiscount = 0;
        int menuPrice;

        for (Map.Entry<String, Integer> orderedItem : orderedMenuItems.entrySet()) {
            menuPrice = Arrays.stream(MenuItem.values())
                    .filter(menuItem -> menuItem.getName().equals(orderedItem.getKey()))
                    .findFirst()
                    .map(menuItem -> menuItem.getPrice() * orderedItem.getValue())
                    .orElse(0);
            totalOrderAmountBeforeDiscount += menuPrice;
        }
        return totalOrderAmountBeforeDiscount;
    }
}
