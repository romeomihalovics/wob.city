package wob.city.objects;

import wob.city.util.Calculations;

public class Girl extends Woman {
    public Girl() {
        this.setWeight(Calculations.getRandomIntBetween(20, 60));
        this.setAge(Calculations.getRandomIntBetween(1, 18));
    }
}
