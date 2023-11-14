package christmas.model.menu;

import christmas.model.ErrorMessage;
import christmas.util.DataTypeConverter;

import java.util.*;

public class MenuManager {
    private static final List<MenuItem> menu = new ArrayList<>(List.of(MenuItem.values()));

    public static Map<String, Integer> validate(List<String> inputOrderMenus) {
        checkForDuplicates(inputOrderMenus);
        checkMenuItemExist(inputOrderMenus);
        checkOnlyBeveragesOrdered(inputOrderMenus);
        checkTotalOrderCountLessThan20(inputOrderMenus);
        return DataTypeConverter.convertToMapOfMenuItems(inputOrderMenus);
    }

    private static void checkForDuplicates(List<String> inputOrderMenus) {
        Set<String> uniqueItems = new HashSet<>();
        for (int i = 0; i < inputOrderMenus.size(); i++) {
            if (i % 2 == 0) {
                if (!uniqueItems.add(inputOrderMenus.get(i))) {
                    throw new IllegalArgumentException(ErrorMessage.INVALID_MENU_FORMAT.getMessage());
                }
            }
        }
    }

    private static void checkMenuItemExist(List<String> inputOrderMenus) {
        List<String> inputMenuNames = new ArrayList<>();
        for (int i = 0; i < inputOrderMenus.size(); i++) {
            if (i % 2 == 0) {
                inputMenuNames.add(inputOrderMenus.get(i));
            }
        }
        for (String name : inputMenuNames) {
            if (menu.stream().allMatch(menuItem -> menuItem.getName().equals(name))) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_MENU_FORMAT.getMessage());
            }
        }
    }

    private static void checkOnlyBeveragesOrdered(List<String> inputOrderMenus) {
        List<String> inputMenuNames = new ArrayList<>();
        for (int i = 0; i < inputOrderMenus.size(); i++) {
            if (i % 2 == 0) {
                inputMenuNames.add(inputOrderMenus.get(i));
            }
        }

        if (inputMenuNames.stream().allMatch(MenuManager::isBeverage)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_MENU_FORMAT.getMessage());
        }
    }

    private static boolean isBeverage(String menuName) {
        return menuName.equals(MenuItem.RED_WINE.getName()) || menuName.equals(MenuItem.ZERO_COKE.getName()) || menuName.equals(MenuItem.CHAMPAGNE.getName());
    }

    private static void checkTotalOrderCountLessThan20(List<String> inputOrderMenus) {
        int orderCount = 0;
        for (int i = 0; i < inputOrderMenus.size(); i++) {
            if (i % 2 != 0) {
                orderCount += Integer.parseInt(inputOrderMenus.get(i));
            }
        }

        if (orderCount > 20) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_MENU_FORMAT.getMessage());
        }
    }
}
