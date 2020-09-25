package wob.city.database.enums;

public enum PersonNewsCategory {
    DEATH("Death"),
    NEW_BORN("NewBorn"),
    CAUGHT_CRIMINAL("CaughtCriminal"),
    ESCAPED_CRIMINAL("EscapedCriminal"),
    KILLED_BY_CRIMINAL("KilledByCriminal"),
    SAVED_BY_PARAMEDIC("SavedByParamedics"),
    SAVED_BY_FIREFIGHTER("SavedByFireFighter");


    private final String category;

    PersonNewsCategory(String category) {
        this.category = category;
    }

    public String getValue(){
        return category;
    }
}
