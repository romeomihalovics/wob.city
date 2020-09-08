package wob.city.objects;

import wob.city.factory.KidFactory;

public class Boy extends Man {
    public Boy() {
        KidFactory.setDetails(this);
    }

    public Boy(boolean isNewBorn) {
        KidFactory.setDetails(this);
        if(isNewBorn) {
            this.setAge(1);
        }
    }
}
