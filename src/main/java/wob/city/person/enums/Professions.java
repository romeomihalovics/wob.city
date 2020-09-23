package wob.city.person.enums;

public enum Professions {
    POLICE("Police"),
    AMBULANCE("Ambulance"),
    FIREFIGHTER("FireFighter");

    private final String profession;

    Professions(String envProfession) {
        this.profession = envProfession;
    }

    public String getValue() {
        return profession;
    }
}
