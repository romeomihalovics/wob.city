package wob.city.disaster.enums;

public enum TemperatureLimit {
    DROUGHT(41.1);

    private double temperature;

    TemperatureLimit(double temperature) {
        this.temperature = temperature;
    }

    public double getValue() {
        return temperature;
    }
}
