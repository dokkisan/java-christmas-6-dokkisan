package christmas.model;

public enum DecemberEventBadge {
    STAR("별", 5_000, "⭐"),
    TREE("트리", 10_000, "🎄"),
    SANTA("산타", 20_000, "🎅"),
    NONE("없음", 0, "");

    private final String name;
    private final int amountCondition;
    private final String badgeEmoji;

    DecemberEventBadge(String name, int amountCondition, String badgeEmoji) {
        this.name = name;
        this.amountCondition = amountCondition;
        this.badgeEmoji = badgeEmoji;
    }

    public String getName() {
        return name;
    }

    public String getBadgeEmoji() {
        return badgeEmoji;
    }

    public int getAmountCondition() {
        return amountCondition;
    }
}
