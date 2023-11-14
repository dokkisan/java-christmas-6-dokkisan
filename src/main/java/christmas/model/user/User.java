package christmas.model.user;

import java.time.LocalDate;
import java.util.Map;

public class User {
    private final LocalDate expectedVisitDate;
    private final Map<String, Integer> expectedMenuItems;

    private User(LocalDate expectedVisitDate, Map<String, Integer> expectedMenuItems) {
        this.expectedVisitDate = expectedVisitDate;
        this.expectedMenuItems = expectedMenuItems;
    }
}
