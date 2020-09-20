package wob.city.person.enums;

public enum StatInFamily {
    PARENT("Parent"),
    CHILD("Child");

    private final String stat;

    StatInFamily(String envStat) {
        this.stat = envStat;
    }

    public String getValue() {
        return stat;
    }
}
