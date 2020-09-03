package wob.city.abstractions;

import wob.city.util.Calculations;
import wob.city.util.Names;

public abstract class Person {
    private String firstName;
    private String lastName;
    private Integer age;
    private Integer weight;
    private Integer height;
    public static final Names names = new Names();

    public Person(){
        this.age = Calculations.getRandomIntBetween(18, 122);
        this.height = Calculations.getRandomIntBetween(1, 200);
        this.lastName = names.getLastName();
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

    @Override
    public String toString() {
        return this.getClass().getSimpleName() +
                "{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", height=" + height +
                '}';
    }
}
