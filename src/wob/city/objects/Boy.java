package wob.city.objects;

import wob.city.util.Calculations;

public class Boy extends Man {
    public Boy() {
        this.setWeight(Calculations.getRandomIntBetween(20, 60));
        this.setAge(Calculations.getRandomIntBetween(1, 18));
    }
}
