package wob.city.database.dto;


import java.time.LocalDateTime;

public class PersonHistoryDto {
    private Integer id;
    private String cityName;
    private String fullName;
    private String event;
    private LocalDateTime date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "\n{" +
                "\n \"id\": " + id + "," +
                "\n \"cityName\": \"" + cityName + "\"," +
                "\n \"fullName\": \"" + fullName + "\"," +
                "\n \"event\": \"" + event.replaceAll("\n", "") + "\"," +
                "\n \"date\": \"" + date + "\"" +
                "\n}";
    }
}
