package wob.city.database.dto;

import wob.city.database.enums.PersonNewsCategory;

public class PersonNewsDto {
    private Integer id;
    private String type;
    private PersonNewsCategory category;
    private String fullname;
    private String involvedPerson;
    private Integer age;
    private Integer weight;
    private Integer height;
    private String city;
    private String energy;
    private String lastFood;
    private Boolean reported;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PersonNewsCategory getCategory() {
        return category;
    }

    public void setCategory(PersonNewsCategory category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEnergy() {
        return energy;
    }

    public void setEnergy(String energy) {
        this.energy = energy;
    }

    public String getLastFood() {
        return lastFood;
    }

    public void setLastFood(String lastFood) {
        this.lastFood = lastFood;
    }

    public Boolean getReported() {
        return reported;
    }

    public void setReported(Boolean reported) {
        this.reported = reported;
    }

    public String getInvolvedPerson() {
        return involvedPerson;
    }

    public void setInvolvedPerson(String involvedPerson) {
        this.involvedPerson = involvedPerson;
    }

    @Override
    public String toString() {
        return "\n{" +
                "\n \"id\": " + id + "," +
                "\n \"type\": \"" + type + "\"," +
                "\n \"fullname\": \"" + fullname + "\"," +
                "\n \"age\": " + age + "," +
                "\n \"weight\": " + weight + "," +
                "\n \"height\": " + height + "," +
                "\n \"city\": \"" + city + "\"," +
                "\n \"energy\": \"" + energy + "\"," +
                "\n \"lastFood\": \"" + lastFood + "\"," +
                "\n \"involvedPerson\": \"" + involvedPerson + "\"" +
                "\n}";
    }
}
