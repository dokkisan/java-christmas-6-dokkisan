package christmas.util;

import christmas.model.DecemberEventBadge;
import christmas.model.event.DecemberEvents;
import christmas.model.event.EventBenefitDetails;
import christmas.model.menu.MenuItem;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class EventCalculator {

    public static DecemberEventBadge calculateEventBadge(int totalBenefitAmount) {
        if (totalBenefitAmount >= DecemberEventBadge.SANTA.getAmountCondition()) {
            return DecemberEventBadge.SANTA;
        }
        if (totalBenefitAmount >= DecemberEventBadge.TREE.getAmountCondition()) {
            return DecemberEventBadge.TREE;
        }
        if (totalBenefitAmount >= DecemberEventBadge.STAR.getAmountCondition()) {
            return DecemberEventBadge.STAR;
        }
        return DecemberEventBadge.NONE;
    }

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

    public static int calculateTotalBenefitAmount(List<EventBenefitDetails> eventPlanBenefitResult) {
        return eventPlanBenefitResult.stream()
                .flatMapToInt(eventBenefitDetails -> IntStream.of(eventBenefitDetails.getBenefitAmount()))
                .sum();
    }

    public static int calculateExpectedPaymentAfterDiscount(
            int totalOrderAmountBeforeDiscount, List<EventBenefitDetails> eventPlanBenefitResult) {
        int totalBenefitAmount = calculateTotalDiscountAmountWithoutGift(eventPlanBenefitResult);
        return totalOrderAmountBeforeDiscount - totalBenefitAmount;
    }

    private static int calculateTotalDiscountAmountWithoutGift(List<EventBenefitDetails> eventPlanBenefitResult) {
        return eventPlanBenefitResult.stream()
                .filter(eventBenefitDetails -> !eventBenefitDetails.getName().equals(DecemberEvents.GIFT.getName()))
                .flatMapToInt(eventBenefitDetails -> IntStream.of(eventBenefitDetails.getBenefitAmount()))
                .sum();
    }
}
