package wob.city.database.dto;

import java.util.Date;

public class DisasterHistoryDto {
    private Integer id;
    private String cityName;
    private String type;
    private String event;
    private Integer destroyedBuildings;
    private Integer diedFamilies;
    private Integer diedPeople;
    private Date date;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Integer getDestroyedBuildings() {
        return destroyedBuildings;
    }

    public void setDestroyedBuildings(Integer destroyedBuildings) {
        this.destroyedBuildings = destroyedBuildings;
    }

    public Integer getDiedFamilies() {
        return diedFamilies;
    }

    public void setDiedFamilies(Integer diedFamilies) {
        this.diedFamilies = diedFamilies;
    }

    public Integer getDiedPeople() {
        return diedPeople;
    }

    public void setDiedPeople(Integer diedPeople) {
        this.diedPeople = diedPeople;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "\n{" +
                "\n \"id\": " + id +
                "\n \"cityName\": \"" + cityName + "\"," +
                "\n \"type\": \"" + type + "\"," +
                "\n \"event\": \"" + event + "\"," +
                "\n \"destroyedBuildings\": " + destroyedBuildings + "," +
                "\n \"diedFamilies\": " + diedFamilies + "," +
                "\n \"diedPeople\": " + diedPeople + "," +
                "\n \"date\": \"" + date + "\"," +
                "\n}";
    }
}
