package wob.city.food.enums;

public enum Type {
    DAIRY("Dairy"),
    GRAIN("Grain"),
    MEAT("Meat"),
    VEGETABLE("Vegetable");

    private final String type;

    Type(String envType) {
        this.type = envType;
    }

    public String getValue() {
        return type;
    }
}
