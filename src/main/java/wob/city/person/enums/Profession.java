package wob.city.person.enums;

public enum Profession {
    POLICE("Police"),
    AMBULANCE("Ambulance"),
    FIREFIGHTER("FireFighter");

    private final String profession;

    Profession(String envProfession) {
        this.profession = envProfession;
    }

    public String getValue() {
        return profession;
    }
}
