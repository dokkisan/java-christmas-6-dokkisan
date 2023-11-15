package christmas.model.user;

import java.time.LocalDate;
import java.util.Map;

public class User {
    private final LocalDate visitDate;
    private final Map<String, Integer> orderedMenuItems;

    public User(LocalDate visitDate, Map<String, Integer> orderedMenuItems) {
        this.visitDate = visitDate;
        this.orderedMenuItems = orderedMenuItems;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public Map<String, Integer> getOrderedMenuItems() {
        return orderedMenuItems;
    }
}
