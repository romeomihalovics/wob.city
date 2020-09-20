package wob.city.person.enums;

public enum Types {
    MAN ("Man"),
    WOMAN ("Woman"),
    BOY ("Boy"),
    GIRL ("Girl");

    private final String type;

    Types(String envType) {
        this.type = envType;
    }

    public String getValue() {
        return type;
    }
}
