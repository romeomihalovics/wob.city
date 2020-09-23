package wob.city.person.enums;

public enum Type {
    MAN ("Man"),
    WOMAN ("Woman"),
    BOY ("Boy"),
    GIRL ("Girl");

    private final String category;

    Type(String category) {
        this.category = category;
    }

    public String getValue() {
        return category;
    }
}
