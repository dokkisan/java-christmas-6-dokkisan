package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.ErrorMessage;

import java.util.ArrayList;
import java.util.List;

public class InputView {
    public static final int INDEX_OF_MENU_NAME = 0;
    public static final int INDEX_OF_MENU_QUANTITY = 0;

    public int askExpectedVisitDate() {
        return parseInt(Console.readLine(), true);
    }

    public List<String> askExpectedMenuItems() {
        return validate(Console.readLine());
    }

    private int parseInt(String input, boolean isExpectedVisitDate) {
        try {
            int number = Integer.parseInt(input);
            if (number < 0) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_MENU_FORMAT.getMessage());
            }
            return number;
        } catch (NumberFormatException e) {
            if (isExpectedVisitDate) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_DAYS_IN_DECEMBER.getMessage());
            }
            throw new IllegalArgumentException(ErrorMessage.INVALID_MENU_FORMAT.getMessage());
        }
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
            int quantity = parseInt(parts[INDEX_OF_MENU_QUANTITY], false);
            menuItems.add(parts[INDEX_OF_MENU_NAME]);
            menuItems.add(String.valueOf(quantity));
        }

        return menuItems;
    }
}
