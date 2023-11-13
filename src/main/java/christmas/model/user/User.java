package christmas.model.user;

import christmas.model.ErrorMessage;

import java.time.DateTimeException;
import java.time.LocalDate;

public class User {
    private final LocalDate expectedVisitDate;

    public User(int expectedVisitDate) {
        this.expectedVisitDate = validate(expectedVisitDate);
    }

    private LocalDate validate(int expectedVisitDate) {
        final int YEAR = 2023;
        final int MONTH = 12;

        try {
            return LocalDate.of(YEAR, MONTH, expectedVisitDate);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DAYS_IN_DECEMBER.getMessage());
        }
    }
}
