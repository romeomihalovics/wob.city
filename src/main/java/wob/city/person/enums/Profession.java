package wob.city.person.enums;

public enum Profession {
    POLICE("Police"),
    PARAMEDIC("Paramedic"),
    FIREFIGHTER("FireFighter");

    private final String type;

    Profession(String type) {
        this.type = type;
    }

    public String getValue() {
        return type;
    }
}
