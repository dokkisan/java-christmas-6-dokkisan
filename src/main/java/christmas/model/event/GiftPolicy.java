package christmas.model.event;

public class GiftPolicy {

    public boolean isSatisfied(int totalOrderAmountBeforeDiscount) {
        final int AMOUNT_CONDITION = 120_000;
        return totalOrderAmountBeforeDiscount >= AMOUNT_CONDITION;
    }
}
