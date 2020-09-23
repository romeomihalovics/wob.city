package wob.city.timing;

public enum Timing {
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

    Timing(int time){
        this.time = time;
    }

    public int getValue() {
        return time;
    }
}