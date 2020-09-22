package wob.city.timing;

public enum Timings {
    MINUTE(60*1000),
    NEW_BORN_WORKER(MINUTE.getValue()*2),
    DISASTER_FIRST_WAVE(MINUTE.getValue()),
    DISASTER_SECOND_WAVE(MINUTE.getValue()*2),
    DISASTER_THIRD_WAVE(MINUTE.getValue()*3),
    DIGESTION(MINUTE.getValue()),
    EATING(MINUTE.getValue()*3),
    AGING(MINUTE.getValue()*5),
    REPORT(MINUTE.getValue()*10);

    private final int time;

    Timings(int envTime){
        this.time = envTime;
    }

    public int getValue() {
        return time;
    }
}