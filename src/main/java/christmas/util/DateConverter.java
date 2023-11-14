package christmas.util;

import christmas.model.ErrorMessage;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class DateConverter {

    public static LocalDate convertToLocalDate(int expectedVisitDate) {
        final int YEAR = 2023;
        final int MONTH = 12;

        try {
            return LocalDate.of(YEAR, MONTH, expectedVisitDate);
        } catch (DateTimeException e) {
            throw new java.lang.IllegalArgumentException(ErrorMessage.INVALID_DAYS_IN_DECEMBER.getMessage());
        }
    }

    public static DayOfWeek convertToDayOfWeek(LocalDate visitDate) {
        return visitDate.getDayOfWeek();
    }
}
