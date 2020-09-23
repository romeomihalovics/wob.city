package wob.city.person.enums;

public enum StatInFamily {
    PARENT("Parent"),
    CHILD("Child");

    private final String stat;

    StatInFamily(String stat) {
        this.stat = stat;
    }

    public String getValue() {
        return stat;
    }
}
