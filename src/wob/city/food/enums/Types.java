package wob.city.food.enums;

public enum Types {
    DAIRY("Dairy"),
    GRAIN("Grain"),
    MEAT("Meat"),
    VEGETABLE("Vegetable");

    private final String type;

    Types(String envType) {
        this.type = envType;
    }

    public String getValue() {
        return type;
    }
}
