package wob.city.person.enums;

public enum Type {
    MAN ("Man"),
    WOMAN ("Woman"),
    BOY ("Boy"),
    GIRL ("Girl");

    private final String type;

    Type(String envType) {
        this.type = envType;
    }

    public String getValue() {
        return type;
    }
}
