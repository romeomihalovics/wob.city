package wob.city.food.enums;

public enum Type {
    DAIRY("Dairy"),
    GRAIN("Grain"),
    MEAT("Meat"),
    VEGETABLE("Vegetable");

    private final String category;

    Type(String category) {
        this.category = category;
    }

    public String getValue() {
        return category;
    }
}
