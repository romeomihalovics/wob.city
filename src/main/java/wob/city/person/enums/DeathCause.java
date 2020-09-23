package wob.city.person.enums;

public enum DeathCause {
    STARVED("Starved to death"),
    KILLED("Killed by a criminal"),
    AGING("Aged badly"),
    DISASTER_VOLCANO("Volcano erruption"),
    DISASTER_AFTERSHOCK("Earthquake Aftershock"),
    DISASTER_EARTHQUAKE("Earthquake"),
    DISASTER_FLOOD("Drowned in a flood"),
    DISASTER_TORNADO("Tried to fly with the tornado"),
    DISASTER_AFTERMATH_FLOOD("Drowned in an aftermath flood");

    private final String cause;

    DeathCause(String cause){
        this.cause = cause;
    }

    public String getValue() {
        return cause;
    }
}
