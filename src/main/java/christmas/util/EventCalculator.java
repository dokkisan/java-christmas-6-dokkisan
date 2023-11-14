package christmas.util;

import christmas.model.menu.MenuItem;

import java.util.Map;

public class EventCalculator {

    public static int calculateTotalOrderAmountBeforeDiscount(Map<String, Integer> menuItems) {
        int totalOrderAmountBeforeDiscount = 0;
        for (Map.Entry<String, Integer> item : menuItems.entrySet()) {
            totalOrderAmountBeforeDiscount += MenuItem.valueOf(item.getKey()).getPrice() * item.getValue();
        }
        return totalOrderAmountBeforeDiscount;
    }
}
