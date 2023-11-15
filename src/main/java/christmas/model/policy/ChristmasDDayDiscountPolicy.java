package christmas.model.policy;

import christmas.util.DateConverter;

import java.time.LocalDate;

public class ChristmasDDayDiscountPolicy {

    public int calculateBenefitAmount(LocalDate visitDate) {
        final int MAX_AMOUNT = 3_400;
        final int UNIT_AMOUNT = 100;
        int dDay = calculateDDay(visitDate);
        if (dDay >= 0) {
            return MAX_AMOUNT - (dDay * UNIT_AMOUNT);
        }
        return 0;
    }

    private int calculateDDay(LocalDate visitDate) {
        return DateConverter.calculateDaysUntilChristmas(visitDate);
    }
}
