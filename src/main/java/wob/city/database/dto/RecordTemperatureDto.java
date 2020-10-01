package wob.city.database.dto;

import wob.city.database.enums.TemperatureRecordType;

import java.time.LocalDateTime;

public class RecordTemperatureDto {
    private int id;
    private String city;
    private TemperatureRecordType type;
    private double temperature;
    private LocalDateTime date;
    private String season;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public TemperatureRecordType getType() {
        return type;
    }

    public void setType(TemperatureRecordType type) {
        this.type = type;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }
}
