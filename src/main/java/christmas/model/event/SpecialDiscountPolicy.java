package christmas.model.event;

import christmas.util.DataConverter;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class SpecialDiscountPolicy {
    private final int BENEFIT_AMOUNT = 1_000;
    private final DayOfWeek SUNDAY = DayOfWeek.SUNDAY;
    private final LocalDate CHRISTMAS_IN_2023 = LocalDate.of(2023, 12, 25);

    public int calculateBenefitAmount(LocalDate visitDate) {
        int totalBenefitAmount = 0;
        if (isSunday(visitDate)) {
            totalBenefitAmount += BENEFIT_AMOUNT;
        }
        if (isChristmas(visitDate)) {
            totalBenefitAmount += BENEFIT_AMOUNT;
        }
        return totalBenefitAmount;
    }

    private boolean isSunday(LocalDate visitDate) {
        return SUNDAY.equals(DataConverter.convertToDayOfWeek(visitDate));
    }

    private boolean isChristmas(LocalDate visitDate) {
        return CHRISTMAS_IN_2023.equals(visitDate);
    }
}

