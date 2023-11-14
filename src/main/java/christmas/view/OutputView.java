package christmas.view;

import christmas.OperationMessage;

import java.text.DecimalFormat;
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

    public void print(int totalAmount) {
        System.out.println(OperationMessage.TOTAL_ORDER_AMOUNT_BEFORE_DISCOUNT.getMessage());
        System.out.println(new DecimalFormat("###,###").format(totalAmount) + "원");
    }

    public void printBlankLine() {
        System.out.println();
    }
}
