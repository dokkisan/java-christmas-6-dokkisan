package christmas.util;

import christmas.model.ErrorMessage;

import java.time.DateTimeException;
import java.time.LocalDate;

public class DateValidator {

    public static LocalDate validate(int expectedVisitDate) {
        final int YEAR = 2023;
        final int MONTH = 12;

        try {
            return LocalDate.of(YEAR, MONTH, expectedVisitDate);
        } catch (DateTimeException e) {
            throw new java.lang.IllegalArgumentException(ErrorMessage.INVALID_DAYS_IN_DECEMBER.getMessage());
        }
    }
}
