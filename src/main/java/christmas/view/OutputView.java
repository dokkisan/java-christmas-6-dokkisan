package christmas.view;

import christmas.OperationMessage;
import christmas.model.event.EventBenefitDetails;
import christmas.model.menu.MenuItem;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

public class OutputView {

    public void printTotalOrderAmountBeforeDiscount(String message) {
        System.out.println(message);
    }

    public void printOrderedMenuItems(Map<String, Integer> menuItems) {
        System.out.println(OperationMessage.EVENT_BENEFITS_PREVIEW.getMessage());
        printBlankLine();
        System.out.println(OperationMessage.ORDER_MENU.getMessage());
        for (Map.Entry<String, Integer> item : menuItems.entrySet()) {
            System.out.println(item.getKey() + " " + item.getValue() + "개");
        }
        printBlankLine();
    }

    public void printTotalOrderAmountBeforeDiscount(int amount) {
        System.out.println(OperationMessage.TOTAL_ORDER_AMOUNT_BEFORE_DISCOUNT.getMessage());
        System.out.println(new DecimalFormat("###,###").format(amount) + "원");
        printBlankLine();
    }

    public void printTotalBenefitsAmount(int amount) {
        System.out.println(OperationMessage.TOTAL_BENEFITS_AMOUNT.getMessage());
        if (amount < 1000) {
            System.out.println(new DecimalFormat("###,###").format(amount) + "원");
            printBlankLine();
            return;
        }
        System.out.println(new DecimalFormat("-###,###").format(amount) + "원");
        printBlankLine();
    }

    public void printBenefitsDetails(List<EventBenefitDetails> eventPlanBenefitResult) {
        System.out.println(OperationMessage.BENEFITS_DETAILS.getMessage());
        if (eventPlanBenefitResult.stream()
                .allMatch(eventBenefitDetails -> eventBenefitDetails.getBenefitAmount() == 0)) {
            System.out.println("없음");
        }
        printBenefitDetailsMoreThanOne(eventPlanBenefitResult);
        printBlankLine();
    }

    private static void printBenefitDetailsMoreThanOne(List<EventBenefitDetails> eventPlanBenefitResult) {
        eventPlanBenefitResult
                .forEach(eventBenefitDetails ->
                {
                    if (eventBenefitDetails.getBenefitAmount() == 0) {
                        return;
                    }
                    System.out.println(
                            eventBenefitDetails.getName() + ": "
                                    + new DecimalFormat("-###,###")
                                    .format(eventBenefitDetails.getBenefitAmount()) + "원");
                });
    }

    public void printChampagneGifted(int count) {
        System.out.println(OperationMessage.GIFT_MENU.getMessage());
        if (count < 0) {
            System.out.println("없음");
            return;
        }
        System.out.println(MenuItem.CHAMPAGNE.getName() + " " + count + "개");
        printBlankLine();
    }

    public void printBlankLine() {
        System.out.println();
    }
}
