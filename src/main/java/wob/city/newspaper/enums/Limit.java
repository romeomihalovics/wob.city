package wob.city.newspaper.enums;

public enum Limit {
    FETCH_LIMIT(10000);

    private final int limitation;

    Limit(int limitation) {
        this.limitation = limitation;
    }

    public int getValue() {
        return limitation;
    }
}
