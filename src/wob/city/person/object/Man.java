package wob.city.person.object;

import wob.city.person.abstraction.Person;
import wob.city.util.Calculations;

public class Man extends Person {
    public Man(){
        super(65, 120, NAMES.getMaleName(), Calculations.getRandomIntBetween(65, 120));
    }

    public Man(int normalMinWeight, int normalMaxWeight, int weight, int age) {
        super(normalMinWeight, normalMaxWeight, weight, age, NAMES.getMaleName());
    }

    public Man(Boy newAdult){
        super(newAdult);
    }

    @Override
    public String getType() {
        return "Man";
    }

    @Override
    public void doAging() {
        this.addAge();
        if((this.getAge() >= 60 && Calculations.getRandomIntBetween(0, 100) <= 25) || this.getAge() >= Person.ABSOLUTE_MAX_AGE) {
            this.die();
        }
    }
}
