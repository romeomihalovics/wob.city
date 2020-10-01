package wob.city.database.dto;

import java.time.LocalDateTime;

public class ConsumptionNewsDto {
    private Integer id;
    private String city;
    private String type;
    private Double amount;
    private Boolean reported;
    private LocalDateTime date;

    public ConsumptionNewsDto(){}

    public ConsumptionNewsDto(String city, String type, Double amount) {
        this.city = city;
        this.type = type;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Boolean getReported() {
        return reported;
    }

    public void setReported(Boolean reported) {
        this.reported = reported;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
