package christmas.model.policy;

import christmas.util.DateConverter;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class SpecialDiscountPolicy {
    private final DayOfWeek SUNDAY = DayOfWeek.SUNDAY;
    private final LocalDate CHRISTMAS_IN_2023 = LocalDate.of(2023, 12, 25);

    public int calculateBenefitAmount(LocalDate visitDate) {
        final int BENEFIT_AMOUNT = 1_000;
        if (isSunday(visitDate) || isChristmas(visitDate)) {
            return BENEFIT_AMOUNT;
        }
        return 0;
    }

    private boolean isSunday(LocalDate visitDate) {
        return SUNDAY.equals(DateConverter.convertToDayOfWeek(visitDate));
    }

    private boolean isChristmas(LocalDate visitDate) {
        return CHRISTMAS_IN_2023.equals(visitDate);
    }
}

