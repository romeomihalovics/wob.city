package wob.city.abstractions;

import wob.city.util.Calculations;
import wob.city.util.Names;

public abstract class Person {
    public static final Names names = new Names();

    private String firstName;
    private String lastName;
    private Integer age;
    private Integer weight;
    private Integer height;
    private final int normalMinHeight = 1;
    private final int normalMaxHeight = 200;
    private final int normalMinAge = 1;
    private final int normalMaxAge = 122;
    private int normalMinWeight;
    private int normalMaxWeight;

    public Person(int normalMinWeight, int normalMaxWeight){
        this.age = Calculations.getRandomIntBetween(18, 122);
        this.height = Calculations.getRandomIntBetween(1, 200);
        this.lastName = names.getLastName();
        this.normalMaxWeight = normalMaxWeight;
        this.normalMinWeight = normalMinWeight;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public int getNormalMinHeight() {
        return normalMinHeight;
    }

    public int getNormalMaxHeight() {
        return normalMaxHeight;
    }

    public int getNormalMinWeight() {
        return normalMinWeight;
    }

    public void setNormalMinWeight(int normalMinWeight) {
        this.normalMinWeight = normalMinWeight;
    }

    public int getNormalMaxWeight() {
        return normalMaxWeight;
    }

    public void setNormalMaxWeight(int normalMaxWeight) {
        this.normalMaxWeight = normalMaxWeight;
    }

    public int getNormalMinAge() {
        return normalMinAge;
    }

    public int getNormalMaxAge() {
        return normalMaxAge;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "\n{" +
                "\n  type: '" + this.getClass().getSimpleName() + "'," +
                "\n  firstName: '" + firstName + "'," +
                "\n  lastName: '" + lastName + "'," +
                "\n  age: " + age + "," +
                "\n  weight: " + weight + "," +
                "\n  height: " + height +
                "\n}";
    }
}
