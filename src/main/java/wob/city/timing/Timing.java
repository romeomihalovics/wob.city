package wob.city.timing;

public enum Timing {
    MINUTE(60*1000),
    HOUR(MINUTE.getValue()*60),
    DAY(HOUR.getValue()*24),
    NEW_BORN_WORKER(MINUTE.getValue()*2),
    DEFAULT_DISASTER_FIRST_WAVE(MINUTE.getValue()),
    DEFAULT_DISASTER_SECOND_WAVE(MINUTE.getValue()*2),
    DEFAULT_DISASTER_THIRD_WAVE(MINUTE.getValue()*3),
    DROUGHT_THIRD_WAVE(DAY.getValue()*7),
    MONSOON_THIRD_WAVE(DAY.getValue()*7),
    INFECTION_THIRD_WAVE(DAY.getValue()*4),
    DIGESTION(MINUTE.getValue()),
    EATING(MINUTE.getValue()*3),
    AGING(MINUTE.getValue()*5),
    CRIMINAL_ACTIVITY(MINUTE.getValue()*5),
    PARAMEDIC_CPR(MINUTE.getValue()*2),
    AVERAGE_REPORT(MINUTE.getValue()*10),
    BIG_REPORT(HOUR.getValue()),
    FOOD_FACTORY(MINUTE.getValue()*5);

    private final int time;

    Timing(int time){
        this.time = time;
    }

    public int getValue() {
        return time;
    }
}