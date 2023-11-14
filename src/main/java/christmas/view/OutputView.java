package christmas.view;

import christmas.OperationMessage;

import java.util.Map;

public class OutputView {

    public void print(String message) {
        System.out.println(message);
    }

    public void printOrderedMenuItems(Map<String, Integer> menuItems) {
        System.out.println(OperationMessage.EVENT_BENEFITS_PREVIEW.getMessage());
        printBlankLine();
        System.out.println(OperationMessage.ORDER_MENU.getMessage());
        for (Map.Entry<String, Integer> item : menuItems.entrySet()) {
            System.out.println(item.getKey() + " " + item.getValue() + "개");
        }
    }

    public void printBlankLine() {
        System.out.println();
    }
}
