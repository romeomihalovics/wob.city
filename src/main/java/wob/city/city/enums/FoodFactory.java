package wob.city.city.enums;

public enum FoodFactory {
    GENERATE_MIN_AMOUNT(30000),
    GENERATE_MAX_AMOUNT(80000);

    private final int amount;

    FoodFactory(int amount) {
        this.amount = amount;
    }

    public int getValue() {
        return amount;
    }
}
