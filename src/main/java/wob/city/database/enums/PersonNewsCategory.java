package wob.city.database.enums;

public enum PersonNewsCategory {
    DEATH("Death"),
    NEW_BORN("NewBorn");

    private final String category;

    PersonNewsCategory(String category) {
        this.category = category;
    }

    public String getValue(){
        return category;
    }
}
