package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.ErrorMessage;

import java.util.ArrayList;
import java.util.List;

public class InputView {
    public static final int INDEX_OF_MENU_NAME = 0;
    public static final int INDEX_OF_MENU_QUANTITY = 1;

    public int askExpectedVisitDate() {
        return validateVisitDateInputFormat(Console.readLine());
    }

    public List<String> askExpectedMenuItems() {
        return validate(Console.readLine());
    }

    private List<String> validate(String inputExpectedMenuItems) {
        final String COMMA = ",";
        final String HYPHEN = "-";
        List<String> menuItems = new ArrayList<>();

        for (String item : inputExpectedMenuItems.split(COMMA)) {
            String[] parts = item.split(HYPHEN);
            if (parts.length != 2) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_MENU_FORMAT.getMessage());
            }
            int quantity = validateMenuQuantityInputFormat(parts[INDEX_OF_MENU_QUANTITY]);
            menuItems.add(parts[INDEX_OF_MENU_NAME]);
            menuItems.add(String.valueOf(quantity));
        }

        return menuItems;
    }

    private int validateVisitDateInputFormat(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DAYS_IN_DECEMBER.getMessage());
        }
    }

    private int validateMenuQuantityInputFormat(String input) {
        try {
            int inputQuantity = Integer.parseInt(input);
            if (inputQuantity < 0) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_MENU_FORMAT.getMessage());
            }
            return inputQuantity;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_MENU_FORMAT.getMessage());
        }
    }
}
