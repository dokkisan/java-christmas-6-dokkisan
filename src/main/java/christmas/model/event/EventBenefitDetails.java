package christmas.model.event;

public class EventBenefitDetails {
    private final String name;
    private final int benefitAmount;

    public EventBenefitDetails(String name, int benefitAmount) {
        this.name = name;
        this.benefitAmount = benefitAmount;
    }

    public String getName() {
        return name;
    }

    public int getBenefitAmount() {
        return benefitAmount;
    }
}
